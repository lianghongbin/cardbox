package com.gamesky.card.service.impl;

import com.gamesky.card.core.Constants;
import com.gamesky.card.core.model.Invite;
import com.gamesky.card.core.model.InviteExample;
import com.gamesky.card.core.model.User;
import com.gamesky.card.dao.mapper.InviteMapper;
import com.gamesky.card.service.InviteService;
import com.gamesky.card.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * lianghongbin on 15/9/7.
 */
@Service
@Transactional
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteMapper inviteMapper;
    @Autowired
    private UserService userService;

    @Override
    public int save(Invite invite) {
        User user = userService.findByPhone(invite.getPhone());
        user.setMoney(user.getMoney() + 1);
        user.setScore(user.getScore() + 50);
        userService.update(user);
        invite.setInvitedTime(System.currentTimeMillis());
        return inviteMapper.insert(invite);
    }

    @Override
    public List<Invite> findByUser(String phone) {
        InviteExample inviteExample = new InviteExample();
        inviteExample.createCriteria().andPhoneEqualTo(phone);
        inviteExample.setOrderByClause("invited_time desc");
        return inviteMapper.selectByExample(inviteExample);
    }

    @Override
    public int findCountByUser(String phone) {
        InviteExample inviteExample = new InviteExample();
        inviteExample.createCriteria().andPhoneEqualTo(phone);
        return inviteMapper.countByExample(inviteExample);
    }
}
