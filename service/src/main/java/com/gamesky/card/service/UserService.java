package com.gamesky.card.service;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.exceptions.CheckCodeInvalidException;
import com.gamesky.card.core.exceptions.CheckCodeWrongException;
import com.gamesky.card.core.model.User;
import com.gamesky.card.core.model.UserExample;

import java.util.List;

/**
 * 用户服务接口类
 * Created on 6/5/15.
 *
 * @Author lianghongbin
 */
public interface UserService {

    /**
     * 保存用户、添加用户
     *
     * @param user 用户对象
     * @return 影响条数
     */
    int save(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 影响条数
     */
    int delete(int id);

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 影响条数
     */
    int update(User user);

    /**
     * 根据ID查找用户
     *
     * @param id 用户ID
     * @return 查找到的用户
     */
    User find(int id);

    /**
     * 根据用户唯一手机查找用户
     *
     * @param phone 用户手机
     * @return 影响条数
     */
    User findByPhone(String phone);

    /**
     * 分页查找所有用户
     *
     * @param page          分页参数
     * @return 用户列表
     */
    List<User> findAll(Page page);

    /**
     * 查找所有用户数
     *
     * @return 用户数
     */
    int findCount();

    /**
     * 根据条件分页查找用户
     *
     * @param userExample 查找条件
     * @return 用户列表
     */
    List<User> findByCondition(UserExample userExample);

    /**
     * 根据条件查找用户数
     *
     * @param userExample 查找条件
     * @return 用户数
     */
    int findCountByCondition(UserExample userExample);

    /**
     * 根据用户手机号，判断用户是否在登录状态
     *
     * @param phone 手机号
     * @return true/false
     */
    boolean isLogin(final String phone);

    /**
     * 使用手机号和验证码进行登录
     *
     * @param phone     手机号
     * @param checkCode 验证码
     * @return 登录用户
     */
    User login(final String phone, String device, String checkCode) throws CheckCodeInvalidException, CheckCodeWrongException;

    /**
     * 手机号注销登录
     * @param phone 手机号
     * @return true/false
     */
    boolean logout(String phone);
}
