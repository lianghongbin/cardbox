package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/system", produces="application/json;charset=UTF-8")
public class SystemController {

    @ResponseBody
    @RequestMapping(value = "/initial", method = RequestMethod.GET)
    public String initial(String device) {
        Random random = new Random(System.currentTimeMillis());
        int result = Math.abs(random.nextInt()) % 100000000;    //8位整数

        Map<String, String> param = new HashMap<>();
        param.put("visitor", "游客 " + result);
        return ResultGenerator.generate(param);
    }
}
