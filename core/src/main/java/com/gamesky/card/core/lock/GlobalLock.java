package com.gamesky.card.core.lock;

import com.gamesky.card.core.exceptions.LockException;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public interface GlobalLock<T extends Lockable> {

    public boolean acquire(T t) throws LockException;

    public boolean acquire(T t, long timeout) throws LockException;

    public void release(T t);
}
