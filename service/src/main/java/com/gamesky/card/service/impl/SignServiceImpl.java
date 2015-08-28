package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Sign;
import com.gamesky.card.core.model.SignExample;
import com.gamesky.card.core.utils.DateUtils;
import com.gamesky.card.dao.mapper.SignMapper;
import com.gamesky.card.service.SignService;
import com.gamesky.card.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * lianghongbin on 15/8/21.
 */
@Service
@Transactional
public class SignServiceImpl implements SignService {

    @Autowired
    private SignMapper signMapper;
    @Autowired
    private UserService userService;
    private int[] signScores = new int[] {50, 70, 90, 120, 150, 180};
    /**
     * 签到
     *
     * @param phone 签到手机
     * @return 影响条数
     */
    @Override
    public int sign(String phone) {
        SignExample signExample = new SignExample();
        signExample.createCriteria().andPhoneEqualTo(phone);
        List<Sign> signs = signMapper.selectByExample(signExample);
        if (signs == null || signs.size() == 0) {
            Sign sign = new Sign();
            sign.setOperateTime(System.currentTimeMillis());
            sign.setTimes(1);
            sign.setPhone(phone);
            userService.addScore(phone, signScores[0]);

            return signMapper.insert(sign);
        }

        Sign sign = signs.get(0);
        long now = System.currentTimeMillis();
        if (DateUtils.nextDay(sign.getOperateTime(), now)) {
            if (sign.getTimes() >= 7) {
                sign.setTimes(0);
                sign.setOperateTime(0L);
                userService.addMoney(phone, 1);

                return signMapper.updateByPrimaryKeySelective(sign);
            }

            userService.addScore(phone, signScores[sign.getTimes()]);

            sign.setTimes(sign.getTimes() + 1);
            sign.setOperateTime(System.currentTimeMillis());

            return signMapper.updateByPrimaryKeySelective(sign);
        }

        sign.setTimes(1);
        sign.setOperateTime(System.currentTimeMillis());
        userService.addScore(phone, signScores[0]);

        return signMapper.updateByPrimaryKeySelective(sign);
    }

    /**
     * 获取用户的签到记录
     *
     * @param phone 手机号
     * @return 签到实例
     */
    @Override
    public Sign findByPhone(String phone) {
        SignExample signExample = new SignExample();
        signExample.createCriteria().andPhoneEqualTo(phone);
        List<Sign> signs = signMapper.selectByExample(signExample);
        if (signs == null || signs.size() == 0) {
            Sign sign = new Sign();
            sign.setPhone(phone);
            sign.setTimes(1);
            return sign;
        }

        long now = System.currentTimeMillis();
        Sign sign = signs.get(0);
        if (DateUtils.nextDay(sign.getOperateTime(), now)) {
            if (sign.getTimes() >= 7) {
                sign.setTimes(1);
            }
            else {
                sign.setTimes(sign.getTimes() + 1);
            }
        }

        return sign;
    }

    /**
     * 所有签到用户数
     *
     * @return 签到用户数
     */
    @Override
    public int findCount() {
        SignExample signExample = new SignExample();

        return signMapper.countByExample(signExample);
    }
}
