package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Search;
import com.gamesky.card.core.model.SearchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SearchMapper {
    int countByExample(SearchExample example);

    int deleteByExample(SearchExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Search record);

    int insertSelective(Search record);

    List<Search> selectByExample(SearchExample example);

    Search selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Search record, @Param("example") SearchExample example);

    int updateByExample(@Param("record") Search record, @Param("example") SearchExample example);

    int updateByPrimaryKeySelective(Search record);

    int updateByPrimaryKey(Search record);
}