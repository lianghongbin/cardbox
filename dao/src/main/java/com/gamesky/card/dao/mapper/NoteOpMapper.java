package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.NoteOp;
import com.gamesky.card.core.model.NoteOpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoteOpMapper {
    int countByExample(NoteOpExample example);

    int deleteByExample(NoteOpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoteOp record);

    int insertSelective(NoteOp record);

    List<NoteOp> selectByExample(NoteOpExample example);

    NoteOp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoteOp record, @Param("example") NoteOpExample example);

    int updateByExample(@Param("record") NoteOp record, @Param("example") NoteOpExample example);

    int updateByPrimaryKeySelective(NoteOp record);

    int updateByPrimaryKey(NoteOp record);
}