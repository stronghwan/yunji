package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.YunLikes;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/4-15-31
 */
public class YunLikesServiceImpl extends CommonServiceImpl<YunLikes>{
    @Override
    public int save(YunLikes yunLikes) {
        return DaoFactory.getDao("yunLikes").save(yunLikes);
    }

    @Override
    public YunLikes findByUserIdSingle(int id) {
        return (YunLikes) DaoFactory.getDao("yunLikes").findByUserIdSingle(id);
    }

    @Override
    public int modifiedUserInfo(YunLikes yunLikes) {
        return DaoFactory.getDao("yunLikes").modifiedUserInfo(yunLikes);
    }
}
