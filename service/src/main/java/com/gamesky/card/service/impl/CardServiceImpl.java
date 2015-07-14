package com.gamesky.card.service.impl;

import com.gamesky.card.core.*;
import com.gamesky.card.core.lock.GlobalLock;
import com.gamesky.card.core.lock.Lockable;
import com.gamesky.card.core.model.*;
import com.gamesky.card.dao.mapper.CardMapper;
import com.gamesky.card.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 卡包服务接口实现类
 * Created on 6/8/15.
 *
 * @Author lianghongbin
 */
@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardMapper cardMapper;
    @Autowired
    private CodeService codeService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private GlobalLock<Lockable> globalLock;
    private final static Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    public int save(CardWithBLOBs card) {
        return cardMapper.insert(card);
    }

    /**
     * 删除一个礼包
     *
     * @param id 礼包ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        codeService.removeByCard(id);

        return cardMapper.deleteByPrimaryKey(id);
    }

    /**
     * 锁死卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    @Override
    public int close(int id) {
        Card card = cardMapper.selectByPrimaryKey(id);
        card.setClosed(true);

        return cardMapper.updateByPrimaryKey(card);
    }

    /**
     * 解锁卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    @Override
    public int open(int id) {
        Card card = cardMapper.selectByPrimaryKey(id);
        card.setClosed(false);

        return cardMapper.updateByPrimaryKey(card);
    }

    /**
     * 修改卡包信息
     *
     * @param card 卡包
     * @return 影响条数
     */
    @Override
    public int update(CardWithBLOBs card) {
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    /**
     * 分发、分配一个激活码给某个用户
     *
     * @param id    卡包ID
     * @param phone 用户手机
     * @return 影响条数
     */
    @Override
    public String assign(final int id, String phone) {
        CardLock cardLock = new CardLock(id);

        try {
            if (!globalLock.acquire(cardLock, 3000)) {
                return String.valueOf(ReturnCode.MARSHAL_ERROR.getCode());
            }

            boolean isLogin = userService.isLogin(phone);
            if (!isLogin) {
                return String.valueOf(ReturnCode.NOT_LOGIN.getCode());
            }

            //校验该卡包是否有效
            CardExample cardExample = new CardExample();
            cardExample.createCriteria()
                    .andClosedEqualTo(false)
                    .andIdEqualTo(id)
                    .andValidEqualTo(true)
                    .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                    .andExpireTimeGreaterThan(System.currentTimeMillis());
            List<Card> cards = cardMapper.selectByExample(cardExample);
            if (cards == null || cards.size() == 0) {
                return String.valueOf(ReturnCode.CARD_EMPTY.getCode());
            }

            Card card = cards.get(0);

            List<Code> codes = codeService.findByCardAndPhone(id, phone, new Page());

            if (codes != null && codes.size() > 0) {
                return String.valueOf(ReturnCode.REPEAT_ASSIGN.getCode());
            }

            if (card.getTotal() <= card.getAssignTotal()) {
                logger.error("没有可使用的激活码");
                return String.valueOf(ReturnCode.DATA_EMPTY.getCode());
            }

            if (card.getType().equalsIgnoreCase(CardType.SCORE.name())) {
                //如果是扣分数的礼包，查看一下分数是否够，如果不够直接返回错误提示
                User user = userService.findByPhone(phone);
                if (user == null) {
                    return String.valueOf(ReturnCode.USER_NOT_EXIST);
                }

                if (card.getScore() > user.getScore()) {
                    return String.valueOf(ReturnCode.SCORE_NOT_ENOUGH.getCode());
                }

                user.setScore(user.getScore() - card.getScore());
                userService.update(user);
            } else if (card.getType().equalsIgnoreCase(CardType.PAY.name())) {
                logger.info("需要付费领取礼包");
            }

            String code = codeService.assign(id, phone);
            if (code == null) {
                logger.error("没有可使用的激活码");
                return String.valueOf(ReturnCode.CODE_EMPTY.getCode());
            }

            increaseAssignTotal(card.getId(), 1);
            return code;
        } catch (Exception e) {
            logger.error("获得他局锁失败：{}", e);
            return String.valueOf(-1);
        } finally {
            globalLock.release(cardLock);
        }
    }

    /**
     * 根据卡包ID，查找卡包
     *
     * @param id 卡包ID
     * @return 影响条数
     */
    @Override
    @Transactional(readOnly = true)
    public Card find(int id) {
        Card card = cardMapper.selectByPrimaryKey(id);
        int total = codeService.findCountByCard(id);
        int assignTotal = codeService.findCountAssignByCard(id);
        card.setTotal(total);
        card.setAssignTotal(assignTotal);
        card.setTao(isTao(card));

        return card;
    }

    public int isTao(Card card) {

        if (card.getTotal() == 0 || card.getTotal() > card.getAssignTotal()) {
            return 0;
        }

        long assignTime = codeService.lastAssignTime(card.getId());
        if (assignTime == 0) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(assignTime);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(new Date());

        calendar.add(Calendar.MINUTE, Constants.TAO_AFTER_TIME);
        if (calendar1.after(calendar)) {
            return 1;
        }

        return 0;
    }

    /**
     * 分页显示所有卡包(为后台使用，显示所有卡包
     *
     * @param page 分页条件
     * @return 卡包列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> findAll(Page page) {
        CardExample cardExample = new CardExample();
        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getPagesize());
        cardExample.setOrderByClause("sort asc, recommend desc, id desc");
        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);

        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    /**
     * 分页显示所有卡包(为后台使用，显示所有卡包
     *
     * @param page 分页条件
     * @return 卡包列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> findEnabledAll(Page page) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andOpenTimeLessThan(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis())
                .andClosedEqualTo(false)
                .andValidEqualTo(true);
        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getPagesize());
        cardExample.setOrderByClause("sort asc, recommend desc, id desc");
        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);
        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    /**
     * 分页显示所有有效卡包数(后台用，显示所有礼包数）
     *
     * @return 卡包数
     */
    @Override
    public int findCount() {
        CardExample cardExample = new CardExample();
        return cardMapper.countByExample(cardExample);
    }

    /**
     * 分页显示所有卡包数（前台接口用，显示可用礼包数）
     *
     * @return 卡包数
     */
    @Override
    @Transactional(readOnly = true)
    public int findEnabledCount() {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andOpenTimeLessThan(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis())
                .andClosedEqualTo(false)
                .andValidEqualTo(true);
        return cardMapper.countByExample(cardExample);
    }

    /**
     * 根据条件分页查询显示卡包
     *
     * @param cardExample 查询条件
     * @return 卡包列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> findByCondition(CardExample cardExample) {
        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);
        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    /**
     * 根据条件分页查询显示卡包数量
     *
     * @param cardExample 查询条件
     * @return 卡包数量
     */
    @Override
    @Transactional(readOnly = true)
    public int findCountByCondition(CardExample cardExample) {
        return cardMapper.countByExample(cardExample);
    }

    /**
     * 根据游戏查找该游戏下的卡包列表
     *
     * @param gameId 游戏ID
     * @param page   分页数据
     * @return 卡包列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> findByGame(int gameId, String platform, Page page) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getPagesize());
        cardExample.setOrderByClause("sort asc, recommend desc, id desc");
        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);
        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    @Override
    public int updateGameName(int gameId, String gameName) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andGameIdEqualTo(gameId);
        CardWithBLOBs cardWithBLOBs = new CardWithBLOBs();
        cardWithBLOBs.setGameName(gameName);
        return cardMapper.updateByExampleSelective(cardWithBLOBs, cardExample);
    }

    /**
     * 查找某款游戏下的卡包类别数量
     *
     * @param gameId 游戏ID
     * @return 卡包类别数量
     */
    @Override
    @Transactional(readOnly = true)
    public int findCountByGame(int gameId, String platform) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        return cardMapper.countByExample(cardExample);
    }


    /**
     * 根据游戏查找该游戏下的推荐卡包列表
     *
     * @param gameId 游戏ID
     * @param page   分页数据
     * @return 卡包列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> findRecommendByGame(int gameId, String platform, Page page) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andRecommendEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getPagesize());
        cardExample.setOrderByClause("sort asc, id desc");
        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);
        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    /**
     * 查找某款游戏下的推荐卡包类别数量
     *
     * @param gameId 游戏ID
     * @return 卡包类别数量
     */
    @Override
    @Transactional(readOnly = true)
    public int findRecommendCountByGame(int gameId, String platform) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andRecommendEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        return cardMapper.countByExample(cardExample);
    }

    /**
     * 指查找礼包
     *
     * @param ids 礼包ID集合
     * @return 礼包集合
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> findByIds(List<Integer> ids) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andIdIn(ids);
        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);
        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    /**
     * 查询手机用户是否已经申领过该礼品包
     *
     * @param id    礼包ID
     * @param phone 用户手机
     * @return true/false
     */
    @Transactional(readOnly = true)
    public boolean hasAssign(int id, String phone) {
        List<Code> codes = codeService.findByCardAndPhone(id, phone, new Page());
        return codes != null && codes.size() > 0;
    }

    /**
     * 随游戏的上线、下线，相应的礼包置为有效或无效状态
     *
     * @param gameId 游戏ID
     * @param valid  是否有效
     * @return 影响条数
     */
    @Override
    public int validByGame(int gameId, boolean valid) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andGameIdEqualTo(gameId);
        CardWithBLOBs card = new CardWithBLOBs();
        card.setValid(valid);
        return cardMapper.updateByExampleSelective(card, cardExample);
    }

    /**
     * 根据条件查找推荐礼包
     *
     * @param type 条件
     * @param page 分页
     * @return 礼包列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> recommend(int type, String platform, Page page) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria()
                .andRecommendEqualTo(true)
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        switch (type) {
            case 1://付费
                criteria.andTypeEqualTo(CardType.PAY.name());
                break;
            case 2://免费
                criteria.andTypeEqualTo(CardType.FREE.name());
                break;
            case 3://积分
                criteria.andTypeEqualTo(CardType.SCORE.name());
                break;
            default:
        }

        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        cardExample.setOrderByClause("sort asc, id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());

        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);
        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    /**
     * 根据条件查找推荐礼包数
     *
     * @param type 条件
     * @return 礼包数
     */
    @Override
    @Transactional(readOnly = true)
    public int recommendCount(int type, String platform) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria()
                .andRecommendEqualTo(true)
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        switch (type) {
            case 1://付费
                criteria.andTypeEqualTo(CardType.PAY.name());
                break;
            case 2://免费
                criteria.andTypeEqualTo(CardType.FREE.name());
                break;
            case 3://积分
                criteria.andTypeEqualTo(CardType.SCORE.name());
                break;
            default:
        }

        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        return cardMapper.countByExample(cardExample);
    }

    /**
     * 根据条件查找礼包
     *
     * @param key      查询关键字
     * @param platform 平台类型
     * @param page     分页
     * @return 礼包列表
     */
    @Override
    @Transactional(readOnly = true)
    public List<CardWithBLOBs> findByKey(String key, String platform, Page page) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andNameLike("%" + key + "%")
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());

        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        cardExample.setOrderByClause("sort asc, recommend desc, id desc");
        cardExample.setLimit(page.getPagesize());
        cardExample.setLimitOffset(page.getOffset());

        List<CardWithBLOBs> cardWithBLOBses = cardMapper.selectByExampleWithBLOBs(cardExample);
        for (Card card : cardWithBLOBses) {
            int total = codeService.findCountByCard(card.getId());
            int assignTotal = codeService.findCountAssignByCard(card.getId());
            card.setTotal(total);
            card.setAssignTotal(assignTotal);
            card.setTao(isTao(card));
        }

        return cardWithBLOBses;
    }

    /**
     * 根据条件查找礼包数
     *
     * @param key      查询关键字
     * @param platform 平台类型
     * @return 礼包数
     */
    @Override
    @Transactional(readOnly = true)
    public int findCountByKey(String key, String platform) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andNameLike("%" + key + "%")
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());

        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        return cardMapper.countByExample(cardExample);
    }

    /**
     * 增加激活码总数量
     *
     * @param id    礼包ID
     * @param count 增加数量
     * @return 影响条数
     */
    @Override
    public int increaseTotal(int id, int count) {
        CardWithBLOBs card = cardMapper.selectByPrimaryKey(id);
        card.setTotal(card.getTotal() + Math.abs(count));
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    /**
     * 减少激活码总数量
     *
     * @param id    礼包ID
     * @param count 增加数量
     * @return 影响条数
     */
    @Override
    public int reduceTotal(int id, int count) {
        CardWithBLOBs card = cardMapper.selectByPrimaryKey(id);
        card.setTotal(card.getTotal() - Math.abs(count));
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    /**
     * 添加激活码总数量
     *
     * @param count 添加数量
     * @return 影响条数
     */
    @Override
    public int increaseAssignTotal(int id, int count) {
        CardWithBLOBs card = cardMapper.selectByPrimaryKey(id);
        card.setAssignTotal(card.getAssignTotal() + Math.abs(count));
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    /**
     * 减少激活码总数量
     *
     * @param id    礼包ID
     * @param count 减少数量
     * @return 影响条数
     */
    @Override
    public int reduceAssignTotal(int id, int count) {
        CardWithBLOBs card = cardMapper.selectByPrimaryKey(id);
        card.setAssignTotal(card.getAssignTotal() - Math.abs(count));
        return cardMapper.updateByPrimaryKeySelective(card);
    }

    /**
     * 根据游戏ID，找到该游戏下，礼包中库存激活码最多的礼包
     *
     * @param gameId 游戏ID
     * @return 礼包ID
     */
    @Override
    public int findMaxCountId(int gameId) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andGameIdEqualTo(gameId);
        cardExample.setLimitOffset(0);
        cardExample.setLimit(1);
        cardExample.setOrderByClause("(total-assign_total) desc");
        List<Card> cards = cardMapper.selectByExample(cardExample);
        if (cards == null || cards.size() == 0) {
            return 0;
        }

        return cards.get(0).getId();
    }

    @Override
    public int validCount(int gameId, String platform) {
        CardExample cardExample = new CardExample();
        CardExample.Criteria criteria = cardExample.createCriteria();
        criteria.andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andValidEqualTo(true)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());

        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        return cardMapper.countByExample(cardExample);
    }
}
