package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Key;
import com.gamesky.card.core.model.KeyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KeyMapper {
    int countByExample(KeyExample example);

    int deleteByExample(KeyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Key record);

    int insertSelective(Key record);

    List<Key> selectByExample(KeyExample example);

    Key selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Key record, @Param("example") KeyExample example);

    int updateByExample(@Param("record") Key record, @Param("example") KeyExample example);

    int updateByPrimaryKeySelective(Key record);

    int updateByPrimaryKey(Key record);
}