package com.gamesky.card.service;

import com.gamesky.card.core.model.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2015/7/25.
 *
 * @Author lianghongbin
 */
public interface AdminService {

    public int save(Admin admin);

    public int remove(int id);

    public int lock(int id);

    public int unlock(int id);

    public Admin findByPhone(String phone);

    public List<Admin> findAll();

    public int login(String phone);
}
