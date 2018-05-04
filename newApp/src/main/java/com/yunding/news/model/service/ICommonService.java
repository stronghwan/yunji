package com.yunding.news.model.service;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-12-41
 */
public interface ICommonService<T extends java.io.Serializable> {
    int save(T t);
    int save(int id);
    int saveByStep(T t);
    int saveByStepTwo(T t);
    int saveByStepThree(T t);
    int modifiedUserInfo(T t);
    int mosdifiedUserInfo(String name);
    T findByUserName(String name);
    T findByPerUserID(String name);
    int findUserId(String name);
    String findUserName(int id);
    List<T> findByUserId(int id);
    T findByUserIdSingle(int id);
    List<T> findAttByUserId(int id);
    List<T> findAll();
    List<T> findCommentFriend(String name);
    String findByUserEmail(String email);
    List<T> findCommon(String common);
    List<String> findUserNameByFId(int id);
}
