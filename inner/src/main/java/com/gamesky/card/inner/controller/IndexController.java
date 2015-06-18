package com.gamesky.card.inner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 6/17/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(produces="text/plain;charset=UTF-8")
public class IndexController {

    @RequestMapping("/test")
    public ModelAndView index() {
        return new ModelAndView("test");
    }
}
