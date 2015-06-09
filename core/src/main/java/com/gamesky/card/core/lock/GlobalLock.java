package com.gamesky.card.core.lock;

import com.gamesky.card.core.exceptions.LockException;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public interface GlobalLock<T extends Lockable> {

    public boolean lock(T t) throws LockException;
    public void unLock(T t);
}
