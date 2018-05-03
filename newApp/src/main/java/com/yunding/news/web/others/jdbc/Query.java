package com.yunding.news.web.others.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query {
    public List inforQuery(){
        //数据库的连接
        Connection connection = Driver.getConnection();
        List<Map> list = new ArrayList<Map>();

        String sql = "SELECT account.user_id,account.user_nickName,account.user_name,\n"+
                "account.user_sex,personalcenter.w_address,account.user_phone,account.user_email,\n"+
                "personalcenter.w_department\r\n"+
                "FROM account JOIN personalcenter ON account.user_id=personalcenter.user_id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Integer id = resultSet.getInt("user_id");//id
                String name = resultSet.getString("user_nickName");//昵称
                String username = resultSet.getString("user_name");//用户名
                String sex = resultSet.getString("user_sex");//性别
                String address = resultSet.getString("w_address");//地址
                Integer phone = resultSet.getInt("user_phone");//电话
                String email = resultSet.getString("user_email");//邮箱
                String department = resultSet.getString("w_department");//部门
                //获取用循环接收数据库的表格信息

                Map map = new HashMap();
                map.put("id",id);
                map.put("name", name);
                map.put("username",username);
                map.put("sex",sex);
                map.put("address",address);
                map.put("phone",phone);
                map.put("email",email);
                map.put("department",department);
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
