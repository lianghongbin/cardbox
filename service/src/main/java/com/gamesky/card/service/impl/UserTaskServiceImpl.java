package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Task;
import com.gamesky.card.core.model.UserTask;
import com.gamesky.card.core.model.UserTaskExample;
import com.gamesky.card.dao.mapper.UserTaskMapper;
import com.gamesky.card.service.TaskService;
import com.gamesky.card.service.UserService;
import com.gamesky.card.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * lianghongbin on 15/10/12.
 */
@Service
@Transactional
public class UserTaskServiceImpl implements UserTaskService {

    @Autowired
    private UserTaskMapper userTaskMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    /**
     * 领取一个任务
     *
     * @param userTask 领取的任务
     * @return 影响条数
     */
    @Override
    public int save(UserTask userTask) {
        UserTask tmp = find(userTask.getPhone(), userTask.getTaskId());
        if (tmp != null) {
            return 0;
        }

        userTask.setCreateTime(System.currentTimeMillis());

        return userTaskMapper.insert(userTask);
    }

    /**
     * 完成一个任务
     *
     * @param id 领取的任务ID
     * @return 影响条数
     */
    @Override
    public int done(int id) {
        UserTask userTask = userTaskMapper.selectByPrimaryKey(id);
        if (userTask == null) {
            return 0;
        }

        Task task = taskService.find(id);
        if (!task.getValid()) {
            return 0;
        }

        userService.addScore(userTask.getPhone(), task.getScore());

        UserTaskExample userTaskExample = new UserTaskExample();
        userTaskExample.createCriteria().andIdEqualTo(id);
        userTask.setDone(true);
        userTask.setDoneTime(System.currentTimeMillis());
        return userTaskMapper.updateByPrimaryKeySelective(userTask);
    }

    /**
     * 根据ID查找用户领取的任务
     *
     * @param id 任务ID
     * @return 领取的任务
     */
    @Override
    public UserTask find(int id) {
        return userTaskMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据用户电话查询某个任务是否已经领取
     *
     * @param phone  电话
     * @param taskId 任务ID
     * @return 用户任务
     */
    @Override
    public UserTask find(String phone, int taskId) {
        UserTaskExample userTaskExample = new UserTaskExample();
        userTaskExample.createCriteria().andPhoneEqualTo(phone).andTaskIdEqualTo(taskId);
        List<UserTask> userTasks = userTaskMapper.selectByExample(userTaskExample);
        if (userTasks == null || userTasks.size() == 0) {
            return null;
        }

        return userTasks.get(0);
    }

    /**
     * 找出我领取的任务
     *
     * @param phone 电话
     * @return 我领取的任务列表
     */
    @Override
    public List<UserTask> find(String phone) {
        UserTaskExample userTaskExample = new UserTaskExample();
        userTaskExample.createCriteria().andPhoneEqualTo(phone);

        return userTaskMapper.selectByExample(userTaskExample);
    }
}
