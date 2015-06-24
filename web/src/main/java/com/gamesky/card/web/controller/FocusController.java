package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Focus;
import com.gamesky.card.service.FocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/focus", produces="application/json;charset=UTF-8")
public class FocusController {

    @Autowired
    private FocusService focusService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Focus focus) {
        focus.setCreateTime(System.currentTimeMillis());
        int result = focusService.save(focus);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("添加焦点图失败");
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String remove(int id) {
        int result = focusService.remove(id);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("删除焦点图失败");
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Focus focus) {
        int result = focusService.update(focus);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("更新焦点图失败");
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findAll() {
        List<Focus> focusList = focusService.findByEnable(true, new Page());
        return ResultGenerator.generate(focusList);
    }
}
