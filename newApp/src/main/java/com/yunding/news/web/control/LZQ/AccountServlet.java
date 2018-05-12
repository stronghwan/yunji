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
 *  类名称:AccountServlet
 *
 *  类作用：为前端提供某用户的全部信息
 *
 *  启动时间:2018年4月21日
 *
 *  最终修改时间:2018年4月21日
 *
 *  @author 李子琦
 */

@WebServlet(name = "AccountServlet",urlPatterns = {"/main/java/com.yunding.news/web/control/AccountServlet"})

public class AccountServlet extends HttpServlet {

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

        //调用该用户的全部信息
        //实例化Account类对象
        Account account=new Account();
        account= (Account) ServiceFactory.getService("user").findByUserName(username);

        //判断是否真正从数据库中调出了数据
        if (account.toString()!=null){
            code=200;
            message="成功调出数据";
        }else {
            message="未成功调出数据";
        }

        String PASSWORD=account.getPassword().trim();
        String QUESTION=account.getChoice().trim();
        String ANSWER=account.getAnswer().trim();
        String EMAIL=account.getAnswer().trim();
        String PHONENUMBER=account.getPhone().trim();
        String NICKNAME=account.getNickName().trim();
        String GENDER=account.getSex().trim();
        String DEPARTMENT=account.getDepartment().trim();

        //将数据返回给前端
        //获得PrintWriter输出流对象
        PrintWriter out=response.getWriter();
        //获得一个JSON对象
        JSONObject Reg=new JSONObject();
        //将数据放入该JSON对象中
        Reg.put("code",code);
        Reg.put("message",message);
        //将数据放入该JSON对象中
        Reg.put("username",username);
        Reg.put("password",PASSWORD);
        Reg.put("question",QUESTION);
        Reg.put("answer",ANSWER);
        Reg.put("email",EMAIL);
        Reg.put("phonenumber",PHONENUMBER);
        Reg.put("nickname",NICKNAME);
        Reg.put("gender",GENDER);
        Reg.put("department",DEPARTMENT);

        //如果该用户可用，则前端收到code为200，否则为500
        out.write(Reg.toString());

        //清空并关闭流
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
