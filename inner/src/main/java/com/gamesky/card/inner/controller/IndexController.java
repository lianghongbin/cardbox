package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.model.Admin;
import com.gamesky.card.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created on 6/17/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(produces="text/plain;charset=UTF-8")
public class IndexController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/top")
    public ModelAndView top(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute(Constants.INNER_LOGIN_SESSION_KEY);
        Admin admin = adminService.findByPhone(phone);

        return new ModelAndView("top", "admin", admin);
    }

    @RequestMapping("/left")
    public ModelAndView left(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute(Constants.INNER_LOGIN_SESSION_KEY);
        Admin admin = adminService.findByPhone(phone);

        return new ModelAndView("left", "admin", admin);
    }

    @RequestMapping("/main")
    public ModelAndView main() {
        return new ModelAndView("main");
    }

    @RequestMapping("/org")
    public ModelAndView org() {
        return new ModelAndView("design");
    }
}
