package com.yunding.news.web.control.GYX;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/13
 * @Time: 10:41
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunding.news.model.pojo.PersonalCenter;
public class ExitUserServlet extends HttpServlet{
    private static final long serialVersionUID = 1599366365079846238L;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        PersonalCenter user = (PersonalCenter)session.getAttribute("user");

        if(user != null){
            session.removeAttribute("user");
            request.setAttribute("info", 1);
        }
        // 转到登录页面
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}
