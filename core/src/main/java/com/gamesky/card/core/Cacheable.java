package com.gamesky.card.core;

/**
 * Created on 2015/6/11.
 *
 * @Author lianghongbin
 */
public interface Cacheable extends Keyable {

    public long expire();   //秒
}
