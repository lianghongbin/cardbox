package com.gamesky.card.web.controller;

import com.gamesky.card.core.CardType;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardExample;
import com.gamesky.card.core.model.CardWithBLOBs;
import com.gamesky.card.core.model.Code;
import com.gamesky.card.service.BeanUtils;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.CodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private CodeService codeService;
    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(CardWithBLOBs card) {
        card.setCreateTime(System.currentTimeMillis());
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
        List<Code> codes = codeService.findByCardAndPhone(id, phone, new Page());
        if (codes != null && codes.size() > 0) {
            Map<String, Object> map = null;
            try {
                map = BeanUtils.beanToMap(card);
                map.put("code", codes.get(0).getCode());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            return ResultGenerator.generate(map);
        }

        return ResultGenerator.generate(card);
    }

    @ResponseBody
    @RequestMapping(value = "/findbygame", method = RequestMethod.GET)
    public String findByGame(int gameId, Page page) {
        List<CardWithBLOBs> cards = cardService.findByGame(gameId, page);
        int count = cardService.findCountByGame(gameId);
        page.setCount(count);
        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public String assign(int id, String phone) {
        int result = cardService.assign(id, phone);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError(ReturnCode.fromCode(result));
    }

    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend(int searchType, Page page) {
        CardExample cardExample = new CardExample();
        long current = System.currentTimeMillis();
        CardExample.Criteria criteria = cardExample.createCriteria()
                .andRecommendEqualTo(true).andClosedEqualTo(false)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        switch (searchType) {
            case 1://付费
                criteria.andTypeEqualTo(CardType.PAY.name());
                break;
            case 2://免费
                criteria.andTypeEqualTo(CardType.PAY.name());
                break;
            case 3://积分
                criteria.andTypeEqualTo(CardType.SCORE.name());
                break;
            default:
        }

        cardExample.setOrderByClause("id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());

        List<CardWithBLOBs> cards = cardService.findByCondition(cardExample);
        int count = cardService.findCountByCondition(cardExample);
        page.setCount(count);
        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(Page page) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andClosedEqualTo(false)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());

        cardExample.setOrderByClause("id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());

        List<CardWithBLOBs> cards = cardService.findByCondition(cardExample);
        int count = cardService.findCountByCondition(cardExample);
        page.setCount(count);
        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/undercard", method = RequestMethod.GET)
    public String underCard(int gameId, Page page) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andRecommendEqualTo(true)
                .andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());

        cardExample.setOrderByClause("id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());

        List<CardWithBLOBs> cards = cardService.findByCondition(cardExample);
        int count = cardService.findCountByCondition(cardExample);
        page.setCount(count);
        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/recommendbygame", method = RequestMethod.GET)
    public String recommendByGame(int gameId, Page page) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andRecommendEqualTo(true)
                .andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());

        cardExample.setOrderByClause("id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());

        List<CardWithBLOBs> cards = cardService.findByCondition(cardExample);
        int count = cardService.findCountByCondition(cardExample);
        page.setCount(count);
        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String myCard(String phone, Page page) {
        List<Code> codes = codeService.findByPhone(phone, page);
        if (codes == null || codes.size() == 0) {
            return ResultGenerator.generate();
        }

        List<Integer> ids = codes.stream().map(Code::getCardId).collect(Collectors.toList());

        List<CardWithBLOBs> cards = cardService.findByIds(ids);
        List<Map> datas = new ArrayList<>();
        for (CardWithBLOBs card : cards) {
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
    @RequestMapping(value = "/hasassign", method = RequestMethod.GET)
    public String hasAssign(int id, String phone) {
        boolean result = cardService.hasAssign(id, phone);
        Map<String, Integer> params = new HashMap<>();
        if (result) {
            params.put("assign", 1);
        }
        else {
            params.put("assign", 0);
        }

        return ResultGenerator.generate(params);
    }
}
