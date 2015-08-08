package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.model.Admin;
import com.gamesky.card.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created on 2015/7/25.
 *
 * @Author lianghongbin
 */
@RequestMapping(value = "/admin", produces = "text/plain;charset=UTF-8")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("admin/add");
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Admin admin) {
        admin.setLocked(false);
        admin.setTop(false);
        int result = adminService.save(admin);

        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/lock", method = RequestMethod.GET)
    public String lock(int id) {
        int result = adminService.lock(id);

        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/unlock", method = RequestMethod.GET)
    public String unlock(int id) {
        int result = adminService.unlock(id);

        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(int id) {
        int result = adminService.remove(id);
        return String.valueOf(result);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView all(HttpServletRequest request) {
        List<Admin> admins = adminService.findAll();
        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute(Constants.INNER_LOGIN_SESSION_KEY);

        ModelAndView modelAndView = new ModelAndView("admin/all", "admins", admins);
        modelAndView.addObject("phone", phone);

        return modelAndView;
    }
}
