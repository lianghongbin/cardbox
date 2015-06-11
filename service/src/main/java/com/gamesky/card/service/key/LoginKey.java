package com.gamesky.card.service.key;

import com.gamesky.card.core.Cacheable;
import com.gamesky.card.core.Constants;
import com.gamesky.card.core.model.User;

/**
 * Created on 2015/6/11.
 *
 * @Author lianghongbin
 */
public class LoginKey implements Cacheable {

    private final String phone;
    private long expire = 60;   //秒

    public LoginKey(String phone) {
        this.phone = phone;
    }

    public LoginKey(String phone, long expire) {
        this(phone);
        this.expire = expire;
    }

    @Override
    public String k() {
        return Constants.USER_LOGIN_KEY_PREFIX + ":" + phone;
    }

    @Override
    public long expire() {
        return expire;
    }
}

