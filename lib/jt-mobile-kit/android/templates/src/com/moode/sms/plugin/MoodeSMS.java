package com.moode.sms.plugin;

import android.util.Log;
import com.moode.sms.framework.CommonServiceContext;
import com.moode.sms.service.SMSSendService;
import com.moode.sms.utils.JsonUtils;
import org.apache.cordova.api.Plugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MoodeSMS extends Plugin {
    private static final String TAG = MoodeSMS.class.getSimpleName();
    private static final String SEND_SMS_ACTION = "send_sms";
    private static PluginResult OK = new PluginResult(PluginResult.Status.OK);
    private static PluginResult ERROR = new PluginResult(PluginResult.Status.ERROR);

    private static final String MESSAGE_CONTENT_TAG = "message_content";

    @Override
    public PluginResult execute(String action, JSONArray args, String callbackId) {
        Log.d(TAG, "SMS delivery OK");
        Log.d(TAG, action);
        if (action.equalsIgnoreCase(SEND_SMS_ACTION)) {
            return sendMessages(args);
        }

        return ERROR;
    }

    private PluginResult sendMessages(JSONArray jsonArray) {
        try {
            JSONObject receiversObj = (JSONObject) jsonArray.get(0);
            Map<String, String> receivers = JsonUtils.parseReceiversFromJson(receiversObj);
            JSONObject contentObj = (JSONObject) jsonArray.get(1);
            String content = (String) contentObj.get(MESSAGE_CONTENT_TAG);
            getSMSSendService().sendSMS(content, receivers.keySet());
        } catch (JSONException e) {
            Log.e(TAG, "send message error");
            return ERROR;
        }
        return OK;
    }


    private SMSSendService getSMSSendService() {
        return CommonServiceContext.getInstance().getObject(SMSSendService.class);
    }
}
