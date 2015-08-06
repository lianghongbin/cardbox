package com.gamesky.card.service;

import com.gamesky.card.core.model.Types;

import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
public interface TypeService {

    int save(Types types);

    int remove(int id);

    Types find(int id);

    List<Types> findAll();
}
