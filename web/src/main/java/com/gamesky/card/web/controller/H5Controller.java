package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.*;
import com.gamesky.card.service.H5GameService;
import com.gamesky.card.service.H5SubscribeService;
import com.gamesky.card.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
@Controller
@RequestMapping(value = "/1_0/h5", produces = "text/plain;charset=UTF-8")
public class H5Controller {

    @Autowired
    private UserService userService;
    @Autowired
    private H5GameService h5GameService;
    @Autowired
    private H5SubscribeService h5SubscribeService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String recommend(Page page) {
        List<H5Game> games = h5GameService.findAllRecommend(page, null);
        int count = h5GameService.findRecommendCount();
        page.setCount(count);

        return ResultGenerator.generate(page, games);
    }

    //下面是H5游戏订阅相关的接口提供

    @ResponseBody
    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public String add(String phone) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResultGenerator.generateError("该用户不存在！");
        }

        int result = h5SubscribeService.save(phone);
        if (result == 1) {
            return ResultGenerator.generate(0, "H5游戏订阅成功！");
        }

        return ResultGenerator.generateError("H5游戏订阅失败！");
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(String phone) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResultGenerator.generateError("该用户不存在！");
        }

        int result = h5SubscribeService.remove(phone);
        if (result == 1) {
            return ResultGenerator.generate(0, "取消订阅成功！");
        }

        return ResultGenerator.generateError("取消订阅失败！");
    }

    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(String phone) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResultGenerator.generateError("该用户不存在！");
        }

        H5Subscribe subscribe = h5SubscribeService.find(phone);

        return ResultGenerator.generate(subscribe);
    }
}
