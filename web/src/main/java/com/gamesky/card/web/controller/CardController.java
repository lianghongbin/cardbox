package com.gamesky.card.web.controller;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardWithBLOBs;
import com.gamesky.card.core.model.Code;
import com.gamesky.card.core.model.User;
import com.gamesky.card.service.BeanUtils;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.CodeService;
import com.gamesky.card.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
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
    @Autowired
    private UserService userService;
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
    public String upload(CardWithBLOBs card) {
        int result = cardService.update(card);
        if (result > 0) {
            return ResultGenerator.generate();
        }

        return ResultGenerator.generateError("更新卡包失败");
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(int id) {
        Map data = cardService.findIncludeTao(id);

        return ResultGenerator.generate(data);
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/findwhenlogin", method = RequestMethod.GET)
    public String findWhenLogin(int id, String phone) {
        Map data = cardService.findIncludeTao(id);

        List<Code> codes = codeService.findByCardAndPhone(id, phone, new Page());
        if (codes != null && codes.size() > 0) {
            try {
                data.put("code", codes.get(0).getCode());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

            return ResultGenerator.generate(data);
        }

        return ResultGenerator.generate(data);
    }

    @ResponseBody
    @RequestMapping(value = "/findbygame", method = RequestMethod.GET)
    public String findByGame(int gameId, String platform, Page page) {
        List<CardWithBLOBs> cards = cardService.findByGame(gameId, platform, page);
        int count = cardService.findCountByGame(gameId, platform);
        page.setCount(count);
        for (Card card : cards) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
        }

        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/assign", method = RequestMethod.GET)
    public String assign(int id, String phone) {
        String result = cardService.assign(id, phone);
        if (StringUtils.startsWith(result, "-")) {
            return ResultGenerator.generateError(ReturnCode.fromCode(Integer.parseInt(result)));
        }

        User user = userService.findByPhone(phone);
        Map<String, Object> params = new HashMap<>();
        params.put("code", result);
        params.put("total", user.getScore());
        return ResultGenerator.generate(params);
    }

    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend(int searchType, String platform, Page page) {

        List<CardWithBLOBs> cards = cardService.recommend(searchType, platform, page);
        int count = cardService.recommendCount(searchType, platform);
        page.setCount(count);
        for (Card card : cards) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
        }

        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(Page page) {

        List<CardWithBLOBs> cards = cardService.findEnabledAll(page);
        int count = cardService.findEnabledCount();
        page.setCount(count);
        for (Card card : cards) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
        }

        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/undercard", method = RequestMethod.GET)
    public String underCard(int gameId, String platform, Page page) {
        List<CardWithBLOBs> cards = cardService.findRecommendByGame(gameId, platform, page);
        int count = cardService.findRecommendCountByGame(gameId, platform);
        page.setCount(count);
        for (Card card : cards) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
        }

        return ResultGenerator.generate(page, cards);
    }

    @ResponseBody
    @RequestMapping(value = "/recommendbygame", method = RequestMethod.GET)
    public String recommendByGame(int gameId, String platform, Page page) {
        List<CardWithBLOBs> cards = cardService.findRecommendByGame(gameId, platform, page);
        int count = cardService.findRecommendCountByGame(gameId, platform);
        page.setCount(count);
        for (Card card : cards) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
        }

        return ResultGenerator.generate(page, cards);
    }

    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String myCard(String phone, Page page) {
        List<Code> codes = codeService.findByPhone(phone, page);
        if (codes == null || codes.size() == 0) {
            return ResultGenerator.generate();
        }

        List<Integer> ids = codes.stream().map(Code::getCardId).collect(Collectors.toList());

        List<CardWithBLOBs> cards = cardService.findByIds(ids);
        List<Map> data = new ArrayList<>();
        for (Card card : cards) {
            try {
                Map param = BeanUtils.beanToMap(card);
                for (Code code : codes) {
                    if (Objects.equals(card.getId(), code.getCardId())) {
                        param.put("code", code.getCode());
                        break;
                    }
                }

                int total = codeService.findCountByCard(card.getId());
                int assignTotal = codeService.findCountAssignByCard(card.getId());
                param.put("total", total);
                param.put("assignTotal", assignTotal);

                data.add(param);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }

        }

        return ResultGenerator.generate(data);
    }

    @ResponseBody
    @RequestMapping(value = "/hasassign", method = RequestMethod.GET)
    public String hasAssign(int id, String phone) {
        boolean result = cardService.hasAssign(id, phone);
        Map<String, Integer> params = new HashMap<>();
        if (result) {
            params.put("assign", 1);
        } else {
            params.put("assign", 0);
        }

        return ResultGenerator.generate(params);
    }

    @ResponseBody
    @RequestMapping(value = "/tao", method = RequestMethod.GET)
    public String tao(int id, int count) {
        List<Code> codes = codeService.tao(id, count);
        if (codes == null || codes.size() == 0) {
            return ResultGenerator.generate();
        }

        List<String> codeList = new ArrayList<>();
        for (Code code : codes) {
            codeList.add(code.getCode());
        }

        return ResultGenerator.generate(codeList);
    }
}
