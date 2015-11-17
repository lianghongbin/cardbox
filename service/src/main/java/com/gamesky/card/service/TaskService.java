package com.gamesky.card.service;

import com.gamesky.card.core.model.Task;

import java.util.List;

/**
 * lianghongbin on 15/10/12.
 */
public interface TaskService {

    /**
     * 添加一个任务
     * @param task 任务
     * @return 影响条数
     */
    int save(Task task);

    /**
     * 关闭一个任务
     * @param id 任务ID
     * @return 影响条数
     */
    int close(int id);

    /**
     * 根据ID查找任务
     * @param id 任务ID
     * @return 任务
     */
    Task find(int id);

    /**
     * 查找所有的任务
     * @return 任务列表
     */
    List<Task> findAll();

    /**
     * 查找所有的可用任务
     * @return 任务列表
     */
    List<Task> findEnabled();
}
