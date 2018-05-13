package com.yunding.news.web.control.ZQ;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/12
 * @Time: 11:53
 */

import com.yunding.news.model.dao.DaoFactory;
import com.yunding.news.model.pojo.Account;
import com.yunding.news.model.pojo.YunCircles;
import com.yunding.news.model.pojo.YunComments;
import com.yunding.news.model.pojo.YunMix;
import com.yunding.news.model.pojo.ZQ.sortClass_y;
import com.yunding.news.model.pojo.ZQ.yunMix_;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
@WebServlet(name = "yunShowServlet", urlPatterns = {"/main/java/com.yunding.news/web/control/ZQ/yunServlet"})

public class yunShowServlet extends HttpServlet {
    public yunShowServlet() {
    }
    public void doPost(HttpServletResponse response, HttpServletRequest request) throws IOException,ServletException{
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
        String JSON=request.getParameter("yunShowList"); //json
        JSONObject json=JSONObject.fromObject(JSON);



            String kindof=json.getString("kindof");
            List<YunMix> yunMixes=ServiceFactory.getService("yMix").findCommon(kindof);
            List<yunMix_> yunMix_s=new ArrayList<>();
            yunMix_ yunmix_=null;
            yunMix_ yunmix_1=null;
            for (YunMix yunMix:yunMixes){
                List<YunCircles> yunCircles1=yunMix.getlYCircles();
                for (YunCircles yunCircle1:yunCircles1){
                    yunmix_=new yunMix_();
                    yunmix_.setKindof(yunCircle1.getKindof());
                    yunmix_.setUsername(yunCircle1.getUserName());
                    Account account1= (Account) DaoFactory.getDao("user").findByUserName(yunCircle1.getUserName());
                    yunmix_.setY_nikename(account1.getNickName());
                    yunmix_.setY_content(yunCircle1.getyContent());
                    yunmix_.setYid(yunCircle1.getyId());
                    Date time=yunCircle1.getY_time();
                    DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                    String Y_time=dateFormat.format(time);
                    yunmix_.setY_time(Y_time);
                    //评论
                    List<YunComments> yunComments1=yunMix.getlYComments();
                    List<yunMix_> comments=yunmix_.getComments();
                    for(YunComments yuncComment1:yunComments1){
                        yunmix_1=new yunMix_();
                        yunmix_1.setC_username(yuncComment1.getUserName());
                        Account account2= (Account) DaoFactory.getDao("user").findByUserName(yuncComment1.getUserName());
                        yunmix_1.setY_nikename(account2.getNickName());
                        yunmix_1.setC_content(yuncComment1.getcContent());
                        yunmix_1.setCid(yuncComment1.getcId());
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


            JSONArray jsonArray = JSONArray.fromObject(yunMix_s);
        PrintWriter printWriter=response.getWriter();
        printWriter.write(jsonArray.toString());
        printWriter.flush();
        printWriter.close();



    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
