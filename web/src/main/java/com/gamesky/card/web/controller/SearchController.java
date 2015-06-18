package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.model.CardExample;
import com.gamesky.card.core.model.CardWithBLOBs;
import com.gamesky.card.service.CardService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created on 6/15/15.
 *
 * @Author lianghongbin
 */
@Controller
@RequestMapping(value = "/1_0/search", produces="application/json;charset=UTF-8")
public class SearchController {

    @Autowired
    private CardService cardService;

    @ResponseBody
    @RequestMapping(value = "/key")
    public String searchByKey(String key, Page page) {
        if (StringUtils.isBlank(key)) {
            return ResultGenerator.generate();
        }

        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andNameLike("%" + key + "%")
                .andClosedEqualTo(false)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        cardExample.setOrderByClause("id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());
        List<CardWithBLOBs> cards = cardService.findByCondition(cardExample);
        int count = cardService.findCountByCondition((cardExample));
        page.setCount(count);

        return ResultGenerator.generate(page, cards);
    }
}
