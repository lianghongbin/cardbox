package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Splash;
import com.gamesky.card.service.SplashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/splash", produces = "text/plain;charset=UTF-8")
public class SplashController {

    @Autowired
    private SplashService splashService;

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        return new ModelAndView("/splash/add");
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Splash splash) {
        splash.setCreateTime(System.currentTimeMillis());
        int result = splashService.save(splash);
        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public String remove(int id) {
        int result = splashService.remove(id);
        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(Splash splash) {
        int result = splashService.update(splash);
        return String.valueOf(result);
    }

    /**
     * 多个启动页的请求接口
     *
     * @return json
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView findAll(Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(15);
        }

        List<Splash> splashes = splashService.findAll(page);
        int count = splashService.findCount();
        page.setCount(count);

        PaginationData paginationData = new PaginationData(page, splashes);

        ModelAndView modelAndView = new ModelAndView("splash/all");
        modelAndView.addObject("paginationData", paginationData);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public String find() {
        Splash splash = splashService.findOne();
        return ResultGenerator.generate(splash);
    }
}
