package com.yunding.news.web.control;

  /*
        敏感词过滤器
        Creater：侯钧耀
        Date: 2018/4/15
        Time: 16:11
        *
        */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "src/main/java/com/yunding/news/web/control/SensitiveServlet")
public class SensitiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String content = request.getParameter("content");
        String name = getParameter(content);
        request.setAttribute("content",name);
        request.getRequestDispatcher("/a.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    public String getParameter(String name){
        List<String> KeyWords = new ArrayList<String>();
        KeyWords.add("傻逼");
        String content = name;
        for (String s:KeyWords){
            if (content.contains("傻逼")){
                content = content.replaceAll(s,"**");
            }
        }
        return content;
    }
}


