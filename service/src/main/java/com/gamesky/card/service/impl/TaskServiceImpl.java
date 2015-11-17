package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Task;
import com.gamesky.card.core.model.TaskExample;
import com.gamesky.card.dao.mapper.TaskMapper;
import com.gamesky.card.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * lianghongbin on 15/10/12.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    /**
     * 添加一个任务
     *
     * @param task 任务
     * @return 影响条数
     */
    @Override
    public int save(Task task) {
        task.setCreateTime(System.currentTimeMillis());
        return taskMapper.insert(task);
    }

    /**
     * 关闭一个任务
     *
     * @param id 任务ID
     * @return 影响条数
     */
    @Override
    public int close(int id) {
        Task task = taskMapper.selectByPrimaryKey(id);
        if (task == null) {
            return 0;
        }

        task.setValid(false);
        return taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     * 根据ID查找任务
     *
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public Task find(int id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有的任务
     *
     * @return 任务列表
     */
    @Override
    public List<Task> findAll() {
        TaskExample taskExample = new TaskExample();
        return taskMapper.selectByExample(taskExample);
    }

    /**
     * 查找所有的可用任务
     *
     * @return 任务列表
     */
    @Override
    public List<Task> findEnabled() {
        TaskExample taskExample = new TaskExample();
        taskExample.createCriteria().andValidEqualTo(true);
        return taskMapper.selectByExample(taskExample);
    }
}
