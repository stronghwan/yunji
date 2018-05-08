package com.yunding.news.model.pojo;

import java.io.Serializable;

/**
 *  评论的实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/3-23-48
 */
public class YunComments implements Serializable{

    private int cId;
    private int userId;
    private int fId;
    private String cContent;
    private String userName;

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
