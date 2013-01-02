package com.moode.sms.utils;

public class StringUtils {

    public static boolean isEmpty(String value) {
        return value == null || "".equals(value.trim());
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static String trimQuote(String value) {
        if (isEmpty(value)) return null;
        return value.replaceAll("(^\'|\")|(\'|\"$)", "");
    }

    public static boolean isSamePhone(String originatingAddress, String savedPhone) {
        return trimCountryCode(originatingAddress).equalsIgnoreCase(savedPhone);
    }

    public static String trimCountryCode(String originatingAddress) {
        return originatingAddress.replaceAll("(^\\+86)", "");
    }
}
