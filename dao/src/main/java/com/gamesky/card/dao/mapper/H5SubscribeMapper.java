package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.H5Subscribe;
import com.gamesky.card.core.model.H5SubscribeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface H5SubscribeMapper {
    int countByExample(H5SubscribeExample example);

    int deleteByExample(H5SubscribeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(H5Subscribe record);

    int insertSelective(H5Subscribe record);

    List<H5Subscribe> selectByExample(H5SubscribeExample example);

    H5Subscribe selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") H5Subscribe record, @Param("example") H5SubscribeExample example);

    int updateByExample(@Param("record") H5Subscribe record, @Param("example") H5SubscribeExample example);

    int updateByPrimaryKeySelective(H5Subscribe record);

    int updateByPrimaryKey(H5Subscribe record);
}