package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Likes;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/27-15-31
 */
public class LikesServiceImpl extends CommonServiceImpl<Likes>{
    @Override
    public int save(Likes likes) {
        return DaoFactory.getDao("like").save(likes);
    }

    @Override
    public Likes findByUserIdSingle(int id) {
        return (Likes) DaoFactory.getDao("like").findByUserIdSingle(id);
    }
}
