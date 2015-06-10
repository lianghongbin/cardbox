package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.lock.GlobalLock;
import com.gamesky.card.core.exceptions.LockException;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardExample;
import com.gamesky.card.core.model.Key;
import com.gamesky.card.dao.mapper.CardMapper;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.KeyService;
import com.gamesky.card.service.RedisGlobalLock;
import com.gamesky.card.service.wrapper.CardWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 卡包服务接口实现类
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private KeyService keyService;
    private GlobalLock<CardWrapper> globalLock = new RedisGlobalLock<>();

    /**
     * 添加保存一个卡包
     *
     * @param card 卡包类
     * @return 影响条数
     */
    @Override
    public int save(Card card) {
        card.setCreateTime(new Date());
        return cardMapper.insert(card);
    }

    /**
     * 锁死卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    @Override
    public int close(int id) {
        Card card = cardMapper.selectByPrimaryKey(id);
        card.setClosed(true);
        return cardMapper.updateByPrimaryKey(card);
    }

    /**
     * 解锁卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    @Override
    public int open(int id) {
        Card card = cardMapper.selectByPrimaryKey(id);
        card.setClosed(false);
        return cardMapper.updateByPrimaryKey(card);
    }

    /**
     * 修改卡包信息
     *
     * @param card 卡包
     * @return 影响条数
     */
    @Override
    public int update(Card card) {
        return cardMapper.updateByPrimaryKey(card);
    }

    /**
     * 分发、分配一个卡给某个用户
     *
     * @param id    卡包ID
     * @param phone 用户手机
     * @return 影响条数
     */
    @Override
    public int assign(int id, String phone) {
        CardWrapper cardWrapper = new CardWrapper(id);
        try {
            globalLock.lock(cardWrapper);
            Card card = cardMapper.selectByPrimaryKey(id);
            if (card.getTotal() > card.getAssignTotal()) {
                card.setAssignTotal(card.getAssignTotal() + 1);
            }

            Key key = keyService.findOne(id);
            key.setAssigned(true);
            key.setPhone(phone);
            key.setAssignTime(new Date());
            keyService.update(key);

            return cardMapper.updateByPrimaryKey(card);
        } catch (LockException e) {
            return 0;
        } finally {
            globalLock.unLock(cardWrapper);
        }
    }

    /**
     * 根据卡包ID，查找卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    @Override
    public Card find(int id) {
        return cardMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页显示所有卡包
     *
     * @param page 分页条件
     * @return 卡包列表
     */
    @Override
    public List<Card> findAll(Page page) {
        CardExample cardExample = new CardExample();
        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getSize());
        cardExample.setOrderByClause("id desc");
        return cardMapper.selectByExample(cardExample);
    }

    /**
     * 根据条件分页查询显示卡包
     *
     * @param cardExample 查询条件
     * @return 卡包列表
     */
    @Override
    public List<Card> findByCondition(CardExample cardExample) {
        return cardMapper.selectByExample(cardExample);
    }

    /**
     * 根据游戏查找该游戏下的卡包列表
     *
     * @param gameId 游戏ID
     * @param page   分页数据
     * @return 卡包列表
     */
    @Override
    public List<Card> findByGame(int gameId, Page page) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andGameIdEqualTo(gameId);
        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getSize());
        cardExample.setOrderByClause("id desc");
        return cardMapper.selectByExample(cardExample);
    }

    /**
     * 查找某款游戏下的卡包类别数量
     *
     * @param gameId 游戏ID
     * @return 卡包类别数量
     */
    @Override
    public int findCountByGame(int gameId) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andGameIdEqualTo(gameId);
        return cardMapper.countByExample(cardExample);
    }
}
