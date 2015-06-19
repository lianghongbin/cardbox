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
     * 每日登录赚积分，一天只能一次
     * @param phone 用户手机
     * @return 积分数
     */
    public int dailySign(String phone);

    /**
     * 微信分享赚积分，一天只能一次
     * @param phone 用户手机
     * @return 积分数
     */
    public int weixinShare(String phone);

    /**
     * QQ分享赚积分 ，一天只能一次
     * @param phone 用户手机
     * @return 积分数
     */
    public int qqShare(String phone);
}
