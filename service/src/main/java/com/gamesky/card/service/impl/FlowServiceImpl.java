package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Flow;
import com.gamesky.card.core.model.FlowExample;
import com.gamesky.card.dao.mapper.FlowMapper;
import com.gamesky.card.service.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Service
public class FlowServiceImpl implements FlowService {

    @Autowired
    private FlowMapper flowMapper;

    /**
     * 添加、保存流水
     *
     * @param flow 流水实体
     * @return 影响条数
     */
    @Override
    public int save(Flow flow) {
        return flowMapper.insert(flow);
    }

    /**
     * 根据ID查询流水
     *
     * @param id 流水ID
     * @return 流水实体
     */
    @Override
    public Flow find(int id) {
        return flowMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户手机号查询流水
     *
     * @param phone 手机号
     * @param page  分页参数
     * @return 流水列表
     */
    @Override
    public List<Flow> findByPhone(String phone, Page page) {
        FlowExample flowExample = new FlowExample();
        flowExample.createCriteria().andPhoneEqualTo(phone);
        flowExample.setOrderByClause("id desc");
        flowExample.setLimitOffset(page.getOffset());
        flowExample.setLimit(page.getSize());
        return flowMapper.selectByExample(flowExample);
    }

    /**
     * 根据用户ID查询流水
     *
     * @param userId 用户ID
     * @param page   分页参数
     * @return 流水列表
     */
    @Override
    public List<Flow> findByUser(int userId, Page page) {
        FlowExample flowExample = new FlowExample();
        flowExample.createCriteria().andUserIdEqualTo(userId);
        flowExample.setOrderByClause("id desc");
        flowExample.setLimitOffset(page.getOffset());
        flowExample.setLimit(page.getSize());
        return flowMapper.selectByExample(flowExample);
    }

    /**
     * 分页查询所有流水
     *
     * @param page 分页参数
     * @return 流水列表
     */
    @Override
    public List<Flow> findAll(Page page) {
        FlowExample flowExample = new FlowExample();
        flowExample.setOrderByClause("id desc");
        flowExample.setLimitOffset(page.getOffset());
        flowExample.setLimit(page.getSize());
        return flowMapper.selectByExample(flowExample);
    }

    /**
     * 查询所有流水数量
     *
     * @return 流水数量
     */
    @Override
    public int FindCount() {
        FlowExample flowExample = new FlowExample();
        flowExample.createCriteria().andIdGreaterThan(0);
        return flowMapper.countByExample(flowExample);
    }

    /**
     * 根据条件分页查询所有流水
     *
     * @param flowExample 分页参数
     * @return 流水列表
     */
    @Override
    public List<Flow> findByCondition(FlowExample flowExample) {
        return flowMapper.selectByExample(flowExample);
    }

    /**
     * 根据条件查询所有流水数量
     *
     * @return 流水数量
     */
    @Override
    public int FindCount(FlowExample flowExample) {
        return flowMapper.countByExample(flowExample);
    }
}
