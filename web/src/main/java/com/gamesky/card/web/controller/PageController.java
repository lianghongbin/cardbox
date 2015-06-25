package com.gamesky.card.web.controller;

import com.gamesky.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 6/25/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/page", produces = "application/json;charset=UTF-8")
public class PageController {

    @Autowired
    private CardService cardService;

    @RequestMapping("/score")
    public ModelAndView score() {
        return new ModelAndView("score");
    }
}
