package com.yunding.news.web.control;

import com.yunding.news.model.pojo.Account;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 查看关注用户的动态
 *@Author HJY
 */
@WebServlet(name = "ViewActivities", urlPatterns = {"/main/java/com.yunding.news/web/control/ViewActivities"})
public class ViewActivities extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String JSON = request.getParameter("ViewActivities");
        JSONObject vd = new JSONObject();
        vd.getString("");
        String name = "";
        Account account = new Account();
        account = (Account) ServiceFactory.getService("user").findByUserName("username");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }
}
