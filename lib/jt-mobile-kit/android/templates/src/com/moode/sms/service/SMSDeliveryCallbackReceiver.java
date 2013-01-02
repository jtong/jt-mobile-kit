package com.moode.sms.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SMSDeliveryCallbackReceiver extends BroadcastReceiver {
    private static final String TAG = SMSDeliveryCallbackReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
                Log.d(TAG, "SMS delivery OK");
                break;
            case Activity.RESULT_CANCELED:
                Log.w(TAG, "SMS delivery Cancel");
                break;
        }
    }
}
