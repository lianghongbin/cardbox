package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.GameType;
import com.gamesky.card.core.model.Subscribe;
import com.gamesky.card.core.model.SubscribeExample;
import com.gamesky.card.core.model.Types;
import com.gamesky.card.service.GameTypeService;
import com.gamesky.card.service.SubscribeService;
import com.gamesky.card.service.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * lianghongbin on 15/8/6.
 */
@Controller
@RequestMapping(value = "/subscribe", produces = "text/plain;charset=UTF-8")
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;
    @Autowired
    private GameTypeService gameTypeService;
    @Autowired
    private TypeService typeService;

    @RequestMapping("/all")
    @SuppressWarnings("unchecked")
    public ModelAndView all(String phone, Integer gameId, String type, Page page) {
        SubscribeExample subscribeExample = new SubscribeExample();
        SubscribeExample.Criteria criteria = subscribeExample.createCriteria();
        criteria.andDeletedEqualTo(false);

        if (StringUtils.isNoneBlank(phone)) {
            criteria.andPhoneEqualTo(phone);
        }
        if (gameId != null) {
            criteria.andGameIdEqualTo(gameId);
        }
        if (StringUtils.isNoneBlank(type)) {
            List<GameType> gameTypes = gameTypeService.findByType(type, new Page());
            List<Integer> ids = new ArrayList<>();
            if (gameTypes != null) {
                for (GameType gameType : gameTypes) {
                    ids.add(gameType.getGameId());
                }
            }

            criteria.andGameIdIn(ids);
        }

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
        params.put("phone", phone);
        params.put("gameId", gameId);
        params.put("type", type);

        PaginationData paginationData = new PaginationData(page, params, subscribes);

        List<Types> types = typeService.findAll();

        ModelAndView modelAndView = new ModelAndView("subscribe/all", "paginationData", paginationData);
        modelAndView.addObject("types", types);
        modelAndView.addAllObjects(params);

        return modelAndView;
    }
}
