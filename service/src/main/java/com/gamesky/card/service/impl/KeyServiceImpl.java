package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.exceptions.LockException;
import com.gamesky.card.core.lock.GlobalLock;
import com.gamesky.card.core.lock.Lockable;
import com.gamesky.card.core.model.Key;
import com.gamesky.card.core.model.KeyExample;
import com.gamesky.card.dao.mapper.KeyMapper;
import com.gamesky.card.service.KeyService;
import com.gamesky.card.service.RedisGlobalLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2015/6/8.
 *
 * @Author lianghongbin
 */
@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    private KeyMapper keyMapper;

    private GlobalLock<Lockable> globalLock =new RedisGlobalLock<>();;

    /**
     * 保存我的激活码
     *
     * @param key 激活码
     * @return 影响条数
     */
    @Override
    public int save(Key key) {
        return keyMapper.insert(key);
    }

    /**
     * 删除我的激活码
     *
     * @param id 激活码ID
     * @return 影响条数
     */
    @Override
    public int delete(int id) {
        return keyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新我的激活码
     *
     * @param key 激活码
     * @return 影响条数
     */
    @Override
    public int update(Key key) {
        return keyMapper.updateByPrimaryKey(key);
    }

    /**
     * 根据id查找激活码
     *
     * @param id 激活码ID
     * @return 激活码
     */
    @Override
    public Key find(int id) {
        return keyMapper.selectByPrimaryKey(id);
    }

    /**
     * 随机获取某种卡包下的一个未分配的激活码
     * @param cardId 卡包ID
     * @return 激活码实体
     */
    @Override
    public Key findOne(final int cardId) {
        Lockable lockable = new Lockable() {
            @Override
            public String key() {
                return Key.class.getCanonicalName() + ":" + cardId;
            }

            @Override
            public int expireSecond() {
                return 5;
            }
        };

        try {
            globalLock.lock(lockable);
            KeyExample keyExample = new KeyExample();
            keyExample.createCriteria().andCardIdEqualTo(cardId).andAssignedEqualTo(false);
            keyExample.setOrderByClause("id asc");
            List<Key> keys = keyMapper.selectByExample(keyExample);
            if (keys == null || keys.size() == 0) {
                return null;
            }

            return keys.get(0);
        }catch (LockException e) {
            return null;
        }
        finally {
            globalLock.unLock(lockable);
        }
    }

    /**
     * 查看某个礼包下的激活码
     *
     * @param cardId 礼包ID
     * @param page   分页参数
     * @return 激活码列表
     */
    @Override
    public List<Key> findByCard(int cardId, Page page) {
        KeyExample keyExample = new KeyExample();
        keyExample.createCriteria().andCardIdEqualTo(cardId);
        keyExample.setLimitOffset(page.getOffset());
        keyExample.setLimit(page.getSize());
        keyExample.setOrderByClause("use_time desc, assign_time desc");
        return keyMapper.selectByExample(keyExample);
    }

    /**
     * 查看某个礼包下的激活码数量
     *
     * @param cardId 礼包ID
     * @return 激活码列表数量
     */
    @Override
    public int findCountByCard(int cardId) {
        KeyExample keyExample = new KeyExample();
        keyExample.createCriteria().andCardIdEqualTo(cardId);
        return keyMapper.countByExample(keyExample);
    }

    /**
     * 根据手机号查看用户的激活码
     *
     * @param phone 用户手机号
     * @param page   分页参数
     * @return 激活码列表
     */
    @Override
    public List<Key> findByPhone(String phone, Page page) {
        KeyExample keyExample = new KeyExample();
        keyExample.createCriteria().andPhoneEqualTo(phone);
        keyExample.setLimitOffset(page.getOffset());
        keyExample.setLimit(page.getSize());
        keyExample.setOrderByClause("use_time desc, assign_time desc");
        return keyMapper.selectByExample(keyExample);
    }

    /**
     * 根据手机号查看用户的激活码
     *
     * @param phone 用户手机号
     * @return 激活码列表
     */
    @Override
    public int findCountByPhone(String phone) {
        KeyExample keyExample = new KeyExample();
        keyExample.createCriteria().andPhoneEqualTo(phone);
        return keyMapper.countByExample(keyExample);
    }

    /**
     * 根据用户的激活码
     *
     * @param page  分页参数
     * @return 激活码列表
     */
    @Override
    public List<Key> findAll(Page page) {
        KeyExample keyExample = new KeyExample();
        keyExample.setLimitOffset(page.getOffset());
        keyExample.setLimit(page.getSize());
        keyExample.setOrderByClause("use_time desc, assign_time desc");
        return keyMapper.selectByExample(keyExample);
    }

    /**
     * 根据用户手机号查找该用户的激活码数量
     *
     * @return 激活码数量
     */
    @Override
    public int findCount() {
        KeyExample keyExample = new KeyExample();
        keyExample.createCriteria().andIdGreaterThan(0);
        return keyMapper.countByExample(keyExample);
    }

    /**
     * 根据条件查询激活码列表
     *
     * @param keyExample 查询条件
     * @return 激活码列表
     */
    @Override
    public List<Key> findByCondition(KeyExample keyExample) {
        return keyMapper.selectByExample(keyExample);
    }

    /**
     * 根据条件查询激活码列表数量
     *
     * @param keyExample 查询条件
     * @return 激活码列表数量
     */
    @Override
    public int findCountByCondition(KeyExample keyExample) {
        return keyMapper.countByExample(keyExample);
    }
}
