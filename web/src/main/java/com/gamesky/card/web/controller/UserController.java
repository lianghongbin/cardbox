package com.gamesky.card.web.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.exceptions.CheckCodeInvalidException;
import com.gamesky.card.core.exceptions.CheckCodeWrongException;
import com.gamesky.card.core.exceptions.MarshalException;
import com.gamesky.card.service.CodeGenerator;
import com.gamesky.card.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 选题控制器
 * Created on 3/13/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/user", produces="application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeGenerator generator;
    @Autowired
    @Qualifier("checkCodeMarshaller")
    private Marshaller<Keyable, Serializable> marshaller;

    /**
     * 系统登录
     * @param phone 登录手机号
     * @param checkCode 登录验证码
     * @return 返回text结果
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String phone, String checkCode) {
        boolean result;
        try {
            result = userService.login(phone, checkCode);
        } catch (CheckCodeInvalidException e) {
            return ResultGenerator.generateError("验证码已过期");
        } catch (CheckCodeWrongException e) {
            return ResultGenerator.generateError("验证码错误");
        }

        if (!result) {
            return ResultGenerator.generateError("登录发生错误");
        }

        return ResultGenerator.generate();
    }

    /**
     * 判断是否是在登录状态
     * @param phone 登录手机
     * @return 返回text结果
     */
    @ResponseBody
    @RequestMapping(value = "/islogin", method = RequestMethod.GET)
    public String isLogin(String phone) {
        if(userService.isLogin(phone)){
            return "";
        }
        return ResultGenerator.generateError("该手机号未登录");
    }

    /**
     * 生成验证码
     * @param phone 手机号
     * @return 返回生成的难码
     */
    @ResponseBody
    @RequestMapping(value = "/checkcode", method = RequestMethod.GET)
    public String checkCode(final String phone) {
        String code = generator.generate();
        try {
            marshaller.marshal(new Keyable() {
                @Override
                public String k() {
                    return Constants.CHECK_CODE_KEY_PREFIX + phone;
                }
            }, code);
        } catch (MarshalException e) {
            ResultGenerator.generateError("生成验证码失败");
        }

        Map<String,String> data = new HashMap<>();
        data.put("checkCode", code);
        return ResultGenerator.generate(data);
    }
}
