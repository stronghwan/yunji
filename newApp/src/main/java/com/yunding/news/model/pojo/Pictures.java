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
    private String puser_name;
    private int fid;            // 修改

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

    public String getPuser_name() {
        return puser_name;
    }

    public void setPuser_name(String puser_name) {
        this.puser_name = puser_name;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
}
