package com.gamesky.card.core.lock;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public interface Lockable {

    public String key();
    public int expireSecond();
}
