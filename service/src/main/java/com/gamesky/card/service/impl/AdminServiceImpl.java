package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Admin;
import com.gamesky.card.core.model.AdminExample;
import com.gamesky.card.dao.mapper.AdminMapper;
import com.gamesky.card.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2015/7/25.
 *
 * @Author lianghongbin
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int save(Admin admin) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andPhoneEqualTo(admin.getPhone());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins != null && admins.size() > 0) {
            return 0;
        }

        return adminMapper.insert(admin);
    }

    @Override
    public int remove(int id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int lock(int id) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setLocked(true);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public int unlock(int id) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setLocked(false);
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Admin findByPhone(String phone) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andPhoneEqualTo(phone);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins == null || admins.size() == 0) {
            return null;
        }

        return admins.get(0);
    }

    @Override
    public List<Admin> findAll() {
        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause("top desc, id asc");
        return adminMapper.selectByExample(adminExample);
    }

    @Override
    public int login(String phone) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andPhoneEqualTo(phone);
        Admin admin = new Admin();
        admin.setLastTime(System.currentTimeMillis());

        return adminMapper.updateByExampleSelective(admin, adminExample);
    }
}
