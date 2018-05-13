package ToolOfJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
* JDBC工具类 
* @author Lu Chang Cai
* @date : 2018年4月17日 下午4:42:532018
*/

public class JdbcOfNews {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/highintheclouds?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PASSWORD = "0723";

	
	public static Connection getConnection(){
		Connection conn = null;
		 try {
			try {
				 Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}

		 return conn;
}
	
}
