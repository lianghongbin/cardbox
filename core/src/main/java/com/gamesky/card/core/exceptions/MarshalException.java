package com.gamesky.card.core.exceptions;

/**
 * Created on 2015/6/9.
 *
 * @Author lianghongbin
 */
public class MarshalException extends Throwable {

    public MarshalException() {
        super();
    }

    public MarshalException(String message) {
        super(message);
    }

    public MarshalException(String message, Throwable cause) {
        super(message, cause);
    }

    public MarshalException(Throwable cause) {
        super(cause);
    }
}
