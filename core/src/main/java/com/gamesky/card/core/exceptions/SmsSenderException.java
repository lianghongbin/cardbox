package com.gamesky.card.core.exceptions;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
public class SmsSenderException extends Exception {

    public SmsSenderException() {
        super();
    }

    public SmsSenderException(String message) {
        super(message);
    }

    public SmsSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SmsSenderException(Throwable cause) {
        super(cause);
    }
}
