package com.gamesky.card.service;

import com.gamesky.card.core.exceptions.MarshalException;
import com.gamesky.card.core.exceptions.SmsSenderException;

/**
 * Created on 3/26/15.
 *
 * @Author lianghongbin
 */
public interface CheckCodeService {

    public void send(String phone, String message) throws MarshalException, SmsSenderException;
}
