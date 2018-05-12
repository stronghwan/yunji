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
    private String fuser_name;
    private int fid;
    private String fcreat_time;
    private String fcontent;


    /**
     * 图片url
     */
    private String furl;

    private String cuser_name;

    public int getCollId() {
        return collId;
    }

    public void setCollId(int collId) {
        this.collId = collId;
    }

    public String getFuser_name() {
        return fuser_name;
    }

    public void setFuser_name(String fuser_name) {
        this.fuser_name = fuser_name;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFcreat_time() {
        return fcreat_time;
    }

    public void setFcreat_time(String fcreat_time) {
        this.fcreat_time = fcreat_time;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }

    public String getCuser_name() {
        return cuser_name;
    }

    public void setCuser_name(String cuser_name) {
        this.cuser_name = cuser_name;
    }
}