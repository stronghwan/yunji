package com.yunding.news.web.others.jdbc;

import java.sql.Connection;

public interface iconnable {
	// 先载入驱动程序，并且拿到数据库的连接
	Connection conn = Driver.getConnection();
}
