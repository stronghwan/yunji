package com.yunding.news.model.dao.impl;

import com.yunding.news.model.dao.ICommonDao;

import java.io.Serializable;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/3-00-12
 */
public abstract class CommonDaoImpl<T extends Serializable> implements ICommonDao<T>{
    public int save(T t) {
        return 0;
    }

    public T findByUserName(String name) {
        return null;
    }

    @Override
    public int findUserId(String name) {
        return 0;
    }

    @Override
    public int save(int id) {
        return 0;
    }

    @Override
    public T findByPerUserID(String name) {
        return null;
    }
}
