package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Sign;
import com.gamesky.card.service.BeanUtils;
import com.gamesky.card.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * lianghongbin on 15/10/21.
 */
@Controller
@RequestMapping(value = "/1_4/sign", produces="application/json;charset=UTF-8")
public class SignController {

    @Autowired
    private SignService signService;

    /**
     * 获取用户的签到记录,根据该数据显示应该签到的步骤,如果sign.getTimes=1则是应该做第一次签到,Times等于几就应该做第几次签到
     * @param phone 用户手机
     * @return Json数据
     */
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String mySign(String phone) {
        Sign sign = signService.findByPhone(phone);
        int total = signService.findCount();
        Map<String, Object> datas = new HashMap<>();
        try {
            datas.put("sign", BeanUtils.beanToMap(sign));
        } catch (Exception ignored) {
        }

        datas.put("total", total);

        return ResultGenerator.generate(datas);
    }

    /**
     * 用户签到
     * @param phone 签到用户手机
     * @return Json数据
     */
    @ResponseBody
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public String sign(String phone) {
        int result = signService.sign(phone);
        if (result == 1) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError();
    }
}
