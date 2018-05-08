package com.yunding.news.web.control;
/*
 * @Name:朋友圈（实体类）
 * @Author:Farmerzhang
 * @Date: 2018/4/23
 * @Time: 11:21
 */import com.yunding.news.model.pojo.Comment;

import java.util.*;

public class commentfriendCircle_ {
    // 说说的内容
   private int fId;
   private String fcreateTime;
   private String fContent;
   private String username;
    // 评论的内容
    List<commentfriendCircle_> comments;
   private int cId;
   private int cUserId;  // 发说说人的id
    private String cUsername;
   private String c_time;
   private String cContent;
    // 点赞的内容
    private int status;

    // 图片的内容
    private String pUrl;


    public List<String> getLikeusernames() {
        return likeusernames;
    }

    public void setLikeusernames(List<String> likeusernames) {
        this.likeusernames = likeusernames;
    }

    private List<String> likeusernames;


    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(String fcreateTime) {
        this.fcreateTime = fcreateTime;
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
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

    public List<commentfriendCircle_> getComments() {
        return comments;
    }

    public void setComments(List<commentfriendCircle_> comments) {
        this.comments = comments;
        }



}
