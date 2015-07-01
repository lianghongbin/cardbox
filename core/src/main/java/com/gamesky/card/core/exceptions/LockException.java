package com.gamesky.card.core.exceptions;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
    public class LockException extends Exception {

        public LockException() {
            super();
        }

        public LockException(String message) {
            super(message);
        }

        public LockException(String message, Throwable cause) {
            super(message, cause);
        }

        public LockException(Throwable cause) {
            super(cause);
        }
}
