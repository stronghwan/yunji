package com.yunding.news.web.others.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    //用户信息的删除
    public void inforDelete(int id){
        //数据库的连接
        Connection connection = Driver.getConnection();
        String sql1="DELETE FROM personalcenter"+
                " WHERE personalcenter.user_id="+id;
        String sql2="DELETE FROM account"+
                " WHERE account.user_id="+id;
        try {
            //将sql语句加载到驱动程序中
            PreparedStatement ptmt1 = connection.prepareStatement(sql1);
            PreparedStatement ptmt2 = connection.prepareStatement(sql2);
            //执行语句
            ptmt1.execute();
            ptmt2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    //主函数测试
//    public static void main(String[] args){
//        Delete delete = new Delete();
//        delete.inforDelete(4);
//    }
}
