package com.yunding.news.web.others.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Permissions {
    //增加用户
    public void addUserPer(int user_id) throws SQLException {
        Connection connection = Driver.getConnection();
        String sql = "INSERT INTO permissions" +
                " (user_id)" +
                " VALUES(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,user_id);
        preparedStatement.execute();
    }


    //查询获取userId
    public int getUserId(String user_name) throws SQLException {
        Connection connection = Driver.getConnection();
        String sql = "SELECT user_id FROM account"+
                " WHERE user_name='"+user_name+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int user_id = 0;
        while (resultSet.next()) {
            user_id = resultSet.getInt("user_id");
        }
            return user_id;
    }


    //更改权限
    public void update(String[] isNo) throws SQLException {
        Connection connection = Driver.getConnection();
        String sql = "UPDATE permissions"+
                " SET frind_forwarding=?,frind_comments=?,BBS_send=?,BBS_forwarding=?,BBS_comments=?"+
                " WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for(int i = 0; i < isNo.length; i++){
            preparedStatement.setString(i+1,isNo[i]);
        }
        preparedStatement.execute();
    }


    //查询获取用户权限信息
    public String[] getPer(int user_id) throws SQLException {
        Connection connection = Driver.getConnection();
        String sql = "SELECT frind_forwarding,frind_comments,BBS_send,BBS_forwarding,BBS_comments"+
                " FROM permissions WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        String[] getPer = new String[5];
        while (resultSet.next()){
            getPer[0] = resultSet.getString("frind_forwarding");
            getPer[1] = resultSet.getString("frind_comments");
            getPer[2] = resultSet.getString("BBS_send");
            getPer[3] = resultSet.getString("BBS_forwarding");
            getPer[4] = resultSet.getString("BBS_comments");
        }
        return getPer;
    }



//    //主函数测试
//    public static void main(String[] args){
//        Permissions permissions = new Permissions();
//////        try {
//////            permissions.addUserPer(4);
//////        } catch (SQLException e) {
//////            e.printStackTrace();
//////        }
//////        try {
////////            System.out.println(permissions.getUserId("aaa"));
////////        } catch (SQLException e) {
////////            e.printStackTrace();
////////        }
//////        String[] test = {"0","0","1","1","0","5"};
//////        try {
//////            permissions.update(test);
//////        } catch (SQLException e) {
//////            e.printStackTrace();
//////        }
////        try {
////            String[] test = permissions.getPer(5);
////            for(int i = 0; i < test.length; i++)
////                System.out.print(test[i]);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }
}
