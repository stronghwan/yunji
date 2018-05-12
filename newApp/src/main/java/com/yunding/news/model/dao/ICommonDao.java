package com.yunding.news.model.dao;

import com.yunding.news.model.pojo.Account;

import java.io.Serializable;
import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/2-23-53
 */
public interface ICommonDao<T extends Serializable> {
    int save(T t);

    /**
     * 通过用户名添加注册信息
     * @param t
     * @return
     */
    int saveByStep(T t);
    int saveByStepTwo(T t);
    int saveByStepThree(T t);
    T findByUserName(String name);
    int modifiedUserInfo(T t);

    /**
     * 通过用户名修改状态
     * @param name
     * @return
     */
    int modifiedUserInfo(String name);
    /**
     * 通过登录用户注册的用户名查找此用户的id
     * @param name 用户名
     * @return id
     */
    int findUserIdPersonal(String name);
    int findUserId(String name);

    /**
     * 通过注册表中的id找到对应的用户名
     * @param id
     * @return
     */
    String findUserName(int id);

    /**
     * 通过传入的id查找对象，主要用于朋友圈
     * @param id
     * @return
     */
    List<T> findByUserId(int id);
    T findByUserIdSingle(int id);

    /**
     *   通过前端传入的email判断
     * @param email 返回的是电子邮箱
     * @return
     */
    String findByuserEmail(String email);
    /**
     * 通过朋友圈的id找到点赞名字的集合
     * @param id 朋友圈的id
     * @return
     */
    List<String> findUserNameByFId(int id);

    /**
     * 用来给其他的表增加用户注册时注册表产生的id
     * @param id
     * @return
     */
    int save(int id);

    /**
     * 查询通过personalCenter表的外键user_id
     * @param name
     * @return
     */
    T findByPerUserID(String name);

    /**
     * 传入用户名，用户名传入里面封装的方法得到注册id
     * @param
     * @return 所有被关注人的集合
     */
    List<T> findAttByUserId(int id);
    List<T> findAll();
    List<T> findCommentFriend(String name);
    List<T> findCommon(String common);
}
