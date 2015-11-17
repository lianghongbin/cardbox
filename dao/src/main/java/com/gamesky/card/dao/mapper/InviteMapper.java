package com.gamesky.card.dao.mapper;

import com.gamesky.card.core.model.Invite;
import com.gamesky.card.core.model.InviteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InviteMapper {
    int countByExample(InviteExample example);

    int deleteByExample(InviteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Invite record);

    int insertSelective(Invite record);

    List<Invite> selectByExample(InviteExample example);

    Invite selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Invite record, @Param("example") InviteExample example);

    int updateByExample(@Param("record") Invite record, @Param("example") InviteExample example);

    int updateByPrimaryKeySelective(Invite record);

    int updateByPrimaryKey(Invite record);
}