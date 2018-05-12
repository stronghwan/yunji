package com.yunding.news.web.control.LZQ;

import com.yunding.news.model.pojo.Account;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  类名称:FindBackThirdServlet
 *
 *  类作用：用于在找回密码功能中修改密码
 *
 *  启动时间:2018年4月15日
 *
 *  最终修改时间:2018年4月15日
 *
 *  @author 李子琦
 */

//实现此Servlet注册
@WebServlet(name = "FindBackThirdServlet",urlPatterns = {"/main/java/com.yunding.news/web/control/FindBacKThirdServlet"})

public class FindBackThirdServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置错误序号为500，正确序号为200
        int code=500;
        //设置错误信息
        String message="";

        // 处理编码问题
            // 请求中文编码设置
            request.setCharacterEncoding("UTF-8");
            // 响应中文乱码  字节流处理
            response.setHeader("ContentType","text/html;application/json;charset=UTF-8");
            //响应中文乱码  字符流处理；设置response缓冲区编码
            response.setContentType("text/html;charset=UTF-8");
            response.setContentType("text/html");  response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");  response.setDateHeader("Expires", 0);

        //对前端传入的json进行解析
            //使用String接收
            String json=request.getParameter("account");
            //将接收的String转换为json
            JSONObject jsonObject=JSONObject.fromObject(json);
            //得到用户名
            String username=jsonObject.getString("username");
            //得到密码
            String password=jsonObject.getString("password");

        //将得到的数据封装
        //实例化Account类对象
        Account account=new Account();
        account.setName(username);
        account.setNickName(password);

        //调用Dao方法修改密码
        ServiceFactory.getService("user").modifiedUserInfo(account);

        //判断密码是否真正被修改
        Account account1=new Account();
        account1=(Account) ServiceFactory.getService("user").findByUserName(username);
        String PASSWORD=account1.getPassword().trim();
        if (PASSWORD.equals(password)){
            code=200;
            message="该用户的密码已经被修改";
        }else {
            message="该用户的密码修改失败";
        }

        //将数据返回给前端
        //获得PrintWriter输出流对象
        PrintWriter out=response.getWriter();
        //获得一个JSON对象
        JSONObject Reg=new JSONObject();
        //将数据放入该JSON对象中
        Reg.put("code",code);
        Reg.put("message",message);
        //如果该用户可用，则前端收到code为200，否则为500
        out.write(Reg.toString());

        //清空并关闭流
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
