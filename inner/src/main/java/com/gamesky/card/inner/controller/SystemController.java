package com.gamesky.card.inner.controller;

import com.gamesky.card.core.model.Setting;
import com.gamesky.card.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/system", produces = "text/plain;charset=UTF-8")
public class SystemController {

    @Autowired
    private SettingService settingService;

    @RequestMapping("/all")
    public ModelAndView all() {
        List<Setting> settings = settingService.findAll();
        ModelAndView modelAndView = new ModelAndView("system/all");

        modelAndView.addObject("settings", settings);

        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("system/add");
    }

    @ResponseBody
    @RequestMapping("/save")
    public String save(Setting setting) {
        Setting has = settingService.find(setting.getV());
        if (has != null) {
            return "该版本配置已经存在";
        }

        setting.setCreateTime(System.currentTimeMillis());
        int result = settingService.save(setting);
        return String.valueOf(result);
    }
}
