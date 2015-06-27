package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Feedback;
import com.gamesky.card.service.FeedbackService;
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
@RequestMapping(value = "/1_0/feedback", produces = "application/json;charset=UTF-8")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public ModelAndView page() {
        return new ModelAndView("feedback");
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Feedback feedback) {
        feedback.setCreateTime(System.currentTimeMillis());
        feedback.setProcessed(false);
        int result = feedbackService.save(feedback);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("添加反馈失败");
    }

    @ResponseBody
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String process(int id, String remark) {
        int result = feedbackService.process(id, remark);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("处理反馈失败");
    }

    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(int id) {
        Feedback feedback = feedbackService.find(id);
        return ResultGenerator.generate(feedback);
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findAll(Page page) {
        List<Feedback> feedbacks = feedbackService.findAll(page);
        int count = feedbackService.findCount();
        page.setCount(count);
        return ResultGenerator.generate(page, feedbacks);
    }

    @ResponseBody
    @RequestMapping(value = "/findbyuser", method = RequestMethod.GET)
    public String findByUser(String phone, Page page) {
        List<Feedback> feedbacks = feedbackService.findByUser(phone, page);
        int count = feedbackService.findCountByUser(phone);
        page.setCount(count);
        return ResultGenerator.generate(page, feedbacks);
    }
}
