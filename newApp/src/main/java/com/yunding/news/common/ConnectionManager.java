package com.yunding.news.common;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.PagedResultsControl;
import javax.sql.DataSource;
import java.beans.Transient;
import java.io.IOException;
import java.io.InputStream;
import java.net.PortUnreachableException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @TODO
 * @Author Hao Wan
 * @Verison
 * @Date2018/4/2-19-52
 */
public final class ConnectionManager {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASSWORD;
    private static DataSource dataSource;
    // 利用多线程 有利于对事物管理
    private final static ThreadLocal<Connection> local = new ThreadLocal<Connection>();

    static {
//        第一种方法
//        Properties properties = new Properties();
//        InputStream inputStream = ConnectionManager.class.getResourceAsStream("jdbcinfo.properties");
//        try {
//            properties.load(inputStream);
//            DRIVER = properties.getProperty("Driver");
//            URL = properties.getProperty("url");
//            USER = properties.getProperty("user");
//            PASSWORD = properties.getProperty("password");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        第二种方法
           dataSource = new ComboPooledDataSource("c3p0");
    }

    /**
     * 连接数据库
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //Class.forName(DRIVER);
        Connection conn = local.get();
        if (conn != null){
            return conn;
        }else {
           // conn = DriverManager.getConnection(URL,USER,PASSWORD);
            conn = dataSource.getConnection();
        }
        local.set(conn);
        System.out.println("连接成功！");
        return conn;
    }

    /**
     * 释放连接对象
     * 释放ThreadLocal
     */
    public static void release(){
        Connection conn = local.get();
        if (conn != null) {
            DBUtil.release(conn);
            local.remove();
        }
    }
}