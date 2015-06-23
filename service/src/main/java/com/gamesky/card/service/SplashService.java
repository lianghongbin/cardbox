package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Splash;

import java.util.List;

/**
 * 启动页面服务接口
 * Created by lianghongbin on 2015/6/8.
 */
public interface SplashService {

    /**
     * 添加一个启动页
     * @param splash 启动页
     * @return 影响条数
     */
    public int save(Splash splash);

    /**
     * 删除一个启动页
     * @param id 删除ID
     * @return 影响条数
     */
    public int remove(int id);

    /**
     * 更新启动页
     * @param splash 启动页
     * @return 影响条数
     */
    public int update(Splash splash);

    /**
     * 获取所有启动页
     *
     * @param page 分页参数
     * @return 启动页列表
     */
    public List<Splash> findAll(Page page);

    /**
     * 获取所有启动页数
     * @return 启动页数
     */
    public int findCount();

    /**
     * 获取所有启动页
     *
     * @return 启动页列表
     */
    public List<Splash> findEnabledAll();

    /**
     * 获取所有启动页数
     * @return 启动页数
     */
    public int findEnabledAllCount();

    /**
     * 根据是否可用条件查询启动项
     *
     * @param enabled 是否可用
     * @param page    分页参数
     * @return 启动项列表
     */
    public List<Splash> findEnabled(boolean enabled, Page page);

    /**
     * 获取第一个启动页
     *
     * @return 启动页列表
     */
    public Splash findOne();

    /**
     * 获取所有启动页数量
     * @param enabled 是否可用
     * @return 启动页数量
     */
    public int findEnabledCount(Boolean enabled);
}
