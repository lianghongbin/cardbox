package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.GameType;
import com.gamesky.card.core.model.GameTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GameTypeMapper {
    int countByExample(GameTypeExample example);

    int deleteByExample(GameTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GameType record);

    int insertSelective(GameType record);

    List<GameType> selectByExample(GameTypeExample example);

    GameType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GameType record, @Param("example") GameTypeExample example);

    int updateByExample(@Param("record") GameType record, @Param("example") GameTypeExample example);

    int updateByPrimaryKeySelective(GameType record);

    int updateByPrimaryKey(GameType record);
}