package com.gamesky.card.service;

import com.gamesky.card.core.GainType;

/**
 * 积分服务接口类
 * Created on 2015/6/9.
 *
 * @Author lianghongbin
 */
public interface PointService {

    /**
     * 用户赚取积分
     * @param userId 用户ID
     * @param points 积分数
     * @return 影响条数
     */
    int gain(int userId, int points, GainType type);

    /**
     * 消费积分
     * @param userId 用户ID
     * @param points 积分数
     * @return 影响条数
     */
    int consume(int userId, int points);
}
