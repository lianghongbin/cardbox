package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Focus;
import com.gamesky.card.core.model.FocusExample;
import com.gamesky.card.dao.mapper.FocusMapper;
import com.gamesky.card.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 焦点图服务接口实现类
 * Created on 2015/6/8.
 *
 * @Author lianghongbin
 */
@Service
public class FocusServiceImpl implements FocusService {

    @Autowired
    private FocusMapper focusMapper;

    /**
     * 添加焦点图
     *
     * @param focus 焦点图
     * @return 影响条数
     */
    @Override
    public int save(Focus focus) {
        return focusMapper.insert(focus);
    }

    /**
     * 删除焦点图
     *
     * @param id 焦点图ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        return focusMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新焦点图
     *
     * @param focus 焦点图
     * @return 影响条数
     */
    @Override
    public int update(Focus focus) {
        return focusMapper.updateByPrimaryKey(focus);
    }

    /**
     * 取出所有焦点图
     *
     * @param page 分页参数
     * @return 焦点图列表
     */
    @Override
    public List<Focus> findAll(Page page) {
        FocusExample focusExample = new FocusExample();
        focusExample.createCriteria().andIdGreaterThan(0);
        focusExample.setLimitOffset(page.getOffset());
        focusExample.setLimit(page.getSize());
        focusExample.setOrderByClause("sort asc");
        return focusMapper.selectByExample(focusExample);
    }

    /**
     * 计算所有焦点图数
     *
     * @return 焦点图数
     */
    @Override
    public int findCount() {
        FocusExample focusExample = new FocusExample();
        focusExample.createCriteria().andIdGreaterThan(0);
        return focusMapper.countByExample(focusExample);
    }

    /**
     * 根据是否有效条件，获取列表
     *
     * @param enable 是否可用
     * @param page   分页参数
     * @return 列表
     */
    @Override
    public List<Focus> findByEnable(boolean enable, Page page) {
        FocusExample focusExample = new FocusExample();
        focusExample.createCriteria().andEnabledEqualTo(enable);
        focusExample.setLimitOffset(page.getOffset());
        focusExample.setLimit(page.getSize());
        focusExample.setOrderByClause("sort asc");
        return focusMapper.selectByExample(focusExample);
    }

    /**
     * 根据是否有效条件，查询个数
     * @param enable 是否有效
     * @return 个数
     */
    @Override
    public int findCountByEnable(boolean enable) {
        FocusExample focusExample = new FocusExample();
        focusExample.createCriteria().andEnabledEqualTo(enable);
        return focusMapper.countByExample(focusExample);
    }
}
