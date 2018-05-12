package com.yunding.news.web.control;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/12
 * @Time: 11:15
 */

import com.yunding.news.model.pojo.Comment;
import com.yunding.news.model.pojo.CommentFriendCircle;
import com.yunding.news.model.pojo.FriendCircle;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.yunding.news.model.service.ServiceFactory.getService;
@WebServlet(name = "fcShowServlet", urlPatterns = {"/main/java/com.yunding.news/web/control/fcShowServlet"})
public class fcShowServlet extends HttpServlet {
    public fcShowServlet() {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

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

        String JSON = request.getParameter("fcShowList"); //json

        JSONObject json = JSONObject.fromObject(JSON);

        //传入登陆人的

        String user_name = json.getString("user_name");

        List<CommentFriendCircle> commentFriendCircles = getService("fcMix").findCommentFriend(user_name);//前端传入的登录用户账号
        commentfriendCircle_ commentfriendCircle_ = null;
        commentfriendCircle_ commentfriendCircle_1 = null;
        List<commentfriendCircle_> commentfriendCircle_s = new ArrayList<>();

        for (CommentFriendCircle commentFriendCircle : commentFriendCircles) {
            List<FriendCircle> friendCircles = commentFriendCircle.getFriendCircleList();

            for (FriendCircle friendCircle : friendCircles)   //小写
            {
                commentfriendCircle_ = new commentfriendCircle_();
                Date cfc2 = friendCircle.getCreateTime();//发朋友圈时间
                DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                String F_time = sdf1.format(cfc2);
                commentfriendCircle_.setFcreate_time(F_time);
                commentfriendCircle_.setFid(friendCircle.getfId());
                commentfriendCircle_.setUsername(friendCircle.getUserName());
                commentfriendCircle_.setFcontent(friendCircle.getfContent());

                List<Comment> comments = commentFriendCircle.getCommentList();
                List<commentfriendCircle_> commentList = commentfriendCircle_.getComments();
                for (Comment comment1 : comments) {
                    commentfriendCircle_1 = new commentfriendCircle_();
                    Date cfc1 = comment1.getC_time();//评论时间
                    DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                    String C_time = sdf2.format(cfc1);
                    commentfriendCircle_1.setCcreate_time(C_time);
                    commentfriendCircle_1.setCid(comment1.getcId());
                    commentfriendCircle_1.setCcontent(comment1.getcContent());
                    commentfriendCircle_1.setCuser_name(comment1.getUserName());//评论人的name
                    commentList.add(commentfriendCircle_1);
                }
                sortClass_Cm sortClass_cm = new sortClass_Cm();
                Collections.sort(commentList, sortClass_cm);
                commentfriendCircle_.setPurl(commentFriendCircle.getpUrl());
                commentfriendCircle_.setStatus(commentFriendCircle.getStatus());
                commentfriendCircle_.setLuser_name(commentFriendCircle.getLikesUserName());
                commentfriendCircle_s.add(commentfriendCircle_);

            }

        }

        //给说说进行排序
        sortClass_f sortClass_f = new sortClass_f();
        Collections.sort(commentfriendCircle_s, sortClass_f);
        JSONArray fcShowList = JSONArray.fromObject(commentfriendCircle_s);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(fcShowList.toString());
        printWriter.flush();
        printWriter.close();


    }
}


