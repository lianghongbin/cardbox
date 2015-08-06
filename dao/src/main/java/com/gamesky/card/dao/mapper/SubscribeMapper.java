package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Subscribe;
import com.gamesky.card.core.model.SubscribeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SubscribeMapper {
    int countByExample(SubscribeExample example);

    int deleteByExample(SubscribeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    List<Subscribe> selectByExample(SubscribeExample example);

    Subscribe selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Subscribe record, @Param("example") SubscribeExample example);

    int updateByExample(@Param("record") Subscribe record, @Param("example") SubscribeExample example);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);
}