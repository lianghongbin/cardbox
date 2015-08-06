package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Types;
import com.gamesky.card.core.model.TypesExample;
import com.gamesky.card.dao.mapper.TypesMapper;
import com.gamesky.card.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * lianghongbin on 15/8/6.
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypesMapper typesMapper;

    @Override
    public int save(Types types) {
        return typesMapper.insert(types);
    }

    @Override
    public int remove(int id) {
        return typesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Types find(int id) {
        return typesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Types> findAll() {
        return typesMapper.selectByExample(new TypesExample());
    }
}
