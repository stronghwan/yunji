package com.yunding.news.web.control;
/*
 * @Name: 云圈
 * @Author:Farmerzhang
 * @Date: 2018/4/23
 * @Time: 17:31
 */
import com.yunding.news.model.pojo.YunCircles;
import com.yunding.news.model.pojo.YunComments;
import com.yunding.news.model.pojo.YunLikes;
import com.yunding.news.model.pojo.YunMix;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class yunquanServlet extends HttpServlet {

    //构造体
    public yunquanServlet(

    ) {
    }
    public void doPost(HttpServletResponse response, HttpServletRequest request) throws ServletException,IOException
    {


      String JSON="";
      JSONArray jsonArray=JSONArray.fromObject(JSON);
        YunCircles yunCircles=null;
        YunComments yunComments=null;
        YunLikes yunLikes=null;
      for (int i=0;i<jsonArray.size();i++){
          //云圈
          String y_time=jsonArray.getJSONObject(i).getString("y_time");
          Date date = new Date();
          DateFormat fdf = new SimpleDateFormat();
          try {
              date = fdf.parse(y_time);
          } catch (ParseException e) {
              e.printStackTrace();
          }
          yunCircles.setY_time((java.sql.Date) date);
          yunCircles=new YunCircles();
          yunCircles.setKindof(jsonArray.getJSONObject(i).getString("kindof"));
          yunCircles.setUserName(jsonArray.getJSONObject(i).getString("username"));
          yunCircles.setyContent(jsonArray.getJSONObject(i).getString("y_content"));
          ServiceFactory.getService("yunCircles").save(yunCircles);

          //评论内容
          yunComments=new YunComments();
          yunComments.setcContent(jsonArray.getJSONObject(i).getString("c_content"));
          yunComments.setfId(jsonArray.getJSONObject(i).getInt("yid"));
          yunComments.setUserName(jsonArray.getJSONObject(i).getString("c_username"));
          String username=jsonArray.getJSONObject(i).getString("y_username");
          int yuserid=ServiceFactory.getService("user").findUserId(username);
          yunComments.setUserId(yuserid);
          ServiceFactory.getService("yunComments").save(yunComments);
          //点赞
          yunLikes=new YunLikes();
          int status=jsonArray.getJSONObject(i).getInt("status");
          //未点赞
          if (status==0){
              yunLikes.setStatus(1);
              yunLikes.setyId(jsonArray.getJSONObject(i).getInt("yid"));
              yunLikes.setTotalNumber(jsonArray.getJSONObject(i).getInt("totalNumber"));
            ServiceFactory.getService("yunLikes").save(yunLikes);
            ServiceFactory.getService("yunLikes").modifiedUserInfo(yunLikes);
          }
          }


           for (int i=0;i<jsonArray.size();i++){
        String kindof=jsonArray.getJSONObject(i).getString("kindof");
        List<YunMix> yunMixes=ServiceFactory.getService("yMix").findCommon("kindof");
        List<yunMix_> yunMix_s=new ArrayList<>();
               yunMix_ yunmix_=null;
               yunMix_ yunmix_1=null;
               for (YunMix yunMix:yunMixes){
            List<YunCircles> yunCircles1=yunMix.getlYCircles();
            for (YunCircles yunCircle1:yunCircles1){
               yunmix_=new yunMix_();
               yunmix_.setKindof(yunCircle1.getKindof());
               yunmix_.setUserName(yunCircle1.getUserName());
               yunmix_.setyContent(yunCircle1.getyContent());
               yunmix_.setyId(yunCircle1.getyId());
               Date time=yunCircle1.getY_time();
               DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd-HH:mm");
               String Y_time=dateFormat.format(time);
               yunmix_.setY_time(Y_time);
               //评论
               List<YunComments> yunComments1=yunMix.getlYComments();
               List<yunMix_> comments=yunmix_.comments;
               for(YunComments yuncComment1:yunComments1){
                   yunmix_1=new yunMix_();
                   yunmix_1.setCuserName(yuncComment1.getUserName());
                   yunmix_1.setcContent(yuncComment1.getcContent());
                   yunmix_1.setcId(yuncComment1.getcId());
                   comments.add(yunmix_1);
                   }
                   //点赞
               yunmix_.setStatus(yunMix.getlStatus());
               yunmix_.setTotalNumber(yunMix.getTotalNumber());

            }
              yunMix_s.add(yunmix_);
            }
               //给说说进行排序
               sortClass_y sortClass_y = new sortClass_y();
               Collections.sort(yunMix_s, sortClass_y);


               jsonArray = JSONArray.fromObject(yunMix_s);


    }
}
}