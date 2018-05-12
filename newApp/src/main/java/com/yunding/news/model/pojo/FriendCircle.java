package com.yunding.news.model.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @朋友圈实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/20-13-34
 */
public class
FriendCircle implements Serializable{
    int fId;
    int fUserId;
    Date createTime;
    String fContent;
    String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
