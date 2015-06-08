package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Feedback;
import com.gamesky.card.core.model.FeedbackExample;

import java.util.List;

/**
 * 用户反馈的服务接口
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
public interface FeedbackService {

    /**
     * 添加一个用户反馈
     * @param feedback 反馈实体
     * @return 影响条数
     */
    public int save(Feedback feedback);

    /**
     * 管理员处理用户反馈
     * @param id 反馈ID
     * @param remark 处理意见
     * @return 影响条数
     */
    public int process(int id, String remark);

    /**
     * 用户反馈分页列表
     * @param page 分页参数
     * @return 反馈列表
     */
    public List<Feedback> findAll(Page page);

    /**
     * 查看某个用户的反馈列表
     * @param phone 用户手机
     * @param page 分页信息
     * @return 反馈列表
     */
    public List<Feedback> findByUser(String phone, Page page);

    /**
     * 根据条件查询反馈列表
     * @param feedbackExample 查询条件
     * @return 反馈列表
     */
    public List<Feedback> findByCondition(FeedbackExample feedbackExample);
}
