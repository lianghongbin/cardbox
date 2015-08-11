package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.H5Subscribe;
import com.gamesky.card.core.model.H5SubscribeExample;
import com.gamesky.card.core.model.Subscribe;
import com.gamesky.card.core.model.SubscribeExample;

import java.util.List;

/**
 * lianghongbin on 15/8/11.
 */
public interface H5SubscribeService {

    /**
     * 添加订阅
     *
     * @param phone 订阅手机号
     * @return 影响条数
     */
    int save(String phone);

    /**
     * 退订阅
     *
     * @param phone 订阅手机号
     * @return 影响条数
     */
    int remove(String phone);

    /**
     * 判断某个用户是否订阅了
     *
     * @param phone  手机
     * @return 订阅实体
     */
    H5Subscribe find(String phone);

    /**
     * 取出所有订阅
     *
     * @param page 分页参数
     * @return 订阅列表
     */
    List<H5Subscribe> findAll(Page page);

    /**
     * 取出订阅条数
     *
     * @return 订阅条数
     */
    int findCount();

    /**
     * 根据条件查询
     * @param subscribeExample 查询条件
     * @return 订阅列表
     */
    List<H5Subscribe> findByCondition(H5SubscribeExample subscribeExample);

    /**
     * 根据条件查询条数
     * @param subscribeExample 查询条件
     * @return 订阅列表
     */
    int findCountByCondition(H5SubscribeExample subscribeExample);
}
