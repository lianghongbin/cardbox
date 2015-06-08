package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Splash;
import com.gamesky.card.core.model.SplashExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SplashMapper {
    int countByExample(SplashExample example);

    int deleteByExample(SplashExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Splash record);

    int insertSelective(Splash record);

    List<Splash> selectByExample(SplashExample example);

    Splash selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Splash record, @Param("example") SplashExample example);

    int updateByExample(@Param("record") Splash record, @Param("example") SplashExample example);

    int updateByPrimaryKeySelective(Splash record);

    int updateByPrimaryKey(Splash record);
}