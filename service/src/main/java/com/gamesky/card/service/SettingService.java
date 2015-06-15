package com.gamesky.card.service;

import com.gamesky.card.core.model.Setting;

/**
 * Created on 6/15/15.
 *
 * @Author lianghongbin
 */
public interface SettingService {

    /**
     * 设置获保存系统设置
     * @param setting 系统设置
     * @return 影响条数
     */
    int saveOrUpload(Setting setting);

    /**
     * 获得系统保存信息
     * @param version 版本号
     * @return 系统信息
     */
    Setting find(String version);
}
