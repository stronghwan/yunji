package com.yunding.news.web.others.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yunding.news.model.pojo.PrivateChat;
import com.yunding.news.model.pojo.SystemData;


public class Updata  implements iconnable{

	//从数据库中读取系统消息
	public List<String> getData() throws Exception{
		String sql = "select content from t_system_message order by id";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		List<String> ls= new ArrayList<String>();
		while(rs.next()){
			ls.add(rs.getString("content"));
		}
		return ls;
	}
	//把管理员发布的消息存入数据库
	public void addData(String message) throws Exception{
		String sql="insert into t_system_message(content) values(?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, message);
		ptmt.execute();
	}
	//从数据库读取私聊信息
	public List<PrivateChat> getPData(String username,String fusername) throws Exception{
		String sql = "select uid,fid,message,time from t_private_message "
				+ "where (uid="+username+" and fid="+fusername+") "
				+ "or (uid="+fusername+" and fid="+username+") order by time";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		List<PrivateChat> ls= new ArrayList<PrivateChat>();
		while(rs.next()){
			PrivateChat pc= new PrivateChat();
			pc.setUid(rs.getString("uid"));
			pc.setFid(rs.getString("fid"));
			pc.setContent(rs.getString("message"));
			pc.setTime(rs.getString("time"));
			ls.add(pc);
		}
		return ls;
	}
	//把用户的私聊信息存入数据库
	public void addPData(String uid,String fid,String message,String time) throws Exception{
		String sql="insert into t_private_message(uid,fid,message,time) values(?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, uid);
		ptmt.setString(2, fid);
		ptmt.setString(3, message);
		ptmt.setString(4, time);
		ptmt.execute();
	}
	//注册用户人数+1
	public void addUser() throws Exception{
		String sql = "update t_system_data set user_num=user_num+1";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.execute();
	}
	//发布内容数量+1
	public void addMessage() throws Exception{
		String sql = "update t_system_data set message_num=message_num+1";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.execute();
	}
	//推送新闻条数+1
	public void addNews() throws Exception{
		String sql = "update t_system_data set news_num=news_num+1";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.execute();
	}
	public SystemData getNum() throws Exception{
		//从数据库获取  	 用户注册数量、发布内容数量、推送新闻数量
		SystemData sd = new SystemData();
		String sql1 = "select * from t_system_data";
		PreparedStatement ptmt1 = conn.prepareStatement(sql1);
		ResultSet rs1 = ptmt1.executeQuery();
		while(rs1.next()){
			sd.setUserNum(rs1.getInt("user_num"));
			sd.setMessageNum(rs1.getInt("message_num"));
			sd.setNewsNum(rs1.getInt("news_num"));
		}
		return sd;
	}
}









