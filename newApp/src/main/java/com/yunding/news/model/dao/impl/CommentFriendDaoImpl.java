package com.yunding.news.model.dao.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.*;

import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/20-14-58
 */
public class CommentFriendDaoImpl extends CommonDaoImpl<CommentFriendCircle> {

    @Override
    public List<CommentFriendCircle> findCommentFriend(String name) {
        List<Attention> aList = DaoFactory.getDao("attention").findAttByUserId(findUserIdPersonal(name));
        List<CommentFriendCircle> cfList = null;
        CommentFriendCircle commentFriendCircle = new CommentFriendCircle();
        for (Attention list : aList) {
            final int aUserId = list.getAuserId();
            String aUserName = list.getAuserName();
            List<FriendCircle> fList = DaoFactory.getDao("fc").findByUserId(aUserId);
            List<Comment> cList = null;
            List<String> lList = null;
            commentFriendCircle.setFriendCircleList(fList);
            for (FriendCircle flist : fList) {
                // 将说说内容放入
                cList = DaoFactory.getDao("comment").findByUserId(flist.getfId());
                lList = DaoFactory.getDao("like").findUserNameByFId(flist.getfId());
                Pictures pictures = (Pictures) DaoFactory.getDao("picture").findByUserIdSingle(flist.getfId());
                Likes likes = (Likes) DaoFactory.getDao("like").findByUserIdSingle(flist.getfId());
                Account account = (Account) DaoFactory.getDao("user").findByUserName(aUserName);
                commentFriendCircle.setNikeName(account.getNickName());
                commentFriendCircle.setStatus(likes.getStatus());
                commentFriendCircle.setpUrl(pictures.getUrl());
                commentFriendCircle.setCommentList(cList);
                commentFriendCircle.setLikesUserName(lList);
            }
            cfList.add(commentFriendCircle);
//            Pictures pictures = (Pictures) DaoFactory.getDao("picture").findByUserId(aUserId);
//            // 将返回值为对象的放这 通过id查询点赞的内容
//            Likes likes = (Likes) DaoFactory.getDao("like").findByUserId(aUserId);
//            // 将返回值为对象的放这 通过id查询的朋友圈
//            FriendCircle friendCircle = (FriendCircle) DaoFactory.getDao("fc").findByUserId(aUserId);
//            // 将返回值为对象的放这 通过id查询的评论
//            Comment comment = (Comment) DaoFactory.getDao("comment").findByUserId(aUserId);
//            // 将这两个对象的值放在CommentFriendCircle这个对象中。
//            CommentFriendCircle commentFriendCircle = new CommentFriendCircle();
//            commentFriendCircle.setUsername(friendCircle.getUserName());
//            commentFriendCircle.setCreateTime(friendCircle.getCreateTime());
//            commentFriendCircle.setfContent(friendCircle.getfContent());
//            commentFriendCircle.setcContent(comment.getcContent());
//            commentFriendCircle.setStatus(likes.getStatus());
//            commentFriendCircle.setpUrl(pictures.getUrl());
//            cfList.add(commentFriendCircle);
        }
        return cfList;
    }
}