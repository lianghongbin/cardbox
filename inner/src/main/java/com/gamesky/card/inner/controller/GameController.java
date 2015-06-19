package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.Photo;
import com.gamesky.card.service.BeanUtils;
import com.gamesky.card.service.GameService;
import com.gamesky.card.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/game", produces = "text/plain;charset=UTF-8")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private PhotoService photoService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    public String save(Game game) {
        int result = gameService.save(game);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("添加游戏失败");
    }

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
    public String find(int id) {
        Game game = gameService.find(id);

        List<Photo> photos = photoService.findByGame(id, new Page());
        Map params;
        try {
            params = BeanUtils.beanToMap(game);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResultGenerator.generateError(e.getMessage());
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

        return ResultGenerator.generate(params);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView findAll(Page page) {
        List<Game> games = gameService.findAll(page);
        int count = gameService.findCount();
        page.setCount(count);
        ModelAndView modelAndView = new ModelAndView("game/all");
        modelAndView.addObject("page", page);
        modelAndView.addObject("games", games);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend(Page page) {
        List<Game> games = gameService.findRecommend(page);
        int count = gameService.findCountRecommend();
        page.setCount(count);
        return ResultGenerator.generate(page, games);
    }

    @ResponseBody
    @RequestMapping(value = "/gamepackages", method = RequestMethod.GET)
    public String findAll() {
        List<String> games = gameService.findPackages();
        Page page = new Page();
        int count = gameService.findCount();
        page.setCount(count);

        return ResultGenerator.generate(page, games);
    }
}
