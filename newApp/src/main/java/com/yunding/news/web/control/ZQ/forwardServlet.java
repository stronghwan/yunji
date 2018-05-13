package com.yunding.news.web.control.ZQ;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/12
 * @Time: 17:39
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "forwardServlet", urlPatterns = {"/main/java/com.yunding.news/web/control/ZQ/forwardServlet"})

public class forwardServlet extends HttpServlet {
    public forwardServlet() {
    }
    public void doPost(HttpServletResponse response, HttpServletRequest request)throws IOException,ServletException{
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
        String forwardList=request.getParameter("forwardList");




        PrintWriter printWriter=response.getWriter();
        printWriter.write(forwardList);
        printWriter.flush();
        printWriter.close();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
