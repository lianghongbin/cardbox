package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.Platform;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.Photo;
import com.gamesky.card.service.BeanUtils;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.GameService;
import com.gamesky.card.service.PhotoService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/game", produces = "application/json;charset=UTF-8")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private CardService cardService;
    @Autowired
    private PhotoService photoService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Game game) {
        int result = gameService.update(game);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("更新游戏失败");
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        int result = gameService.remove(id);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("删除游戏失败");
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(int id, String platform) {
        Game game = gameService.find(id);

        List<Photo> photos = photoService.findByGame(id, new Page());
        Map params;
        try {
            params = BeanUtils.beanToMap(game);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultGenerator.generateError(e.getMessage());
        }

        int cardId =cardService.findMaxCountId(id);
        if (cardId > 0) {
            params.put("cardId", cardId);
        }

        List<String> photoList = new ArrayList<>();

        if (photos == null || photos.size() == 0) {
            params.put("photo", photoList);
            return ResultGenerator.generate(params);
        } else {
            for (Photo photo : photos) {
                photoList.add(photo.getUrl());
            }

            params.put("photo", photoList);
        }

        int cardCount = cardService.validCount(id, platform);
        params.put("total", cardCount);

        return ResultGenerator.generate(params);
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findAll(Page page) {
        List<Game> games = gameService.findAll(page);
        int count = gameService.findCount();
        page.setCount(count);

        List<Map> data = new ArrayList<>();

        for (Game game : games) {
            Map params;
            try {
                params = BeanUtils.beanToMap(game);
                params.put("total", cardService.validCount(game.getId(), Platform.ALL.name()));
                data.add(params);
            } catch (Exception e) {
                return ResultGenerator.generateError(e.getMessage());
            }
        }

        return ResultGenerator.generate(page, data);
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend(String platform, Page page) {
        List<Game> games = gameService.findRecommend(platform, page);
        int count = gameService.findCountRecommend(platform);
        page.setCount(count);
        List<Map> data = new ArrayList<>();

        for (Game game : games) {
            Map params;
            try {
                params = BeanUtils.beanToMap(game);
                params.put("total", cardService.validCount(game.getId(), platform));
                data.add(params);
            } catch (Exception e) {
                return ResultGenerator.generateError(e.getMessage());
            }
        }

        return ResultGenerator.generate(page, data);
    }

    @ResponseBody
    @RequestMapping(value = "/my")
    public String findAll(String data) {
        Type collectionType = new TypeToken<List<String>>() {}.getType();
        Gson gson = new Gson();
        List<String> listData = gson.fromJson(data, collectionType);
        List<Game> games = gameService.findByPackages(listData);


        List<Map<String, String>> out = new ArrayList<>();
        if (games == null) {
            return ResultGenerator.generate(out);
        }

        for (Game game : games) {
            Map<String, String> params = new HashMap<>();
            params.put("packagename", game.getIdentifier());
            out.add(params);
        }

        return ResultGenerator.generate(out);
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bcd");

        Gson gson = new Gson();
        System.out.println(gson.toJson(list));
    }
}
