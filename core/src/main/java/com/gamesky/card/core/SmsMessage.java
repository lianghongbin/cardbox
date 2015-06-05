package com.gamesky.card.core;

import java.io.Serializable;

/**
 * Created on 6/5/15.
 *
 * @Author lianghongbin
 */
public class SmsMessage implements Serializable {
    private final String phone;
    private final String message;

    public SmsMessage(String phone, String message) {
        this.phone = phone;
        this.message = message;
    }

    public String getPhone() {
        return phone;
    }

    public String getMessage() {
        return message;
    }
}
