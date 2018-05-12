package com.yunding.news.web.control;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/12
 * @Time: 12:08
 */

import com.yunding.news.model.pojo.FriendCircle;
import com.yunding.news.model.service.ServiceFactory;
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
import java.util.*;
@WebServlet(name = "selfCircleServlet", urlPatterns = {"/main/java/com.yunding.news/web/control/selfCircleServlet"})
public class selfCircleServlet extends HttpServlet {
    public selfCircleServlet() {
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
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
        String JSON=request.getParameter("selfCircleList"); //json
        JSONObject json=JSONObject.fromObject(JSON);
        String user_name=json.getString("user_name");
        int user_id=ServiceFactory.getService("user").findUserId(user_name);
        List<FriendCircle> friendCircles=ServiceFactory.getService("fc").findByUserId(user_id);
        List<commentfriendCircle_> selfCircles=new ArrayList<>();
        commentfriendCircle_ selfCircle=null;
        for (FriendCircle friendCircle:friendCircles){
            selfCircle=new commentfriendCircle_();
            selfCircle.setFid(friendCircle.getfId());
            selfCircle.setFcontent(friendCircle.getfContent());
            Date ftime=friendCircle.getCreateTime();
            DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd-HH:mm");
            String fcreate_time=dateFormat.format(ftime);
            selfCircle.setFcreate_time(fcreate_time);
            selfCircle.setUsername(friendCircle.getUserName());
            selfCircles.add(selfCircle);
            }

        //给说说进行排序
        sortClass_f sortClass_f = new sortClass_f();
        Collections.sort(selfCircles, sortClass_f);
        JSONArray jsonArray = JSONArray.fromObject(selfCircles);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(jsonArray.toString());
        printWriter.flush();
        printWriter.close();



    }
}
