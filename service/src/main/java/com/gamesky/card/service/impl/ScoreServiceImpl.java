package com.gamesky.card.service.impl;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.FlowType;
import com.gamesky.card.core.MethodType;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Flow;
import com.gamesky.card.core.model.Setting;
import com.gamesky.card.core.model.User;
import com.gamesky.card.service.FlowService;
import com.gamesky.card.service.ScoreService;
import com.gamesky.card.service.SettingService;
import com.gamesky.card.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private UserService userService;
    @Autowired
    private FlowService flowService;
    @Autowired
    private SettingService settingService;
    /**
     * 用户赚取积分
     *
     * @param phone 用户手机
     * @param score 积分数
     * @param methodType   获取积分类别
     * @return 影响条数
     */
    @Override
    public int gain(String phone, int score, MethodType methodType) {
        if (score <= 0) {
            return 0;
        }

        User user = userService.findByPhone(phone);
        if (user == null) {
            return 0;
        }

        user.setScore(user.getScore() + score);
        userService.update(user);

        Flow flow = new Flow();
        flow.setUserId(user.getId());
        flow.setScore(score);
        flow.setMethod(methodType.name());
        flow.setType(FlowType.IN.getValue());
        flow.setPhone(user.getPhone());
        flow.setCreateTime(System.currentTimeMillis());

        return flowService.save(flow);
    }

    /**
     * 消费积分
     *
     * @param phone 用户手机
     * @param score 积分数
     * @return 影响条数
     */
    @Override
    public int consume(String phone, int score) {
        if (score <= 0) {
            return 0;
        }

        User user = userService.findByPhone(phone);
        if (user == null) {
            return 0;
        }

        user.setScore(user.getScore() - score);
        userService.update(user);

        Flow flow = new Flow();
        flow.setUserId(user.getId());
        flow.setScore(score);
        flow.setType(FlowType.OUT.getValue());
        flow.setPhone(user.getPhone());
        flow.setCreateTime(System.currentTimeMillis());

        return flowService.save(flow);
    }

    /**
     * 每日登录赚积分，一天只能一次
     * @param phone 用户ID
     * @return 影响条数
     */
    public int dailySign(String phone) {
        List<Flow> flows = flowService.findByPhone(phone, new Page());
        int score = Constants.DAILY_SCORE;
        Setting setting;
        if (flows == null || flows.size() == 0) {
            setting = settingService.find("1_0");
            if (setting != null) {
                score = setting.getDaily();
            }

            int result = this.gain(phone, score, MethodType.DAILY_GAIN);
            if (result > 0) {
                return score;
            }

            return 0;
        }

        for (Flow flow : flows) {
            if (!flow.getMethod().equals(MethodType.DAILY_GAIN.name())) {
                continue;
            }

            if(sameDay(flow.getCreateTime(), System.currentTimeMillis())) {
                return 0;
            }
        }

        setting = settingService.find("1_0");
        if (setting != null) {
            score = setting.getDaily();
        }

        int result = this.gain(phone, score, MethodType.DAILY_GAIN);
        if (result > 0) {
            return score;
        }

        return 0;
    }

    /**
     * 微信分享赚积分，一天只能一次
     * @param phone 用户手机
     * @return 影响条数
     */
    public int weixinShare(String phone) {
        List<Flow> flows = flowService.findByPhone(phone, new Page());
        int score = Constants.SHARE_WEIXIN;
        Setting setting;
        if (flows == null || flows.size() == 0) {
            setting = settingService.find("1_0");
            if (setting != null) {
                score = setting.getDaily();
            }

            int result = this.gain(phone, score, MethodType.WEIXIN_GAIN);
            if (result > 0) {
                return score;
            }

            return 0;
        }

        for (Flow flow : flows) {
            if (!flow.getMethod().equals(MethodType.WEIXIN_GAIN.name())) {
                continue;
            }

            if(sameDay(flow.getCreateTime(), System.currentTimeMillis())) {
                return 0;
            }
        }
        setting = settingService.find("1_0");
        if (setting != null) {
            score = setting.getDaily();
        }

        int result = this.gain(phone, score, MethodType.WEIXIN_GAIN);
        if (result > 0) {
            return score;
        }

        return 0;
    }

    /**
     * QQ分享赚积分 ，一天只能一次
     * @param phone 用户手机
     * @return 影响条数
     */
    public int qqShare(String phone) {
        List<Flow> flows = flowService.findByPhone(phone, new Page());
        int score = Constants.SHARE_QQ;
        Setting setting;
        if (flows == null || flows.size() == 0) {
            setting = settingService.find("1_0");
            if (setting != null) {
                score = setting.getDaily();
            }

            int result = this.gain(phone, score, MethodType.QQ_GAIN);
            if (result > 0) {
                return score;
            }

            return 0;
        }

        for (Flow flow : flows) {
            if (!flow.getMethod().equals(MethodType.QQ_GAIN.name())) {
                continue;
            }

            if(sameDay(flow.getCreateTime(), System.currentTimeMillis())) {
                return 0;
            }
        }

        setting = settingService.find("1_0");
        if (setting != null) {
            score = setting.getDaily();
        }

        int result = this.gain(phone, score, MethodType.QQ_GAIN);
        if (result > 0) {
            return score;
        }

        return 0;
    }

    private boolean sameDay(long one, long two) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dataFormat.format(one);
        String now = dataFormat.format(two);
        return date.equals(now);
    }
}
