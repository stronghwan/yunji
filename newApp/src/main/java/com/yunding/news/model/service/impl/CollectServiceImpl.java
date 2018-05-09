package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Collect;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/4-15-33
 */
public class CollectServiceImpl extends CommonServiceImpl<Collect>{

    @Override
    public int save(Collect collect) {
        return DaoFactory.getDao("collect").save(collect);
    }

    @Override
    public List<Collect> findByUserId(int id) {
        return DaoFactory.getDao("collect").findByUserId(id);
    }
}
