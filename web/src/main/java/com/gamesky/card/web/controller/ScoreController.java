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
    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public String daily(String phone) {
        int score = scoreService.dailySign(phone);
        if (score > 0) {
            return ResultGenerator.generate(ReturnCode.SUCCESS.getCode(), String.valueOf(score));
        }

        return ResultGenerator.generateError("当日已经领取或者领取积分失败");
    }

    @ResponseBody
    @RequestMapping(value = "/weixin", method = RequestMethod.GET)
    public String weiXin(String phone) {
        int score = scoreService.weixinShare(phone);
        if (score > 0) {
            return ResultGenerator.generate(ReturnCode.SUCCESS.getCode(), String.valueOf(score));
        }

        return ResultGenerator.generateError("当日微信已经分享或者领取积分失败");
    }

    @ResponseBody
    @RequestMapping(value = "/qq", method = RequestMethod.GET)
    public String qq(String phone) {
        int score = scoreService.qqShare(phone);
        if (score > 0) {
            return ResultGenerator.generate(ReturnCode.SUCCESS.getCode(), String.valueOf(score));
        }

        return ResultGenerator.generateError("当日QQ已经分享或者领取积分失败");
    }
}
