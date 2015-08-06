package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.GameType;

import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
public interface GameTypeService {

    int save(GameType gameType);

    List<GameType> findByGame(int gameId);

    List<GameType> findByType(String type, Page page);
}
