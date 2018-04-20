package com.yunding.news.model.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @TODO
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
    // 评论的内容
    int cId;
    int cUserId; // 发说说人的id
    Date c_time;
    String cContent;

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
}
