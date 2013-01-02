package com.moode.sms.domain;

import com.moode.sms.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String phoneNumber;
    private String messageBody;
    private Date receivedDate;

    public Message(String originatingAddress, String messageBody, Date receivedDate) {
        this.phoneNumber = originatingAddress;
        this.messageBody = messageBody;
        this.receivedDate = receivedDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public String getMessage() {
        return StringUtils.trimQuote(messageBody);
    }
}
