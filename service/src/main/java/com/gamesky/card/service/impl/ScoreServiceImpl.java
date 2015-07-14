package com.gamesky.card.service.impl;

import com.gamesky.card.core.*;
import com.gamesky.card.core.exceptions.MarshalException;
import com.gamesky.card.core.model.Flow;
import com.gamesky.card.core.model.Setting;
import com.gamesky.card.core.model.User;
import com.gamesky.card.service.FlowService;
import com.gamesky.card.service.ScoreService;
import com.gamesky.card.service.SettingService;
import com.gamesky.card.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private UserService userService;
    @Autowired
    private FlowService flowService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private Marshaller<Cacheable, Integer> marshaller;
    private static final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class);

    /**
     * 用户赚取积分
     *
     * @param phone      用户手机
     * @param score      积分数
     * @param methodType 获取积分类别
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
        if (user == null || user.getScore()<score) {
            logger.error("手机用户 {} 不存在或者积分不足");
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
     *
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

            if (sameDay(flow.getCreateTime(), System.currentTimeMillis())) {
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
     *
     * @param phone 用户手机
     * @return 影响条数
     */
    public int weixinShare(String phone) {
        int score = Constants.SHARE_WEIXIN;
        Setting setting = settingService.find("1_0");
        if (setting != null) {
            score = setting.getWeixin();
        }

        //一天只前三次下载送积分
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dataFormat.format(new Date());
        String suffix = "_share_weixin_" + today;
        try {
            Integer count = marshaller.unmarshal(new Cacheable() {
                @Override
                public int expire() {
                    return 0;
                }

                @Override
                public String k() {
                    return phone + suffix;
                }
            });

            count = count == null ? 0 : count;

            if (count >= 3) {
                return 0;
            } else {
                marshaller.marshal(new Cacheable() {
                    @Override
                    public int expire() {
                        return 24 * 60 * 60 * 2;
                    }

                    @Override
                    public String k() {
                        return phone + suffix;
                    }
                }, count + 1);

                int result = this.gain(phone, score, MethodType.WEIXIN_GAIN);
                if (result > 0) {
                    return score;
                }
            }
        } catch (MarshalException e) {
            logger.error("缓存读取异常：{}", e);
            return 0;
        }

        return 0;
    }

    /**
     * QQ分享赚积分 ，一天只能一次
     *
     * @param phone 用户手机
     * @return 影响条数
     */
    public int qqShare(String phone) {
        int score = Constants.SHARE_QQ;
        Setting setting = settingService.find("1_0");
        if (setting != null) {
            score = setting.getQq();
        }

        //一天只前三次下载送积分
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dataFormat.format(new Date());
        String suffix = "_share_qq_" + today;
        try {
            Integer count = marshaller.unmarshal(new Cacheable() {
                @Override
                public int expire() {
                    return 0;
                }

                @Override
                public String k() {
                    return phone + suffix;
                }
            });

            count = count == null ? 0 : count;

            if (count >= 3) {
                return 0;
            } else {
                marshaller.marshal(new Cacheable() {
                    @Override
                    public int expire() {
                        return 24 * 60 * 60 * 2;
                    }

                    @Override
                    public String k() {
                        return phone + suffix;
                    }
                }, count + 1);

                int result = this.gain(phone, score, MethodType.QQ_GAIN);
                if (result > 0) {
                    return score;
                }
            }
        } catch (MarshalException e) {
            logger.error("缓存读取异常：{}", e);
            return 0;
        }

        return 0;
    }

    private int download(final String phone) {
        int score = Constants.DOWNLOAD_SCORE;
        Setting setting = settingService.find("1_0");
        if (setting != null) {
            score = setting.getDownload();
        }

        //一天只前三次下载送积分
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dataFormat.format(new Date());
        String suffix = "_login_" + today;
        try {
            Integer count = marshaller.unmarshal(new Cacheable() {
                @Override
                public int expire() {
                    return 0;
                }

                @Override
                public String k() {
                    return phone + suffix;
                }
            });

            count = count == null ? 0 : count;

            if (count >= 3) {
                return 0;
            } else {
                marshaller.marshal(new Cacheable() {
                    @Override
                    public int expire() {
                        return 24 * 60 * 60 * 2;
                    }

                    @Override
                    public String k() {
                        return phone + suffix;
                    }
                }, count + 1);

                int result = this.gain(phone, score, MethodType.DOWNLOAD_GAIN);
                if (result > 0) {
                    return score;
                }
            }
        } catch (MarshalException e) {
            logger.error("缓存读取异常：{}", e);
            return 0;
        }

        return 0;
    }

    private boolean sameDay(long one, long two) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dataFormat.format(one);
        String now = dataFormat.format(two);
        return date.equals(now);
    }

    /**
     * 获取积分
     *
     * @param phone 手机号
     * @param type  获取类别
     * @return 积分
     */
    @Override
    public int acquire(String phone, String type) {
        if (type.equalsIgnoreCase("weixin")) {
            return weixinShare(phone);
        }

        if (type.equalsIgnoreCase("qq")) {
            return qqShare(phone);
        }

        if (type.equalsIgnoreCase("login")) {
            return dailySign(phone);
        }

        if (type.equalsIgnoreCase("download")) {
            return download(phone);
        }

        return 0;
    }
}
