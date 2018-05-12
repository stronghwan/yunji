package com.yunding.news.web.control.LZQ;

import com.yunding.news.model.pojo.Account;
import com.yunding.news.model.service.ServiceFactory;
import com.yunding.news.web.others.LZQ.DoSend;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  类名称:ThirdRegServelt
 *
 *  类作用：用于接收并存储第三注册页面提交的用户昵称，性别和部门信息
 *
 *  启动时间:2018年4月14日
 *
 *  最终修改时间:2018年4月15日
 *
 *  @author 李子琦
 */

//实现此Servlet注册
@WebServlet(name = "ThirdRegServlet",urlPatterns = {"/main/java/com.yunding.news/web/control/ThirdRegServlet"})

public class ThirdRegServlet extends HttpServlet {

    //用户点击第三页面的完成注册时，前端发送请求到此servlet，负责将用户昵称，性别，部门存入数据库，
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
            //得到昵称
            String nickName=request.getParameter("nickName");
            //得到性别
            String gender=request.getParameter("gender");
            //得到部门
            String department=request.getParameter("department");
            //得到邮箱
            String email=request.getParameter("email");

        //将得到的数据封装
        //实例化Account类对象
        Account account=new Account();
        account.setName(username);
        account.setNickName(nickName);
        account.setSex(gender);
        account.setDepartment(department);

        //调用Dao层方法，讲昵称，性别以及部门存入数据库
        ServiceFactory.getService("user").saveByStepThree(account);

        //判断是否真的存入数据库了
        Account account1=new Account();
        account1=(Account) ServiceFactory.getService("user").findByUserName(username);
        String USERNAME=account1.getName().trim();
        String NICKNAME=account1.getNickName().trim();
        String GENDER=account1.getSex().trim();
        String DEPARTMENT=account1.getDepartment().trim();
        if (USERNAME.equals(username)){
            if (NICKNAME.equals(nickName)&&GENDER.equals(gender)&&DEPARTMENT.equals(department)){
                code=200;
                message="正确存入数据，可以进行下一步";
            }else {
                message="存入数据错误";
            }
        }else{
            message="该用户不存在，请检查之前的数据库存储是否有异常";
        }

        
        DoSend ds=new DoSend();
        ds.sendMail(email, "云际app账号激活邮件","<h1>这是一封来自云际app的账号激活邮件</h1><style=textalign:center;>\n" +
                "<br/>\n" +
                "<h2>欢迎您使用云际app，为了保证用户体验，防止恶意注册，我们采用了账号激活的方式，请点击邮件下方的超链接，您的账号将被激活。如果您未进行本操作，请勿点击下方链接并检查您的邮箱安全，祝您生活愉快！</h2>\n\n" +
                "<h2>You are welcome to use the cloud app. In order to ensure the user experience and prevent malicious registration, we adopted the way of account activation. " +
                "Please click the hyperlink at the bottom of the email, and your account will be activated.If you do not do this operation, please do not click the link below and check your email security, wish you a happy life!</h2>\n\n"+
                "<h3><a href='http://192.168.5.1:8080/servlet/ServletForActivate?username="+username+"'>点击此处激活账号</a></h3>\n");

        //将数据返回给前端
        //获得PrintWriter输出流对象
        PrintWriter out=response.getWriter();
        //获得一个JSON对象
        JSONObject Reg=new JSONObject();
        //将数据放入该JSON对象中
        Reg.put("code",code);
        Reg.put("message",message);
        //如果昵称，性别以及部门正确存入数据库，前端得到的序号是200，出现错误即得到500
        out.write(Reg.toString());

        //清空并关闭流
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
