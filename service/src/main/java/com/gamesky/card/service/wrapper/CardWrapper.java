package com.gamesky.card.service.wrapper;

import com.gamesky.card.core.lock.Lockable;
import com.gamesky.card.core.model.Card;

/**
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public class CardWrapper implements Lockable {
    private int id;

    public CardWrapper(int id) {
        this.id = id;
    }

    @Override
    public String key() {
        return Card.class.getCanonicalName() + ":" + id;
    }

    @Override
    public int expireSecond() {
        return 5;   //锁住5秒钟
    }
}
