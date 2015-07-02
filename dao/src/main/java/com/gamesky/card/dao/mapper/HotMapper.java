package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Hot;
import com.gamesky.card.core.model.HotExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotMapper {
    int countByExample(HotExample example);

    int deleteByExample(HotExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hot record);

    int insertSelective(Hot record);

    List<Hot> selectByExample(HotExample example);

    Hot selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hot record, @Param("example") HotExample example);

    int updateByExample(@Param("record") Hot record, @Param("example") HotExample example);

    int updateByPrimaryKeySelective(Hot record);

    int updateByPrimaryKey(Hot record);
}