package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import com.gamesky.card.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping("/acquire")
    public String acquire(String phone, String type) {
        int score = scoreService.acquire(phone, type);
        if (score > 0) {
            return ResultGenerator.generate(ReturnCode.SUCCESS.getCode(), String.valueOf(score));
        }

        return ResultGenerator.generateError("已经领取过积分或者获取积分失败");
    }
}
