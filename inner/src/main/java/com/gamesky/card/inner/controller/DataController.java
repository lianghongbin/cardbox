package com.gamesky.card.inner.controller;

import com.gamesky.card.core.model.Download;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2015/7/22.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/data", produces = "text/plain;charset=UTF-8")
public class DataController {

    @Autowired
    private UserOutput userOutput;
    @Autowired
    private DownloadOutput downloadOutput;
    @Autowired
    private CardOutput cardOutput;

    @RequestMapping("/")
    public ModelAndView index() {
        return new ModelAndView("data/out");
    }

    @RequestMapping("/user")
    public String user(HttpServletRequest request, HttpServletResponse response) {
        return userOutput.out(request, response);
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response) {
        return downloadOutput.out(request, response);
    }

    @RequestMapping("/card")
    public String card(HttpServletRequest request, HttpServletResponse response) {
        return cardOutput.out(request, response);
    }
}
