package com.gamesky.card.service.key;

import com.gamesky.card.core.Cacheable;
import com.gamesky.card.core.Constants;
import com.gamesky.card.core.model.User;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class CheckCodeKey implements Cacheable {

    private final String phone;
    private long expire = 60;   //秒

    public CheckCodeKey(String phone) {
        this.phone = phone;
    }

    public CheckCodeKey(String phone, long expire) {
        this(phone);
        this.expire = expire;
    }

    @Override
    public String k() {
        return Constants.CHECK_CODE_KEY_PREFIX + ":" + phone;
    }

    @Override
    public long expire() {
        return expire;
    }
}
