package com.gamesky.card.service;

import com.gamesky.card.core.model.Sign;

/**
 * lianghongbin on 15/8/21.
 */
public interface SignService {

    /**
     * 签到
     * @param phone 签到手机
     * @return 影响条数
     */
    int sign(String phone);

    /**
     * 获取用户的签到记录
     * @param phone 手机号
     * @return 签到实例
     */
    Sign findByPhone(String phone);

    /**
     * 所有签到用户数
     * @return 签到用户数
     */
    int findCount();
}
