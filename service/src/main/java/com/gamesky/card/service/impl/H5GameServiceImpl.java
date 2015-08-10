package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.Platform;
import com.gamesky.card.core.model.H5Game;
import com.gamesky.card.core.model.H5GameExample;
import com.gamesky.card.dao.mapper.H5GameMapper;
import com.gamesky.card.service.H5GameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * lianghongbin on 15/8/5.
 */
@Service
@Transactional
public class H5GameServiceImpl implements H5GameService {

    @Autowired
    private H5GameMapper h5GameMapper;

    @Override
    public int remove(int aid) {
        return h5GameMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public int update(H5Game h5Game) {
        h5Game.setUpdateTime(System.currentTimeMillis());
        return h5GameMapper.updateByPrimaryKeySelective(h5Game);
    }

    @Override
    public int sort(int aid, int sort) {
        H5Game h5Game = new H5Game();
        h5Game.setAid(aid);
        h5Game.setSort(sort);
        return h5GameMapper.updateByPrimaryKeySelective(h5Game);
    }

    @Override
    public int save(H5Game h5Game) {
        H5Game game = h5GameMapper.selectByPrimaryKey(h5Game.getAid());
        if (game == null) {
            h5Game.setSort(100);
            h5Game.setPlatform(Platform.ALL.name());
            h5Game.setRecommend(false);
            h5Game.setCreateTime(System.currentTimeMillis());
            return h5GameMapper.insert(h5Game);
        }

        return this.update(h5Game);
    }

    @Override
    public int save(List<H5Game> games) {
        int count = 0;
        for (H5Game game : games) {
            count += this.save(game);
        }

        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public H5Game find(int aid) {
        return h5GameMapper.selectByPrimaryKey(aid);
    }

    @Override
    public int recommend(int aid) {
        H5Game h5Game = new H5Game();
        h5Game.setAid(aid);
        h5Game.setRecommend(true);
        return h5GameMapper.updateByPrimaryKeySelective(h5Game);
    }

    @Override
    public int unrecommend(int aid) {
        H5Game h5Game = new H5Game();
        h5Game.setAid(aid);
        h5Game.setRecommend(false);
        return h5GameMapper.updateByPrimaryKeySelective(h5Game);
    }

    @Override
    @Transactional(readOnly = true)
    public List<H5Game> findAll(Page page, String orderByClause) {
        H5GameExample h5GameExample = new H5GameExample();
        if (StringUtils.isNotBlank(orderByClause)) {
            h5GameExample.setOrderByClause(orderByClause);
        }

        h5GameExample.setLimitOffset(page.getOffset());
        h5GameExample.setLimit(page.getPagesize());

        return h5GameMapper.selectByExample(h5GameExample);
    }

    @Override
    public int findCount() {
        return h5GameMapper.countByExample(new H5GameExample());
    }

    @Override
    @Transactional(readOnly = true)
    public List<H5Game> findAllRecommend(Page page, String orderByClause) {
        H5GameExample h5GameExample = new H5GameExample();
        h5GameExample.createCriteria().andRecommendEqualTo(true);
        if (StringUtils.isNotBlank(orderByClause)) {
            h5GameExample.setOrderByClause(orderByClause);
        }
        else {
            h5GameExample.setOrderByClause("sort asc, create_time desc");
        }

        h5GameExample.setLimitOffset(page.getOffset());
        h5GameExample.setLimit(page.getPagesize());
        return h5GameMapper.selectByExample(h5GameExample);
    }

    @Override
    @Transactional(readOnly = true)
    public int findRecommendCount() {
        H5GameExample h5GameExample = new H5GameExample();
        h5GameExample.createCriteria().andRecommendEqualTo(true);
        return h5GameMapper.countByExample(h5GameExample);
    }

    @Override
    @Transactional(readOnly = true)
    public List<H5Game> findByCondition(H5GameExample h5GameExample) {
        return h5GameMapper.selectByExample(h5GameExample);
    }

    @Override
    @Transactional(readOnly = true)
    public int findCountByCondition(H5GameExample h5GameExample) {
        return h5GameMapper.countByExample(h5GameExample);
    }
}
