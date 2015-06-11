package com.gamesky.card.service;

import com.gamesky.card.core.lock.GlobalLock;
import com.gamesky.card.core.exceptions.LockException;
import com.gamesky.card.core.lock.Lockable;
import redis.clients.jedis.Jedis;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public class RedisGlobalLock<T extends Lockable> implements GlobalLock<T> {

    private Jedis jedis;
    private int expire = 60 * 1000;    //Lock expiration in miliseconds.
    private boolean locked = false;

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public synchronized boolean acquire(T t) throws LockException {
        int timeout = 5 * 1000; //默认5秒超时
        return this.acquire(t, timeout);
    }

    @Override
    public boolean acquire(T t, long timeoutMillis) throws LockException {
        long timeout = timeoutMillis;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + t.expire() + 1;
            String expiresStr = String.valueOf(expires);

            if (jedis.setnx(t.key(), expiresStr) == 1) {
                // acquire acquired
                locked = true;
                return true;
            }

            String currentValueStr = jedis.get(t.key());
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                // acquire is expired

                String oldValueStr = jedis.getSet(t.key(), expiresStr);
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    // acquire acquired
                    locked = true;
                    return true;
                }
            }

            timeout -= 100;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }

        return false;
    }

    @Override
    public synchronized void release(T t) {
        if (locked) {
            jedis.del(t.key());
            locked = false;
        }
    }


}
