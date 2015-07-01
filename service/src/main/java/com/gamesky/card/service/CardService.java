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
     * 删除一个礼包
     * @param id 礼包ID
     * @return 影响条数
     */
    public int remove(int id);

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
    public String assign(int id, String phone);

    /**
     * 根据卡包ID，查找卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    public Card find(int id);

    /**
     * 分页显示所有卡包(后台用，显示所有礼包）
     *
     * @param page 分页条件
     * @return 卡包列表
     */
    public List<Card> findAll(Page page);

    /**
     * 分页显示所有卡包数(后台用，显示所有礼包数）
     *
     * @return 卡包数
     */
    public int findCount();

    /**
     * 分页显示所有卡包（前台接口用，显示可用礼包）
     *
     * @param page 分页条件
     * @return 卡包列表
     */
    public List<Card> findEnabledAll(Page page);

    /**
     * 分页显示所有卡包数（前台接口用，显示可用礼包数）
     *
     * @return 卡包数
     */
    public int findEnabledCount();


    /**
     * 根据条件分页查询显示卡包
     *
     * @param cardExample 查询条件
     * @return 卡包列表
     */
    public List<Card> findByCondition(CardExample cardExample);

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
    public List<Card> findByGame(int gameId, String platform, Page page);

    /**
     * 查找某款游戏下的卡包类别数量
     *
     * @param gameId 游戏ID
     * @return 卡包类别数量
     */
    public int findCountByGame(int gameId, String platform);

    /**
     * 根据游戏查找该游戏下的推荐卡包列表
     *
     * @param gameId 游戏ID
     * @param page   分页数据
     * @return 卡包列表
     */
    public List<Card> findRecommendByGame(int gameId, String platform, Page page);

    /**
     * 查找某款游戏下的推荐卡包类别数量
     *
     * @param gameId 游戏ID
     * @return 卡包类别数量
     */
    public int findRecommendCountByGame(int gameId, String platform);


    /**
     * 指查找礼包
     * @param ids 礼包ID集合
     * @return 礼包集合
     */
    public List<Card> findByIds(List<Integer> ids);

    /**
     * 查询手机用户是否已经申领过该礼品包
     * @param cardId 礼包ID
     * @param phone 用户手机
     * @return true/false
     */
    public boolean hasAssign(int cardId, String phone);

    /**
     * 随游戏的上线、下线，相应的礼包置为有效或无效状态
     * @param gameId 游戏ID
     * @param valid 是否有效
     * @return 影响条数
     */
    public int validByGame(int gameId, boolean valid);

    /**
     * 根据条件查找推荐礼包
     * @param type 条件
     * @param page 分页
     * @return 礼包列表
     */
    public List<Card> recommend(int type, String platform, Page page);

    /**
     * 根据条件查找推荐礼包数
     * @param type 条件
     * @return 礼包数
     */
    public int recommendCount(int type, String platform);

    /**
     * 根据条件查找礼包
     * @param key 查询关键字
     * @param page 分页
     * @return 礼包列表
     */
    List<Card> findByKey(String key, String platform, Page page);

    /**
     * 根据条件查找礼包数
     * @param key 查询关键字
     * @return 礼包数
     */
    int findCountByKey(String key, String platform);

    /**
     * 增加激活码数量
     * @param id 礼包ID
     * @param count 增加数量
     * @return 影响条数
     */
    int increaseTotal(int id, int count);

    /**
     * 增加激活码数量
     * @param id 礼包ID
     * @param count 增加数量
     * @return 影响条数
     */
    int reduceTotal(int id, int count);

    /**
     * 添加已分发激活码数量
     * @param id 礼包ID
     * @param count 减少数量
     * @return 影响条数
     */
    int increaseAssignTotal(int id, int count);

    /**
     * 减少激活码总数量
     *
     * @param count 减少数量
     * @return 影响条数
     */
    public int reduceAssignTotal(int id, int count);
}
