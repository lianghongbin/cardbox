package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Share;
import com.gamesky.card.core.model.ShareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareMapper {
    int countByExample(ShareExample example);

    int deleteByExample(ShareExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertSelective(Share record);

    List<Share> selectByExample(ShareExample example);

    Share selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Share record, @Param("example") ShareExample example);

    int updateByExample(@Param("record") Share record, @Param("example") ShareExample example);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);
}