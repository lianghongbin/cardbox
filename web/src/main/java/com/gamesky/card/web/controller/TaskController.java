package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Task;
import com.gamesky.card.core.model.UserTask;
import com.gamesky.card.service.TaskService;
import com.gamesky.card.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * lianghongbin on 15/10/14.
 */
@Controller
@RequestMapping(value = "/1_4/task", produces="application/json;charset=UTF-8")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserTaskService userTaskService;

    /**
     * 任务列表
     * @return 任务列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findTasks() {
        List<Task> tasks = taskService.findEnabled();
        return ResultGenerator.generate(tasks);
    }

    /**
     * 我的任务列表(任务有是否完成状态)
     * @param phone 我的手机号
     * @return 任务列表
     */
    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String findByPhone(String phone) {
        List<UserTask> userTasks = userTaskService.find(phone);
        return ResultGenerator.generate(userTasks);
    }

    /**
     * 去完成某个未完成的任务
     * @param userTaskId 我的任务ID
     * @return 完成结果数据
     */
    @ResponseBody
    @RequestMapping(value = "/done", method = RequestMethod.POST)
    public String done(String phone, int userTaskId) {
        UserTask userTask = userTaskService.find(userTaskId);
        if (userTask == null || !userTask.getPhone().equals(phone)) {
            return ResultGenerator.generateError("此任务不属于该用户");
        }

        int result = userTaskService.done(userTaskId);
        if (result == 0) {
            return ResultGenerator.generateError("完成任务异常");
        }

        return ResultGenerator.generate();
    }

    /**
     * 领取任务
     * @param phone 电话
     * @param taskId 任务ID
     * @return Json
     */
    @ResponseBody
    @RequestMapping(value = "/take", method = RequestMethod.GET)
    public String take(String phone, int taskId) {
        UserTask userTask = userTaskService.find(phone, taskId);
        if (userTask != null) {
            return ResultGenerator.generate();
        }

        userTask = new UserTask();
        userTask.setPhone(phone);
        userTask.setTaskId(taskId);
        userTask.setCreateTime(System.currentTimeMillis());
        int result = userTaskService.save(userTask);
        if (result == 1) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError();
    }
}
