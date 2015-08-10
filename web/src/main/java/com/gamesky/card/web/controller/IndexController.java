package com.gamesky.card.web.controller;

import com.gamesky.card.core.model.Setting;
import com.gamesky.card.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * lianghongbin on 15/8/10.
 */
@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class IndexController {

    @Autowired
    private SettingService settingService;

    @RequestMapping("/")
    public ModelAndView index() {

        Setting setting = settingService.find("1_0");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("android", setting.getAndroid());
        modelAndView.addObject("ios", setting.getIos());

        return modelAndView;
    }
}
