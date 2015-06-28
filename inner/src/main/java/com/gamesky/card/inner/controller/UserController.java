package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.User;
import com.gamesky.card.core.model.UserExample;
import com.gamesky.card.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 选题控制器
 * Created on 3/13/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/user", produces = "text/plain;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/all")
    public ModelAndView all(String phone, String startDate, String endDate, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(20);
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNoneBlank(phone)) {
            criteria.andPhoneEqualTo(phone);
        } else {
            if (StringUtils.isNoneBlank(startDate)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date = simpleDateFormat.parse(startDate);
                    criteria.andCreateTimeGreaterThanOrEqualTo(date.getTime());
                } catch (ParseException ignored) {
                }
            }

            if (StringUtils.isNoneBlank(endDate)) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date date = simpleDateFormat.parse(endDate);
                    criteria.andCreateTimeLessThan(date.getTime());
                } catch (ParseException ignored) {
                }
            }
        }

        userExample.setLimitOffset(page.getOffset());
        userExample.setLimit(page.getPagesize());
        userExample.setOrderByClause("id desc");

        List<User> users = userService.findByCondition(userExample);
        int count = userService.findCountByCondition(userExample);
        page.setCount(count);

        PaginationData paginationData = new PaginationData(page, users);
        ModelAndView modelAndView = new ModelAndView("user/all");
        modelAndView.addObject("paginationData", paginationData);

        return modelAndView;
    }

    @RequestMapping("/modify")
    public ModelAndView modify(String phone) {
        User user = userService.findByPhone(phone);
        return new ModelAndView("user/score", "user", user);
    }

    @ResponseBody
    @RequestMapping("/score")
    public String score(String phone, int score) {
        User user = userService.findByPhone(phone);
        if (user == null) {
            return "该用户不存在";
        }
        user.setScore(user.getScore() + score);
        int result = userService.update(user);

        return String.valueOf(result);
    }
}
