package com.ticket.master.dao.impl;

import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.ticket.master.common.JDBC_test;
import com.ticket.master.dao.FlightDiscountDao;
import com.ticket.master.entity.FlightDiscount;

public class FlightDiscountDaoImpl implements FlightDiscountDao{
	JDBC_test jdbc = new JDBC_test();
	
	@SuppressWarnings("static-access")
	public FlightDiscount getDiscount(String sql) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FlightDiscount flightDiscount=null;;
		try {
			conn = (Connection) jdbc.getConnection();
			pstmt = (PreparedStatement) conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flightDiscount=new FlightDiscount();
				flightDiscount.setAirway(rs.getString("airway"));
				flightDiscount.setCabin(rs.getString("cabin"));
				flightDiscount.setDiscount(rs.getString("discount"));
				flightDiscount.setEndTime(rs.getString("end_time"));
				flightDiscount.setFlightDiscountId(rs.getInt("flight_discount_id"));
				flightDiscount.setStartTime(rs.getString("start_time"));
				flightDiscount.setEndCity(rs.getString("end_city"));
				flightDiscount.setStartCity(rs.getString("start_city"));
			}else{
				flightDiscount=null;
			}
			return flightDiscount;
		} catch (Exception e) {
			// TODO: handle exception
			flightDiscount=null;
		}finally{
			jdbc.closeAll(conn, pstmt, null, rs);
		}
		return flightDiscount;
	}


}
