package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Flow;
import com.gamesky.card.core.model.FlowExample;

import java.util.List;

/**
 * 积分流水接口类
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
public interface FlowService {

    /**
     * 添加、保存流水
     * @param flow 流水实体
     * @return 影响条数
     */
    int save(Flow flow);

    /**
     * 根据ID查询流水
     * @param id 流水ID
     * @return 流水实体
     */
    Flow find(int id);

    /**
     * 根据用户手机号查询流水
     * @param phone 手机号
     * @param page 分页参数
     * @return 流水列表
     */
    List<Flow> findByPhone(String phone, Page page);

    /**
     * 根据用户ID查询流水
     * @param userId 用户ID
     * @param page 分页参数
     * @return 流水列表
     */
    List<Flow> findByUser(int userId, Page page);

    /**
     * 分页查询所有流水
     * @param page 分页参数
     * @return 流水列表
     */
    List<Flow> findAll(Page page);

    /**
     * 查询所有流水数量
     * @return 流水数量
     */
    int FindCount();

    /**
     * 根据条件分页查询所有流水
     *
     * @param flowExample 分页参数
     * @return 流水列表
     */
    public List<Flow> findByCondition(FlowExample flowExample);

    /**
     * 根据条件查询所有流水数量
     *
     * @return 流水数量
     */
    public int FindCount(FlowExample flowExample);
}
