package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.Platform;
import com.gamesky.card.core.model.Hot;
import com.gamesky.card.core.model.HotExample;
import com.gamesky.card.dao.mapper.HotMapper;
import com.gamesky.card.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 7/2/15.
 *
 * @Author lianghongbin
 */
@Service
public class HotServiceImpl implements HotService {

    @Autowired
    private HotMapper hotMapper;

    /**
     * 热词添加
     *
     * @param hot 热词
     * @return 影响条数
     */
    @Override
    public int save(Hot hot) {
        return hotMapper.insert(hot);
    }

    /**
     * 热词删除
     *
     * @param id 热词ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        return hotMapper.deleteByPrimaryKey(id);
    }

    /**
     * 热词修改
     *
     * @param hot 热词
     * @return 修改
     */
    @Override
    public int update(Hot hot) {
        return hotMapper.updateByPrimaryKeySelective(hot);
    }

    @Override
    public List<Hot> findAll(String platform, Page page) {
        HotExample hotExample = new HotExample();
        if (!"All".equalsIgnoreCase(platform)) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            hotExample.createCriteria().andPlatformIn(platforms);
        }

        hotExample.setLimitOffset(page.getOffset());
        hotExample.setLimit(page.getPagesize());
        hotExample.setOrderByClause("sort asc, id desc");
        return hotMapper.selectByExample(hotExample);
    }

    @Override
    public int findCount(String platform) {
        HotExample hotExample = new HotExample();
        if (!"All".equalsIgnoreCase(platform)) {
            List<String> platforms = new ArrayList<>();
            platforms.add(Platform.ALL.name());
            platforms.add(platform);
            hotExample.createCriteria().andPlatformIn(platforms);
        }

        return hotMapper.countByExample(hotExample);
    }
}
