package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;

import org.json.JSONObject;

import com.mysql.jdbc.Connection;

import Javabean.comment;
import Javabean.newsOfIT;
import ToolOfJDBC.JdbcOfNews;
import net.sf.json.JSONArray;

/**
 * servlet:用于新闻的推送
 * 
 * @author Lu Chang cai
 * @date : 2018年4月22日 下午9:17:452018
 */
@SuppressWarnings({ "serial", "unused" })
public class pushNewsOfIT extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		doPost(request, response);

	}

	// 用于返回一类新闻对象的方法（以json格式）
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		Connection conn = (Connection) JdbcOfNews.getConnection();
		String userID = request.getParameter("userID");
		String str = "SELECT hasBrowse FROM browseIT WHERE userID=" + userID;

		PreparedStatement pre;
		ResultSet rs;
		try {
			pre = conn.prepareStatement(str);
			rs = pre.executeQuery();

			if (rs == null) {
				//如果不存在阅读记录 则先插入新记录  
				String str2 = "INSERT INTO browseIT(userID,hasBrowse) VALUES('"+userID+"',10);";
				pre = conn.prepareStatement(str2);
				pre.execute();
				
				//再返回新闻数据（json） 每次十条
				int i = 1;
				List<newsOfIT> list = null ;
				while(i<=10){
					list.add(new newsOfIT(i));
					i++;
				}
				JSONArray jsonArray = JSONArray.fromObject(list);
				response.getWriter().print(jsonArray);
				
				//伴随返回每条新闻对应的评论
				List list2 = list.get(0).ReturnComments();
				JSONArray jsonArrayTwo = JSONArray.fromObject(list2);
				response.getWriter().print(jsonArrayTwo);
			} else {
				
				//浏览表中已存在该用户的阅读记录
				int browser = rs.getInt("hasBrowse");
				List<newsOfIT> list= null;
				int i = 1;
				while(i<=10){
					list.add(new newsOfIT(browser));
					i++;
					String str3 ="UPDATE browseIT SET hasBroser=hasBrowse+1 WHERE userID="+userID; 
					pre = conn.prepareStatement(str3);
					pre.execute();
				}
				JSONArray jsonArray = JSONArray.fromObject(list);
				response.getWriter().print(jsonArray);
				//伴随返回每条新闻对应的评论
				List list2 = list.get(0).ReturnComments();
				JSONArray jsonArrayTwo = JSONArray.fromObject(list2);
				response.getWriter().print(jsonArrayTwo);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
