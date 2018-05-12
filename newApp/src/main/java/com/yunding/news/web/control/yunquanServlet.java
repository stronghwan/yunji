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
import net.sf.json.JSONObject;

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
        //请求中文编码设置
        request.setCharacterEncoding("UTF-8");

        // 响应中文乱码  字节流处理
        response.setHeader("ContentType", "text/html;application/json;charset=UTF-8");

        //响应中文乱码  字符流处理；设置response缓冲区编码
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        String JSON=request.getParameter("yunquanList"); //json
        JSONObject json=JSONObject.fromObject(JSON);
        YunCircles yunCircles=null;
        YunComments yunComments=null;
        YunLikes yunLikes=null;

          //云圈
          String y_time=json.getString("y_time");
          Date date = new Date();
          DateFormat fdf = new SimpleDateFormat();
          try {
              date = fdf.parse(y_time);
          } catch (ParseException e) {
              e.printStackTrace();
          }
          yunCircles.setY_time((java.sql.Date) date);
          yunCircles=new YunCircles();
          yunCircles.setKindof(json.getString("kindof"));
          yunCircles.setUserName(json.getString("username"));
          yunCircles.setyContent(json.getString("y_content"));
          ServiceFactory.getService("yunCircles").save(yunCircles);

          //评论内容
          yunComments=new YunComments();
          yunComments.setcContent(json.getString("c_content"));
          yunComments.setfId(json.getInt("yid"));
          yunComments.setUserName(json.getString("c_username"));
          String username=json.getString("y_username");
          int yuserid=ServiceFactory.getService("user").findUserId(username);
          yunComments.setUserId(yuserid);
          ServiceFactory.getService("yunComments").save(yunComments);
          //点赞
          yunLikes=new YunLikes();
          int status=json.getInt("status");
          //未点赞
          if (status==0){
              yunLikes.setStatus(1);
              yunLikes.setyId(json.getInt("yid"));
              yunLikes.setTotalNumber(json.getInt("totalNumber"));
            ServiceFactory.getService("yunLikes").save(yunLikes);
            ServiceFactory.getService("yunLikes").modifiedUserInfo(yunLikes);
          }
          }


}
