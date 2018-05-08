package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Comment;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/27-15-30
 */
public class CommentServiceImpl extends CommonServiceImpl<Comment>{
    @Override
    public int save(Comment comment) {
        return DaoFactory.getDao("comment").save(comment);
    }

    @Override
    public List<Comment> findByUserId(int id) {
        return DaoFactory.getDao("comment").findByUserId(id);
    }
}
