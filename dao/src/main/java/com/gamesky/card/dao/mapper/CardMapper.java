package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Card;
import com.gamesky.card.core.model.CardExample;
import com.gamesky.card.core.model.CardWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardMapper {
    int countByExample(CardExample example);

    int deleteByExample(CardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardWithBLOBs record);

    int insertSelective(CardWithBLOBs record);

    List<CardWithBLOBs> selectByExampleWithBLOBs(CardExample example);

    List<Card> selectByExample(CardExample example);

    CardWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardWithBLOBs record, @Param("example") CardExample example);

    int updateByExampleWithBLOBs(@Param("record") CardWithBLOBs record, @Param("example") CardExample example);

    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    int updateByPrimaryKeySelective(CardWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CardWithBLOBs record);

    int updateByPrimaryKey(Card record);
}