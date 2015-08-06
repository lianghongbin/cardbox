package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.GameType;
import com.gamesky.card.core.model.GameTypeExample;
import com.gamesky.card.dao.mapper.GameTypeMapper;
import com.gamesky.card.service.GameTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
@Service
@Transactional
public class GameTypeServiceImpl implements GameTypeService {

    @Autowired
    private GameTypeMapper gameTypeMapper;

    @Override
    public int save(GameType gameType) {
        return gameTypeMapper.insert(gameType);
    }

    @Override
    public List<GameType> findByGame(int gameId) {
        GameTypeExample gameTypeExample = new GameTypeExample();
        gameTypeExample.createCriteria().andGameIdEqualTo(gameId);

        return gameTypeMapper.selectByExample(gameTypeExample);
    }

    @Override
    public List<GameType> findByType(String type, Page page) {
        GameTypeExample gameTypeExample = new GameTypeExample();
        gameTypeExample.createCriteria().andTypeEqualTo(type);
        gameTypeExample.setLimitOffset(page.getOffset());
        gameTypeExample.setLimit(page.getPagesize());

        return gameTypeMapper.selectByExample(gameTypeExample);
    }
}
