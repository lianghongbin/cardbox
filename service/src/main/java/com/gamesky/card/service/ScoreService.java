package com.gamesky.card.service;

import com.gamesky.card.core.MethodType;

/**
 * 积分服务接口类
 * Created on 2015/6/9.
 *
 * @Author lianghongbin
 */
public interface ScoreService {

    /**
     * 用户赚取积分
     *
     * @param phone 用户手机
     * @param score 积分数
     * @param methodType   获取积分方式类别
     * @return 影响条数
     */
    int gain(String phone, int score, MethodType methodType);

    /**
     * 消费积分
     *
     * @param phone 用户手机
     * @param score 积分数
     * @return 影响条数
     */
    int consume(String phone, int score);

    /**
     * 获取积分
     * @param phone 手机号
     * @param type 获取类别
     * @return
     */
    int acquire(String phone, String type);
}
