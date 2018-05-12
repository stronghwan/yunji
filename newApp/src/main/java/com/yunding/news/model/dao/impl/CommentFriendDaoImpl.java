package com.yunding.news.model.dao.impl;

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.*;

import java.util.ArrayList;
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
        List<CommentFriendCircle> cfList = new ArrayList<CommentFriendCircle>();
        List<Comment> cListSave = new ArrayList<Comment>();
        List<String> lListSave = new ArrayList<String>();
        List<Comment> cList = new ArrayList<Comment>();
        List<String> lList = new ArrayList<String>();
        List<Likes> likesList = new ArrayList<Likes>();
        List<Pictures> picturesList = new ArrayList<Pictures>();
        CommentFriendCircle commentFriendCircle = null;
        for (Attention list : aList) {
            commentFriendCircle = new CommentFriendCircle();
            final int aUserId = list.getAuserId();
            String aUserName = list.getAuserName();
            List<FriendCircle> fList = DaoFactory.getDao("fc").findByUserId(aUserId);
            commentFriendCircle.setFriendCircleList(fList);
            for (FriendCircle flist : fList) {
                // 将说说内容放入
                cList = DaoFactory.getDao("comment").findByUserId(flist.getfId());
                // 加一个foreach循环
                for (Comment comment : cListSave) {
                    cListSave.add(comment);
                }
                lList = DaoFactory.getDao("like").findUserNameByFId(flist.getfId());
                for (String s : lList) {
                    lListSave.add(s);
                }
                Pictures pictures = (Pictures) DaoFactory.getDao("picture").findByUserIdSingle(flist.getfId());
                picturesList.add(pictures);
                Likes likes = (Likes) DaoFactory.getDao("like").findByUserIdSingle(flist.getfId());
                likesList.add(likes);
//                Account account = (Account) DaoFactory.getDao("user").findByUserName(aUserName);
//                commentFriendCircle.setNikeName(account.getNickName());
//                commentFriendCircle.setStatus(likes.getStatus());
//                commentFriendCircle.setpUrl(pictures.getUrl());
            }
            commentFriendCircle.setCommentList(cListSave);
            commentFriendCircle.setLikesUserName(lListSave);
            commentFriendCircle.setLikes(likesList);
            commentFriendCircle.setPictures(picturesList);
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
//            commentFriendCircle.setUsername(friendCircle.getPuser_name());
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