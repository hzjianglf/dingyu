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
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.AppraiseDao;
import com.wxpt.site.entity.Appraise;

public class AppraiseDaoImp implements AppraiseDao {
	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	List<Appraise> list = new ArrayList<Appraise>();

	@Override
	public List<Appraise> getAll(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		list = null;
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Appraise.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		list = query.list();

		return list;
	}

	public List<Appraise> getList() {
		return list;
	}

	public void setList(List<Appraise> list) {
		this.list = list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getCount(String sql) {
		// TODO Auto-generated method stub
		list = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Appraise.class).list();
		return list.size();
	}

	@Override
	public boolean delete(String sql) {
		// TODO Auto-generated method stub
		boolean f=true;
		try {
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			f=false;
			e.printStackTrace();
		}
		return f;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Appraise> getAll2(String sql) {
		// TODO Auto-generated method stub
		list = null;
		list= this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Appraise.class).list();
		return list;
	}
	@Transactional
	@Override
	public void update(String sql) {
		// TODO Auto-generated method stub
		con=jdbc.getConnection2();
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
	

	
	/**
	 * 刘百冰
	 * 获取评价
	 * @param hql
	 * @return
	 */
	public List<Appraise> getAppraise(String hql){
		List <Appraise> appraise = new ArrayList<Appraise>();
		try {
			appraise = sessionFactory.getCurrentSession().createQuery(hql).list();
		} catch (HibernateException e) {
			appraise = null;
			System.out.println("获取评价报异常！");
		}
		return appraise;
	}
	/**
	 * 刘百冰
	 * 分页获取评价
	 * @param hql
	 * @param startIndex 开始索引
	 * @param pageSize 每页个数
	 * @return
	 */
	public List<Appraise> getAppraiseByPage(String hql,int startIndex, int pageSize){
		List <Appraise> appraise = new ArrayList<Appraise>();
		try {
			Query query = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Appraise.class);
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			appraise = query.list();
		} catch (HibernateException e) {
			appraise = null;
			System.out.println("获取评价报异常！");
		}
		return appraise;
	}
	public int getNumber(String hql){
		int i = 0;
		try {
			i = sessionFactory.getCurrentSession().createSQLQuery(hql).addEntity(Appraise.class).list().size();
		} catch (HibernateException e) {
			i = 0;
			System.out.println("获取评价数目报异常！");
		}
		return i;
	}
	public int save(String sql) {
		int i = 0;
		session=(Session)this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		 try {
			i = session.connection().createStatement().executeUpdate(sql);
		} catch (HibernateException e) {
			i = 0;
			e.printStackTrace();
		} catch (SQLException e) {
			i = 0;
			e.printStackTrace();
		}
		 return i;
	}

}
