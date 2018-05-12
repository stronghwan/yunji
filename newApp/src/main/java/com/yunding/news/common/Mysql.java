package com.yunding.news.common;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Mysql {
	
	private static final String URL="jdbc:mysql://127.0.0.1:3306/test";
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	private static Connection conn=null;
	
	static{
		try {
			//1.������������
			Class.forName("com.mysql.jdbc.Driver");
			//2.������ݿ������
			conn= (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
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























