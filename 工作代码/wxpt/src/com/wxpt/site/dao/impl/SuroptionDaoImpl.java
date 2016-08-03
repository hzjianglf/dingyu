package com.wxpt.site.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


import com.wxpt.site.dao.SuroptionDao;

import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Survey;

import com.wxpt.site.entity.Suroption;


public class SuroptionDaoImpl implements SuroptionDao{

	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public List<Suroption> findByquestionId(Integer surquestionId,Integer enterId) {
		String sql="SELECT * FROM wxpt"+enterId+".suroption WHERE `surquestion_id`="+surquestionId;
		List<Suroption>list =new ArrayList<Suroption>();
		try {
			list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Suroption.class).list();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list=null;
			System.out.println("数据出错,请仔细检查数据错误");
		}
		return list;
	}


	@Override
	public List<Suroption> getAllSuroption(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		 List<Suroption> list = new ArrayList<Suroption>();
				Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
						.addEntity(Suroption.class);
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				list=query.list();
				return list;
	}


	@Override
	public void addSuroption(String sql) {
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
	public void updateSurSuroption(String sql, int enterId) {
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


	@Override
	public Suroption getSuroption(String sql) {
		// TODO Auto-generated method stub
		
		List<Suroption>list =new ArrayList<Suroption>();
		Suroption	suroption =null;
		try {
			list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Suroption.class).list();
			if(list.size()!=0){
				suroption =list.get(0);
			}
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list=null;
			System.out.println("数据出错,请仔细检查数据错误");
		}
		return suroption;
	}


	@Override
	public List<Suroption> getAllSuroptionList(String sql) {
		// TODO Auto-generated method stub
		 List<Suroption> list = new ArrayList<Suroption>();
			Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
					.addEntity(Suroption.class);
			list=query.list();
			return list;
	}

}
