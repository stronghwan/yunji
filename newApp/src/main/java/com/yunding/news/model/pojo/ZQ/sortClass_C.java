package com.yunding.news.model.pojo.ZQ;
/*
 * @Name: 朋友圈•收藏（时间排序）
 * @Author:Farmerzhang
 * @Date: 2018/5/3
 * @Time: 14:54
 */

import com.yunding.news.model.pojo.ZQ.collect_;

import java.util.Comparator;

public class sortClass_C implements Comparator {


    @Override
    public int compare(Object o1, Object o2) {
        collect_ collect_1= (collect_) o1;
        collect_ collect_2= (collect_) o2;
        int flag=-(((collect_) o1).getFcreat_time().compareTo(((collect_) o2).getFcreat_time()));
        return flag;
    }

}
