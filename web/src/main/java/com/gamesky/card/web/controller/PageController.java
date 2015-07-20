package com.gamesky.card.web.controller;

import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.Setting;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.DownloadService;
import com.gamesky.card.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private SettingService settingService;
    @Autowired
    private DownloadService downloadService;

    @RequestMapping("/score")
    public ModelAndView score() {
        return new ModelAndView("score");
    }

    /**
     * 礼包分享页
     *
     * @param cardId 卡包ID
     * @return 分享页面
     */
    @RequestMapping("/card")
    public ModelAndView card(int cardId) {
        Card card = cardService.find(cardId);
        Setting setting = settingService.find("1_0");

        ModelAndView modelAndView = new ModelAndView("card", "card", card);
        if (setting != null) {
            modelAndView.addObject("android", setting.getAndroid());
            modelAndView.addObject("ios", setting.getIos());
        }

        return modelAndView;
    }

    @RequestMapping("/download")
    @ResponseBody
    public String download(String platform) {
        downloadService.count(platform);

        return "";
    }
}
