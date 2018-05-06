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
 *获取当前用户的关注列表
 * @Author：HJY
 */
@WebServlet(name = "FocusList", urlPatterns = {"/main/java/com.yunding.news/web/control/FocusList"})
public class FocusList extends HttpServlet {
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

        String JSON = request.getParameter("FocusList");
        JSONObject fl = new JSONObject();
        fl.getString("");
        String name = "";
        Account account = new Account();
        account = (Account) ServiceFactory.getService("user").findByUserName(name);

        if (name.equals(account.getName())) {
            Attention attention = new Attention();
            ServiceFactory.getService("user").findUserId("name");
            ServiceFactory.getService("user").findUserId("user_id");
            ServiceFactory.getService("user").findUserId("userName");

        }
        PrintWriter pr = response.getWriter();
        pr.write("FocusList");
        pr.flush();
        pr.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
