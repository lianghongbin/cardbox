package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Hot;
import com.gamesky.card.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 7/2/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/hot", produces = "application/json;charset=UTF-8")
public class HotController {

    @Autowired
    private HotService hotService;

    @ResponseBody
    @RequestMapping("/list")
    public String list(String platform, int count) {
        Page page = new Page(count, 1);

        List<Hot> hots = hotService.findAll(platform, page);
        return ResultGenerator.generate(hots);
    }
}
