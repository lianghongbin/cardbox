package com.gamesky.card.service.key;

import com.gamesky.card.core.Cacheable;
import com.gamesky.card.core.Constants;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class CheckCodeKey implements Cacheable {

    private final String phone;
    private int expire = 60;   //秒

    public CheckCodeKey(String phone) {
        this.phone = phone;
    }

    public CheckCodeKey(String phone, int expire) {
        this(phone);
        this.expire = expire;
    }

    @Override
    public String k() {
        return Constants.CHECK_CODE_KEY_PREFIX + ":" + phone;
    }

    @Override
    public int expire() {
        return expire;
    }
}
