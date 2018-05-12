package com.yunding.news.web.control.LZQ;

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
 *  类名称:CheckoutServlet
 *
 *  类作用：用于验证用户名是否重复并返回一个布尔值
 *
 *  启动时间:2018年4月12日
 *
 *  最终修改时间:2018年4月13日
 *
 *  @author 李子琦
 */

//实现此servelt的注册
@WebServlet(name = "Checkout2Servlet",urlPatterns = {"/main/java/com.yunding.news/web/control/Checkout2Servlet"})

public class Checkout2Servlet extends HttpServlet {

    //前端监视email栏的输入状态，一旦完成输入，前端将请求此Servlet，检测email的重复性
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置错误序号为500，正确序号为200
        int code=500;
        //设置错误信息
        String message;

        // 处理编码问题
            //请求中文编码设置
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
            String email=jsonObject.getString("email");

        //调用Dao层方法，检查email是否重复
        String Email= ServiceFactory.getService("user").findByUserEmail(email).trim();
            //查看数据库中是否有此邮箱
            Boolean flag=Email.equals(email);
            if (flag){
                message="该邮箱已经被使用";
            }else{
                code=200;
                message="该邮箱可以使用";
            }

        //将数据返回给前端
            //获得PrintWriter输出流对象
            PrintWriter out=response.getWriter();
            //获得一个JSON对象
            JSONObject Reg=new JSONObject();
            //将数据放入该JSON对象中
            Reg.put("code",code);
            Reg.put("message",message);
            //如果该用户可以登录，则前端得到的code是200，否则得到500
            out.write(Reg.toString());

        //清空并关闭流
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);

    }
}
