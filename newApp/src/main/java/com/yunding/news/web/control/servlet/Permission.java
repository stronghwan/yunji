package com.yunding.news.web.control.servlet;

import com.yunding.news.web.others.javaBean.JavaBean;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Permission")
public class Permission extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收String用户名与String[]权限数组
        String permission_data = request.getParameter("permission_data");
        JSONObject jsonObject = JSONObject.fromObject(permission_data);
        String user_name = (String) jsonObject.get("user_name");
        String[] permissions = (String[]) jsonObject.get("permissions");

        JavaBean javaBean = new JavaBean();
        try {
            javaBean.upDate(permissions,user_name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
