package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.MyCard;
import com.gamesky.card.core.model.MyCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyCardMapper {
    int countByExample(MyCardExample example);

    int deleteByExample(MyCardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyCard record);

    int insertSelective(MyCard record);

    List<MyCard> selectByExample(MyCardExample example);

    MyCard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyCard record, @Param("example") MyCardExample example);

    int updateByExample(@Param("record") MyCard record, @Param("example") MyCardExample example);

    int updateByPrimaryKeySelective(MyCard record);

    int updateByPrimaryKey(MyCard record);
}