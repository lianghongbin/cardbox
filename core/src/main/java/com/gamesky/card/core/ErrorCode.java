package com.gamesky.card.core;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public enum ErrorCode {

    GENERAL(-1),
    ILLEGAL_ARGUMENT(-2);

    private int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
