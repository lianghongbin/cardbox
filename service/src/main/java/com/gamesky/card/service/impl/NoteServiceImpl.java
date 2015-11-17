package com.gamesky.card.service.impl;

import com.gamesky.card.core.model.Note;
import com.gamesky.card.core.model.NoteExample;
import com.gamesky.card.dao.mapper.NoteMapper;
import com.gamesky.card.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * lianghongbin on 15/11/12.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    /**
     * 发布一个站内系统消息
     *
     * @param note 消息
     * @return 影响条数
     */
    @Override
    public int save(Note note) {
        return noteMapper.insert(note);
    }

    /**
     * 删除一个站内系统消息
     *
     * @param id 消息ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        Note note = new Note();
        note.setId(id);
        note.setDeleted(true);
        return noteMapper.updateByPrimaryKeySelective(note);
    }

    /**
     * 获取所有系统消息列表
     *
     * @return 消息列表
     */
    @Override
    public List<Note> findAll() {
        NoteExample noteExample = new NoteExample();
        noteExample.setOrderByClause("deleted asc, id desc");
        return noteMapper.selectByExample(noteExample);
    }

    /**
     * 获取所有的未删除系统消息列表
     *
     * @return 消息列表
     */
    @Override
    public List<Note> findValidAll() {
        NoteExample noteExample = new NoteExample();
        noteExample.setOrderByClause("id desc");
        noteExample.createCriteria().andDeletedEqualTo(false);
        return noteMapper.selectByExample(noteExample);
    }

    /**
     * 根据ID获取指定消息
     *
     * @param id 系统消息ID
     * @return 消息
     */
    @Override
    public Note find(int id) {
        return noteMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据ID批量查询
     *
     * @param ids ID列表
     * @return 消息列表
     */
    @Override
    public List<Note> findByIds(List<Integer> ids) {
        NoteExample noteExample = new NoteExample();
        noteExample.createCriteria().andDeletedEqualTo(false).andIdIn(ids);
        return noteMapper.selectByExample(noteExample);
    }
}
