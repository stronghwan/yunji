package com.yunding.news.model.pojo;

import java.io.Serializable;

/**
 * @云圈点赞实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/5/3-23-48
 */
public class YunLikes implements Serializable{

      private int lId;
      private int userId;
      private int status;
      private int yId;
      private String userselfName;
      private String userbyName;
      private int totalNumber;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getlId() {
        return lId;
    }

    public void setlId(int lId) {
        this.lId = lId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getyId() {
        return yId;
    }

    public void setyId(int yId) {
        this.yId = yId;
    }

    public String getUserselfName() {
        return userselfName;
    }

    public void setUserselfName(String userselfName) {
        this.userselfName = userselfName;
    }

    public String getUserbyName() {
        return userbyName;
    }

    public void setUserbyName(String userbyName) {
        this.userbyName = userbyName;
    }
}
