package com.gamesky.card.service;

import com.gamesky.card.core.lock.Lockable;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.Code;

/**
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
public class CardLock implements Lockable {

    private int id;

    public CardLock(int id) {
        this.id = id;
    }

    /**
     * 锁定的KEY
     *
     * @return 锁定的Key
     */
    @Override
    public String key() {
        return Card.class.getCanonicalName() + ":" + id;
    }

    /**
     * 在使用redis时，设置缓存时长
     *
     * @return 有效时长
     */
    @Override
    public long expire() {
        return 10000;
    }
}