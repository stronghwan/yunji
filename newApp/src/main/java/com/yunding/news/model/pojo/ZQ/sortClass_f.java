package com.yunding.news.model.pojo.ZQ;
/*
 * @Name:朋友圈（时间排序）
 * @Author:Farmerzhang
 * @Date: 2018/5/1
 * @Time: 20:31
 */

import com.yunding.news.model.pojo.ZQ.commentfriendCircle_;

import java.util.Comparator;

public class sortClass_f implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        commentfriendCircle_ commentfriendCircle_1= (commentfriendCircle_) o1;
        commentfriendCircle_ commentfriendCircle_2= (commentfriendCircle_) o2;
        int flag=-(((commentfriendCircle_) o1).getFcreate_time().compareTo(((commentfriendCircle_) o2).getFcreate_time()));
        return flag;
    }
}
