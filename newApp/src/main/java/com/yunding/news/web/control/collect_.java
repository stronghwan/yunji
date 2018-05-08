package com.yunding.news.web.control;
/*
 * @Name:朋友圈•收藏（实体类）
 * @Author:Farmerzhang
 * @Date: 2018/5/3
 * @Time: 14:31
 */

import java.sql.Date;

public class collect_ {
    private int collId;
    /**
     * 被收藏说说的名字  用来显示
     */
    private String userName;
    private int fId;
    private String fTime;
    private String fContent;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 图片url

     */
    private String url;
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

    public String getfTime() {
        return fTime;
    }

    public void setfTime(String fTime) {
        this.fTime = fTime;
    }

    public String getfContent() {
        return fContent;
    }

    public void setfContent(String fContent) {
        this.fContent = fContent;
    }
}
