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
 *  类名称:SecondRegServlet
 *
 *  类作用：用于接收并存储第二注册页面提交的问题内容，问题答案，邮箱地址和电话号码
 *
 *  启动时间:2018年4月14日
 *
 *  最终修改时间:2018年4月14日
 *
 *  @author 李子琦
 */

//实现此Servlet注册
@WebServlet(name = "SecondRegServlet",urlPatterns = {"/main/java/com.yunding.news/web/control/SecondRegServlet"})

public class SecondRegServlet extends HttpServlet {

    //用户点击第二注册界面的下一步按钮时，前端请求发送给此Servlet，负责将用户的安全问题，答案，电话号码以及手机邮箱存入数据库
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
            //得到问题内容
            String question=request.getParameter("question");
            //得到答案
            String answer=request.getParameter("answer");
            //得到邮箱
            String email=request.getParameter("email");
            //得到电话
            String phoneNumber=request.getParameter("phoneNumber");

        //将得到的数据封装
        //实例化Account类对象
        Account account=new Account();
        account.setName(username);
        account.setChoice(question);
        account.setAnswer(answer);
        account.setEmail(email);
        account.setPhone(phoneNumber);

        //调用Dao层方法，讲问题内容，答案，邮箱以及电话存入数据库
        ServiceFactory.getService("user").saveByStepTwo(account);

        //验证是否真的存入了数据库
        Account account1;
        account1=(Account) ServiceFactory.getService("user").findByUserName(username);
        String USERNAME=account1.getName().trim();
        String QUESTION=account1.getChoice().trim();
        String ANSWER=account1.getAnswer().trim();
        String EMAIL=account1.getEmail().trim();
        String PHONENUMBER=account1.getPhone().trim();
        if (USERNAME.equals(username)){
            if (QUESTION.equals(question)&&ANSWER.equals(answer)&&EMAIL.equals(email)&&PHONENUMBER.equals(phoneNumber)){
                code=200;
                message="正确存入数据，可以进行下一步";
            }else {
                message="存入数据错误";
            }
        }else {
            message="该用户不存在，请检查之前的数据库存储是否有异常";
        }

        //将数据返回给前端
        //获得PrintWriter输出流对象
        PrintWriter out=response.getWriter();
        //获得一个JSON对象
        JSONObject Reg=new JSONObject();
        //将数据放入该JSON对象中
        Reg.put("code",code);
        Reg.put("message",message);
        //如果安全问题，答案，邮箱以及手机号正确存入数据库，前端得到的序号是200，出现错误即得到500
        out.write(Reg.toString());

        //清空并关闭流
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
