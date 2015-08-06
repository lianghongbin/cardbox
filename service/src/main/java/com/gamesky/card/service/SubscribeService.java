package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Subscribe;
import com.gamesky.card.core.model.SubscribeExample;

import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
public interface SubscribeService {

    /**
     * 添加订阅
     *
     * @param subscribe 订阅
     * @return 影响条数
     */
    int save(Subscribe subscribe);

    /**
     * 退订阅
     *
     * @param id 订阅ID
     * @return 影响条数
     */
    int delete(int id);

    /**
     * 取出所有订阅
     *
     * @param page 分页参数
     * @return 订阅列表
     */
    List<Subscribe> findAll(Page page);

    /**
     * 取出订阅条数
     *
     * @return 订阅条数
     */
    int findCount();

    /**
     * 判断某个用户对某个游戏是否订阅了
     *
     * @param phone  手机
     * @param gameId 游戏ID
     * @return 订阅实体
     */
    Subscribe find(String phone, int gameId);

    /**
     * 查询某个手机的订阅
     *
     * @param phone 手机号
     * @param page  分页参数
     * @return 订阅列表
     */
    List<Subscribe> find(String phone, Page page);

    /**
     * 查询某个游戏的订阅
     *
     * @param gameId 游戏ID
     * @param page   分页参数
     * @return 订阅列表
     */
    List<Subscribe> find(int gameId, Page page);

    /**
     * 根据游戏类别查询订阅
     *
     * @param type 游戏类别
     * @param page 分页参数
     * @return 订阅列表
     */
    List<Subscribe> findByGameType(String type, Page page);
    
    List<Subscribe> findByCondition(SubscribeExample subscribeExample);

    int findCountByCondition(SubscribeExample subscribeExample);
}
