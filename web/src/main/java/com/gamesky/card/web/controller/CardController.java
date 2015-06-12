package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.Code;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/card", produces = "application/json;charset=UTF-8")
public class CardController {

    @Autowired
    private CardService cardService;
    @Autowired
    private CodeService keyService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Card card) {
        int result = cardService.save(card);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("添加卡包失败");
    }

    @ResponseBody
    @RequestMapping(value = "/close", method = RequestMethod.POST)
    public String close(int id) {
        int result = cardService.close(id);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("锁死卡包失败");
    }

    @ResponseBody
    @RequestMapping(value = "/open", method = RequestMethod.POST)
    public String open(int id) {
        int result = cardService.open(id);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("锁定卡包解锁失败");
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(Card card) {
        int result = cardService.update(card);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("更新卡包失败");
    }

    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(int id) {
        Card card = cardService.find(id);
        return ResultGenerator.generate(card);
    }

    @ResponseBody
    @RequestMapping(value = "/findwhenlogin", method = RequestMethod.GET)
    public String find(int id, String phone) {
        Card card = cardService.find(id);
        List<Code> codes = keyService.findByCardAndPhone(id, phone, new Page());
        if (codes != null && codes.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("card", card);
            map.put("code", codes.get(0));
            return ResultGenerator.generate(map);
        }
        return ResultGenerator.generate(card);
    }

    @ResponseBody
    @RequestMapping(value = "/findbygame", method = RequestMethod.GET)
    public String findByGame(int gameId, Page page) {
        List<Card> cards = cardService.findByGame(gameId, page);
        int count = cardService.findCountByGame(gameId);
        page.setTotal(count);
        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public String assign(int id, String phone) {
        int result = cardService.assign(id, phone);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("领取卡包失败");
    }
}
