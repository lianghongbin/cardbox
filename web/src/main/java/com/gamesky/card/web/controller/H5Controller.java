package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.H5Game;
import com.gamesky.card.service.H5GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
@Controller
@RequestMapping(value = "/1_0/h5", produces = "text/plain;charset=UTF-8")
public class H5Controller {

    @Autowired
    private H5GameService h5GameService;

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String recommend(Page page) {
        List<H5Game> games = h5GameService.findAllRecommend(page, null);
        int count = h5GameService.findRecommendCount();
        page.setCount(count);

        return ResultGenerator.generate(page, games);
    }
}
