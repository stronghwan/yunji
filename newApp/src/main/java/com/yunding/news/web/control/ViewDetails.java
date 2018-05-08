package com.yunding.news.web.control;

import com.yunding.news.model.pojo.PersonalCenter;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * 查看关注用户的详细信息
 * @Author：HJY
 * */
@WebServlet(name = "ViewDetails", urlPatterns = {"/main/java/com.yunding.news/web/control/ViewDetails"})
public class ViewDetails extends HttpServlet {
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


        String JSON = request.getParameter("ViewDetails");
        JSONObject vd = new JSONObject();
        vd.getString("");
        String name = "";

        PersonalCenter personalCenter = new PersonalCenter();
        personalCenter = (PersonalCenter) ServiceFactory.getService("personalCenter").findByUserName(name);
        personalCenter.getSex();
        personalCenter.getDepartment();
        personalCenter.getAddress();
        personalCenter.getPhone();
        personalCenter.getEmail();
        personalCenter.getSignature();
//
        PrintWriter pr = response.getWriter();
        pr.write("ViewDetails");
        pr.flush();
        pr.close();

//        Account account = new Account();
//        account = (Account) ServiceFactory.getService("user").findByUserName(name);
//
//        if (name.equals(account.getName())) {
//            Attention attention = new Attention();
//            int ud = getService("user").findUserId(name);
//            List<Attention> uat= ServiceFactory.getService("attention").findAttByUserId(ud);
//            List<String> udl = new ArrayList<String>();
//            for (Attention Dat: uat) {
//                Dat.getAuserName();
//                udl.add(Dat.getAuserName());
//                response.getWriter().write(JSONArray.fromObject(udl).toString());
//            }
//        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}
