package com.gamesky.card.inner.controller;

import com.gamesky.card.core.*;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardWithBLOBs;
import com.gamesky.card.core.model.Focus;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.FocusService;
import com.gamesky.card.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/focus", produces = "text/plain;charset=UTF-8")
public class FocusController {

    @Autowired
    private FocusService focusService;
    @Autowired
    private CardService cardService;
    @Autowired
    private GameService gameService;
    @Autowired
    @Qualifier("uploadMarshaller")
    private Marshaller<Keyable, Serializable> marshaller;
    private static final Logger logger = LoggerFactory.getLogger(FocusController.class);

    @RequestMapping("/add")
    public ModelAndView add() {
        List<Game> games = gameService.findAll(new Page());
        List<Card> cards = cardService.findAll(new Page());
        ModelAndView modelAndView = new ModelAndView("focus/add");
        modelAndView.addObject("games", games);
        modelAndView.addObject("cards", cards);

        FocusType[] focusTypes = FocusType.values();
        modelAndView.addObject("types", focusTypes);

        return modelAndView;
    }

    @RequestMapping("/modify")
    public ModelAndView modify(int id) {
        Focus focus = focusService.find(id);
        return new ModelAndView("focus/modify", "focus", focus);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Focus focus) {
        focus.setCreateTime(System.currentTimeMillis());
        int result = focusService.save(focus);
        if (result > 0) {
            return "1";
        }

        return "添加焦点图失败";
    }

    @ResponseBody
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(int id) {
        int result = focusService.remove(id);
        return String.valueOf(result);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Focus focus) {
        int result = focusService.update(focus);
        if (result > 0) {
            return "1";
        }

        return "更新焦点图失败";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView findAll(Page page) {
        if (page.getPagesize() == Integer.MAX_VALUE) {
            page.setPagesize(15);
        }

        List<Focus> focusList = focusService.findByEnable(null, new Page());
        PaginationData paginationData = new PaginationData(page, focusList);

        ModelAndView modelAndView = new ModelAndView("focus/all");
        modelAndView.addObject("paginationData", paginationData);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/enabled")
    public String enabled(int id, boolean enabled) {
        Focus focus = new Focus();
        focus.setId(id);
        focus.setEnabled(enabled);

        int result = focusService.update(focus);
        return String.valueOf(result);
    }
}
