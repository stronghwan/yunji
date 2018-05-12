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
   private int fid;
   private String fcreate_time;
   private String fcontent;
   private String user_name;
    // 评论的内容
    List<commentfriendCircle_> comments;
   private int cid;
   private int cuserId;  // 发说说人的id
    private String cuser_name;
   private String ccreate_time;
   private String ccontent;

   // 点赞的内容
    private int status;
    private List<String> luser_name;
    private  String userselfname;
    // 图片的内容
    private String purl;

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFcreate_time() {
        return fcreate_time;
    }

    public void setFcreate_time(String fcreate_time) {
        this.fcreate_time = fcreate_time;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public List<commentfriendCircle_> getComments() {
        return comments;
    }

    public void setComments(List<commentfriendCircle_> comments) {
        this.comments = comments;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getCuserId() {
        return cuserId;
    }

    public void setCuserId(int cuserId) {
        this.cuserId = cuserId;
    }

    public String getCuser_name() {
        return cuser_name;
    }

    public void setCuser_name(String cuser_name) {
        this.cuser_name = cuser_name;
    }

    public String getCcreate_time() {
        return ccreate_time;
    }

    public void setCcreate_time(String ccreate_time) {
        this.ccreate_time = ccreate_time;
    }

    public String getCcontent() {
        return ccontent;
    }

    public void setCcontent(String ccontent) {
        this.ccontent = ccontent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getLuser_name() {
        return luser_name;
    }

    public void setLuser_name(List<String> luser_name) {
        this.luser_name = luser_name;
    }

    public String getUserselfname() {
        return userselfname;
    }

    public void setUserselfname(String userselfname) {
        this.userselfname = userselfname;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
