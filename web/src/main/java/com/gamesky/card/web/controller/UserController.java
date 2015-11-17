package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import com.gamesky.card.core.exceptions.CheckCodeInvalidException;
import com.gamesky.card.core.exceptions.CheckCodeWrongException;
import com.gamesky.card.core.exceptions.MarshalException;
import com.gamesky.card.core.exceptions.SmsSenderException;
import com.gamesky.card.core.model.Invite;
import com.gamesky.card.core.model.User;
import com.gamesky.card.core.utils.CodeUtils;
import com.gamesky.card.core.utils.MD5Utils;
import com.gamesky.card.service.CheckCodeService;
import com.gamesky.card.service.CodeGenerator;
import com.gamesky.card.service.InviteService;
import com.gamesky.card.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 选题控制器
 * Created on 3/13/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/user", produces="application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CodeGenerator generator;
    @Autowired
    private CheckCodeService checkCodeService;
    @Autowired
    private PhotoController photoController;
    @Autowired
    private InviteService inviteService;

    /**
     * 系统登录
     * @param phone 登录手机号
     * @param checkCode 登录验证码
     * @return 返回text结果
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String phone, String device, String checkCode) {
        User user;
        try {
            user = userService.login(phone, device, checkCode);
        } catch (CheckCodeInvalidException e) {
            return ResultGenerator.generateError("验证码已过期");
        } catch (CheckCodeWrongException e) {
            return ResultGenerator.generateError("验证码错误");
        }

        if (user == null) {
            return ResultGenerator.generateError("登录发生错误");
        }

        Map<String, User> params = new HashMap<>();
        params.put("user", user);
        return ResultGenerator.generate(params);
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(String phone) {
        boolean result = userService.logout(phone);
        if (result) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("注销出错");
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
            return ResultGenerator.generate();
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
            checkCodeService.send(phone, code);
        } catch (MarshalException e) {
            return ResultGenerator.generateError(ReturnCode.MARSHAL_ERROR);
        } catch (SmsSenderException e) {
            return ResultGenerator.generateError(ReturnCode.SMS_SEND_ERROR);
        }

        Map<String,String> data = new HashMap<>();
        data.put("checkCode", code);
        return ResultGenerator.generate(data);
    }

    /**
     * 根据用户手机，查看用户信息
     * @param phone 手机号
     * @return 用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(String phone) {
        User user = userService.findByPhone(phone);

        return ResultGenerator.generate(user);
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, String checkCode, String invateCode) {
        try {
            String code = checkCodeService.find(user.getPhone());
            if (StringUtils.isBlank(code)) {
                return ResultGenerator.generateError("验证码已过期!");
            }

            if (code.equalsIgnoreCase(checkCode)) {
                return ResultGenerator.generateError("验证码错误!");
            }
        } catch (MarshalException e) {
            return ResultGenerator.generateError("验证码读取失败!");
        }

        if (StringUtils.isNoneBlank(invateCode)) {
            String phone = CodeUtils.toPhone(invateCode);

            Invite invite = new Invite();   //记录推荐记录
            invite.setInvitedPhone(user.getPhone());
            invite.setPhone(phone);
            inviteService.save(invite);
        }

        user.setPassword(MD5Utils.toString(user.getPassword()));
        user.setCreateTime(System.currentTimeMillis());

        int result = userService.save(user);
        if (result == 1) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("注册失败!");
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        if (StringUtils.isNoneBlank(user.getPassword()) && user.getPassword().length() < 32) {
            user.setPassword(MD5Utils.toString(user.getPassword()));
        }

        int result = userService.update(user);
        if (result == 1) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("修改信息失败!");
    }

    @ResponseBody
    @RequestMapping(value = "/uploadHeader", method = RequestMethod.POST)
    public String uploadHeader(HttpServletRequest request, String phone) {
        String headerUrl = photoController.single(request);
        int result = userService.uploadHeader(phone, headerUrl);

        if (StringUtils.isNoneBlank(headerUrl) && result > 0) {
            return ResultGenerator.generate(headerUrl);
        }

        return ResultGenerator.generateError("头像上传失败!");
    }
}
