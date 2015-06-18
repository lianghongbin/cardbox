package com.gamesky.card.service.impl;

import com.gamesky.card.core.CardType;
import com.gamesky.card.core.ErrorCode;
import com.gamesky.card.core.Page;
import com.gamesky.card.core.exceptions.LockException;
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
        card.setCreateTime(System.currentTimeMillis());
        Game game = new Game();
        game.setId(card.getGameId());
        game.setTotal(findCountByGame(card.getGameId()));
        gameService.update(game);
        return cardMapper.insert(card);
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
        card.setCloseTime(System.currentTimeMillis());
        card.setClosed(true);

        Game game = new Game();
        game.setId(card.getGameId());
        game.setTotal(findCountByGame(card.getGameId()));
        gameService.update(game);

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

        Game game = new Game();
        game.setId(card.getGameId());
        game.setTotal(findCountByGame(card.getGameId()));
        gameService.update(game);
        return cardMapper.updateByPrimaryKey(card);
    }

    /**
     * 修改卡包信息
     *
     * @param card 卡包
     * @return 影响条数
     */
    @Override
    public int update(Card card) {
        Game game = new Game();
        game.setId(card.getGameId());
        game.setTotal(findCountByGame(card.getGameId()));
        gameService.update(game);
        return cardMapper.updateByPrimaryKey(card);
    }

    /**
     * 分发、分配一个卡给某个用户
     *
     * @param id    卡包ID
     * @param phone 用户手机
     * @return 影响条数
     */
    @Override
    public int assign(final int id, String phone) {
        CardLock cardLock = new CardLock(id);

        try {
            if (!globalLock.acquire(cardLock, 3000)) {
                return 0;
            }

            boolean isLogin = userService.isLogin(phone);
            if (!isLogin) {
                return ErrorCode.NOT_LOGIN.getCode();
            }

            //校验该卡包是否有效
            CardExample cardExample = new CardExample();
            cardExample.createCriteria().andClosedEqualTo(false).andIdEqualTo(id).andOpenTimeLessThanOrEqualTo(System.currentTimeMillis()).andExpireTimeGreaterThan(System.currentTimeMillis());
            List<Card> cards = cardMapper.selectByExample(cardExample);
            if (cards == null || cards.size()==0) {
                return ErrorCode.DATA_EMPTY.getCode();
            }

            Card card = cards.get(0);

            List<Code> codes = codeService.findByCardAndPhone(id, phone,new Page());

            if (codes != null && codes.size() > 0) {
                return ErrorCode.ILLEGAL_OPERATE.getCode();
            }

            if (card.getTotal()<=card.getAssignTotal()) {
                return -1;
            }

            if(card.getType().equalsIgnoreCase(CardType.SCORE.name())) {
                //如果是扣分数的礼包，查看一下分数是否够，如果不够直接返回错误提示
                User user = userService.findByPhone(phone);
                if (user == null) {
                    return ErrorCode.ILLEGAL_ARGUMENT.getCode();
                }

                if (card.getScore() > user.getScore()) {
                    return ErrorCode.SCORE_NOT_ENOUGH.getCode();
                }

                user.setScore(user.getScore() - card.getScore());
                userService.update(user);
            }
            else if (card.getType().equalsIgnoreCase(CardType.PAY.name())) {
                logger.info("需要付费领取礼包");
            }

            card.setAssignTotal(card.getAssignTotal() + 1);

            Code code = codeService.findOne(id);
            if (code == null) {
                logger.error("礼包激活码没有导入");
                return ErrorCode.DATA_EMPTY.getCode();
            }

            code.setAssigned(true);
            code.setPhone(phone);
            code.setAssignTime(System.currentTimeMillis());
            codeService.update(code);

            return cardMapper.updateByPrimaryKey(card);

        } catch (LockException e) {
            return 0;
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
    public Card find(int id) {
        return cardMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页显示所有卡包
     *
     * @param page 分页条件
     * @return 卡包列表
     */
    @Override
    public List<CardWithBLOBs> findAll(Page page) {
        CardExample cardExample = new CardExample();
        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getPagesize());
        cardExample.setOrderByClause("id desc");
        return cardMapper.selectByExampleWithBLOBs(cardExample);
    }

    /**
     * 根据条件分页查询显示卡包
     *
     * @param cardExample 查询条件
     * @return 卡包列表
     */
    @Override
    public List<CardWithBLOBs> findByCondition(CardExample cardExample) {
        return cardMapper.selectByExampleWithBLOBs(cardExample);
    }

    /**
     * 根据条件分页查询显示卡包数量
     *
     * @param cardExample 查询条件
     * @return 卡包数量
     */
    @Override
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
    public List<CardWithBLOBs> findByGame(int gameId, Page page) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        cardExample.setLimitOffset(page.getOffset());
        cardExample.setLimit(page.getPagesize());
        cardExample.setOrderByClause("id desc");
        return cardMapper.selectByExampleWithBLOBs(cardExample);
    }

    /**
     * 查找某款游戏下的卡包类别数量
     *
     * @param gameId 游戏ID
     * @return 卡包类别数量
     */
    @Override
    public int findCountByGame(int gameId) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria()
                .andGameIdEqualTo(gameId)
                .andClosedEqualTo(false)
                .andOpenTimeLessThanOrEqualTo(System.currentTimeMillis())
                .andExpireTimeGreaterThan(System.currentTimeMillis());
        return cardMapper.countByExample(cardExample);
    }

    /**
     * 指查找礼包
     * @param ids 礼包ID集合
     * @return 礼包集合
     */
    @Override
    public List<CardWithBLOBs> findByIds(List<Integer> ids) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andIdIn(ids);
        return cardMapper.selectByExampleWithBLOBs(cardExample);
    }

    /**
     * 查询手机用户是否已经申领过该礼品包
     * @param id 礼包ID
     * @param phone 用户手机
     * @return true/false
     */
    public boolean hasAssign(int id, String phone) {
        List<Code> codes = codeService.findByCardAndPhone(id, phone, new Page());
        return codes != null && codes.size() > 0;
    }
}
