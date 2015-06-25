package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Photo;
import com.gamesky.card.core.model.PhotoExample;

import java.util.List;

/**
 * 图片服务接口类
 * Created on 6/9/15.
 *
 * @Author lianghongbin
 */
public interface PhotoService {

    /**
     * 添加一个图片
     * @param photo 图片对象
     * @return 影响条数
     */
    int save(Photo photo);

    /**
     * 删除一个图片
     * @param id 图片ID
     * @return 影响条数
     */
    int remove(int id);

    /**
     * 修改一个图片
     * @param photo 图片ID
     * @return 影响条数
     */
    int update(Photo photo);

    /**
     * 获取所有图片
     * @param page 分页参数
     * @return 图片列表
     */
    List<Photo> findAll(Page page);

    /**
     * 根据游戏ID获取游戏中的图片
     * @param gameId 游戏ID
     * @param page 分页参数
     * @return 图片列表
     */
    List<Photo> findByGame(int gameId, Page page);

    /**
     * 根据礼包ID获取礼包中的图片
     * @param cardId 礼包ID
     * @param page 分页参数
     * @return 图片列表
     */
    List<Photo> findByCard(int cardId, Page page);

    /**
     * 获取所有图片数
     * @return 图片数
     */
    int findCount();

    /**
     * 根据条件查询图片
     * @param photoExample 查询条件
     * @return 图片列表
     */
    List<Photo> findByCondition(PhotoExample photoExample);

    /**
     * 根据条件查询图片数量
     * @param photoExample 查询条件
     * @return 图片数量
     */
    int findCountByCondition(PhotoExample photoExample);
}
