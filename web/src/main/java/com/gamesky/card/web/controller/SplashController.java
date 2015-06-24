package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Splash;
import com.gamesky.card.service.SplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/splash", produces="application/json;charset=UTF-8")
public class SplashController {

    @Autowired
    private SplashService splashService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Splash splash) {
        splash.setCreateTime(System.currentTimeMillis());
        int result = splashService.save(splash);
        if (result == 1) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("添加启动页失败");
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        int result = splashService.remove(id);
        if (result == 1) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("删除启动页失败");
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Splash splash) {
        int result = splashService.update(splash);
        if (result == 1) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("更新启动页失败");
    }

    /**
     * 多个启动页的请求接口
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findAll() {
        List<Splash> splashes = splashService.findEnabledAll();
        return ResultGenerator.generate(splashes);
    }

    @ResponseBody
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public String find() {
        Splash splash = splashService.findOne();
        return ResultGenerator.generate(splash);
    }
}
