package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Setting;
import com.gamesky.card.service.SettingService;
import com.gamesky.card.service.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/system", produces = "application/json;charset=UTF-8")
public class SystemController {

    @Autowired
    private SettingService settingService;

    @ResponseBody
    @RequestMapping(value = "/initial", method = RequestMethod.GET)
    public String initial(String device) {
        Random random = new Random(System.currentTimeMillis());
        int result = Math.abs(random.nextInt()) % 100000000;    //8位整数

        Map<String, String> param = new HashMap<>();
        param.put("visitor", "游客 " + result);
        return ResultGenerator.generate(param);
    }

    @ResponseBody
    @RequestMapping(value = "/announce", method = RequestMethod.GET)
    public String announce() {
        Setting setting = settingService.find("1_0");
        if (setting == null) {
            return ResultGenerator.generate("");
        }
        return ResultGenerator.generate(setting.getAnnounce());
    }

    @RequestMapping(value = "/us", method = RequestMethod.GET)
    public ModelAndView us() {
        return new ModelAndView("aboutus");
    }

    @ResponseBody
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public String token(HttpServletRequest request) {
        String token = TokenGenerator.generate(request);
        return ResultGenerator.generate(token);
    }
}
