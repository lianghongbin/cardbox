package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.PushPayload;
import com.gamesky.card.core.model.*;
import com.gamesky.card.service.*;
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
 * LHB on 8/7/15.
 */
@Controller
@RequestMapping(value = "/push", produces = "text/plain;charset=UTF-8")
public class PushController {

    @Autowired
    private UserService userService;
    @Autowired
    private PushService<PushPayload> pushService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private GameTypeService gameTypeService;
    @Autowired
    private SubscribeService subscribeService;
    private static final Logger logger = LoggerFactory.getLogger(PushController.class);

    @RequestMapping("/view")
    @SuppressWarnings("unchecked")
    public ModelAndView view(String start, String end, Integer gameId, String[] types, Page page) {
        SubscribeExample subscribeExample = new SubscribeExample();
        SubscribeExample.Criteria criteria = subscribeExample.createCriteria();
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

        if (gameId != null) {
            criteria.andGameIdEqualTo(gameId);
        }
        if (types != null && types.length > 0) {
            List<GameType> gameTypes = gameTypeService.findByTypes(Arrays.asList(types), new Page());
            List<Integer> ids = new ArrayList<>();
            ids.add(0);
            if (gameTypes != null && gameTypes.size() > 0) {
                for (GameType gameType : gameTypes) {
                    ids.add(gameType.getGameId());
                }
            }

            criteria.andGameIdIn(ids);
        }

        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(20);
        }

        subscribeExample.setLimitOffset(page.getOffset());
        subscribeExample.setLimit(page.getPagesize());
        subscribeExample.setOrderByClause("create_time desc");

        List<Subscribe> subscribes = subscribeService.findByCondition(subscribeExample);
        int count = subscribeService.findCountByCondition(subscribeExample);

        page.setCount(count);

        if (subscribes != null) {
            for (Subscribe subscribe : subscribes) {
                List<GameType> gameTypes = gameTypeService.findByGame(subscribe.getGameId());
                if (gameTypes != null) {
                    StringBuilder builder = new StringBuilder();
                    for (GameType gameType : gameTypes) {
                        if (builder.length() != 0) {
                            builder.append(" ");
                        }

                        builder.append(gameType.getType());
                    }

                    subscribe.setTypes(builder.toString());
                }
            }
        }

        Map params = new HashMap<>();
        params.put("gameId", gameId);
        params.put("start", start);
        params.put("end", end);
        params.put("types", types);

        PaginationData paginationData = new PaginationData(page, params, subscribes);

        List<Types> typesList = typeService.findAll();

        ModelAndView modelAndView = new ModelAndView("push/view");
        modelAndView.addObject("typesList", typesList);
        modelAndView.addAllObjects(params);
        modelAndView.addObject("count", count);
        modelAndView.addObject("paginationData", paginationData);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/go", method = RequestMethod.POST)
    @SuppressWarnings("unchecked")
    public String go(HttpServletRequest request, String start, String end, Integer gameId, String[] types, String title, String content) {

        logger.info("----------{} start the push based subscriber!-----------", request.getSession().getAttribute(Constants.INNER_LOGIN_SESSION_KEY));
        SubscribeExample subscribeExample = new SubscribeExample();
        SubscribeExample.Criteria criteria = subscribeExample.createCriteria();
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

        if (gameId != null) {
            criteria.andGameIdEqualTo(gameId);
        }
        if (types != null && types.length > 0) {
            List<GameType> gameTypes = gameTypeService.findByTypes(Arrays.asList(types), new Page());
            List<Integer> ids = new ArrayList<>();
            ids.add(0);
            if (gameTypes != null && gameTypes.size() > 0) {
                for (GameType gameType : gameTypes) {
                    ids.add(gameType.getGameId());
                }
            }

            criteria.andGameIdIn(ids);
        }

        List<Subscribe> subscribes = subscribeService.findByCondition(subscribeExample);
        if (subscribes == null || subscribes.size() == 0) {
            return "0";
        }

        Set<String> phones = new HashSet<>();
        for (Subscribe subscribe : subscribes) {
            phones.add(subscribe.getPhone());
        }

        this.pushByPhone(phones, title, content);
        return "0";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(Integer min, Integer max, String content, Page page) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (min != null) {
            criteria.andScoreGreaterThanOrEqualTo(min);
        }
        if (max != null) {
            criteria.andScoreLessThan(max);
        }

        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(20);
        }

        userExample.setOrderByClause("score desc");
        userExample.setLimit(page.getPagesize());
        userExample.setLimitOffset(page.getOffset());

        List<User> users = userService.findByCondition(userExample);
        int count = userService.findCountByCondition(userExample);
        page.setCount(count);

        Map params = new HashMap<>();
        params.put("min", min);
        params.put("max", max);
        params.put("content", content);

        PaginationData paginationData = new PaginationData(page, params, users);
        ModelAndView modelAndView = new ModelAndView("push/user");
        modelAndView.addAllObjects(params);
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("count", count);

        return modelAndView;
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/userpush", method = RequestMethod.POST)
    public String userPush(HttpServletRequest request, Integer min, Integer max, String title, String content) {
        logger.info("----------{} start the push based user's score!-----------", request.getSession().getAttribute(Constants.INNER_LOGIN_SESSION_KEY));

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (min != null) {
            criteria.andScoreGreaterThanOrEqualTo(min);
        }
        if (max != null) {
            criteria.andScoreLessThan(max);
        }

        List<User> users = userService.findByCondition(userExample);

        if (users == null || users.size() == 0) {
            return "0";
        }

        pushByUser(users, title, content);
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

    @Async
    private void pushByUser(Collection<User> users, String title, String content) {
        for (User user : users) {
            if (content != null && StringUtils.contains(content, "{0}")) {
                content = MessageFormat.format(content, user.getPhone());
            }

            if (StringUtils.isBlank(user.getDevice())) {
                logger.warn("该手机号 {} 的推送token为空,无法推送!", user.getPhone());
                continue;
            }

            try {
                pushService.push(new PushPayload(user.getDevice(), title, content));
            } catch (Exception e) {
                logger.error("push service error:{}", e.getMessage());
            }
        }
    }
}
