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


@WebServlet(name = "BatchDelete",urlPatterns = "/servlet/BatchDelete")
public class BatchDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deletes_data =request.getParameter("deletes_data");
        JSONObject jsonObject = JSONObject.fromObject(deletes_data);
        String[] user_names = (String[]) jsonObject.get("user_names");

        JavaBean javaBean = new JavaBean();
        try {
            int[] user_ids = javaBean.queryIDs(user_names);
            javaBean.batchDelete(user_ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
