package com.gamesky.card.core;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public enum ErrorCode {

    GENERAL(-1),
    ILLEGAL_ARGUMENT(-2),
    EXCEPTION(-3),
    ERROR(-4),
    SERVER_ERROR_500(500),
    SERVER_ERROR_504(504),
    PAGE_NOT_FOUND(404);

    private int code;

    private ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
