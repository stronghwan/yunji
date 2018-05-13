package com.yunding.news.web.control.GYX;    /*
 * @Name:
 * @Author:Farmerzhang
 * @Date: 2018/5/13
 * @Time: 10:48
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yunding.news.model.pojo.PersonalCenter;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONObject;

public class ViewUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String content=request.getParameter("");//json name

        JSONObject jsonObject=JSONObject.fromObject(content);
        String yonghuming = jsonObject.getString("username");

        PersonalCenter pc = (PersonalCenter) ServiceFactory.getService("personalCenter").findByPerUserID(yonghuming);
        String sex_v = pc.getSex();
        String signature_v = pc.getSignature();
        String address_v = pc.getAddress();
        String phone_v = pc.getPhone();
        String email_v = pc.getEmail();
        String nickname_v = pc.getNickname();
        String username=pc.getUserName();
        JSONObject json = new JSONObject();
        json.put("username",username);
        json.put("sex",sex_v);
        json.put("signature",signature_v);
        json.put("address",address_v);
        json.put("phone",phone_v);
        json.put("email",email_v);
        json.put("nickname",nickname_v);
    }
}

