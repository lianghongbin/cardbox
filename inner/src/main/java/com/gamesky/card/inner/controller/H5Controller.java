package com.gamesky.card.inner.controller;

import com.gamesky.card.core.*;
import com.gamesky.card.core.model.*;
import com.gamesky.card.service.H5GameService;
import com.gamesky.card.service.H5SubscribeService;
import com.gamesky.card.service.PushService;
import com.gamesky.card.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * lianghongbin on 15/8/6.
 */
@Controller
@RequestMapping(value = "/h5", produces = "text/plain;charset=UTF-8")
public class H5Controller {

    @Autowired
    private PushService<PushPayload> pushService;
    @Autowired
    private UserService userService;
    @Autowired
    private H5GameService h5GameService;
    @Autowired
    private H5GameSchedule h5GameSchedule;
    @Autowired
    private H5SubscribeService h5SubscribeService;
    private static final Logger logger = LoggerFactory.getLogger(H5Controller.class);

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/all")
    public ModelAndView all(Integer recommend, Integer aid, String title, String platform, String type, Page page) {

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

        if (StringUtils.isNoneBlank(type)) {
            criteria.andTypeEqualTo(type);
        }

        h5GameExample.setOrderByClause("sort asc, update_time desc");

        page.setPagesize(20);
        page.setCount(h5GameService.findCountByCondition(h5GameExample));
        h5GameExample.setLimitOffset(page.getOffset());
        h5GameExample.setLimit(page.getPagesize());

        List<H5Game> h5Games = h5GameService.findByCondition(h5GameExample);
        Map params = new HashMap<>();
        params.put("type", type);
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
    @RequestMapping(value = "/unrecommend", method = RequestMethod.POST)
    public String unrecommend(int aid) {
        int result = h5GameService.unrecommend(aid);

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


    //下面是H5游戏订阅相关的接口提供
    @RequestMapping("/subscribe")
    public ModelAndView h5subscribe(String phone, String start, String end, Page page) {
        H5SubscribeExample h5SubscribeExample = new H5SubscribeExample();
        H5SubscribeExample.Criteria criteria = h5SubscribeExample.createCriteria();
        if (StringUtils.isNoneBlank(phone)) {
            criteria.andPhoneEqualTo(phone);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isNotBlank(start)) {
            try {
                Date date = sdf.parse(start);
                criteria.andCreateTimeGreaterThanOrEqualTo(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        if (StringUtils.isNotBlank(end)) {
            try {
                Date date = sdf.parse(end);
                criteria.andCreateTimeLessThan(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(20);
        }

        h5SubscribeExample.setLimitOffset(page.getOffset());
        h5SubscribeExample.setLimit(page.getPagesize());
        h5SubscribeExample.setOrderByClause("create_time desc");

        List<H5Subscribe> h5Subscribes = h5SubscribeService.findByCondition(h5SubscribeExample);
        int count = h5SubscribeService.findCountByCondition(h5SubscribeExample);
        page.setCount(count);
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("start", start);
        params.put("end", end);
        PaginationData paginationData = new PaginationData(page, params, h5Subscribes);

        ModelAndView modelAndView = new ModelAndView("h5/subscribe");
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addAllObjects(params);
        modelAndView.addObject("count", count);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String go(HttpServletRequest request, String start, String end, String title, String content) {

        logger.info("----------{} start the push based h5subscriber!-----------", request.getSession().getAttribute(Constants.INNER_LOGIN_SESSION_KEY));
        H5SubscribeExample subscribeExample = new H5SubscribeExample();
        H5SubscribeExample.Criteria criteria = subscribeExample.createCriteria();
        criteria.andDeletedEqualTo(false);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (StringUtils.isNotBlank(start)) {
            try {
                Date date = sdf.parse(start);
                criteria.andCreateTimeGreaterThanOrEqualTo(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        if (StringUtils.isNotBlank(end)) {
            try {
                Date date = sdf.parse(end);
                criteria.andCreateTimeLessThan(date.getTime());
            } catch (ParseException ignored) {
            }
        }

        List<H5Subscribe> subscribes = h5SubscribeService.findByCondition(subscribeExample);
        if (subscribes == null || subscribes.size() == 0) {
            return "0";
        }

        Set<String> phones = new HashSet<>();
        for (H5Subscribe subscribe : subscribes) {
            phones.add(subscribe.getPhone());
        }

        this.pushByPhone(phones, title, content);
        return "0";
    }

    @Async
    private void pushByPhone(Collection<String> phones, String title, String content) {
        for (String phone : phones) {
            User user = userService.findByPhone(phone);
            if (user == null) {
                continue;
            }

            if (StringUtils.isBlank(user.getDevice())) {
                logger.warn("该手机号 {} 的推送token为空,无法推送!", user.getPhone());
                continue;
            }

            if (content != null && StringUtils.contains(content, "{0}")) {
                content = MessageFormat.format(content, phone);
            }

            try {
                pushService.push(new PushPayload(user.getDevice(), title, content));
            } catch (Exception e) {
                logger.error("push service error:{}", e.getMessage());
            }
        }
    }
}
