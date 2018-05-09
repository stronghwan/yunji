package com.yunding.news.web.control;

import com.yunding.news.model.pojo.Account;
import com.yunding.news.model.pojo.Attention;
import com.yunding.news.model.service.ServiceFactory;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/16-21-09
 */
public class Servlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 前端传过来的数据 用户名 这个用户名就是你要关注的人  name
        // 判断是否关注过这个人
        String name1 = null;
        String name2 = null;
        Account account = (Account) ServiceFactory.getService("user").findByUserName(name1);
        if(account.getName().equals(name1)){
            // 如果已经存在
        }else {
            Attention attention = new Attention();
            attention.setAuserName(name1);
            // 通过前端传来的name查找到自己的id
            int id1 = ServiceFactory.getService("user").findUserId(name1);
            attention.setAuserId(id1);
            int id2 = ServiceFactory.getService("user").findUserId(name2);
            attention.setAuserId(id2);
            int number = ServiceFactory.getService("attention").save(account);
        }
    }
}
