package com.yunding.news.model.pojo;

import java.io.Serializable;

/**
 * 图片实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/25-00-02
 */
public class Pictures implements Serializable{

    private int pId;
    private int userId;
    private String url;
    private String userName;
    private int fId;            // 修改

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
