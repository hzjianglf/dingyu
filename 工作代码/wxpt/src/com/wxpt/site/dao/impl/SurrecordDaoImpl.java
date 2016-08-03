package com.wxpt.site.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;

import com.wxpt.site.dao.SurrecordDao;

import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surrecord;

import com.wxpt.site.entity.Surrecord;
import com.wxpt.site.entity.Survey;


public class SurrecordDaoImpl implements SurrecordDao{

@Autowired
SessionFactory sessionFactory;
protected Session session = null;
JDBC_test jdbc=new JDBC_test();
Connection con = null;
PreparedStatement pstmt = null;
Statement stmt = null;
	@Override
	public Suroption findByOptionId(int optionId, int enterId) {
		String sql="SELECT * FROM wxpt"+enterId+".suroption WHERE `option_id`="+optionId;
		List<Suroption> list=new ArrayList<Suroption>();
		try {
			list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Suroption.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据可能存在错误，请仔细检查数据");
		}
		return list.get(0);
	}
	@Override
	public void save(String sql, int enterId) {
		
//		con=jdbc.getConnection(enterId);
//		 try {
//			stmt = con.createStatement();
//			int result = stmt.executeUpdate(sql);
//			stmt.close();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		 
		// TODO Auto-generated method stub
		System.out.println(sql);
		/*this.sessionFactory.getCurrentSession().save(luckUser);*/
		session=(Session)this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		 try {
			session.connection().createStatement().executeUpdate(sql);
			session.getTransaction().commit();
		//	session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	List<Surrecord> list = new ArrayList<Surrecord>();
	@Override
	public List<Surrecord> getAllSurrecord(String sql) {
		System.out.println(sql);
		// TODO Auto-generated method stub
		 try {
			
				Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
						.addEntity(Surrecord.class);
				
				list=query.list();
				return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public List<Surrecord> getList() {
		return list;
	}
	public void setList(List<Surrecord> list) {
		this.list = list;
	}
	@Override
	public void deleteSurrecord(String sql, int enterId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				// TODO Auto-generated method stub
						try {
							this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
						} catch (HibernateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	}
	
	
	


}
