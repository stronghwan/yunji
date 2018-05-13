package com.yunding.news.web.control.GYX;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/13
 * @Time: 10:43
 */

import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONObject;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.yunding.news.model.pojo.PersonalCenter;



public class UserServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        //解析json,得到用户名
        //传 PersonalCenter对象 保存在pc.set……（）；
        //把pc用 save方法传入 数据库
        String content=request.getParameter("");//json name

        JSONObject jsonObject=JSONObject.fromObject(content);
        String yonghuming = jsonObject.getString("username");
        PersonalCenter pc = (PersonalCenter) ServiceFactory.getService("personalCenter").findByPerUserID(yonghuming);

        int id = ServiceFactory.getService("user").findUserId(yonghuming);

        pc.setSex(jsonObject.getString("sex"));
        pc.setSignature(jsonObject.getString("signature"));
        pc.setAddress(jsonObject.getString("address"));
        pc.setPhone(jsonObject.getString("phone"));
        pc.setEmail(jsonObject.getString("email"));
        pc.setNickname(jsonObject.getString("nickname"));
        pc.setUserId(id);

        int i = ServiceFactory.getService("personalCenter").save(pc);
    }
}