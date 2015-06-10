package com.gamesky.card.core.exceptions;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
public class CheckCodeWrongException  extends Throwable {

    public CheckCodeWrongException() {
        super();
    }

    public CheckCodeWrongException(String message) {
        super(message);
    }

    public CheckCodeWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckCodeWrongException(Throwable cause) {
        super(cause);
    }
}
