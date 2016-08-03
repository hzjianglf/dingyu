package com.wxpt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_test {
	// 创建静态全局变量
	static Connection conn;

	static Statement st;
	/* 获取数据库连接的函数*/
	public static Connection getConnection(int enterId) {

		Connection con = null;	//创建用于连接数据库的Connection对象
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

			

			String url="jdbc:mysql://localhost:3306/wxpt"+enterId+"?useUnicode=true&characterEncoding=utf8";
			//String url="jdbc:mysql://211.154.224.231:3306/wxpt"+enterId+"?useUnicode=true&characterEncoding=utf8";
			String username="wxpt";
			String password="wxpt";

			con =  DriverManager.getConnection(url , username , password ) ;// 创建数据连接
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con;	//返回所建立的数据库连接
	}
	/* 获取数据库连接的函数*/
	public static Connection getConnection2() {

		Connection con = null;	//创建用于连接数据库的Connection对象
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

			

			//String url="jdbc:mysql://211.154.224.231:3306/webchat?useUnicode=true&characterEncoding=utf8";
			String url="jdbc:mysql://localhost:3306/webchat?useUnicode=true&characterEncoding=utf8";
			String username="wxpt";
			String password="wxpt";

			con =  DriverManager.getConnection(url , username , password ) ;// 创建数据连接
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败" + e.getMessage());
		}
		return con;	//返回所建立的数据库连接
	}

}


