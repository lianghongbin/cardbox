package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Photo;
import com.gamesky.card.core.model.PhotoExample;
import com.gamesky.card.dao.mapper.PhotoMapper;
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
    public int delete(int id) {
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
        photoExample.setLimit(page.getSize());
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
