package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.Platform;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.GameExample;
import com.gamesky.card.core.model.GameType;
import com.gamesky.card.core.model.Types;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.GameService;
import com.gamesky.card.service.GameTypeService;
import com.gamesky.card.service.TypeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private TypeService typeService;
    @Autowired
    private GameTypeService gameTypeService;
    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @RequestMapping("/add")
    public ModelAndView add() {
        List<Types> typesList = typeService.findAll();
        return new ModelAndView("game/add", "typesList", typesList);
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(Game game, String[] types) {
        if (game.getTotal() == null) {
            game.setTotal(0);
        }

        if (StringUtils.isBlank(game.getIcon())) {
            game.setIcon(Constants.DEFAULT_ICON);
        }

        game.setCreateTime(System.currentTimeMillis());
        int result = gameService.save(game);
        for (String type : types) {
            GameType gameType = new GameType();
            gameType.setGameId(game.getId());
            gameType.setType(type);
            gameTypeService.save(gameType);
        }

        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Game game, String[] types) {
        gameTypeService.removeByGame(game.getId());
        if (types.length > 0) {
            for (String type : types) {
                GameType gameType = new GameType();
                gameType.setGameId(game.getId());
                gameType.setType(type);
                gameTypeService.save(gameType);
            }
        }

        int result = gameService.update(game);
        return String.valueOf(result);
    }

    @RequestMapping(value = "/modify")
    public ModelAndView modify(int id) {
        Game game = gameService.find(id);

        List<GameType> gameTypes = gameTypeService.findByGame(id);
        List<Types> typesList = typeService.findAll();
        List<String> types = new ArrayList<>();
        if (gameTypes != null) {
            for (GameType gameType : gameTypes) {
                types.add(gameType.getType());
            }
        }

        ModelAndView modelAndView = new ModelAndView("game/modify");
        modelAndView.addObject("game", game);
        modelAndView.addObject("typesList", typesList);
        modelAndView.addObject("types", types);
        return modelAndView;
    }

    @RequestMapping(value = "/view")
    public ModelAndView view(int id) {
        Game game = gameService.find(id);
        int total = cardService.validCount(game.getId(), Platform.ALL.name());
        game.setTotal(total);

        List<GameType> gameTypes = gameTypeService.findByGame(id);

        ModelAndView modelAndView = new ModelAndView("game/view");
        modelAndView.addObject("game", game);
        modelAndView.addObject("gameTypes", gameTypes);
        return modelAndView;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/all")
    public ModelAndView findAll(String platform, Integer closed, String name, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(10);
        }

        GameExample gameExample = new GameExample();
        GameExample.Criteria criteria = gameExample.createCriteria();

        if (StringUtils.isNotBlank(platform)) {
            criteria.andPlatformEqualTo(platform);
        }
        if (closed!=null && closed != 2) {
            boolean c = closed!=0;
            criteria.andClosedEqualTo(c);
        }
        if (StringUtils.isNotBlank(StringUtils.trimToEmpty(name))) {
            criteria.andNameLike("%" + name + "%");
        }

        gameExample.setOrderByClause("sort asc, closed asc, recommend desc, id desc");
        gameExample.setLimitOffset(page.getOffset());
        gameExample.setLimit(page.getPagesize());

        List<Game> games = gameService.findByCondition(gameExample);
        int count = gameService.findCountByCondition(gameExample);
        page.setCount(count);

        for (Game game : games) {
            int total = cardService.validCount(game.getId(), Platform.ALL.name());
            game.setTotal(total);
        }

        Map params = new HashMap<>();
        params.put("platform", platform);
        params.put("closed", closed);
        params.put("name", name);

        PaginationData paginationData = new PaginationData(page, params, games);

        ModelAndView modelAndView = new ModelAndView("game/all");
        modelAndView.addObject("page", page);
        modelAndView.addObject("games", games);
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("platform", platform);
        modelAndView.addObject("closed", closed);

        return modelAndView;
    }

    @SuppressWarnings("unchecked")
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
        for (Game game : games) {
            int total = cardService.validCount(game.getId(), Platform.ALL.name());
            game.setTotal(total);
        }

        Map params = new HashMap<>();
        params.put("platform", platform);
        params.put("name", name);
        PaginationData paginationData = new PaginationData(page, params, games);

        ModelAndView modelAndView = new ModelAndView("game/ranking");
        modelAndView.addObject("page", page);
        modelAndView.addObject("games", games);
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("platform", platform);
        modelAndView.addObject("name", name);

        return modelAndView;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/sort")
    public ModelAndView sort(String platform, String name, Page page) {
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
        for (Game game : games) {
            int total = cardService.validCount(game.getId(), Platform.ALL.name());
            game.setTotal(total);
        }

        Map params = new HashMap<>();
        params.put("platform", platform);
        params.put("name", name);
        PaginationData paginationData = new PaginationData(page, params, games);

        ModelAndView modelAndView = new ModelAndView("game/ranking");
        modelAndView.addObject("page", page);
        modelAndView.addObject("games", games);
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("platform", platform);
        modelAndView.addObject("name", name);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/openorclose")
    public String openOrClose(int id, boolean operate) {
        Game game = new Game();
        game.setId(id);
        game.setClosed(operate);
        int result = gameService.update(game);

        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping("/findplatform")
    public String findPlatform(int id) {
        Game game = gameService.find(id);
        if (game == null) {
            return "";
        }

        return game.getPlatform();
    }
}
