package com.gamesky.card.inner.controller;

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
@RequestMapping(value = "/feedback", produces = "text/plain;charset=UTF-8")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Feedback feedback) {
        feedback.setCreateTime(System.currentTimeMillis());
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
        return String.valueOf(result);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView find(int id) {
        Feedback feedback = feedbackService.find(id);
        ModelAndView modelAndView = new ModelAndView("feedback/view");
        modelAndView.addObject("feedback", feedback);

        return modelAndView;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView findAll(Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(15);
        }

        List<Feedback> feedbacks = feedbackService.findAll(page);
        int count = feedbackService.findCount();
        page.setCount(count);

        PaginationData paginationData = new PaginationData(page, feedbacks);
        ModelAndView modelAndView = new ModelAndView("feedback/all");
        modelAndView.addObject("paginationData", paginationData);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/findbyuser", method = RequestMethod.GET)
    public String findByUser(String phone, Page page) {
        List<Feedback> feedbacks = feedbackService.findByUser(phone, page);
        int count = feedbackService.findCountByUser(phone);
        page.setCount(count);
        return ResultGenerator.generate(page, feedbacks);
    }

    @ResponseBody
    @RequestMapping("/remove")
    public String remove(int id) {
        int result = feedbackService.remove(id);
        return String.valueOf(result);
    }
}
