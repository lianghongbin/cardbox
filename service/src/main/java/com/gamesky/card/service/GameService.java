package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Game;
import com.gamesky.card.core.model.GameExample;

import java.util.List;

/**
 * 游戏服务接口类
 * Created on 2015/6/9.
 *
 * @Author lianghongbin
 */
public interface GameService {

    /**
     * 添加、保存游戏
     *
     * @param game 游戏对象
     * @return 影响条数
     */
    int save(Game game);

    /**
     * 删除一个游戏
     *
     * @param id 游戏ID
     * @return 影响条数
     */
    int remove(int id);

    /**
     * 修改一个游戏
     *
     * @param game 游戏对象
     * @return 影响条数
     */
    int update(Game game);

    /**
     * 根据ID获取一个游戏对象
     *
     * @param id 游戏ID
     * @return 游戏对象
     */
    Game find(int id);

    /**
     * 推荐一个游戏
     *
     * @param id        游戏ID
     * @param recommend 是否推荐
     * @return 影响条数
     */
    int recommend(int id, boolean recommend);

    /**
     * 获取所有的游戏列表
     *
     * @param page 分页参数
     * @return 游戏列表
     */
    List<Game> findAll(Page page);

    /**
     * 获取所有的游戏数量
     *
     * @return 游戏数量
     */
    int findCount();

    /**
     * 获取所有的游戏包名
     * @return 游戏包名列表
     */
    List<String> findPackages();

    /**
     * 取出推荐列表
     *
     * @param page 分页参数
     * @return 游戏列表
     */
    List<Game> findRecommend(Page page);

    /**
     * 取出推荐的游戏个数
     * @return 游戏数量
     */
    int findCountRecommend();

    /**
     * 根据条件获取游戏列表
     *
     * @param gameExample 查询条件
     * @return 游戏列表
     */
    List<Game> findByCondition(GameExample gameExample);

    /**
     * 根据条件获取游戏数量
     *
     * @param gameExample 查询条件
     * @return 游戏数量
     */
    int findCountByCondition(GameExample gameExample);
}
