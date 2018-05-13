package Javabean;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import ToolOfJDBC.JdbcOfNews;

/**
 * @author Lu Chang Cai
 * @date : 2018年4月19日 下午5:34:122018
 */
@SuppressWarnings("unused")
public class newsOfIT {

	// 新闻自增主键
	private int newsID;
	// 新闻标题

	private String headline;
	// 发布时间
	private Timestamp releaseTime;
	// 新闻正文
	private String mainBody;
	// 图片材料
	private String pictureOne;
	
	private String pictureTwo;
	
	private String pictureThere;
	// 被赞数量
	private int numberOfLike;
	// 新闻来源
	private String sourceName;

	// 新闻摘要

	private String digestOfNews;
	// 该属性用去消去releaseTime后面的 ".0"
	String releaseTimeToLocaleString;
	// 评论集合
	List<comment> comments;

	// 含参构造器(用于新闻搜索）
	public newsOfIT(int newsID, String headline, Timestamp releaseTime, String mainBody, String pictureOne,
			String pictureThere, String pictureTwo, int numberOfLike, String sourceName,String digestOfNews) {
		this.newsID = newsID;
		this.headline = headline;
		this.releaseTime = releaseTime;
		this.mainBody = mainBody;
		this.pictureOne = pictureOne;
		this.pictureTwo = pictureTwo;
		this.pictureThere = pictureThere;
		this.numberOfLike = numberOfLike;
		this.sourceName = sourceName;
		this.digestOfNews = digestOfNews;

	}

	// 新闻实体类构造方法
	@SuppressWarnings("deprecation")
	public newsOfIT(int counter) throws SQLException {
		super();
		Connection conn = JdbcOfNews.getConnection();
		String sql = "" + "SELECT * FROM newsofit WHERE NewsID=?";
		PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
		ptmt.setInt(1, counter);
		ResultSet rs = ptmt.executeQuery();

		while (rs.next()) {
			this.newsID = rs.getInt("newsID");
			this.headline = rs.getNString("headline");
			this.releaseTime = rs.getTimestamp("releaseTime");
			this.mainBody = rs.getNString("mainBody");
			this.pictureOne = rs.getString("pictureOne");
			this.pictureTwo = rs.getString("pictureTwo");
			this.pictureThere = rs.getString("pictureThere");
			this.numberOfLike = rs.getInt("numberOfLike");
			this.sourceName = rs.getString("sourceName");
			this.digestOfNews = rs.getNString("digestOfNews");
			this.releaseTimeToLocaleString = releaseTime.toLocaleString();// 运用toLocaleString方法将得到的时间后面的.0去掉

		}

		// 在构建对象的同时 创建对应的评论表（首先判断该条新闻的评论表是否存在）
		String str2 = "" + "CREATE TABLE IF NOT EXISTS ITComment" + this.newsID + "("
				+ "commentID SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," + "UserID VARCHAR(50),"
				+ "Content MEDIUMTEXT," + "CommentTime DATETIME)ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str2);
		if (!ps.execute()) {

			System.out.println("NewsOfIT" + this.newsID + "的评论表：" + "ITComment" + this.newsID + "已创建成功");
		}

		// 资源释放
		if (conn != null) {
			conn.close();
		}
	}

	// 跟随构造方法 返回该新闻类对象的评论
	public List<comment> ReturnComments() {

		Connection conn = JdbcOfNews.getConnection();
		String sql = "" + "SELECT * FROM ITcomment" + this.newsID;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(new comment(rs.getInt("commentID"), rs.getString("userID"), rs.getString("content"),
						rs.getTimestamp("commentTime")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return comments;

	}

	// 点赞方法
	public void addLike() throws SQLException {

		++this.numberOfLike;
		Connection conn = (Connection) JdbcOfNews.getConnection();
		String str = "" + "UPDATE newsOfIT SET numberOfLike=numberOfLike+1 WHERE NewsID=?";

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
		--this.numberOfLike;
		Connection conn = (Connection) JdbcOfNews.getConnection();
		String str = "" + "UPDATE newsOfIT SET numberOfLike=numberOfLike-1 WHERE NewsID=?";

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
	public void comment(String comment, String UserID) { // 这里需要前端页面传来的操作账户的ID账号
															// 并将其储存进MySQL
															// comment表格中

		Connection conn = JdbcOfNews.getConnection();

		java.util.Date date = new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());

		String str1 = "" + "INSERT ITcomment" + this.newsID + "(UserID,Content,CommentTime) VALUES('" + UserID + "','"
				+ comment + "','" + time + "');";

		try {
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(str1);
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

}
