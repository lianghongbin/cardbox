package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.MyGame;
import com.gamesky.card.core.model.MyGameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyGameMapper {
    int countByExample(MyGameExample example);

    int deleteByExample(MyGameExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MyGame record);

    int insertSelective(MyGame record);

    List<MyGame> selectByExample(MyGameExample example);

    MyGame selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MyGame record, @Param("example") MyGameExample example);

    int updateByExample(@Param("record") MyGame record, @Param("example") MyGameExample example);

    int updateByPrimaryKeySelective(MyGame record);

    int updateByPrimaryKey(MyGame record);
}