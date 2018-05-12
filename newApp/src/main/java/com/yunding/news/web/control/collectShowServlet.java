package com.yunding.news.web.control;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/12
 * @Time: 10:43
 */

import com.yunding.news.model.pojo.Collect;
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
@WebServlet(name = "collectShowServlet", urlPatterns = {"/main/java/com.yunding.news/web/control/collectShowServlet"})
public class collectShowServlet extends HttpServlet {
    public collectShowServlet() {
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

        String JSON=request.getParameter("collectShowList"); //json

        JSONObject json= JSONObject.fromObject(JSON);
        String username=json.getString("username");
        int cUser_id= getService( "user" ).findUserId(username  );
        List<Collect> collects= getService("collect").findByUserId(cUser_id);
        collect_ collect_=null;
        List<collect_> collect_s=new ArrayList<>();
        for (Collect collect1:collects){
                collect_=new collect_();
                collect_.setFid(collect1.getfId());  //
                collect_.setCollId(collect1.getCollId());
                collect_.setFcontent(collect1.getfContent());
                collect_.setFuser_name(collect1.getUserName());
                Date ftime=collect1.getfTime();
                DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                String fTime=sdf.format(ftime);
                collect_.setFcreat_time(fTime);
                collect_.setFurl(collect1.getUrl());
                collect_s.add(collect_);
            }

            //给说说进行排序
            sortClass_C sortClass_c=new sortClass_C();
            Collections.sort(collect_s,sortClass_c);
            JSONArray jsonArray=JSONArray.fromObject(collect_s);

        PrintWriter printWriter=response.getWriter();
        printWriter.write(jsonArray.toString());
        printWriter.flush();
        printWriter.close();



    }
}
