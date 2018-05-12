package com.yunding.news.web.control;
/*
 * @Name:朋友圈•收藏
 * @Author:Farmerzhang
 * @Date: 2018/5/1
 * @Time: 19:34
 */

import com.yunding.news.model.pojo.Collect;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.yunding.news.model.service.ServiceFactory.getService;
@WebServlet(name = "collectServlet", urlPatterns = {"/main/java/com.yunding.news/web/control/coll"})
public class collectServlet extends HttpServlet {


    public collectServlet() {
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

        //解析JSON数据
        String JSON=request.getParameter("collectList");
        JSONObject json=JSONObject.fromObject(JSON);
        Collect collect=new Collect();
            String fCreatetime = json.getString("fcreate_time");
            java.sql.Date fdate = null;  //格式
            DateFormat sdf = new SimpleDateFormat();
            try {
                fdate = (java.sql.Date) sdf.parse(fCreatetime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            collect.setfTime(fdate);
            collect.setfContent(json.getString("fcontent"));
            collect.setfId(json.getInt("fid"));
            collect.setUserName(json.getString("fuser_name"));//说说name

            String cUsername=json.getString("cuser_name");
            int cuserid= getService( "user" ).findUserId( cUsername);//收藏人的id
            collect.setUserId(cuserid);
            collect.setUrl(json.getString("furl"));
            ServiceFactory.getService("collect").save(collect);


            JSONObject jsonObject=new JSONObject();
            jsonObject.put("code",200);
            jsonObject.put("message","success");
            PrintWriter printWriter=response.getWriter();
            printWriter.write(jsonObject.toString());
            printWriter.flush();
            printWriter.close();



        }








        }










