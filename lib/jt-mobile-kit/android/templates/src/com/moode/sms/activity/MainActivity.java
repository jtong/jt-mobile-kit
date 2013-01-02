/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.moode.sms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import com.moode.sms.domain.Message;
import com.moode.sms.framework.CommonServiceContext;
import com.moode.sms.service.SMSSendService;
import com.moode.sms.utils.Constants;
import com.moode.sms.utils.JsonUtils;
import org.apache.cordova.DroidGap;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DroidGap {

    private static final Message[] EMPTY_MESSAGES = new Message[0];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
        registerCommonService();
        handleReceivedMessages();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleReceivedMessages();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //loadUrl("javascript:$(\"#trigger\").click()");
    }

    private void registerCommonService() {
        CommonServiceContext context = CommonServiceContext.getInstance();
        context.putObject(SMSSendService.class, new SMSSendService(getApplicationContext(), SmsManager.getDefault()));
    }

    private void handleReceivedMessages() {
        Message[] messages = getReceivedMessages();
        if (messages.length == 0) return;
        notifyUser(messages);
    }

    private void notifyUser(Message[] messages) {
        try {
            loadUrl("javascript:notify_message_received(" + JsonUtils.parseMessagesToJson(messages) + ")");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Message[] getReceivedMessages() {
        if (getIntent() == null || getIntent().getExtras() == null) return EMPTY_MESSAGES;
        Object[] objects = (Object[]) getIntent().getExtras().get(Constants.MESSAGES_EXTRA);
        if (objects == null) return EMPTY_MESSAGES;
        List<Message> messages = new ArrayList<Message>();
        for (Object o : objects) {
            messages.add((Message) o);
        }
        return messages.toArray(new Message[messages.size()]);
    }
}


