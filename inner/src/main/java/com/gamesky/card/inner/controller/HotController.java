package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.Platform;
import com.gamesky.card.core.model.Hot;
import com.gamesky.card.service.HotService;
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
@RequestMapping(value = "/hot", produces = "text/plain;charset=UTF-8")
public class HotController {

    @Autowired
    private HotService hotService;

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        return new ModelAndView("/hot/add");
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Hot hot) {
        int result = hotService.save(hot);
        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public String remove(int id) {
        int result = hotService.remove(id);
        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public String update(Hot hot) {
        int result = hotService.update(hot);
        return String.valueOf(result);
    }

    /**
     * 多个启动页的请求接口
     *
     * @return json
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView findAll(Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(15);
        }

        List<Hot> hots = hotService.findAll(Platform.ALL.name(), page);
        int count = hotService.findCount(Platform.ALL.name());
        page.setCount(count);

        PaginationData paginationData = new PaginationData(page, hots);

        ModelAndView modelAndView = new ModelAndView("hot/all");
        modelAndView.addObject("paginationData", paginationData);
        return modelAndView;
    }
}
