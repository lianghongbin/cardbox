package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Hot;

import java.util.List;

/**
 * Created on 7/2/15.
 *
 * @Author lianghongbin
 */
public interface HotService {

    /**
     * 热词添加
     * @param hot 热词
     * @return 影响条数
     */
    int save(Hot hot);

    /**
     * 热词删除
     * @param id 热词ID
     * @return 影响条数
     */
    int remove(int id);

    /**
     * 热词修改
     * @param hot 热词
     * @return 修改
     */
    int update(Hot hot);

    List<Hot> findAll(String platform, Page page);

    int findCount(String platform);
}
