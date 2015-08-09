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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public ModelAndView all(String phone, String start, String end, Integer gameId, String[] types, Page page) {
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

        if (StringUtils.isNoneBlank(phone)) {
            criteria.andPhoneEqualTo(phone);
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
        params.put("types", types);
        params.put("start", start);
        params.put("end", end);

        PaginationData paginationData = new PaginationData(page, params, subscribes);

        List<Types> typesList = typeService.findAll();

        ModelAndView modelAndView = new ModelAndView("subscribe/all", "paginationData", paginationData);
        modelAndView.addObject("typesList", typesList);
        modelAndView.addObject("count", count);
        modelAndView.addAllObjects(params);

        return modelAndView;
    }
}
