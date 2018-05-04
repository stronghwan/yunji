package com.yunding.news.model.pojo;

import java.io.Serializable;

/**
 * @云圈实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/3-23-47
 */
public class YunCircles implements Serializable{
    private int yId;
    private int userId;
    private String userName;
    private String yContent;
    private String kindof;

    public int getyId() {
        return yId;
    }

    public void setyId(int yId) {
        this.yId = yId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getyContent() {
        return yContent;
    }

    public void setyContent(String yContent) {
        this.yContent = yContent;
    }

    public String getKindof() {
        return kindof;
    }

    public void setKindof(String kindof) {
        this.kindof = kindof;
    }
}
