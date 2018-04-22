package com.yunding.news.model.pojo;

import java.io.Serializable;

/**
 * @关注实体类
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/15-21-32
 */
public class Attention implements Serializable{

  private int aId;
  private int uselfId;
  private int auserId;
  private String auserName;

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public int getUselfId() {
        return uselfId;
    }

    public void setUselfId(int uselfId) {
        this.uselfId = uselfId;
    }

    public int getAuserId() {
        return auserId;
    }

    public void setAuserId(int auserId) {
        this.auserId = auserId;
    }

    public String getAuserName() {
        return auserName;
    }

    public void setAuserName(String auserName) {
        this.auserName = auserName;
    }
}
