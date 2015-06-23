package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardExample;
import com.gamesky.card.core.model.CardWithBLOBs;

import java.util.List;

/**
 * 卡包服务接口
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public interface CardService {

    /**
     * 添加保存一个卡包
     *
     * @param card 卡包类
     * @return 影响条数
     */
    public int save(CardWithBLOBs card);

    /**
     * 锁死卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    public int close(int id);

    /**
     * 解锁卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    public int open(int id);

    /**
     * 修改卡包信息
     *
     * @param card 卡包
     * @return 影响条数
     */
    public int update(CardWithBLOBs card);

    /**
     * 分发、分配一个卡
     *
     * @param id    卡包ID
     * @param phone 分配给用户（手机唯一）
     * @return 影响条数
     */
    public int assign(int id, String phone);

    /**
     * 根据卡包ID，查找卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    public Card find(int id);

    /**
     * 分页显示所有卡包
     *
     * @param page 分页条件
     * @return 卡包列表
     */
    public List<Card> findAll(Page page);

    /**
     * 根据条件分页查询显示卡包
     *
     * @param cardExample 查询条件
     * @return 卡包列表
     */
    public List<CardWithBLOBs> findByCondition(CardExample cardExample);

    /**
     * 根据条件分页查询显示卡包数量
     *
     * @param cardExample 查询条件
     * @return 卡包数量
     */
    public int findCountByCondition(CardExample cardExample);

    /**
     * 根据游戏查找该游戏下的卡包列表
     *
     * @param gameId 游戏ID
     * @param page   分页数据
     * @return 卡包列表
     */
    public List<CardWithBLOBs> findByGame(int gameId, Page page);

    /**
     * 查找某款游戏下的卡包类别数量
     *
     * @param gameId 游戏ID
     * @return 卡包类别数量
     */
    public int findCountByGame(int gameId);

    /**
     * 指查找礼包
     * @param ids 礼包ID集合
     * @return 礼包集合
     */
    public List<CardWithBLOBs> findByIds(List<Integer> ids);

    /**
     * 查询手机用户是否已经申领过该礼品包
     * @param cardId 礼包ID
     * @param phone 用户手机
     * @return true/false
     */
    public boolean hasAssign(int cardId, String phone);
}
