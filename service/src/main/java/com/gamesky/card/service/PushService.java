package com.gamesky.card.service;

/**
 * LHB on 8/7/15.
 */
public interface PushService<T> {

    public void push(T t) throws Exception;
}
