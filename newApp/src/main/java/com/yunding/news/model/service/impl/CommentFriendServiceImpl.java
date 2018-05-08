package com.yunding.news.model.service.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.CommentFriendCircle;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/27-15-30
 */
public class CommentFriendServiceImpl extends CommonServiceImpl<CommentFriendCircle>{
    @Override
    public List<CommentFriendCircle> findCommentFriend(String name) {
        return DaoFactory.getDao("fcMix").findCommentFriend(name);
    }
}
