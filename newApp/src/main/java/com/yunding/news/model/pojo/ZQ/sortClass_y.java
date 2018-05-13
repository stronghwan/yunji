package com.yunding.news.model.pojo.ZQ;    /*
 * @Name: 云圈（时间排序）
 * @Author:Farmerzhang
 * @Date: 2018/5/5
 * @Time: 15:39
 */

import java.util.Comparator;

public class sortClass_y implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
       yunMix_ yunMix_1= (yunMix_) o1;
       yunMix_ yunMix_2= (yunMix_) o2;
       int flag=-(((yunMix_) o1).getY_time().compareTo(((yunMix_) o2).getY_time()));
       return flag;
    }
}
