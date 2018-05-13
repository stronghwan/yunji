package searchTool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.mysql.jdbc.Connection;

import Javabean.newsOfChina;
import Javabean.newsOfIT;
import Javabean.newsOfWorld;
import Javabean.newsOfYunding;
import ToolOfJDBC.JdbcOfNews;

/**
* @author Lu Chang cai 
* @date : 2018年4月19日 下午9:23:532018
*/

public class searching {
	
	public static List<Object> Searching(String str){
		//当前存在弊端： 当用户输入空格 或者未输入时  应该如何处理
		
		List<Object> newsList = new ArrayList<Object>();
		Connection conn = (Connection) JdbcOfNews.getConnection();
		String strIT = ""+"SELECT * FROM newsOfIT WHERE headline LIKE '%"+str+"%'";
		String strChina = ""+"SELECT * FROM newsOfChina WHERE headline LIKE '%"+str+"%'";
		String strWorld = ""+"SELECT * FROM newsOfWorld WHERE headline LIKE '%"+str+"%'";
		String strYunding = ""+"SELECT * FROM newsOfYunding WHERE headline LIKE '%"+str+"%'";
		

		try {
			PreparedStatement psIT = conn.prepareStatement(strIT);
			ResultSet rsIT = psIT.executeQuery();
			while(rsIT.next()){
				newsList.add(new newsOfIT(rsIT.getInt("NewsID"),rsIT.getNString("headline"),rsIT.getTimestamp("releaseTime"),rsIT.getNString("mainBody")
						,rsIT.getString("pictureOne"),rsIT.getString("pictureTwo"),rsIT.getString("pictureThere"),rsIT.getInt("numberOfLike"),rsIT.getNString("sourceName")
						,rsIT.getNString("digestOfNews")));//  在每一个类中写一个有参的构造方法  用于添加new对象到list集合中
			}
			
			rsIT.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
		}
		
		try {
			PreparedStatement psChina = conn.prepareStatement(strChina);
			ResultSet rsChina = psChina.executeQuery();
			while(rsChina.next()){
				newsList.add(new newsOfChina(rsChina.getInt("NewsID"),rsChina.getNString("headline"),rsChina.getTimestamp("releaseTime"),rsChina.getNString("mainBody")
						,rsChina.getString("pictureOne"),rsChina.getString("pictureTwo"),rsChina.getString("pictureThere"),rsChina.getInt("numberOfLike"),rsChina.getNString("sourceName")
						,rsChina.getNString("digestOfNews")));
			}
			rsChina.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			PreparedStatement psWorld = conn.prepareStatement(strWorld);
			ResultSet rsWorld = psWorld.executeQuery();
			while(rsWorld.next()){
				newsList.add(new newsOfWorld(rsWorld.getInt("NewsID"),rsWorld.getNString("headline"),rsWorld.getTimestamp("releaseTime"),rsWorld.getNString("mainBody")
						,rsWorld.getString("pictureOne"),rsWorld.getString("pictureTwo"),rsWorld.getString("pictureThere"),rsWorld.getInt("numberOfLike"),rsWorld.getNString("sourceName")
						,rsWorld.getNString("digestOfNews")));
			}
			rsWorld.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			PreparedStatement psYunding = conn.prepareStatement(strYunding);
			ResultSet rsYunding = psYunding.executeQuery();
			while(rsYunding.next()){
				newsList.add(new newsOfYunding(rsYunding.getInt("NewsID"),rsYunding.getNString("headline"),rsYunding.getTimestamp("releaseTime"),rsYunding.getNString("mainBody")
						,rsYunding.getString("pictureOne"),rsYunding.getString("pictureTwo"),rsYunding.getString("pictureThere"),rsYunding.getInt("numberOfLike"),rsYunding.getNString("sourceName")
						,rsYunding.getNString("digestOfNews")));		
				}
			rsYunding.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//conn资源释放
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(newsList.isEmpty()){
			String withoutStr = "暂无相关内容";
			newsList.add(withoutStr);
		}
		return newsList;
	}

}
