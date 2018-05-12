package com.yunding.news.web.control;
/*
 * @Name:朋友圈
 * @Author:Farmerzhang
 * @Date: 2018/4/13
 * @Time: 13:31
 */
import com.yunding.news.model.pojo.Comment;
import com.yunding.news.model.pojo.CommentFriendCircle;
import com.yunding.news.model.pojo.FriendCircle;
import com.yunding.news.model.pojo.Likes;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.yunding.news.model.service.ServiceFactory.getService;

public class friendcircleServlet extends HttpServlet {

    // constructor
    public friendcircleServlet(){
        super();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {

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

        String JSON=request.getParameter("friendCircleList"); //json
        JSONObject json=JSONObject.fromObject(JSON);

        //解析前端发朋友圈json数据
        Comment comment = null;
        Likes likes = null;
        FriendCircle friendCircle = null;

            //将传入的user_name转为user_id,存入数据库
            friendCircle=new FriendCircle();
            String user_name = json.getString("user_name");
            int user_id = getService("user").findUserId(user_name);
            friendCircle.setfUserId(user_id);
            friendCircle.setUserName(user_name);
            friendCircle.setfContent(json.getString("fcontent"));
            String fcreate_time = json.getString("fcreate_time");
            //日期格式也string一致
            Date fdate = new Date();
            DateFormat fdf = new SimpleDateFormat();
            try {
                fdate = fdf.parse(fcreate_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            friendCircle.setCreateTime(fdate);
            ServiceFactory.getService("fc").save(friendCircle);

            //解析朋友圈评论内容的json
            //将评论人的id转为name
            comment=new Comment();
            String cuser_name = json.getString("cuser_name");
            int cuser_id = getService("user").findUserId(cuser_name);
            if (1 == 1)   //方法判断是否能过够进行评论
            {
                comment.setcUserId(cuser_id);  //评论人的id
                comment.setUserName(cuser_name);  //评论人的name
                String ccreatetime = json.getString("ccreate_time");
                Date cdate = new Date();
                DateFormat cdf = new SimpleDateFormat("yyyy-MM-dd-HH:MM");
                try {
                    cdate = cdf.parse(ccreatetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                comment.setC_time(cdate);
                comment.setcContent(json.getString("ccontent"));
                comment.setfId(json.getInt("fid"));
                ServiceFactory.getService("comment").save(comment);
            }else {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("code","200");
                jsonObject.put("message","false");
                PrintWriter printWriter=response.getWriter();
                printWriter.write(jsonObject.toString());
                printWriter.flush();
                printWriter.close();



            }

            //解析点赞的json数据
            likes=new Likes();
            int status = json.getInt("status");
            if (status == 0) {
                String luser_name = json.getString("luser_name");
                int luser_id = getService("user").findUserId(luser_name);
                likes.setUserId(luser_id);
                likes.setStatus(1);  //表明已经点赞
                //点赞人
                likes.setUserByName(luser_name);
                //被点赞人
                likes.setUserSelfName(json.getString("userselfname"));
                likes.setfId(json.getInt("fid"));
               ServiceFactory.getService("like").save(likes);
            }



        }




    }














