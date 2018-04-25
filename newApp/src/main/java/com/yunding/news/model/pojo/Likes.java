package com.yunding.news.model.pojo;

import java.io.Serializable;

/**
 * 点赞实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/24-23-29
 */
public class Likes implements Serializable{

    private int lId;
    private int userId;
    private int status;
    private String userSelfName;
    private String  userByName;

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserSelfName() {
        return userSelfName;
    }

    public void setUserSelfName(String userSelfName) {
        this.userSelfName = userSelfName;
    }

    public String getUserByName() {
        return userByName;
    }

    public void setUserByName(String userByName) {
        this.userByName = userByName;
    }
}