package com.yunding.news.web.control;

import com.yunding.news.model.pojo.Account;
import com.yunding.news.model.pojo.Attention;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
 * 添加关注的用户至关注列表
 * @Author: HJY
 * time
 * */

@WebServlet(name = "AddFocuses", urlPatterns = {"/main/java/com.yunding.news/web/control/AddFocuses"})
public class AddFocuses extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        String JSON = request.getParameter("AddFocus");
        JSONObject af = new JSONObject();
        af.getString("");
        String name1 = "";
        String name2 = "";
        Account account = new Account();
        account = (Account) ServiceFactory.getService("user").findByUserName(name1);
        //判断是否已在关注列表，若不在，则添加至列表
        if (name1.equals(account.getName())) {
            System.out.println("已关注");
        } else {
            Attention attention = new Attention();
            attention.setAuserName(name1);
            //通过name查找自己的id
            int id1 = ServiceFactory.getService("user").findUserId(name1);
            attention.setAuserId(id1);
            int id2 = ServiceFactory.getService("user").findUserId(name2);
            attention.setAuserId(id2);
            System.out.println("关注成功");
        }
        PrintWriter pr = response.getWriter();
        pr.write("AddFocus");
        pr.flush();
        pr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
