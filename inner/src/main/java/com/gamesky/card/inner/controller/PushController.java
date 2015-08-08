package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.*;
import com.gamesky.card.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    private PushService<String> pushService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private GameTypeService gameTypeService;
    @Autowired
    private SubscribeService subscribeService;
    private static final Logger logger = LoggerFactory.getLogger(PushController.class);

    @RequestMapping("/view")
    @SuppressWarnings("unchecked")
    public ModelAndView view(String start, String end, Integer gameId, String type, Page page) {
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
        if (StringUtils.isNoneBlank(type)) {
            List<GameType> gameTypes = gameTypeService.findByType(type, new Page());
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
        int count = subscribeService.findCountByCondition(subscribeExample);
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(20);
        }
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
        params.put("type", type);

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
    @RequestMapping("/go")
    @SuppressWarnings("unchecked")
    public String go(HttpServletRequest request,String start, String end, Integer gameId, String type, String content) {

        logger.error("----------{} start the push!-----------", request.getSession().getAttribute(Constants.INNER_LOGIN_SESSION_KEY));
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
        if (StringUtils.isNoneBlank(type)) {
            List<GameType> gameTypes = gameTypeService.findByType(type, new Page());
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
        if (subscribes == null || subscribes.size()==0) {
            return "0";
        }

        Set<String> phones = new HashSet<>();
        for (Subscribe subscribe : subscribes) {
            phones.add(subscribe.getPhone());
        }

        this.push(phones);
        return "0";
    }

    @Async
    private void push(Collection<String> phones) {
        for (String phone : phones) {
            User user = userService.findByPhone(phone);
            if (user == null) {
                continue;
            }

            pushService.push(user.getDevice());
        }
    }
}
