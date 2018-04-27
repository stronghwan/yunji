package com.yunding.news.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @二次修改  加一些集合属性
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/20-14-41
 */
public class CommentFriendCircle implements Serializable{
    // 说说的内容
    int fId;
    int fUserId;
    Date createTime;
    String fContent;
    String username;
    // 评论的内容
    int cId;
    int cUserId; // 发说说人的id
    Date c_time;
    String cContent;

    // 点赞的内容
    private int status;
    // 图片的内容
    private String pUrl;
    // 集合的添加
    List<FriendCircle>  friendCircleList = new ArrayList<FriendCircle>();
    List<Comment> commentList = new ArrayList<Comment>();
    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public int getfUserId() {
        return fUserId;
    }

    public void setfUserId(int fUserId) {
        this.fUserId = fUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getcUserId() {
        return cUserId;
    }

    public void setcUserId(int cUserId) {
        this.cUserId = cUserId;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getpUrl() {
        return pUrl;
    }

    public void setpUrl(String pUrl) {
        this.pUrl = pUrl;
    }

    public List<FriendCircle> getFriendCircleList() {
        return friendCircleList;
    }

    public void setFriendCircleList(List<FriendCircle> friendCircleList) {
        this.friendCircleList = friendCircleList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
