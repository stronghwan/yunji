package com.yunding.news.model.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/1-14-17
 */
public class Collect implements Serializable{

    private int collId;
    /**
     * 被收藏说说的名字  用来显示
     */
    private String userName;
    private int fId;
    private Date fTime;
    private String fContent;
    /**
     * 收藏人的id  用来遍历
     */
    private int userId;
    /**
     * 图片url
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCollId() {
        return collId;
    }

    public void setCollId(int collId) {
        this.collId = collId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
