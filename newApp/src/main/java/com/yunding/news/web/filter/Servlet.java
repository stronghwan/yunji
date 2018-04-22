        /*
        敏感词过滤器
        Creater：侯钧耀
        Date: 2018/4/15
        Time: 16:11
        */
package com.yunding.news.web.filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet",urlPatterns = "/Ser/Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String content = request.getParameter("content");
        MyServlet myServlet = new MyServlet();
        String name = myServlet.getParameter(content);
        request.setAttribute("content", name);//h
        request.getRequestDispatcher("/a.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
