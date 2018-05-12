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
 *  类名称:ActivateServlet
 *
 *  类作用：当用户点击邮箱中的链接时，将启用此Servlet，将用户的状态调整为激活
 *
 *  启动时间:2018年4月21日
 *
 *  最终修改时间:2018年4月21日
 *
 *  @author 李子琦
 */

//实现此servlet的注册
@WebServlet(name = "ActivateServlet",urlPatterns = {"/main/java/com.yunding.news/web/control/ActivateServlet"})

public class ActivateServlet extends HttpServlet {

    //发送的邮件中会包含一个超链接，该超链接指向了此servlet，负责将该用户名的状态激活
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

        //对前端传入的json进行解析
            //使用String接收
            String json=request.getParameter("account");
            //将接收的String转换为json
            JSONObject jsonObject=JSONObject.fromObject(json);
            //得到用户名
            String username=jsonObject.getString("username");

        //调用Dao层方法，将状态由0改为1
        ServiceFactory.getService("user").mosdifiedUserInfo(username);

        //判断是否真的修改了数据库内容
        Account account1=new Account();
        account1=(Account) ServiceFactory.getService("user").findByUserName(username);
        String status=account1.getStatus().trim();
        if (status.equals("1")){
            code=200;
            message="该用户已经成功被激活";
        }else {
            message="该用户未被成功激活";
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
