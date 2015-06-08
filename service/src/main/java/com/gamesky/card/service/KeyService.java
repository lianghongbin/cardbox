package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Key;
import com.gamesky.card.core.model.KeyExample;

import java.util.List;

/**
 * 领取的激活码管理接口类
 * Created on 2015/6/8.
 *
 * @Author lianghongbin
 */
public interface KeyService {

    /**
     * 保存我的激活码
     *
     * @param key 激活码
     * @return 影响条数
     */
    int save(Key key);

    /**
     * 删除我的激活码
     *
     * @param id 激活码ID
     * @return 影响条数
     */
    int delete(int id);

    /**
     * 更新我的激活码
     *
     * @param key 激活码
     * @return 影响条数
     */
    int update(Key key);

    /**
     * 根据id查找激活码
     *
     * @param id 激活码ID
     * @return 激活码
     */
    Key find(int id);

    /**
     * 查看某个礼包下的激活码
     *
     * @param cardId 礼包ID
     * @param page   分页参数
     * @return 激活码列表
     */
    List<Key> findByCard(int cardId, Page page);

    /**
     * 查看某个礼包下的激活码数量
     *
     * @param cardId 礼包ID
     * @return 激活码列表数量
     */
    int findCountByCard(int cardId);

    /**
     * 根据用户手机号查找该用户的激活码
     *
     * @param phone 手机号
     * @param page  分页参数
     * @return 激活码列表
     */
    List<Key> findByPhone(String phone, Page page);

    /**
     * 根据用户手机号查找该用户的激活码数量
     *
     * @param phone 手机号
     * @return 激活码数量
     */
    int findCountByPhone(String phone);

    /**
     * 根据用户手机号查找该用户的激活码
     *
     * @param page  分页参数
     * @return 激活码列表
     */
    List<Key> findAll(Page page);

    /**
     * 根据用户手机号查找该用户的激活码数量
     *
     * @return 激活码数量
     */
    int findCount();

    /**
     * 根据条件查询激活码列表
     * @param keyExample 查询条件
     * @return 激活码列表
     */
    List<Key> findByCondition(KeyExample keyExample);

    /**
     * 根据条件查询激活码列表数量
     * @param keyExample 查询条件
     * @return 激活码列表数量
     */
    int findCountByCondition(KeyExample keyExample);
}
