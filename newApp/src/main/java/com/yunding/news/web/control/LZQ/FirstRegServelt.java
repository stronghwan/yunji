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
 *  类名称:FirstRegServlet
 *
 *  类作用：用于接收并存储第一注册页面提交的用户名和密码
 *
 *  启动时间:2018年4月12日
 *
 *  最终修改时间:2018年4月13日
 *
 *  @author 李子琦
 */

//实现此Servlet注册
@WebServlet(name = "FirstRegServelt",urlPatterns = {"/main/java/com.yunding.news/web/control/FirstRegServelt"})

public class FirstRegServelt extends HttpServlet {

    //用户填写完成第一页面的信息后点击下一步时，前端发送请求，请求后台存入用户名和密码
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置错误序号为500，正确序号为200
        int code=500;
        //设置错误信息
        String message;

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
            account.setPassword(password);

        //调用Dao层方法，将用户名和密码存入数据库
        ServiceFactory.getService("user").saveByStep(account);

        //验证是否真的存入了数据库
        Account account1=new Account();
        account1=(Account) ServiceFactory.getService("user").findByUserName(username);
        String USERNAME=account1.getName().trim();
        String PASSWORD=account1.getPassword().trim();
        if(username.equals(USERNAME)){
            if (password.equals(PASSWORD)){
                code=200;
                message="用户名密码正确存入数据库";
            }else {
                message="密码未正确存入数据库！！！";
            }
        }else {
            message="用户名未正确存入数据库！！！";
        }

        //将数据返回给前端
        //获得PrintWriter输出流对象
        PrintWriter out=response.getWriter();
        //获得一个JSON对象
        JSONObject Reg=new JSONObject();
        //将数据放入该JSON对象中
        Reg.put("code",code);
        Reg.put("message",message);
        //如果用户名密码正确存入数据库，前端得到的序号是200，出现错误即得到500
        out.write(Reg.toString());

        //清空并关闭流
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
