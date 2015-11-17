package com.gamesky.card.service;

import com.gamesky.card.core.model.Invite;

import java.util.List;

/**
 * lianghongbin on 15/9/7.
 */
public interface InviteService {

    int save(Invite invite);

    List<Invite> findByUser(String phone);

    int findCountByUser(String phone);
}
