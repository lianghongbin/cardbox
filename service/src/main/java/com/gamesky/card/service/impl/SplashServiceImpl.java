package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Splash;
import com.gamesky.card.core.model.SplashExample;
import com.gamesky.card.dao.mapper.SplashMapper;
import com.gamesky.card.service.SplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2015/6/8.
 * @Author lianghongbin
 */
@Service
public class SplashServiceImpl implements SplashService {

    @Autowired
    private SplashMapper splashMapper;

    /**
     * 添加一个启动页
     *
     * @param splash 启动页
     * @return 影响条数
     */
    @Override
    public int save(Splash splash) {
        return splashMapper.insert(splash);
    }

    /**
     * 删除一个启动页
     *
     * @param id 删除ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        return splashMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新启动页
     *
     * @param splash 启动页
     * @return 影响条数
     */
    @Override
    public int update(Splash splash) {
        return splashMapper.updateByPrimaryKey(splash);
    }

    /**
     * 获取所有启动页
     *
     * @param page 分页参数
     * @return 启动页列表
     */
    @Override
    public List<Splash> findAll(Page page) {
        SplashExample splashExample = new SplashExample();
        splashExample.setLimitOffset(page.getOffset());
        splashExample.setLimit(page.getPagesize());
        splashExample.setOrderByClause("sort asc");
        return splashMapper.selectByExample(splashExample);
    }

    /**
     * 获取所有启动页
     *
     * @return 启动页列表
     */
    @Override
    public List<Splash> findAll() {
        SplashExample splashExample = new SplashExample();
        splashExample.createCriteria().andEnabledEqualTo(true);
        splashExample.setOrderByClause("sort asc");
        return splashMapper.selectByExample(splashExample);
    }

    /**
     * 获取所有启动页数
     *
     * @return 启动页数
     */
    @Override
    public int findCount() {
        SplashExample splashExample = new SplashExample();
        splashExample.createCriteria().andIdGreaterThan(0);
        return splashMapper.countByExample(splashExample);
    }

    /**
     * 根据是否可用条件查询启动项
     *
     * @param enabled 是否可用
     * @param page    分页参数
     * @return 启动项列表
     */
    @Override
    public List<Splash> findByEnable(boolean enabled, Page page) {
        SplashExample splashExample = new SplashExample();
        splashExample.createCriteria().andEnabledEqualTo(enabled);
        splashExample.setLimit(page.getPagesize());
        splashExample.setLimitOffset(page.getOffset());
        splashExample.setOrderByClause("sort asc");
        return splashMapper.selectByExample(splashExample);
    }

    /**
     * 获取所有启动页数量
     *
     * @param enabled 是否可用
     * @return 启动页数量
     */
    @Override
    public int findCountByEnable(Boolean enabled) {
        SplashExample splashExample = new SplashExample();

        if (enabled != null) {
            splashExample.createCriteria().andEnabledEqualTo(enabled);
            return splashMapper.countByExample(splashExample);
        }

        splashExample.createCriteria().andIdGreaterThan(0);
        return splashMapper.countByExample(splashExample);
    }

    /**
     * 获取第一个启动页
     *
     * @return 启动页列表
     */
    @Override
    public Splash findOne() {
        SplashExample splashExample = new SplashExample();
        splashExample.createCriteria().andEnabledEqualTo(true);
        splashExample.setOrderByClause("sort asc");
        List<Splash> splashs = splashMapper.selectByExample(splashExample);
        if (splashs == null || splashs.size() == 0) {
            return null;
        }

        return splashs.get(0);
    }
}
