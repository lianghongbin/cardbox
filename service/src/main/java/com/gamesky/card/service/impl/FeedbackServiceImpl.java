package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Feedback;
import com.gamesky.card.core.model.FeedbackExample;
import com.gamesky.card.dao.mapper.FeedbackMapper;
import com.gamesky.card.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户反馈服务接口实现类
 * 该服务没有必要使用事务，因此就没有添加声明式事务注解
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 添加一个用户反馈
     *
     * @param feedback 反馈实体
     * @return 影响条数
     */
    @Override
    public int save(Feedback feedback) {
        feedback.setCreateTime(new Date());
        return feedbackMapper.insert(feedback);
    }

    /**
     * 管理员处理用户反馈
     *
     * @param id     反馈ID
     * @param remark 处理意见
     * @return 影响条数
     */
    @Override
    public int process(int id, String remark) {
        Feedback feedback = feedbackMapper.selectByPrimaryKey(id);
        if (feedback == null || feedback.getProcessed()) {
            return 0;
        }

        feedback.setRemark(remark);
        feedback.setProcessed(true);
        feedback.setProcessTime(new Date());
        return feedbackMapper.updateByPrimaryKey(feedback);
    }

    /**
     * 根据ID获取反馈实体
     *
     * @param id 反馈ID
     * @return 反馈实体
     */
    @Override
    public Feedback find(int id) {
        return feedbackMapper.selectByPrimaryKey(id);
    }

    /**
     * 用户反馈分页列表
     *
     * @param page 分页参数
     * @return 反馈列表
     */
    @Override
    public List<Feedback> findAll(Page page) {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setLimitOffset(page.getOffset());
        feedbackExample.setLimit(page.getSize());
        feedbackExample.setOrderByClause("id desc");
        return feedbackMapper.selectByExample(feedbackExample);
    }

    /**
     * 用户反馈数量
     *
     * @return 反馈数量
     */
    @Override
    public int findCount() {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.createCriteria().andIdGreaterThan(0);
        return feedbackMapper.countByExample(feedbackExample);
    }

    /**
     * 查看某个用户的反馈列表
     *
     * @param page 分页信息
     * @return 影响条数
     */
    @Override
    public List<Feedback> findByUser(String phone, Page page) {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setLimitOffset(page.getOffset());
        feedbackExample.setLimit(page.getSize());
        feedbackExample.setOrderByClause("id desc");
        feedbackExample.createCriteria().andPhoneEqualTo(phone);
        return feedbackMapper.selectByExample(feedbackExample);
    }

    /**
     * 根据用户手机查看用户反馈数量
     *
     * @param phone 用户手机
     * @return 反馈数量
     */
    @Override
    public int findCountByUser(String phone) {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.createCriteria().andPhoneEqualTo(phone);
        return feedbackMapper.countByExample(feedbackExample);
    }

    /**
     * 根据条件查询反馈列表
     *
     * @param feedbackExample 查询条件
     * @return 反馈列表
     */
    @Override
    public List<Feedback> findByCondition(FeedbackExample feedbackExample) {
        return feedbackMapper.selectByExample(feedbackExample);
    }

    /**
     * 根据条件查询反馈数量
     *
     * @param feedbackExample 查询条件
     * @return 反馈数量
     */
    @Override
    public int findCountByCondition(FeedbackExample feedbackExample) {
        return feedbackMapper.countByExample(feedbackExample);
    }
}
