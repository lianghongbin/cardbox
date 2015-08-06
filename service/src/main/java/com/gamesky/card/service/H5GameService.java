package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.H5Game;
import com.gamesky.card.core.model.H5GameExample;

import java.util.List;

/**
 * lianghongbin on 15/8/5.
 */
public interface H5GameService {

    int remove(int aid);

    int update(H5Game h5Game);

    int sort(int aid, int sort);

    int save(H5Game h5Game);

    int save(List<H5Game> games);

    H5Game find(int aid);

    int recommend(int aid);
    int unrecommend(int aid);

    List<H5Game> findAll(Page page, String orderByClause);

    int findCount();

    List<H5Game> findAllRecommend(Page page, String orderByClause);

    int findRecommendCount();

    List<H5Game> findByCondition(H5GameExample h5GameExample);

    int findCountByCondition(H5GameExample h5GameExample);
}
