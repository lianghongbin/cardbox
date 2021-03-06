package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.*;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.CodeService;
import com.gamesky.card.service.GameService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/code", produces = "text/plain;charset=UTF-8")
public class CodeController {

    @Autowired
    private CardService cardService;
    @Autowired
    private GameService gameService;
    @Autowired
    private CodeService codeService;
    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);

    @RequestMapping(value = "/add")
    public ModelAndView add(int cardId) {
        Card card = cardService.find(cardId);
        Game game = gameService.find(card.getGameId());
        ModelAndView modelAndView = new ModelAndView("/code/add");

        modelAndView.addObject("card", card);
        modelAndView.addObject("game", game);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public String save(String[] codes, String cardName, String gameName, int gameId, int cardId) {

        for (String code : codes) {
            if (StringUtils.isBlank(code)) {
                continue;
            }

            Code tmp = new Code();
            tmp.setPhone("");
            tmp.setGameId(gameId);
            tmp.setGameName(gameName);
            tmp.setCardId(cardId);
            tmp.setCardName(cardName);
            tmp.setCode(code);
            tmp.setUsed(false);
            tmp.setAssigned(false);
            tmp.setAssignTime(0L);
            tmp.setCreateTime(System.currentTimeMillis());

            codeService.save(tmp);
        }

        return String.valueOf("1");
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/all")
    public ModelAndView all(String key, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(10);
        }

        CodeExample codeExample = new CodeExample();
        if (StringUtils.isNotBlank(StringUtils.trimToEmpty(key))) {
            codeExample.or().andGameNameLike("%" + key + "%");
            codeExample.or().andCardNameLike("%" + key + "%");
            codeExample.or().andCodeLike("%" + key + "%");
            codeExample.or().andPhoneLike("%" + key + "%");
        }

        codeExample.setOrderByClause("id desc");
        codeExample.setLimit(page.getPagesize());
        codeExample.setLimitOffset(page.getOffset());

        List<Code> codes = codeService.findByCondition(codeExample);
        int count = codeService.findCountByCondition(codeExample);
        page.setCount(count);

        List<Game> games = gameService.findAll(new Page());
        List<CardWithBLOBs> cards = cardService.findAll(new Page());

        ModelAndView modelAndView = new ModelAndView("/code/all");
        Map params = new HashMap<>();
        params.put("key", key);

        PaginationData paginationData = new PaginationData(page, params, codes);


        modelAndView.addObject("paginationData", paginationData);
        modelAndView.addObject("cards", cards);
        modelAndView.addObject("games", games);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/remove")
    public String remove(int id) {
        int result = codeService.remove(id);
        return String.valueOf(result);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/findbycard")
    public ModelAndView findByCard(int cardId, String key, Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(15);
        }

        CodeExample codeExample = new CodeExample();
        if (StringUtils.isNotBlank(StringUtils.trimToEmpty(key))) {
            CodeExample.Criteria criteria1 = codeExample.createCriteria();
            criteria1.andGameNameLike("%" + key + "%");
            criteria1.andCardIdEqualTo(cardId);
            codeExample.or(criteria1);
            CodeExample.Criteria criteria2 = codeExample.createCriteria();
            criteria2.andCardNameLike("%" + key + "%");
            criteria2.andCardIdEqualTo(cardId);
            codeExample.or(criteria2);
            CodeExample.Criteria criteria3 = codeExample.createCriteria();
            criteria3.andCodeLike("%" + key + "%");
            criteria3.andCardIdEqualTo(cardId);
            codeExample.or(criteria3);
            CodeExample.Criteria criteria4 = codeExample.createCriteria();
            criteria4.andPhoneLike("%" + key + "%");
            criteria4.andCardIdEqualTo(cardId);
            codeExample.or(criteria4);
        } else {
            codeExample.createCriteria().andCardIdEqualTo(cardId);
        }

        codeExample.setOrderByClause("id desc");
        codeExample.setLimit(page.getPagesize());
        codeExample.setLimitOffset(page.getOffset());

        List<Code> codes = codeService.findByCondition(codeExample);
        int count = codeService.findCountByCondition(codeExample);
        page.setCount(count);

        Map params = new HashMap<>();
        params.put("cardId", cardId);
        params.put("key", key);

        PaginationData paginationData = new PaginationData(page, params, codes);
        ModelAndView modelAndView = new ModelAndView("code/list");
        modelAndView.addObject("cardId", cardId);
        modelAndView.addObject("paginationData", paginationData);

        return modelAndView;
    }

    @RequestMapping("/input")
    public ModelAndView input(int cardId) {
        Card card = cardService.find(cardId);
        Game game = gameService.find(card.getGameId());
        ModelAndView modelAndView = new ModelAndView("/code/input");

        modelAndView.addObject("card", card);
        modelAndView.addObject("game", game);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/batchsave")
    public String batchSave(String data, String cardName, String gameName, int gameId, int cardId) {
        if (StringUtils.isBlank(data)) {
            return "请添加激活码数据";
        }

        String input = data;
        Pattern p = Pattern.compile("\t|\r|\n");
        Matcher m = p.matcher(data);
        if (m.find()) {
            input = m.replaceAll(" ");
        }

        String[] codes = StringUtils.split(input, " ");

        for (String code : codes) {
            if (StringUtils.isBlank(code)) {
                continue;
            }

            Code tmp = new Code();
            tmp.setGameId(gameId);
            tmp.setGameName(gameName);
            tmp.setCardId(cardId);
            tmp.setCardName(cardName);
            tmp.setCode(code);
            tmp.setUsed(false);
            tmp.setAssigned(false);
            tmp.setPhone("");
            tmp.setAssignTime(0L);
            tmp.setCreateTime(System.currentTimeMillis());

            codeService.save(tmp);
        }

        return String.valueOf("1");
    }
}
