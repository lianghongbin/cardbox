package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Photo;
import com.gamesky.card.core.model.PhotoExample;
import com.gamesky.card.dao.mapper.PhotoMapper;
import com.gamesky.card.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 6/9/15.
 *
 * @Author lianghongbin
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    /**
     * 添加一个图片
     *
     * @param photo 图片对象
     * @return 影响条数
     */
    @Override
    public int save(Photo photo) {
        return photoMapper.insert(photo);
    }

    /**
     * 删除一个图片
     *
     * @param id 图片ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        return photoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改一个图片
     *
     * @param photo 图片ID
     * @return 影响条数
     */
    @Override
    public int update(Photo photo) {
        return photoMapper.updateByPrimaryKeySelective(photo);
    }

    /**
     * 获取所有图片
     *
     * @param page 分页参数
     * @return 图片列表
     */
    @Override
    public List<Photo> findAll(Page page) {
        PhotoExample photoExample = new PhotoExample();
        photoExample.createCriteria().andIdGreaterThan(0);
        photoExample.setLimitOffset(page.getOffset());
        photoExample.setLimit(page.getPagesize());
        photoExample.setOrderByClause("id desc");
        return photoMapper.selectByExample(photoExample);
    }

    /**
     * 获取所有图片数
     *
     * @return 图片数
     */
    @Override
    public int findCount() {
        PhotoExample photoExample = new PhotoExample();
        photoExample.createCriteria().andIdGreaterThan(0);
        return photoMapper.countByExample(photoExample);
    }

    /**
     * 根据游戏ID获取游戏中的图片
     *
     * @param gameId 游戏ID
     * @param page   分页参数
     * @return 图片列表
     */
    @Override
    public List<Photo> findByGame(int gameId, Page page) {
        PhotoExample photoExample = new PhotoExample();
        photoExample.createCriteria().andTypeEqualTo("GAME")
                .andItemIdEqualTo(gameId);
        photoExample.setOrderByClause("id desc");
        photoExample.setLimitOffset(page.getOffset());
        photoExample.setLimit(page.getPagesize());
        return photoMapper.selectByExample(photoExample);
    }

    /**
     * 根据礼包ID获取礼包中的图片
     *
     * @param cardId 礼包ID
     * @param page   分页参数
     * @return 图片列表
     */
    @Override
    public List<Photo> findByCard(int cardId, Page page) {
        PhotoExample photoExample = new PhotoExample();
        photoExample.createCriteria().andTypeEqualTo("CARD")
                .andItemIdEqualTo(cardId);
        photoExample.setOrderByClause("id desc");
        photoExample.setLimitOffset(page.getOffset());
        photoExample.setLimit(page.getPagesize());
        return photoMapper.selectByExample(photoExample);
    }

    /**
     * 根据条件查询图片
     *
     * @param photoExample 查询条件
     * @return 图片列表
     */
    @Override
    public List<Photo> findByCondition(PhotoExample photoExample) {
        return photoMapper.selectByExample(photoExample);
    }

    /**
     * 根据条件查询图片数量
     *
     * @param photoExample 查询条件
     * @return 图片数量
     */
    @Override
    public int findCountByCondition(PhotoExample photoExample) {
        return photoMapper.countByExample(photoExample);
    }
}
