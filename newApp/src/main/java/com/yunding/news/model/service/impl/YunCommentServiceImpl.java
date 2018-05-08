package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.YunComments;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/4-15-30
 */
public class YunCommentServiceImpl extends CommonServiceImpl<YunComments>{
    @Override
    public int save(YunComments yunComments) {
        return DaoFactory.getDao("yunComments").save(yunComments);
    }

    @Override
    public List<YunComments> findByUserId(int id) {
        return DaoFactory.getDao("yunComments").findByUserId(id);
    }
}
