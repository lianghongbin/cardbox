package com.gamesky.card.service;

/**
 * Created on 3/26/15.
 *
 * @Author lianghongbin
 */
public interface CheckCodeService {

    public boolean send(String phone, String message);
}
