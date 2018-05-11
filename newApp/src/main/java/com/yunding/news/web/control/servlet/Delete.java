package com.yunding.news.web.control.servlet;

import com.yunding.news.web.others.javaBean.JavaBean;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/*
 * 单个删除
 * @Author WFH
 */

@WebServlet(name = "Delete",urlPatterns = "servlet/Delete")
public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //获取JSON数据
        String delete_data = request.getParameter("delete_data");
        JSONObject json = JSONObject.fromObject(delete_data);
        String name = (String) json.get("delete_name");

        //将name转换为id，利用id删除用户
        JavaBean javaBean = new JavaBean();
        try {
            int user_id = javaBean.queryID(name);
            javaBean.delete(user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request,response);
    }
}
