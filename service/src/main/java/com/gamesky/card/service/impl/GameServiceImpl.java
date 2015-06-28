package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.Platform;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.GameExample;
import com.gamesky.card.dao.mapper.GameMapper;
import com.gamesky.card.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2015/6/9.
 *
 * @Author lianghongbin
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameMapper gameMapper;

    /**
     * 添加、保存游戏
     *
     * @param game 游戏对象
     * @return 影响条数
     */
    @Override
    public int save(Game game) {
        return gameMapper.insert(game);
    }

    /**
     * 删除一个游戏
     *
     * @param id 游戏ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        return gameMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改一个游戏
     *
     * @param game 游戏对象
     * @return 影响条数
     */
    @Override
    public int update(Game game) {
        return gameMapper.updateByPrimaryKeySelective(game);
    }

    /**
     * 根据ID获取一个游戏对象
     *
     * @param id 游戏ID
     * @return 游戏对象
     */
    @Override
    public Game find(int id) {
        return gameMapper.selectByPrimaryKey(id);
    }

    /**
     * 推荐/取消推荐一个游戏
     *
     * @param id        游戏ID
     * @param recommend 是否推荐
     * @return 影响条数
     */
    @Override
    public int recommend(int id, boolean recommend) {
        Game game = new Game();
        game.setId(id);
        game.setRecommend(recommend);
        return gameMapper.updateByPrimaryKeySelective(game);
    }

    /**
     * 获取所有的游戏列表
     *
     * @param page 分页参数
     * @return 游戏列表
     */
    @Override
    public List<Game> findAll(Page page) {
        GameExample gameExample = new GameExample();
        gameExample.setLimit(page.getPagesize());
        gameExample.setLimitOffset(page.getOffset());
        gameExample.setOrderByClause("recommend desc, id desc");
        return gameMapper.selectByExample(gameExample);
    }

    /**
     * 获取所有的游戏数量
     *
     * @return 游戏数量
     */
    @Override
    public int findCount() {
        GameExample gameExample = new GameExample();
        gameExample.createCriteria().andIdGreaterThan(0);
        return gameMapper.countByExample(gameExample);
    }

    /**
     * 获取所有的游戏包名
     *
     * @param data 包名列表
     * @return 游戏包名列表
     */
    @Override
    public List<String> findPackages(List<String> data) {
        GameExample gameExample = new GameExample();
        gameExample.createCriteria()
                .andClosedEqualTo(false)
                .andIdentifierIn(data);
        gameExample.setOrderByClause("id desc");
        List<Game> games = gameMapper.selectByExample(gameExample);
        if (games == null || games.size() == 0) {
            return null;
        }

        List<String> packages = new ArrayList<>();
        for (Game game : games) {
            packages.add(game.getIdentifier());
        }

        return packages;
    }

    /**
     * 获取所有的游戏
     *
     * @param data 包名列表
     * @return 游戏列表
     */
    @Override
    public List<Game> findByPackages(List<String> data) {
        GameExample gameExample = new GameExample();
        gameExample.createCriteria()
                .andClosedEqualTo(false)
                .andIdentifierIn(data);
        gameExample.setOrderByClause("id desc");
        return gameMapper.selectByExample(gameExample);
    }

    /**
     * 取出推荐列表
     *
     * @param platform 平台类型
     * @param page 分页参数
     * @return 游戏列表
     */
    @Override
    public List<Game> findRecommend(String platform, Page page) {
        GameExample gameExample = new GameExample();
        GameExample.Criteria criteria = gameExample.createCriteria();
        criteria.andClosedEqualTo(false)
                .andRecommendEqualTo(true);
        if (!platform.equalsIgnoreCase("ALL")) {
            List<String> platforms = new ArrayList<>();
            platforms.add("ALL");
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        gameExample.setLimit(page.getPagesize());
        gameExample.setLimitOffset(page.getOffset());
        gameExample.setOrderByClause("sort asc");

        return gameMapper.selectByExampleWithBLOBs(gameExample);
    }

    /**
     * 取出推荐的游戏个数
     *
     * @param platform 平台类型
     * @return 游戏数量
     */
    @Override
    public int findCountRecommend(String platform) {
        GameExample gameExample = new GameExample();
        GameExample.Criteria criteria = gameExample.createCriteria();
        criteria.andClosedEqualTo(false)
                .andRecommendEqualTo(true);
        if (!platform.equalsIgnoreCase(Platform.ALL.name())) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            criteria.andPlatformIn(platforms);
        }

        return gameMapper.countByExample(gameExample);
    }

    /**
     * 根据条件获取游戏列表
     *
     * @param gameExample 查询条件
     * @return 游戏列表
     */
    @Override
    public List<Game> findByCondition(GameExample gameExample) {
        return gameMapper.selectByExample(gameExample);
    }

    /**
     * 根据条件获取游戏数量
     *
     * @param gameExample 查询条件
     * @return 游戏数量
     */
    @Override
    public int findCountByCondition(GameExample gameExample) {
        return gameMapper.countByExample(gameExample);
    }
}
