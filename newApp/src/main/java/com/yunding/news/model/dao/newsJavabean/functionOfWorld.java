package Javabean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import ToolOfJDBC.JdbcOfNews;

public class functionOfWorld {

	int newsID;

	public functionOfWorld(int newsID) {
		super();
		this.newsID = newsID;
	}

	// 点赞方法
	public void addLike() throws SQLException {

		
		Connection conn = (Connection) JdbcOfNews.getConnection();
		String str = "" + "UPDATE newsOfWorld SET numberOfLike=numberOfLike+1 WHERE NewsID=?";

		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str);
		ps.setInt(1, this.newsID);
		ps.execute();

		// 资源释放
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 取消赞方法
	public void cancelLike() {
		
		Connection conn = (Connection) JdbcOfNews.getConnection();
		String str = "" + "UPDATE newsOfWorld SET numberOfLike=numberOfLike-1 WHERE NewsID=?";

		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(str);
			ps.setInt(1, this.newsID);
			ps.execute();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 资源释放
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 评论方法
	public void comment(String comment,String UserID) {   //这里需要前端页面传来的操作账户的ID账号  并将其储存进MySQL comment表格中

		Connection conn = JdbcOfNews.getConnection();
		
		java.util.Date date = new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		
		String str1 = "" + "INSERT Worldcomment"+this.newsID+"(UserID,Content,CommentTime) VALUES('"+UserID+"','"+comment+"','"+time+"');";
		
		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str1);
			ps.execute();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 资源释放
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}