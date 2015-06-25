package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.User;
import com.gamesky.card.service.ScoreService;
import com.gamesky.card.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/score", produces = "application/json;charset=UTF-8")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/acquire")
    public String acquire(String phone, String type) {
        int score = scoreService.acquire(phone, type);
        User user = userService.findByPhone(phone);
        int total = 0;
        Map map = new HashMap<>();
        if (user != null) {
            total = user.getScore();
        }

        map.put("score", score);
        map.put("total", total);

        return ResultGenerator.generate(map);
    }
}
