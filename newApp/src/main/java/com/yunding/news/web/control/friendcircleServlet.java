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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.yunding.news.model.service.ServiceFactory.getService;

public class friendcircleServlet extends HttpServlet {
    private JSONObject json;
    private  JSONArray jsonArray;
    // constructor
    public friendcircleServlet(){
        super();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {





        //解析前端发朋友圈json数据
        String json = "";
        JSONArray jsonArray = JSONArray.fromObject(json);
        Comment comment = null;
        Likes likes = null;
        FriendCircle friendCircle = null;
        for (int i = 0; i < jsonArray.size(); i++) {
            //将传入的user_name转为user_id,存入数据库
            friendCircle=new FriendCircle();
            String user_name = jsonArray.getJSONObject(i).getString("user_name");
            int user_id = getService("user").findUserId(user_name);
            friendCircle.setfUserId(user_id);
            friendCircle.setUserName(user_name);
            friendCircle.setfContent(jsonArray.getJSONObject(i).getString("fcontent"));
            String fcreate_time = jsonArray.getJSONObject(i).getString("fcreate_time");
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
            String cuser_name = jsonArray.getJSONObject(i).getString("cuser_name");
            int cuser_id = getService("user").findUserId(cuser_name);
            if (1 == 1)   //方法判断是否能过够进行评论
            {
                comment.setcUserId(cuser_id);  //评论人的id
                comment.setUserName(cuser_name);  //评论人的name
                String ccreatetime = jsonArray.getJSONObject(i).getString("ccreate_time");
                Date cdate = new Date();
                DateFormat cdf = new SimpleDateFormat("yyyy-MM-dd-HH:MM");
                try {
                    cdate = cdf.parse(ccreatetime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                comment.setC_time(cdate);
                comment.setcContent(jsonArray.getJSONObject(i).getString("ccontent"));
                comment.setfId(jsonArray.getJSONObject(i).getInt("fid"));
                ServiceFactory.getService("comment").save(comment);
            }

            //解析点赞的json数据
            likes=new Likes();
            int status = jsonArray.getJSONObject(i).getInt("status");
            if (status == 0) {
                String luser_name = jsonArray.getJSONObject(i).getString("luser_name");
                int luser_id = getService("user").findUserId(luser_name);
                likes.setUserId(luser_id);
                likes.setStatus(1);  //表明已经点赞
                //点赞人
                likes.setUserByName(luser_name);
                //被点赞人
                likes.setUserSelfName(jsonArray.getJSONObject(i).getString("userselfname"));
                likes.setfId(jsonArray.getJSONObject(i).getInt("fid"));
               ServiceFactory.getService("like").save(likes);
            }


        }

         //传入登陆人的
        for (int i = 0; i < jsonArray.size(); i++) {
            String user_name=jsonArray.getJSONObject(i).getString("user_name");
            List<CommentFriendCircle> commentFriendCircles = getService("fcMix").findCommentFriend(user_name);//前端传入的登录用户账号
            commentfriendCircle_ commentfriendCircle_ = null;
            commentfriendCircle_ commentfriendCircle_1= null;
            List<commentfriendCircle_> commentfriendCircle_s = new ArrayList<>();
            for (CommentFriendCircle commentFriendCircle : commentFriendCircles) {

                List<FriendCircle> friendCircles = commentFriendCircle.getFriendCircleList();
                for (FriendCircle friendcircle : friendCircles)   //小写
                {
                    commentfriendCircle_=new commentfriendCircle_();
                    Date cfc2 = friendcircle.getCreateTime();//发朋友圈时间
                    DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                    String F_time = sdf1.format(cfc2);
                    commentfriendCircle_.setFcreateTime(F_time);
                    commentfriendCircle_.setfId(friendcircle.getfId());
                    commentfriendCircle_.setUsername(friendcircle.getUserName());
                    commentfriendCircle_.setfContent(friendcircle.getfContent());

                    List<Comment> comments = commentFriendCircle.getCommentList();
                    List<commentfriendCircle_> commentList = commentfriendCircle_.getComments();
                    for (Comment comment1 : comments) {
                        commentfriendCircle_1 = new commentfriendCircle_();
                        Date cfc1 = comment1.getC_time();//评论时间
                        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                        String C_time = sdf2.format(cfc1);
                        commentfriendCircle_1.setC_time(C_time);
                        commentfriendCircle_1.setcId(comment1.getcId());
                        commentfriendCircle_1.setcContent(comment1.getcContent());
                        commentfriendCircle_1.setcUserId(comment1.getcUserId());
                        commentfriendCircle_1.setcUsername(comment1.getUserName());//评论人的name
                        commentList.add(commentfriendCircle_1);
                    }
                    sortClass_Cm sortClass_cm = new sortClass_Cm();
                    Collections.sort(commentList, sortClass_cm);
                    commentfriendCircle_.setpUrl(commentFriendCircle.getpUrl());
                    commentfriendCircle_.setStatus(commentFriendCircle.getStatus());
                    commentfriendCircle_.setLikeusernames(commentFriendCircle.getLikesUserName());
                    commentfriendCircle_s.add(commentfriendCircle_);

                }



            }

            //给说说进行排序
            sortClass_f sortClass_f = new sortClass_f();
            Collections.sort(commentfriendCircle_s, sortClass_f);
            jsonArray = JSONArray.fromObject(commentfriendCircle_s);

        }


    }










}



