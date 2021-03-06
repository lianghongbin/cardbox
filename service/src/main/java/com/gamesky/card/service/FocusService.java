package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Focus;

import java.util.List;

/**
 * 焦点图服务接口类
 * Created on 2015/6/8.
 *
 * @Author lianghongbin
 */
public interface FocusService {

    /**
     * 添加焦点图
     *
     * @param focus 焦点图
     * @return 影响条数
     */
    public int save(Focus focus);

    /**
     * 删除焦点图
     *
     * @param id 焦点图ID
     * @return 影响条数
     */
    public int remove(int id);

    /**
     * 更新焦点图
     *
     * @param focus 焦点图
     * @return 影响条数
     */
    public int update(Focus focus);

    Focus find(int id);

    /**
     * 取出所有焦点图
     *
     * @param page 分页参数
     * @return 焦点图列表
     */
    public List<Focus> findAll(Page page);

    /**
     * 取出所有焦点图
     *
     * @return 焦点图列表
     */
    public List<Focus> findAll();

    /**
     * 计算所有焦点图数
     *
     * @return 焦点图数
     */
    public int findCount();

    /**
     * 根据是否有效条件，获取列表
     *
     * @param enabled  是否可用
     * @param platform 设备类别
     * @param page     分页参数
     * @return 列表
     */
    public List<Focus> findByEnabled(Boolean enabled, String platform, Page page);

    /**
     * 根据是否有效条件，查询个数
     *
     * @param enabled 是否有效
     * @return 个数
     */
    public int findCountByEnabled(Boolean enabled, String platform);
}
