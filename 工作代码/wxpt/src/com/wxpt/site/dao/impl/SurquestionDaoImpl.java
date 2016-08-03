package com.wxpt.site.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.wxpt.site.dao.SurquestionDao;

import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surquestion;
import com.wxpt.site.entity.Survey;

import com.wxpt.site.entity.Surquestion;


public class SurquestionDaoImpl implements SurquestionDao{

	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public List<Surquestion> getAllSurquestion(String sql, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		 List<Surquestion> list = new ArrayList<Surquestion>();
			Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
					.addEntity(Surquestion.class);
			query.setFirstResult((page-1)*pageSize);
			query.setMaxResults(pageSize);
			list=query.list();
			return list;
	}
	@Override
	public List<Surquestion> getAllSurquestion(String sql) {
		// TODO Auto-generated method stub 
			// TODO Auto-generated method stub
		System.out.println(sql);
			 try {
				List<Surquestion> list = new ArrayList<Surquestion>();
					Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
							.addEntity(Surquestion.class);
					list=query.list();
					return list;
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}

	@Autowired
	SessionFactory sessionFactory;
	@Override
	public List<Surquestion> findAll(int enterId) {
		// TODO Auto-generated method stub
		List<Surquestion> list=new ArrayList<Surquestion>();
		String sql="SELECT * FROM wxpt"+enterId+".surquestion ";
		try {
			 list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Surquestion.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据可能有问题");
			list=null;
		}
		return list;
	}
	@Override
	public void addSurquestion(String sql) {
		// TODO Auto-generated method stub
		System.out.println(sql);
		try {
			session=(Session)this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	@Override
	public void updateSurquestion(String sql, int enterId) {
		System.out.println(sql);
		// TODO Auto-generated method stub
		con=jdbc.getConnection(enterId);
		try {
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
			con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void deleteSurquestion(String sql, int enterId) {
		// TODO Auto-generated method stub
		try {
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
