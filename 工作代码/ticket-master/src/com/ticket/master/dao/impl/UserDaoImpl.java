package com.ticket.master.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ticket.master.common.JDBC_test;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.ticket.master.dao.UserDao;
import com.ticket.master.entity.HibernateSessionFactory;
import com.ticket.master.entity.User;

public class UserDaoImpl implements UserDao {
	JDBC_test jdbc = new JDBC_test();
	int ret = 0;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public User getUserBySql(String sql) {
		// TODO Auto-generated method stub
		/*Session session = HibernateSessionFactory.getSession();
		try {
			
			Transaction tx = session.beginTransaction();
			tx = session.beginTransaction();
			user = (User) session.createSQLQuery(sql).addEntity(User.class)
					.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user = null;
		}finally{
			session.clear();
			session.close();
		}*/
		User user = null;
		try {
			conn = (Connection) jdbc.getConnection();
			pstmt = (PreparedStatement) conn
					.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			rs = pstmt.executeQuery();
		//	rs = (ResultSet) pstmt.getGeneratedKeys();获取该记录Id
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setPassword(rs.getString("password"));
				user.setUsername(rs.getString("username"));
				user.setCompanyName(rs.getString("company_name"));
				user.setCompanyAbbreviation(rs.getString("company_abbreviation"));
				user.setBusinessLicense(rs.getString("business_license"));
				user.setAdress(rs.getString("adress"));
				user.setResponsiblePerson(rs.getString("responsible_person"));
				user.setIdNo(rs.getString("id_no"));
				user.setResponsiblePhone(rs.getString("responsible_phone"));
				user.setQq(rs.getString("qq"));
				user.setEmergencyContact(rs.getString("emergency_contact"));
				user.setContactAddress(rs.getString("contact_address"));
				user.setUserType(rs.getInt("user_type"));
				user.setCompanyTel(rs.getString("company_tel"));
				user.setRoleId(rs.getInt("role_id"));
				user.setUserParentId(rs.getInt("user_parentId"));
				user.setAddTime(rs.getString("add_time"));
				user.setUpdateTime(rs.getString("update_time"));
				user.setUserState(rs.getInt("user_state"));
				user.setDiscount(rs.getString("discount"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}finally{
			jdbc.closeAll(conn, pstmt, null, rs);
		}
		return user;
	}

}
