package com.yunding.news.model.service.impl;

import com.yunding.news.model.service.ICommonService;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/9-12-46
 */
public abstract class CommonServiceImpl<T extends java.io.Serializable> implements ICommonService<T> {
    public int save(T t) {
        return 0;
    }

    public T findByUserName(String name) {
        return null;
    }

    @Override
    public int saveByStep(T t) {
        return 0;
    }

    @Override
    public int saveByStepTwo(T t) {
        return 0;
    }

    @Override
    public int saveByStepThree(T t) {
        return 0;
    }

    @Override
    public int modifiedUserInfo(T t) {
        return 0;
    }

    @Override
    public T findByPerUserID(String name) {
        return null;
    }

    @Override
    public int findUserId(String name) {
        return 0;
    }

    @Override
    public List<T> findAttByUserId(int id) {
        return null;
    }

    @Override
    public int mosdifiedUserInfo(String name) {
        return 0;
    }

    @Override
    public int save(int id) {
        return 0;
    }

    @Override
    public String findUserName(int id) {
        return null;
    }

    @Override
    public List<T> findByUserId(int id) {
        return null;
    }

    @Override
    public T findByUserIdSingle(int id) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findCommentFriend(String name) {
        return null;
    }
}
