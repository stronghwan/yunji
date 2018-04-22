package com.yunding.news.model.dao.impl;

import com.yunding.news.common.JdbcTemplate;
import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Attention;
import com.yunding.news.model.pojo.Comment;
import com.yunding.news.model.pojo.CommentFriendCircle;
import com.yunding.news.model.pojo.FriendCircle;

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
        List<CommentFriendCircle> cfList = null;
        for (Attention list : aList) {
            final int aUserId = list.getAuserId();
            // 将返回值为对象的放这 通过id查询的朋友圈
            FriendCircle friendCircle = (FriendCircle) DaoFactory.getDao("fc").findByUserId(aUserId);
            // 将返回值为对象的放这 通过id查询的评论
            Comment comment = (Comment) DaoFactory.getDao("comment").findByUserId(aUserId);
            // 将这两个对象的值放在CommentFriendCircle这个对象中。
            CommentFriendCircle commentFriendCircle = new CommentFriendCircle();
            commentFriendCircle.setCreateTime(friendCircle.getCreateTime());
            commentFriendCircle.setfContent(friendCircle.getfContent());
            commentFriendCircle.setcContent(comment.getcContent());
            cfList.add(commentFriendCircle);
        }
        return null;
    }
}