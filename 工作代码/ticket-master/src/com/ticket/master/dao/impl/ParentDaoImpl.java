package com.ticket.master.dao.impl;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.ticket.master.common.JDBC_test;
import com.ticket.master.common.TimeUtil;

@SuppressWarnings("static-access")
public class ParentDaoImpl {
	JDBC_test jdbc = new JDBC_test();
	TimeUtil time=new TimeUtil();
/*	public int allSql(String sql) {
		int ret = 0;
		Connection conn = null;
		Statement state = null;
		ResultSet rs = null;
		try {
			conn = jdbc.getConnection();
			state = (Statement) conn.createStatement();
			state.executeUpdate(sql);
			rs = state.getGeneratedKeys();
			if (rs.next()) {
				ret = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.closeAll(conn, state, null, rs);
			jdbc = null;
		}
		return ret;
	}*/
	public int allSql(String sql) {

		int ret = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = (Connection) jdbc.getConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.executeUpdate();
			rs = (ResultSet) pstmt.getGeneratedKeys();
			if (rs.next()) {
				ret = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbc.closeAll(conn, pstmt, null, rs);
		}
		return ret;
	}
	//查詢
	public int selectSql(String sql){

		int ret = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = (Connection) jdbc.getConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			rs=pstmt.executeQuery();
			if (rs.next()) {
				ret = rs.getInt(1);
			}
//			conn = (Connection) jdbc.getConnection();
//			mt = conn.createStatement();
//			rs10 = mt.executeQuery(sql);
//			while (rs10.next()) {
//				count = rs10.getInt(1);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}/*finally{
			jdbc.closeAll(conn, pstmt, null, rs);

		}*/
		return ret;
	
	}
}
