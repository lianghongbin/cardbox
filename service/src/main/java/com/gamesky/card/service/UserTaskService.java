package com.gamesky.card.service;

import com.gamesky.card.core.model.UserTask;

import java.util.List;

/**
 * lianghongbin on 15/10/12.
 */
public interface UserTaskService {

    /**
     * 领取一个任务
     * @param userTask 领取的任务
     * @return 影响条数
     */
    int save(UserTask userTask);

    /**
     * 完成一个任务
     * @param id 领取的任务ID
     * @return 影响条数
     */
    int done(int id);

    /**
     * 根据ID查找用户领取的任务
     * @param id 任务ID
     * @return 领取的任务
     */
    UserTask find(int id);

    /**
     * 根据用户电话查询某个任务是否已经领取
     * @param phone 电话
     * @param taskId 任务ID
     * @return 用户任务
     */
    UserTask find(String phone, int taskId);

    /**
     * 找出我领取的任务
     * @param phone 电话
     * @return 我领取的任务列表
     */
    List<UserTask> find(String phone);
}
