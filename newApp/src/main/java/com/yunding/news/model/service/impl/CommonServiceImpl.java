package com.yunding.news.model.service.impl;

import com.yunding.news.model.service.ICommonService;

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
}
