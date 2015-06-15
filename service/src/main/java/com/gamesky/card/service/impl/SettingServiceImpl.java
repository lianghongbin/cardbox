package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Setting;
import com.gamesky.card.core.model.SettingExample;
import com.gamesky.card.dao.mapper.SettingMapper;
import com.gamesky.card.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 6/15/15.
 *
 * @Author lianghongbin
 */
@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingMapper settingMapper;

    /**
     * 设置获保存系统设置
     *
     * @param setting 系统设置
     * @return 影响条数
     */
    @Override
    public int saveOrUpload(Setting setting) {
        SettingExample settingExample = new SettingExample();
        settingExample.createCriteria().andVEqualTo(setting.getV());
        List<Setting> settingList = settingMapper.selectByExample(settingExample);
        if (settingList == null || settingList.size() == 0) {
            return settingMapper.insert(setting);
        }

        return settingMapper.updateByPrimaryKeySelective(setting);
    }

    /**
     * 获得系统保存信息
     *
     * @param version 版本号
     * @return 系统信息
     */
    @Override
    public Setting find(String version) {
        SettingExample settingExample = new SettingExample();
        settingExample.createCriteria().andVEqualTo(version);
        List<Setting> settingList = settingMapper.selectByExample(settingExample);
        if (settingList == null || settingList.size() == 0) {
            return null;
        }

        return settingList.get(0);
    }
}
