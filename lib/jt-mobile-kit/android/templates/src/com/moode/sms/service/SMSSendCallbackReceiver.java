package com.moode.sms.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class SMSSendCallbackReceiver extends BroadcastReceiver {

    private static final String TAG = SMSSendCallbackReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                Log.d(TAG, "SMS send OK");
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                Log.e(TAG, "SMS send generic failure");
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                Log.e(TAG, "SMS send no service");
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                Log.e(TAG, "SMS send null pdu");
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                Log.e(TAG, "SMS send error radio off");
                break;
        }
    }
}
