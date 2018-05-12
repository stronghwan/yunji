package com.yunding.news.web.control;    /*
 * @Name:云圈（实体类）
 * @Author:Farmerzhang
 * @Date: 2018/5/5
 * @Time: 15:40
 */

import java.sql.Date;
import java.util.List;

public class yunMix_ {
    private int yid;
    private String username;
    private String y_content;
    private String kindof;
    private String y_time;
    //评论

    private int cid;
    private String c_content;
    private String c_username;
    List<yunMix_> comments;

    //点赞
    private int status;
    private int totalNumber;

    public List<yunMix_> getComments() {
        return comments;
    }

    public void setComments(List<yunMix_> comments) {
        this.comments = comments;
    }


    public String getKindof() {
        return kindof;
    }

    public void setKindof(String kindof) {
        this.kindof = kindof;
    }

    public String getY_time() {
        return y_time;
    }

    public void setY_time(String y_time) {
        this.y_time = y_time;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getYid() {
        return yid;
    }

    public void setYid(int yid) {
        this.yid = yid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getY_content() {
        return y_content;
    }

    public void setY_content(String y_content) {
        this.y_content = y_content;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getC_content() {
        return c_content;
    }

    public void setC_content(String c_content) {
        this.c_content = c_content;
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }
}
