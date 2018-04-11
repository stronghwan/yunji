package com.yunding.news.model.service;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-12-41
 */
public interface ICommonService<T extends java.io.Serializable> {
    int save(T t);
    T findByUserName(String name);
    T findByPerUserID(String name);
}
