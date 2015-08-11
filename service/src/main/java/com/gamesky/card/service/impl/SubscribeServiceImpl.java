package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.GameType;
import com.gamesky.card.core.model.Subscribe;
import com.gamesky.card.core.model.SubscribeExample;
import com.gamesky.card.dao.mapper.SubscribeMapper;
import com.gamesky.card.service.GameTypeService;
import com.gamesky.card.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
@Service
@Transactional
public class SubscribeServiceImpl implements SubscribeService {

    @Autowired
    private SubscribeMapper subscribeMapper;
    @Autowired
    private GameTypeService gameTypeService;

    /**
     * 添加订阅
     *
     * @param subscribe 订阅
     * @return 影响条数
     */
    @Override
    public int save(Subscribe subscribe) {
        SubscribeExample subscribeExample = new SubscribeExample();
        subscribeExample.createCriteria().andPhoneEqualTo(subscribe.getPhone()).andGameIdEqualTo(subscribe.getGameId());
        List<Subscribe> subscribes = subscribeMapper.selectByExample(subscribeExample);
        if (subscribes == null || subscribes.size() == 0) {
            subscribe.setCreateTime(System.currentTimeMillis());
            return subscribeMapper.insert(subscribe);
        }

        Subscribe old = subscribes.get(0);
        subscribe.setId(old.getId());
        subscribe.setDeleted(false);
        subscribe.setCreateTime(System.currentTimeMillis());
        return subscribeMapper.updateByPrimaryKeySelective(subscribe);
    }

    /**
     * 退订阅
     *
     * @param id 订阅ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        Subscribe subscribe = subscribeMapper.selectByPrimaryKey(id);
        if (subscribe == null) {
            return 0;
        }

        subscribe.setDeleted(true);
        subscribe.setDeleteTime(System.currentTimeMillis());
        return subscribeMapper.updateByPrimaryKeySelective(subscribe);
    }

    /**
     * 取出所有订阅
     *
     * @param page 分页参数
     * @return 订阅列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<Subscribe> findAll(Page page) {
        SubscribeExample subscribeExample = new SubscribeExample();
        subscribeExample.createCriteria().andDeletedEqualTo(false);
        subscribeExample.setOrderByClause("create_time desc");
        subscribeExample.setLimitOffset(page.getOffset());
        subscribeExample.setLimit(page.getPagesize());

        return subscribeMapper.selectByExample(subscribeExample);
    }

    /**
     * 取出订阅条数
     *
     * @return 订阅条数
     */
    @Override
    @Transactional(readOnly = true)
    public int findCount() {
        SubscribeExample subscribeExample = new SubscribeExample();
        subscribeExample.createCriteria().andDeletedEqualTo(false);

        return subscribeMapper.countByExample(subscribeExample);
    }

    /**
     * 判断某个用户对某个游戏是否订阅了
     *
     * @param phone  手机
     * @param gameId 游戏ID
     * @return 如果订阅了则为订阅时间, 否则为0
     */
    @Override
    @Transactional(readOnly = true)
    public Subscribe find(String phone, int gameId) {
        SubscribeExample subscribeExample = new SubscribeExample();
        subscribeExample.createCriteria().andGameIdEqualTo(gameId).andPhoneEqualTo(phone).andDeletedEqualTo(false);
        List<Subscribe> subscribes = subscribeMapper.selectByExample(subscribeExample);
        if (subscribes == null || subscribes.size() == 0) {
            return null;
        }

        return subscribes.get(0);
    }

    /**
     * 查询某个手机的订阅
     *
     * @param phone 手机号
     * @param page  分页参数
     * @return 订阅列表
     */
    @Override
    public List<Subscribe> find(String phone, Page page) {
        SubscribeExample subscribeExample = new SubscribeExample();
        subscribeExample.createCriteria().andPhoneEqualTo(phone).andDeletedEqualTo(false);
        subscribeExample.setOrderByClause("create_time desc");
        subscribeExample.setLimit(page.getPagesize());
        subscribeExample.setLimitOffset(page.getOffset());

        return subscribeMapper.selectByExample(subscribeExample);
    }

    /**
     * 查询某个游戏的订阅
     *
     * @param gameId 游戏ID
     * @param page   分页参数
     * @return 订阅列表
     */
    @Override
    public List<Subscribe> find(int gameId, Page page) {
        SubscribeExample subscribeExample = new SubscribeExample();
        subscribeExample.createCriteria().andGameIdEqualTo(gameId).andDeletedEqualTo(false);
        subscribeExample.setOrderByClause("create_time desc");
        subscribeExample.setLimit(page.getPagesize());
        subscribeExample.setLimitOffset(page.getOffset());

        return subscribeMapper.selectByExample(subscribeExample);
    }

    /**
     * 根据游戏类别查询订阅
     *
     * @param type 游戏类别
     * @param page 分页参数
     * @return 订阅列表
     */
    @Override
    public List<Subscribe> findByGameType(String type, Page page) {
        List<GameType> gameTypes = gameTypeService.findByType(type, new Page());
        if (gameTypes == null || gameTypes.size() == 0) {
            return null;
        }

        List<Integer> ids = new ArrayList<>();
        for (GameType gameType : gameTypes) {
            ids.add(gameType.getGameId());
        }

        SubscribeExample subscribeExample = new SubscribeExample();
        subscribeExample.setDistinct(true);
        subscribeExample.createCriteria().andGameIdIn(ids).andDeletedEqualTo(false);
        subscribeExample.setOrderByClause("create_time desc");
        subscribeExample.setLimit(page.getPagesize());
        subscribeExample.setLimitOffset(page.getOffset());

        return subscribeMapper.selectByExample(subscribeExample);
    }

    @Override
    public List<Subscribe> findByCondition(SubscribeExample subscribeExample) {
        return subscribeMapper.selectByExample(subscribeExample);
    }

    @Override
    public int findCountByCondition(SubscribeExample subscribeExample) {
        return subscribeMapper.countByExample(subscribeExample);
    }
}
