package Javabean;

import java.sql.Timestamp;
/**
* comment-javabean类
* @author Lu Chang cai 
* @date : 2018年4月19日 下午6:00:332018
*/
public class comment {
	
	private int commentID;
	private String userID;
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	private String Content;
	private Timestamp date;
	private String commentTime;
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	@SuppressWarnings("deprecation")
	public comment(int commentID, String userID, String content, Timestamp date) {
		super();
		this.commentID = commentID;
		this.userID = userID;
		Content = content;
		this.date = date;
		this.commentTime=this.date.toLocaleString();//用于除去‘.0’
	}
	
}
