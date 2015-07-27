package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.exceptions.MarshalException;
import com.gamesky.card.core.exceptions.SmsSenderException;
import com.gamesky.card.core.model.Admin;
import com.gamesky.card.service.AdminService;
import com.gamesky.card.service.CheckCodeService;
import com.gamesky.card.service.CodeGenerator;
import com.gamesky.card.service.key.CheckCodeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created on 2015/7/25.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(produces = "text/plain;charset=UTF-8")
public class LoginController {

    @Autowired
    private CodeGenerator generator;
    @Autowired
    private CheckCodeService checkCodeService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private Marshaller<Keyable, String> marshaller;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView show() {
        return new ModelAndView("login");
    }

    @ResponseBody
    @RequestMapping(value = "/login/check", method = RequestMethod.POST)
    public String login(String phone, String checkCode, HttpServletRequest request) {
        Admin admin = adminService.findByPhone(phone);
        if (admin == null || admin.getLocked()) {
            return "请确认，你是否有管理权限！";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());
        logger.info("手机号 {} 在 {} 时间登录", phone, now);

        try {
            String code = marshaller.unmarshal(new CheckCodeKey(phone, 60));
            if (code == null) {
                return "验证码不存在";
            }

            if (!checkCode.equalsIgnoreCase(code)) {
                return "验证码错误";
            }
        } catch (MarshalException e) {
            logger.error("获取验证码失败");
            return "获取验证码失败";
        }

        HttpSession session = request.getSession();
        session.setAttribute(Constants.INNER_LOGIN_KEY, phone);
        //adminService.login(phone);

        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(String phone, HttpServletRequest request) {

        try {
            marshaller.marshal(new CheckCodeKey(phone, 0), phone);
        } catch (MarshalException e) {
            logger.error("验证码删除失败");
            return "登录注销失败！";
        }

        HttpSession session = request.getSession();
        session.setAttribute(Constants.INNER_LOGIN_KEY, "");

        return "";
    }

    /**
     * 生成验证码
     * @param phone 手机号
     * @return 返回生成的难码
     */
    @ResponseBody
    @RequestMapping(value = "/checkcode", method = RequestMethod.GET)
    public String checkCode(final String phone) {

        Admin admin = adminService.findByPhone(phone);
        if (admin == null || admin.getLocked()) {
            return "请确认，你是否有管理权限！";
        }

        String code = generator.generate();

        try {
            checkCodeService.send(phone, code);
        } catch (MarshalException e) {
            return "验证码存储失败！";
        } catch (SmsSenderException e) {
            return "短信发送失败";
        }

        return "";
    }
}
