package com.gamesky.card.inner.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 错误控制器
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/error", produces = "application/json;charset=UTF-8")
public class ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ResponseBody
    @RequestMapping("/500")
    public String error500() {
        return ResultGenerator.generateError(ReturnCode.SERVER_ERROR_500);
    }

    @ResponseBody
    @RequestMapping("/504")
    public String error504() {
        return ResultGenerator.generateError(ReturnCode.SERVER_ERROR_504);
    }


    @ResponseBody
    @RequestMapping("/404")
    public String error404() {
        return ResultGenerator.generateError(ReturnCode.PAGE_NOT_FOUND);
    }

    @ResponseBody
    @RequestMapping("/400")
    public String error400() {
        return ResultGenerator.generateError(ReturnCode.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping("/exception")
    public String exception(Throwable e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        e.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        logger.error("system error or exception：{}", buffer.toString());
        return ResultGenerator.generate(ReturnCode.EXCEPTION.getCode(), buffer.toString());
    }
}
