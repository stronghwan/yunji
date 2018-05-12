package com.yunding.news.web.control;    /*
 * @Name:朋友圈 评论排序
 * @Author:Farmerzhang
 * @Date: 2018/5/3
 * @Time: 21:01
 */

import java.util.Comparator;

public class sortClass_Cm implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        commentfriendCircle_ commentfriendCircle_1= (commentfriendCircle_) o1;
        commentfriendCircle_ commentfriendCircle_2= (commentfriendCircle_) o2;
        int flag=-(((commentfriendCircle_) o1).getCcreate_time().compareTo(((commentfriendCircle_) o2).getCcreate_time()));
        return flag;
    }
}
