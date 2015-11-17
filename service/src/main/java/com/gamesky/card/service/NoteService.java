package com.gamesky.card.service;

import com.gamesky.card.core.model.Note;

import java.util.List;

/**
 * lianghongbin on 15/11/12.
 */
public interface NoteService {

    /**
     * 发布一个站内系统消息
     * @param note 消息
     * @return 影响条数
     */
    int save(Note note);

    /**
     * 删除一个站内系统消息
     * @param id 消息ID
     * @return 影响条数
     */
    int remove(int id);

    /**
     * 获取所有系统消息列表
     * @return 消息列表
     */
    List<Note> findAll();

    /**
     * 获取所有的未删除系统消息列表
     * @return 消息列表
     */
    List<Note> findValidAll();

    /**
     * 根据ID获取指定消息
     * @param id 系统消息ID
     * @return 消息
     */
    Note find(int id);

    /**
     * 根据ID批量查询
     * @param ids ID列表
     * @return 消息列表
     */
    List<Note> findByIds(List<Integer> ids);
}
