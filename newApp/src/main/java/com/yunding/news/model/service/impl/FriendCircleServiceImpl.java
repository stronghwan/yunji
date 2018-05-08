package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.FriendCircle;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/27-15-31
 */
public class FriendCircleServiceImpl extends CommonServiceImpl<FriendCircle>{
    @Override
    public int save(FriendCircle friendCircle) {
        return DaoFactory.getDao("fc").save(friendCircle);
    }

    @Override
    public List<FriendCircle> findAll() {
        return DaoFactory.getDao("fc").findAll();
    }

    @Override
    public List<FriendCircle> findByUserId(int id) {
        return DaoFactory.getDao("fc").findByUserId(id);
    }
}
