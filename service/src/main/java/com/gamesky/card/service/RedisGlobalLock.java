package com.gamesky.card.service;

import com.gamesky.card.core.lock.GlobalLock;
import com.gamesky.card.core.lock.LockException;
import com.gamesky.card.core.lock.Lockable;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public class RedisGlobalLock<T extends Lockable> implements GlobalLock<T> {
    @Override
    public boolean lock(T t) throws LockException {
        return false;
    }

    @Override
    public void unLock(T t) {

    }
}
