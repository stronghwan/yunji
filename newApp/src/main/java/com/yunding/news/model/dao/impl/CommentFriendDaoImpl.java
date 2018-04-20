package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Attention;
import com.yunding.news.model.pojo.CommentFriendCircle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/20-14-58
 */
public class CommentFriendDaoImpl extends CommonDaoImpl<CommentFriendCircle> {

    @Override
    public List<CommentFriendCircle> findCommentFriend() {
        List<Attention> aList = DaoFactory.getDao("attention").findAttByUserId();
        for (Attention list : aList) {
            final int aUserId = list.getAuserId();
            // 将返回值为对象的放这 通过id查询的朋友圈
            // 将返回值为对象的放这 通过id查询的评论
            // 将这两个对象的值放在CommentFriendCircle这个对象中。
        }
        return null;
    }
}