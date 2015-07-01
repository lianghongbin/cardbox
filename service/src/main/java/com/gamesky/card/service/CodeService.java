package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.model.Code;
import com.gamesky.card.core.model.CodeExample;

import java.util.List;

/**
 * 领取的激活码管理接口类
 * Created on 2015/6/8.
 *
 * @Author lianghongbin
 */
public interface CodeService {

    /**
     * 保存我的激活码
     *
     * @param code 激活码
     * @return 影响条数
     */
    int save(Code code);

    /**
     * 删除我的激活码
     *
     * @param id 激活码ID
     * @return 影响条数
     */
    int remove(int id);

    /**
     * 根据礼包ID，删除该礼包下的所有激活码
     * @param cardId 礼包ID
     * @return 影响条数
     */
    int removeByCard(int cardId);

    /**
     * 更新我的激活码
     *
     * @param code 激活码
     * @return 影响条数
     */
    int update(Code code);

    /**
     * 根据id查找激活码
     *
     * @param id 激活码ID
     * @return 激活码
     */
    Code find(int id);

    /**
     * 查看某个礼包下的激活码
     *
     * @param cardId 礼包ID
     * @param page   分页参数
     * @return 激活码列表
     */
    List<Code> findByCard(int cardId, Page page);

    /**
     * 查看某个礼包下的激活码数量
     *
     * @param cardId 礼包ID
     * @return 激活码列表数量
     */
    int findCountByCard(int cardId);

    /**
     * 根据用户手机号查找该用户的激活码
     *
     * @param phone 手机号
     * @param page  分页参数
     * @return 激活码列表
     */
    List<Code> findByPhone(String phone, Page page);

    /**
     * 根据用户手机号查找该用户的激活码数量
     *
     * @param phone 手机号
     * @return 激活码数量
     */
    int findCountByPhone(String phone);

    /**
     * 根据用户手机号查找该用户的激活码
     *
     * @param page  分页参数
     * @return 激活码列表
     */
    List<Code> findAll(Page page);

    /**
     * 根据用户手机号查找该用户的激活码数量
     *
     * @return 激活码数量
     */
    int findCount();

    /**
     * 随机获取某种卡包下的一个未分配的激活码
     * @param cardId 卡包ID
     * @return 激活码实体
     */
    Code findOne(int cardId);

    /**
     * 根据手机号查看用户的激活码
     *
     * @param cardId 卡包ID
     * @param phone  用户手机号
     * @param page   分页参数
     * @return 激活码列表
     */
    public List<Code> findByCardAndPhone(int cardId, String phone, Page page);

    /**
     * 根据手机号查看用户的激活码
     *
     * @param cardId 卡包ID
     * @param phone  用户手机号
     * @return 激活码列表
     */
    public int findCountByCardAndPhone(int cardId, String phone);

    /**
     * 根据条件查询激活码列表
     * @param keyExample 查询条件
     * @return 激活码列表
     */
    List<Code> findByCondition(CodeExample keyExample);

    /**
     * 根据条件查询激活码列表数量
     * @param keyExample 查询条件
     * @return 激活码列表数量
     */
    int findCountByCondition(CodeExample keyExample);

    /**
     * 为某人分一个礼包的激活码
     * @param cardId 礼包ID
     * @param phone 手机号
     * @return 申请到的激活码
     */
    String assign(int cardId, String phone);

    /**
     * 使用某个激活码
     * @param gameId 游戏ID
     * @param code 激活码
     * @param phone 手机号
     * @return 影响条数
     */
    int used(int gameId, String code, String phone);
}
