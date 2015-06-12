package com.gamesky.card.inner.controller;

import com.gamesky.card.core.ErrorCode;
import com.gamesky.card.core.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 错误控制器
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/error", produces = "application/json;charset=UTF-8")
public class ErrorController {

    @ResponseBody
    @RequestMapping("/500")
    public String error500() {
        return ResultGenerator.generateError(ErrorCode.SERVER_ERROR_500, "服务器内部错误");
    }

    @ResponseBody
    @RequestMapping("/504")
    public String error504() {
        return ResultGenerator.generateError(ErrorCode.SERVER_ERROR_504, "错误的访问格式");
    }


    @ResponseBody
    @RequestMapping("/404")
    public String error404() {
        return ResultGenerator.generateError(ErrorCode.PAGE_NOT_FOUND, "请求页面不存在");
    }

    @ResponseBody
    @RequestMapping("/exception")
    public String exception() {
        return ResultGenerator.generateError(ErrorCode.EXCEPTION, "系统内部发生异常");
    }

    @ResponseBody
    @RequestMapping("/error")
    public String error() {
        return ResultGenerator.generateError(ErrorCode.ERROR, "未知错误");
    }
}
