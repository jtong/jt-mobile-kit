package com.moode.sms.framework;

import java.util.HashMap;
import java.util.Map;

public class CommonServiceContext {
    private static CommonServiceContext commonServiceContext;

    private Map<Class, Object> objectMap;
    
    private CommonServiceContext() {
        objectMap = new HashMap<Class, Object>();
    }

    public static CommonServiceContext getInstance() {
        if (commonServiceContext == null) {
            commonServiceContext = new CommonServiceContext();
        }
        return commonServiceContext;
    }

    public <T> void putObject(Class<T> clazz, T object) {
        objectMap.put(clazz, object);
    }

    public <T> T getObject(Class<T> clazz) {
        return (T) objectMap.get(clazz);
    }

}
