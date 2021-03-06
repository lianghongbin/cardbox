package com.gamesky.card.service.impl;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.exceptions.LockException;
import com.gamesky.card.core.lock.GlobalLock;
import com.gamesky.card.core.lock.Lockable;
import com.gamesky.card.core.model.Code;
import com.gamesky.card.core.model.CodeExample;
import com.gamesky.card.dao.mapper.CodeMapper;
import com.gamesky.card.service.CardService;
import com.gamesky.card.service.CodeLock;
import com.gamesky.card.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private CardService cardService;
    @Autowired
    private GlobalLock<Lockable> globalLock;

    /**
     * 保存我的激活码
     *
     * @param code 激活码
     * @return 影响条数
     */
    @Override
    public int save(Code code) {
        cardService.increaseTotal(code.getCardId(), 1);
        return codeMapper.insert(code);
    }

    /**
     * 删除我的激活码
     *
     * @param id 激活码ID
     * @return 影响条数
     */
    @Override
    public int remove(int id) {
        Code code = find(id);
        cardService.reduceTotal(code.getCardId(), 1);
        return codeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据礼包ID，删除该礼包下的所有激活码
     *
     * @param cardId 礼包ID
     * @return 影响条数
     */
    @Override
    public int removeByCard(int cardId) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andCardIdEqualTo(cardId);
        int count = codeMapper.countByExample(codeExample);
        cardService.reduceTotal(cardId, count);
        return codeMapper.deleteByExample(codeExample);
    }

    /**
     * 更新我的激活码
     *
     * @param code 激活码
     * @return 影响条数
     */
    @Override
    public int update(Code code) {
        return codeMapper.updateByPrimaryKeySelective(code);
    }

    /**
     * 根据id查找激活码
     *
     * @param id 激活码ID
     * @return 激活码
     */
    @Override
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public int findCountByCondition(CodeExample codeExample) {
        return codeMapper.countByExample(codeExample);
    }

    /**
     * 为某人分一个礼包的激活码
     *
     * @param cardId 礼包ID
     * @param phone  手机号
     * @return 影响条数
     */
    @Override
    public String assign(int cardId, String phone) {
        Code code = findOne(cardId);
        if (code == null) {
            return null;
        }

        code.setAssigned(true);
        code.setPhone(phone);
        code.setAssignTime(System.currentTimeMillis());
        update(code);

        return code.getCode();
    }

    /**
     * 使用某个激活码
     *
     * @param gameId 游戏ID
     * @param code   激活码
     * @param phone  手机号
     * @return 影响条数
     */
    @Override
    public int used(int gameId, String code, String phone) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria()
                .andGameIdEqualTo(gameId)
                .andCodeEqualTo(code)
                .andPhoneEqualTo(phone)
                .andAssignedEqualTo(true);

        List<Code> codes = codeMapper.selectByExample(codeExample);
        if (codes == null || codes.size() == 0) {
            return 0;
        }

        Code assignCode = codes.get(0);
        assignCode.setUsed(true);
        return update(assignCode);
    }

    /**
     * 查找某个礼包下激活码最后领取时间
     *
     * @param cardId 礼包ID
     * @return 领取时间
     */
    @Transactional(readOnly = true)
    @Override
    public long lastAssignTime(int cardId) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andCardIdEqualTo(cardId);
        codeExample.setOrderByClause("assign_time desc");
        codeExample.setLimitOffset(0);
        codeExample.setLimit(1);

        List<Code> codes = codeMapper.selectByExample(codeExample);
        if (codes == null || codes.size() == 0 || codes.get(0).getAssignTime() == null) {
            return 0;
        }

        return codes.get(0).getAssignTime();
    }

    /**
     * 淘礼包
     *
     * @param count    每次取的个数
     * @return 激活码列表
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Code> tao(int cardId, int count) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andCardIdEqualTo(cardId);

        List<Code> codes = codeMapper.selectByExample(codeExample);
        if (codes == null || codes.size() == 0) {
            return null;
        }

        List<Integer> ids = new ArrayList<>();
        int[] random = randomArray(0, codes.size()-1, count);
        for (int index : random) {
            ids.add(codes.get(index).getId());
        }

        codeExample.clear();
        codeExample.createCriteria().andIdIn(ids);

        return codeMapper.selectByExample(codeExample);
    }

    private int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if (n > len) {
            n = len;
        }

        if (min == max) {
            return new int[] {min};
        }

        if (max < min) {
            return new int[0];
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min + len; i++) {
            source[i - min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }

    @Override
    public int findCountAssignByCard(int cardId) {
        CodeExample codeExample = new CodeExample();
        codeExample.createCriteria().andCardIdEqualTo(cardId).andAssignedEqualTo(true);
        return codeMapper.countByExample(codeExample);
    }
}
