package com.yunding.news.web.control;    /*
 * @Name:云圈（实体类）
 * @Author:Farmerzhang
 * @Date: 2018/5/5
 * @Time: 15:40
 */

import java.sql.Date;
import java.util.List;

public class yunMix_ {
    private int yId;
    private String userName;
    private String yContent;
    private String kindof;
    private String y_time;
    //评论

    private int cId;
    private String cContent;
    private String cuserName;
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

    public int getyId() {
        return yId;
    }

    public void setyId(int yId) {
        this.yId = yId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getyContent() {
        return yContent;
    }

    public void setyContent(String yContent) {
        this.yContent = yContent;
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

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }




    public String getcContent() {
        return cContent;
    }

    public void setcContent(String cContent) {
        this.cContent = cContent;
    }

    public String getCuserName() {
        return cuserName;
    }

    public void setCuserName(String cuserName) {
        this.cuserName = cuserName;
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
}
