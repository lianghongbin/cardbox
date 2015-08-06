package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.Platform;
import com.gamesky.card.core.model.H5Game;
import com.gamesky.card.core.model.H5GameExample;
import com.gamesky.card.service.H5GameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lianghongbin on 15/8/6.
 */
@Controller
@RequestMapping(value = "/h5", produces = "text/plain;charset=UTF-8")
public class H5Controller {

    @Autowired
    private H5GameService h5GameService;
    @Autowired
    private H5GameSchedule h5GameSchedule;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/all")
    public ModelAndView all(Integer recommend, Integer aid, String title, String platform, String orderClause, Page page) {

        H5GameExample h5GameExample = new H5GameExample();
        H5GameExample.Criteria criteria = h5GameExample.createCriteria();
        if (recommend != null && recommend != 2) {
            boolean flag = recommend == 1;
            criteria.andRecommendEqualTo(flag);
        }
        if (aid != null) {
            criteria.andAidEqualTo(aid);
        }
        if (StringUtils.isNoneBlank(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (StringUtils.isNoneBlank(platform) && !platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>(2);
            platforms.add(Platform.android.name());
            platforms.add(Platform.iOS.name());
            criteria.andPlatformIn(platforms);
        }

        if (StringUtils.isNoneBlank(orderClause)) {
            h5GameExample.setOrderByClause(orderClause);
        }
        else {
            h5GameExample.setOrderByClause("sort asc, update_time desc");
        }

        page.setPagesize(20);
        page.setCount(h5GameService.findCountByCondition(h5GameExample));
        h5GameExample.setLimitOffset(page.getOffset());
        h5GameExample.setLimit(page.getPagesize());

        List<H5Game> h5Games = h5GameService.findByCondition(h5GameExample);
        Map params = new HashMap<>();
        params.put("orderClause", orderClause);
        params.put("recommend", recommend);
        params.put("aid", aid);
        params.put("title", title);
        params.put("platform", platform);

        PaginationData paginationData = new PaginationData(page, params, h5Games);
        ModelAndView modelAndView = new ModelAndView("h5/all");
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.POST)
    public String recommend(int aid) {
        int result = h5GameService.recommend(aid);

        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public String sort(int aid, int sort) {
        int result = h5GameService.sort(aid, sort);

        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public String refresh() {
        h5GameSchedule.fetch();
        return "OK";
    }
}
