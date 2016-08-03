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
import com.wxpt.site.dao.LogisticDao;
import com.wxpt.site.entity.Logistics;


public class LogisticDaoImpl  implements LogisticDao {
	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	List<Logistics> list = new ArrayList<Logistics>();
	@SuppressWarnings("unchecked")
	@Override
	public List<Logistics> getAll(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		list = null;
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Logistics.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getcount(String sql) {
		// TODO Auto-generated method stub
		list = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Logistics.class).list();
		return list.size();
	}
	

	

	@SuppressWarnings("deprecation")
	@Override
	public void save(String sql) {
		// TODO Auto-generated method stub
		session=(Session)this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		 try {
			session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//更新
	@SuppressWarnings("static-access")
	public void update(String sql,int enterId){
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
	

	public List<Logistics> getList() {
		return list;
	}

	public void setList(List<Logistics> list) {
		this.list = list;
	}

	@Override
	public Logistics getByLogId(String sql) {
		// TODO Auto-generated method stub
		return (Logistics) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Logistics.class).list().get(0);
	}

	

}
