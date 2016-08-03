package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.ZaixianzhifuDao;


public class ZaixianzhifuDaoImpl implements ZaixianzhifuDao {

	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc = new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;

	
	
	@Override
	public void fukuanchenggong(int enterId,String ordermum) {
		// TODO Auto-generated method stub
		String sql = "UPDATE "+enterId+".`order` SET `pay_type`=1  WHERE `order_num`='"+ordermum+"'";
		try {
			con = jdbc.getConnection(enterId);
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void yifahuo(int enterId,String ordermum) {
		// TODO Auto-generated method stub
				String sql = "UPDATE wxpt"+enterId+".`order` SET `send_type`=1  WHERE `order_num`='"+ordermum+"'";
				try {
					con = jdbc.getConnection(enterId);
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

}
