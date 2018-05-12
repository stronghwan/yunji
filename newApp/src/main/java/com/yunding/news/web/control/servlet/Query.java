package com.yunding.news.web.control.servlet;

import com.yunding.news.web.others.javaBean.JavaBean;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * 用户信息
 * @Author WFH
 * */

@WebServlet(name = "Query",urlPatterns = "servlet/Query")
public class Query extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //将用户的list数据包装成JSON数据
        JavaBean javaBean =new JavaBean();
        List list = javaBean.query();
        JSONArray query = JSONArray.fromObject(list);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
