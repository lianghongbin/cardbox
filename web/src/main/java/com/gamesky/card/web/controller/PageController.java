package com.gamesky.card.web.controller;

import com.gamesky.card.core.model.Card;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 6/25/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/page", produces = "application/json;charset=UTF-8")
public class PageController {

    @Autowired
    private CardService cardService;
    @Autowired
    private GameService gameService;

    @RequestMapping("/score")
    public ModelAndView score() {
        return new ModelAndView("score");
    }

    /**
     * 礼包分享页
     * @param cardId 卡包ID
     * @return 分享页面
     */
    @RequestMapping("/card")
    public ModelAndView card(int cardId) {
        Card card = cardService.find(cardId);
        return new ModelAndView("card", "card", card);
    }
}
