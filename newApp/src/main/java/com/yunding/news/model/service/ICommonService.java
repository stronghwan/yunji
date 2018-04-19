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
    int saveByStep(T t);
    int saveByStepTwo(T t);
    int saveByStepThree(T t);
    int modifiedUserInfo(T t);
    T findByUserName(String name);
    T findByPerUserID(String name);
    int findUserId(String name);
    List<T> findAttByUserId();
}
