package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.GameExample;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.GameService;
import com.gamesky.card.service.PhotoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/game", produces="text/plain;charset=UTF-8")
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private CardService cardService;
    @Autowired
    private PhotoService photoService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @RequestMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("game/add");
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Game game) {
        game.setCreateTime(System.currentTimeMillis());
        int result = gameService.save(game);
        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Game game) {
        int result = gameService.update(game);
        return String.valueOf(result);
    }

    @RequestMapping(value = "/modify")
    public ModelAndView modify(int id) {
        Game game = gameService.find(id);
        ModelAndView modelAndView = new ModelAndView("game/modify");
        modelAndView.addObject("game", game);
        return modelAndView;
    }

    @RequestMapping(value = "/view")
    public ModelAndView view(int id) {
        Game game = gameService.find(id);
        ModelAndView modelAndView = new ModelAndView("game/view");
        modelAndView.addObject("game", game);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView findAll(String platform, Boolean closed, String name, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(10);
        }

        GameExample gameExample = new GameExample();
        GameExample.Criteria criteria = gameExample.createCriteria();

        if (StringUtils.isNotBlank(platform)) {
            criteria.andPlatformEqualTo(platform);
        }
        if (closed != null) {
            criteria.andClosedEqualTo(closed);
        }
        if (StringUtils.isNotBlank(StringUtils.trimToEmpty(name))) {
            criteria.andNameLike("%" + name + "%");
        }

        gameExample.setOrderByClause("closed asc, sort asc, recommend desc, id desc");
        gameExample.setLimitOffset(page.getOffset());
        gameExample.setLimit(page.getPagesize());

        List<Game> games = gameService.findByCondition(gameExample);
        int count = gameService.findCountByCondition(gameExample);

        page.setCount(count);

        PaginationData paginationData = new PaginationData(page, games);

        ModelAndView modelAndView = new ModelAndView("game/all");
        modelAndView.addObject("page", page);
        modelAndView.addObject("games", games);
        modelAndView.addObject("paginationData", paginationData);
        return modelAndView;
    }

    @RequestMapping(value = "/ranking")
    public ModelAndView ranking(String platform, String name, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(50);
        }

        GameExample gameExample = new GameExample();
        GameExample.Criteria criteria = gameExample.createCriteria();
        criteria.andClosedEqualTo(false);
        if (StringUtils.isNotBlank(platform)) {
            criteria.andPlatformEqualTo(platform);
        }

        if (StringUtils.isNotBlank(StringUtils.trimToEmpty(name))) {
            criteria.andNameLike("%" + name + "%");
        }

        gameExample.setOrderByClause("sort asc, recommend desc, id desc");
        gameExample.setLimitOffset(page.getOffset());
        gameExample.setLimit(page.getPagesize());

        List<Game> games = gameService.findByCondition(gameExample);
        int count = gameService.findCountByCondition(gameExample);

        page.setCount(count);

        PaginationData paginationData = new PaginationData(page, games);

        ModelAndView modelAndView = new ModelAndView("game/ranking");
        modelAndView.addObject("page", page);
        modelAndView.addObject("games", games);
        modelAndView.addObject("paginationData", paginationData);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/openorclose")
    public String openOrClose(int id, boolean operate) {
        Game game = new Game();
        game.setId(id);
        game.setClosed(operate);
        int result = gameService.update(game);

        cardService.validByGame(id, !operate);
        return String.valueOf(result);
    }
}