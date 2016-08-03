package com.ticket.master.common;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;



public class JDBC_test {
	// 创建静态全局变量
	static Connection conn;
	static PreparedStatement ptst;
	static Statement st;
	
	public static void main(String[] args) {
		Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			String url = "jdbc:mysql://211.154.224.228:3306/ticketmaster?useUnicode=true&autoReconnect=true&characterEncoding=utf8";
			String username = "user";
			String password = "user";
			con = (Connection) DriverManager.getConnection(url, username, password);// 创建数据连接
			System.out.println("数据库连接dsd");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败" + e.getMessage());
		}
	}
	/* 获取数据库连接的函数 */
	public static Connection getConnection() {

		Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动
			String url = "jdbc:mysql://211.154.224.228:3306/ticketmaster?useUnicode=true&autoReconnect=true&characterEncoding=utf8";
			String username = "user";
			String password = "user";
			con = (Connection) DriverManager.getConnection(url, username, password);// 创建数据连接
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败" + e.getMessage());
			return null;
		}
		return con; // 返回所建立的数据库连接
	}

	public static void closeAll(Connection con, Statement mt,
			PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (mt != null) {
				mt.close();
				mt = null;
			}
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			if (con != null && con.isClosed() == false) {
				con.close();
				con = null;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
