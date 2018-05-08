package com.yunding.news.model.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @评论实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/20-13-49
 */
public class Comment implements Serializable{
    private int cId;
    private int cUserId; // 评论人的id
    private Date c_time;
    private String cContent;
    private int fId;    // 修改
    private String userName; //评论人的名字

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }
}
