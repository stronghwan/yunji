package com.yunding.news.web.others.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {

    //Driver=com.mysql.jdbc.Driver
    //url=jdbc:mysql://localhost:3306/ydapp?useSSL=true
    //user=root
    //password=123456
    private static final String URL = "jdbc:mysql://localhost:3306/ydapp?useSSL=true";
    private static final String NAME = "root";
    private static final String PASSWORD = "123456";

    private static Connection conn = null;

    static{
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            //2.获得数据库的链接
            conn = DriverManager.getConnection(URL,NAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }
}
