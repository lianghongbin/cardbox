package com.gamesky.card.web.controller;

import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.utils.CodeUtils;
import com.gamesky.card.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * lianghongbin on 15/11/3.
 */
@Controller
@RequestMapping(value = "/1_4/invite", produces = "application/json;charset=UTF-8")
public class InviteController {

    @Autowired
    private InviteService inviteService;

    /**
     * 获取用户的邀请码
     * @param phone 手机号
     * @return 邀请码
     */
    @ResponseBody
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public String inviteCode(String phone) {
        String inviteCode = CodeUtils.fromPhone(phone);

        Map<String, String> datas = new HashMap<>(1);
        datas.put("inviteCode", inviteCode);
        return ResultGenerator.generate(datas);
    }

    /**
     * 获取邀请信息
     * @param phone 用户手机号
     * @return Json
     */
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String inviteInfo(String phone) {
        String inviteCode = CodeUtils.fromPhone(phone);
        int count = inviteService.findCountByUser(phone);
        Map<String, String> datas = new HashMap<>();
        datas.put("count", String.valueOf(count));
        datas.put("inviteCode", inviteCode);
        datas.put("score", String.valueOf(count * 50));
        datas.put("money", String.valueOf(count));
        return ResultGenerator.generate(datas);
    }
}
