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
 *  类名称:DologinServlet
 *
 *  类作用：用于接收并存储登录界面提交的用户名和密码信息
 *
 *  启动时间:2018年4月15日
 *
 *  最终修改时间:2018年4月15日
 *
 *  @author 李子琦
 */

//实现此servlet的注册
@WebServlet(name = "DologinServlet",urlPatterns = {"/main/java/com.yunding.news/web/control/DologinServlet"})

public class DologinServlet extends HttpServlet {

    //点击登录的同时发送请求给这个servlet，进行登录
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

        //调用Dao层方法，得到用户信息
        //实例化Account类对象
        Account account=new Account();
        account= (Account) ServiceFactory.getService("user").findByUserName(username);

        //获取需要进行对比的信息
        String NAME=account.getName().trim();
        String STATUS=account.getStatus().trim();
        String PASSWORD=account.getPassword().trim();

        //首先判断该用户是否存在
        if(NAME!=null){

            //如果该用户名存在，判断是否激活
            if(STATUS.equals("1")){

                //如果该用户已经激活，判断密码是否正确
                if (PASSWORD.equals(password)){

                    //三个要求均满足，返回ture
                    code=200;
                    message="该用户可以登录";

                }else{//如果密码错误，返回false
                    message="密码错误";
                }

            }else {//如果该用户未激活，同样返回false
                    message="用户名未激活";
                }

        }else {//如果该用户名不存在，同样返回false
                message="该用户不存在";
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
