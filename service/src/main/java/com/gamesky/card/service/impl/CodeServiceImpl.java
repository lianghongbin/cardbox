package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.exceptions.LockException;
import com.gamesky.card.core.lock.GlobalLock;
import com.gamesky.card.core.lock.Lockable;
import com.gamesky.card.core.model.Code;
import com.gamesky.card.core.model.CodeExample;
import com.gamesky.card.dao.mapper.CodeMapper;
import com.gamesky.card.service.CodeLock;
import com.gamesky.card.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
@Service
@Transactional
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;
    @Autowired
    private GlobalLock<Lockable> globalLock;
    ;

    /**
     * 保存我的激活码
     *
     * @param code 激活码
     * @return 影响条数
     */
    @Override
    public int save(Code code) {
        return codeMapper.insert(code);
    }

    /**
     * 删除我的激活码
     *
     * @param id 激活码ID
     * @return 影响条数
     */
    @Override
    public int delete(int id) {
        return codeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新我的激活码
     *
     * @param code 激活码
     * @return 影响条数
     */
    @Override
    public int update(Code code) {
        return codeMapper.updateByPrimaryKey(code);
    }

    /**
     * 根据id查找激活码
     *
     * @param id 激活码ID
     * @return 激活码
     */
    @Override
    public Code find(int id) {
        return codeMapper.selectByPrimaryKey(id);
    }

    /**
     * 随机获取某种卡包下的一个未分配的激活码
     *
     * @param cardId 卡包ID
     * @return 激活码实体
     */
    @Override
    public Code findOne(final int cardId) {
        CodeLock codeLock = new CodeLock(cardId);

        try {
            if (!globalLock.acquire(codeLock, 3000)) {
                return null;
            }

            CodeExample codeExample = new CodeExample();
            codeExample.createCriteria().andCardIdEqualTo(cardId).andAssignedEqualTo(false);
            codeExample.setOrderByClause("id asc");
            List<Code> keys = codeMapper.selectByExample(codeExample);
            if (keys == null || keys.size() == 0) {
                return null;
            }

            return keys.get(0);
        } catch (LockException e) {
            return null;
        } finally {
            globalLock.release(codeLock);
        }
    }

    /**
     * 查看某个礼包下的激活码
     *
     * @param cardId 礼包ID
     * @param page   分页参数
     * @return 激活码列表
     */
    @Override
    public List<Code> findByCard(int cardId, Page page) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andCardIdEqualTo(cardId);
        codeExample.setLimitOffset(page.getOffset());
        codeExample.setLimit(page.getPagesize());
        codeExample.setOrderByClause("use_time desc, assign_time desc");
        return codeMapper.selectByExample(codeExample);
    }

    /**
     * 查看某个礼包下的激活码数量
     *
     * @param cardId 礼包ID
     * @return 激活码列表数量
     */
    @Override
    public int findCountByCard(int cardId) {
        CodeExample keyExample = new CodeExample();
        keyExample.createCriteria().andCardIdEqualTo(cardId);
        return codeMapper.countByExample(keyExample);
    }

    /**
     * 根据手机号查看用户的激活码
     *
     * @param phone 用户手机号
     * @param page  分页参数
     * @return 激活码列表
     */
    @Override
    public List<Code> findByPhone(String phone, Page page) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andPhoneEqualTo(phone);
        codeExample.setLimitOffset(page.getOffset());
        codeExample.setLimit(page.getPagesize());
        codeExample.setOrderByClause("use_time desc, assign_time desc");
        return codeMapper.selectByExample(codeExample);
    }

    /**
     * 根据手机号查看用户的激活码
     *
     * @param phone 用户手机号
     * @return 激活码列表
     */
    @Override
    public int findCountByPhone(String phone) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andPhoneEqualTo(phone);
        return codeMapper.countByExample(codeExample);
    }

    /**
     * 根据手机号查看用户的激活码
     *
     * @param cardId 卡包ID
     * @param phone  用户手机号
     * @param page   分页参数
     * @return 激活码列表
     */
    @Override
    public List<Code> findByCardAndPhone(int cardId, String phone, Page page) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andCardIdEqualTo(cardId).andPhoneEqualTo(phone).andAssignedEqualTo(true);
        codeExample.setLimitOffset(page.getOffset());
        codeExample.setLimit(page.getPagesize());
        codeExample.setOrderByClause("use_time desc, assign_time desc");
        return codeMapper.selectByExample(codeExample);
    }

    /**
     * 根据手机号查看用户的激活码
     *
     * @param cardId 卡包ID
     * @param phone  用户手机号
     * @return 激活码列表
     */
    @Override
    public int findCountByCardAndPhone(int cardId, String phone) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andCardIdEqualTo(cardId).andPhoneEqualTo(phone);
        return codeMapper.countByExample(codeExample);
    }

    /**
     * 根据用户的激活码
     *
     * @param page 分页参数
     * @return 激活码列表
     */
    @Override
    public List<Code> findAll(Page page) {
        CodeExample codeExample = new CodeExample();
        codeExample.setLimitOffset(page.getOffset());
        codeExample.setLimit(page.getPagesize());
        codeExample.setOrderByClause("use_time desc, assign_time desc");
        return codeMapper.selectByExample(codeExample);
    }

    /**
     * 根据用户手机号查找该用户的激活码数量
     *
     * @return 激活码数量
     */
    @Override
    public int findCount() {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andIdGreaterThan(0);
        return codeMapper.countByExample(codeExample);
    }

    /**
     * 根据条件查询激活码列表
     *
     * @param codeExample 查询条件
     * @return 激活码列表
     */
    @Override
    public List<Code> findByCondition(CodeExample codeExample) {
        return codeMapper.selectByExample(codeExample);
    }

    /**
     * 根据条件查询激活码列表数量
     *
     * @param codeExample 查询条件
     * @return 激活码列表数量
     */
    @Override
    public int findCountByCondition(CodeExample codeExample) {
        return codeMapper.countByExample(codeExample);
    }
}
