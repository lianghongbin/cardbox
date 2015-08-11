package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.H5Subscribe;
import com.gamesky.card.core.model.H5SubscribeExample;
import com.gamesky.card.dao.mapper.H5SubscribeMapper;
import com.gamesky.card.service.H5SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * lianghongbin on 15/8/11.
 */
@Service
@Transactional
public class H5SubscribeServiceImpl implements H5SubscribeService {

    @Autowired
    private H5SubscribeMapper h5SubscribeMapper;

    /**
     * 添加订阅
     *
     * @param phone 订阅手机号
     * @return 影响条数
     */
    @Override
    public int save(String phone) {
        H5SubscribeExample h5SubscribeExample = new H5SubscribeExample();
        h5SubscribeExample.createCriteria().andPhoneEqualTo(phone);
        List<H5Subscribe> h5Subscribes = h5SubscribeMapper.selectByExample(h5SubscribeExample);
        if (h5Subscribes == null || h5Subscribes.size() == 0) {
            H5Subscribe h5Subscribe = new H5Subscribe();
            h5Subscribe.setPhone(phone);
            h5Subscribe.setDeleted(false);
            h5Subscribe.setCreateTime(System.currentTimeMillis());
            return h5SubscribeMapper.insert(h5Subscribe);
        }

        H5Subscribe h5Subscribe = h5Subscribes.get(0);
        if (!h5Subscribe.getDeleted()) {
            return 1;
        }

        h5Subscribe.setDeleted(false);
        h5Subscribe.setCreateTime(System.currentTimeMillis());

        return h5SubscribeMapper.updateByPrimaryKeySelective(h5Subscribe);
    }

    /**
     * 退订阅
     *
     * @param phone 订阅手机号
     * @return 影响条数
     */
    @Override
    public int remove(String phone) {
        H5SubscribeExample h5SubscribeExample = new H5SubscribeExample();
        h5SubscribeExample.createCriteria().andPhoneEqualTo(phone).andDeletedEqualTo(false);
        List<H5Subscribe> h5Subscribes = h5SubscribeMapper.selectByExample(h5SubscribeExample);
        if (h5Subscribes == null || h5Subscribes.size() == 0) {
            return 0;
        }

        H5Subscribe h5Subscribe = h5Subscribes.get(0);
        if (h5Subscribe.getDeleted()) {
            return 1;
        }

        h5Subscribe.setDeleted(true);
        h5Subscribe.setDeletedTime(System.currentTimeMillis());

        return h5SubscribeMapper.updateByPrimaryKeySelective(h5Subscribe);
    }

    /**
     * 判断某个用户是否订阅了
     *
     * @param phone 手机
     * @return 订阅实体
     */
    @Override
    public H5Subscribe find(String phone) {
        H5SubscribeExample h5SubscribeExample = new H5SubscribeExample();
        h5SubscribeExample.createCriteria().andPhoneEqualTo(phone).andDeletedEqualTo(false);
        List<H5Subscribe> h5Subscribes = h5SubscribeMapper.selectByExample(h5SubscribeExample);
        if (h5Subscribes == null || h5Subscribes.size() == 0) {
            return null;
        }

        return h5Subscribes.get(0);
    }

    /**
     * 取出所有订阅
     *
     * @param page 分页参数
     * @return 订阅列表
     */
    @Override
    public List<H5Subscribe> findAll(Page page) {
        H5SubscribeExample h5SubscribeExample = new H5SubscribeExample();
        h5SubscribeExample.setOrderByClause("create_time desc");
        h5SubscribeExample.setLimit(page.getPagesize());
        h5SubscribeExample.setLimitOffset(page.getOffset());

        return h5SubscribeMapper.selectByExample(h5SubscribeExample);
    }

    /**
     * 取出订阅条数
     *
     * @return 订阅条数
     */
    @Override
    public int findCount() {
        return h5SubscribeMapper.countByExample(new H5SubscribeExample());
    }

    /**
     * 根据条件查询
     *
     * @param subscribeExample 查询条件
     * @return 订阅列表
     */
    @Override
    public List<H5Subscribe> findByCondition(H5SubscribeExample subscribeExample) {
        return h5SubscribeMapper.selectByExample(subscribeExample);
    }

    /**
     * 根据条件查询条数
     *
     * @param subscribeExample 查询条件
     * @return 订阅列表
     */
    @Override
    public int findCountByCondition(H5SubscribeExample subscribeExample) {
        return h5SubscribeMapper.countByExample(subscribeExample);
    }
}
