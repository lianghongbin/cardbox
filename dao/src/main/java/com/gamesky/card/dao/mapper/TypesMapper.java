package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Types;
import com.gamesky.card.core.model.TypesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TypesMapper {
    int countByExample(TypesExample example);

    int deleteByExample(TypesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Types record);

    int insertSelective(Types record);

    List<Types> selectByExample(TypesExample example);

    Types selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Types record, @Param("example") TypesExample example);

    int updateByExample(@Param("record") Types record, @Param("example") TypesExample example);

    int updateByPrimaryKeySelective(Types record);

    int updateByPrimaryKey(Types record);
}