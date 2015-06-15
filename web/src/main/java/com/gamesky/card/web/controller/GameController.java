package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/game", produces="application/json;charset=UTF-8")
public class GameController {

    @Autowired
    private GameService gameService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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

    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(int id) {
        Game game = gameService.find(id);
        return ResultGenerator.generate(game);
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String findAll(Page page) {
        List<Game> games = gameService.findAll(page);
        int count = gameService.findCount();
        page.setCount(count);
        return ResultGenerator.generate(page, games);
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
