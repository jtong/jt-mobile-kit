package com.moode.sms.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import com.moode.sms.activity.MainActivity;
import com.moode.sms.domain.Message;
import com.moode.sms.utils.Constants;
import com.moode.sms.utils.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SMSReceiveService extends BroadcastReceiver {
    private static final String PDUS = "pdus";
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        handleIncomingMessages(intent);
    }

    private void handleIncomingMessages(Intent intent) {
        List<Message> messages = new ArrayList<Message>();
        for (SmsMessage smsMessage : parseToMessages(intent)) {
                messages.add(createMessage(smsMessage));
        }
        if (messages.size() != 0) startSMSListActivity(messages);
    }

    private Message createMessage(SmsMessage smsMessage) {
        return new Message(StringUtils.trimCountryCode(smsMessage.getOriginatingAddress()),
                smsMessage.getMessageBody(), new Date(smsMessage.getTimestampMillis()));
    }

    private void startSMSListActivity(List<Message> messages) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        startActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityIntent.putExtra(Constants.MESSAGES_EXTRA, messages.toArray(new Message[messages.size()]));
        context.startActivity(startActivityIntent);
    }

    private SmsMessage[] parseToMessages(Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get(PDUS);
        SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int index = 0; index < messages.length; index++) {
            messages[index] = SmsMessage.createFromPdu((byte[]) pdus[index]);
        }
        return messages;
    }
}
