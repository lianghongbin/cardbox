package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.H5Game;
import com.gamesky.card.core.model.H5GameExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface H5GameMapper {
    int countByExample(H5GameExample example);

    int deleteByExample(H5GameExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(H5Game record);

    int insertSelective(H5Game record);

    List<H5Game> selectByExample(H5GameExample example);

    H5Game selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") H5Game record, @Param("example") H5GameExample example);

    int updateByExample(@Param("record") H5Game record, @Param("example") H5GameExample example);

    int updateByPrimaryKeySelective(H5Game record);

    int updateByPrimaryKey(H5Game record);
}