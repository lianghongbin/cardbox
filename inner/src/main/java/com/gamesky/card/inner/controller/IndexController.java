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

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/top")
    public ModelAndView top() {
        return new ModelAndView("top");
    }

    @RequestMapping("/left")
    public ModelAndView left() {
        return new ModelAndView("left");
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
