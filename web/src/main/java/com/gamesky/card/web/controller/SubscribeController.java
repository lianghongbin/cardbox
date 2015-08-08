package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.Subscribe;
import com.gamesky.card.core.model.User;
import com.gamesky.card.service.GameService;
import com.gamesky.card.service.SubscribeService;
import com.gamesky.card.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * LHB on 8/7/15.
 */
@Controller
@RequestMapping(value = "/1_0/subscribe", produces = "application/json;charset=UTF-8")
public class SubscribeController {

    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    @Autowired
    private SubscribeService subscribeService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(String phone, int gameId) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResultGenerator.generateError("该用户不存在！");
        }

        Game game = gameService.find(gameId);
        if (game == null) {
            return ResultGenerator.generateError("该游戏不存在！");
        }

        Subscribe subscribe = new Subscribe();
        subscribe.setGameId(gameId);
        subscribe.setPhone(phone);
        subscribe.setGameName(game.getName());
        subscribe.setDeleted(false);
        subscribe.setCreateTime(System.currentTimeMillis());

        int result = subscribeService.save(subscribe);
        if (result == 1) {
            return ResultGenerator.generate(0, "游戏订阅成功！");
        }

        return ResultGenerator.generateError("游戏订阅失败！");
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(String phone, int gameId) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResultGenerator.generateError("该用户不存在！");
        }

        Game game = gameService.find(gameId);
        if (game == null) {
            return ResultGenerator.generateError("该游戏不存在！");
        }

        Subscribe subscribe = subscribeService.find(phone, gameId);
        if (subscribe == null) {
            return ResultGenerator.generateError("你没有订阅该游戏！");
        }

        int result = subscribeService.delete(subscribe.getId());
        if (result == 1) {
            return ResultGenerator.generate(0, "取消订阅成功！");
        }

        return ResultGenerator.generateError("取消订阅失败！");
    }

    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(String phone, int gameId) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResultGenerator.generateError("该用户不存在！");
        }

        Game game = gameService.find(gameId);
        if (game == null) {
            return ResultGenerator.generateError("该游戏不存在！");
        }

        Subscribe subscribe = subscribeService.find(phone, gameId);

        return ResultGenerator.generate(subscribe);
    }

    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String my(String phone, Page page) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return ResultGenerator.generateError("该用户不存在！");
        }

        List<Subscribe> subscribes = subscribeService.find(phone, page);
        if (subscribes != null) {
            page.setCount(subscribes.size());
        }

        return ResultGenerator.generate(page, subscribes);
    }
}
