package com.gamesky.card.inner.controller;

import com.gamesky.card.core.CardType;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.*;
import com.gamesky.card.service.BeanUtils;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.CodeService;
import com.gamesky.card.service.GameService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/card", produces = "text/plain;charset=UTF-8")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private GameService gameService;
    @Autowired
    private CodeService codeService;
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @RequestMapping(value = "/add")
    public ModelAndView add() {
        List<Game> games = gameService.findAll(new Page());
        ModelAndView modelAndView = new ModelAndView("/card/add");
        modelAndView.addObject("games", games);
        CardType[] cardTypes = CardType.values();

        modelAndView.addObject("types", cardTypes);
        return modelAndView;
    }

    @RequestMapping(value = "/input")
    public ModelAndView input(int gameId) {
        Game game = gameService.find(gameId);
        ModelAndView modelAndView = new ModelAndView("/card/input");
        modelAndView.addObject("game", game);
        CardType[] cardTypes = CardType.values();

        modelAndView.addObject("types", cardTypes);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(String openTimeString, String expireTimeString, CardWithBLOBs card) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(openTimeString);
            card.setOpenTime(date.getTime());
            date = sdf.parse(expireTimeString);
            card.setExpireTime(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Game game = gameService.find(card.getGameId());
        card.setGameName(game.getName());
        card.setAssignTotal(0);
        card.setCreateTime(System.currentTimeMillis());
        int result = cardService.save(card);
        return String.valueOf(result);
    }

    @RequestMapping(value = "/modify")
    public ModelAndView modify(int id) {
        Card card = cardService.find(id);
        List<Game> games = gameService.findAll(new Page());
        CardType[] cardTypes = CardType.values();

        ModelAndView modelAndView = new ModelAndView("/card/modify");
        modelAndView.addObject("card", card);
        modelAndView.addObject("games", games);
        modelAndView.addObject("types", cardTypes);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String upload(String openTimeString, String expireTimeString, CardWithBLOBs card) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-dd");
        try {
            Date date = simpleDateFormat.parse(openTimeString);
            card.setOpenTime(date.getTime());
            date = simpleDateFormat.parse(expireTimeString);
            card.setExpireTime(date.getTime());
        } catch (ParseException ignored) {
        }
        int result = cardService.update(card);
        if (result > 0) {
            return "1";
        }

        return "更新卡包失败";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(int id) {
        Card card = cardService.find(id);
        ModelAndView modelAndView = new ModelAndView("card/view");
        modelAndView.addObject("card", card);
        return modelAndView;
    }

    @RequestMapping(value = "/all")
    public ModelAndView all(Integer gameId, Boolean closed, String name, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(10);
        }

        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();

        if (gameId != null) {
            criteria.andGameIdEqualTo(gameId);
        }
        if (closed != null) {
            criteria.andClosedEqualTo(closed);
        }
        if (StringUtils.isNotBlank(StringUtils.trimToEmpty(name))) {
            criteria.andNameLike("%" + name + "%");
        }

        criteria.andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());

        cardExample.setOrderByClause("closed asc, recommend desc, id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());

        List<Card> cards = cardService.findByCondition(cardExample);
        int count = cardService.findCountByCondition(cardExample);
        page.setCount(count);

        List<Game> games = gameService.findAll(new Page());

        ModelAndView modelAndView = new ModelAndView("/card/all");
        PaginationData paginationData = new PaginationData(page, cards);
        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("page", page);
        modelAndView.addObject("cards", cards);
        modelAndView.addObject("games", games);
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String myCard(String phone, Page page) {
        List<Code> codes = codeService.findByPhone(phone, page);
        if (codes == null || codes.size() == 0) {
            return ResultGenerator.generate();
        }

        List<Integer> ids = codes.stream().map(Code::getCardId).collect(Collectors.toList());

        List<Card> cards = cardService.findByIds(ids);
        List<Map> datas = new ArrayList<>();
        for (Card card : cards) {
            try {
                Map param = BeanUtils.beanToMap(card);
                for (Code code : codes) {
                    if (card.getId() == code.getCardId()) {
                        param.put("code", code.getCode());
                        break;
                    }
                }

                datas.add(param);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        }

        return ResultGenerator.generate(datas);
    }

    @ResponseBody
    @RequestMapping("/openorclose")
    public String openOrClose(int id, int gameId, boolean operate) {
        CardWithBLOBs card = new CardWithBLOBs();
        card.setId(id);
        card.setGameId(gameId);
        card.setClosed(operate);
        int result = cardService.update(card);

        return String.valueOf(result);
    }
}
