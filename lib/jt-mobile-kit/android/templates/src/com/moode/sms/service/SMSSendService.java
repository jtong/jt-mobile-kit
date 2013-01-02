package com.moode.sms.service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import com.moode.sms.utils.StringUtils;

import java.util.Set;

public class SMSSendService {
    private static final String SEND_SMS = "SEND_SMS";
    private static final String DELIVERY_SMS = "delivery_sms";
    private static final String NEW_LINE = "\n";

    private Context context;
    private SmsManager smsManager;

    public SMSSendService(Context context, SmsManager smsManager) {
        this.context = context;
        this.smsManager = smsManager;
        registerSendCallback(context);
    }

    public void sendSMS(String content, Set<String> phones) {
        for (String number : phones) {
            smsManager.sendTextMessage(number, null, content, makeSentIntent(), makeDeliveryIntent());
        }
    }

    private void registerSendCallback(Context context) {
        context.registerReceiver(new SMSSendCallbackReceiver(), new IntentFilter(SEND_SMS));
        context.registerReceiver(new SMSDeliveryCallbackReceiver(), new IntentFilter(DELIVERY_SMS));
    }

    private PendingIntent makeSentIntent() {
        return PendingIntent.getBroadcast(context, 0, new Intent(DELIVERY_SMS), 0);
    }

    private PendingIntent makeDeliveryIntent() {
        return PendingIntent.getBroadcast(context, 0, new Intent(SEND_SMS), 0);
    }

    private String composeSendMessage(String content, String survey) {
        if (StringUtils.isNotEmpty(survey)) {
            return content + NEW_LINE + survey;
        }
        return content;
    }

}
