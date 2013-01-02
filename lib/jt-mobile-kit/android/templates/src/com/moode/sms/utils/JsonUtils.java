package com.moode.sms.utils;

import com.moode.sms.domain.Message;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static Map<String, String> parseReceiversFromJson(JSONObject jsonObject) throws JSONException {
        JSONArray phoneArray = (JSONArray) jsonObject.get("receivers");
        Map<String, String> receives = new HashMap<String, String>();
        for (int index = 0; index < phoneArray.length(); index++) {
            JSONObject each_obj = (JSONObject) phoneArray.get(index);
            receives.put((String) each_obj.get("phone"), (String) each_obj.get("name"));
        }
        return receives;
    }

    public static String parseMessagesToJson(Message[] messages) throws JSONException {
        JSONObject data = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (Message message : messages) {
            JSONObject messageObj = new JSONObject();
            messageObj.put("message", message.getMessage());
            messageObj.put("phone", message.getPhoneNumber());
            messageObj.put("create_date", message.getReceivedDate());
            jsonArray.put(messageObj);
        }
        return data.put("messages", jsonArray).toString();

    }

}
