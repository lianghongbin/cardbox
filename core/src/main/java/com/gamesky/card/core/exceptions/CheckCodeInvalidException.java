package com.gamesky.card.core.exceptions;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
public class CheckCodeInvalidException extends Exception {

    public CheckCodeInvalidException() {
        super();
    }

    public CheckCodeInvalidException(String message) {
        super(message);
    }

    public CheckCodeInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckCodeInvalidException(Throwable cause) {
        super(cause);
    }
}

