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
    int cId;
    int cUserId; // 发说说人的id
    Date c_time;
    String cContent;

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
}
