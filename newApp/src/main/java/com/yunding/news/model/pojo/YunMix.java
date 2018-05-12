package com.yunding.news.model.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/4-14-03
 */
public class YunMix implements Serializable{
    private List<YunCircles> lYCircles = new ArrayList<YunCircles>();
    private List<YunComments> lYComments = new ArrayList<YunComments>();
    private int lStatus;
    private int totalNumber;
    private String nickName;
    public List<YunCircles> getlYCircles() {
        return lYCircles;
    }

    public void setlYCircles(List<YunCircles> lYCircles) {
        this.lYCircles = lYCircles;
    }

    public List<YunComments> getlYComments() {
        return lYComments;
    }

    public void setlYComments(List<YunComments> lYComments) {
        this.lYComments = lYComments;
    }

    public int getlStatus() {
        return lStatus;
    }

    public void setlStatus(int lStatus) {
        this.lStatus = lStatus;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
