package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Focus;
import com.gamesky.card.core.model.FocusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FocusMapper {
    int countByExample(FocusExample example);

    int deleteByExample(FocusExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Focus record);

    int insertSelective(Focus record);

    List<Focus> selectByExample(FocusExample example);

    Focus selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Focus record, @Param("example") FocusExample example);

    int updateByExample(@Param("record") Focus record, @Param("example") FocusExample example);

    int updateByPrimaryKeySelective(Focus record);

    int updateByPrimaryKey(Focus record);
}