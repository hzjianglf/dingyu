package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.ParentDao;
import com.wxpt.site.entity.Reply;



public class ParentDaoImpl implements ParentDao{

	@Autowired
	SessionFactory sessionFactory;
	protected Session session=null;
	JDBC_test jdbc=new JDBC_test();
	Connection con =null;
	PreparedStatement ps = null;
	@Override
	public void add(int enterId,Object o) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(o);
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(int enterId,Object o) {
		Reply replay=(Reply) o;
		String sql="UPDATE wxpt"+enterId+".reply SET `reply_type`='"+replay.getReplyType()+"',`keywordID`="+replay.getKeywords().getKeywordId()+" WHERE `reply_id`="+replay.getReplyId();
		con=jdbc.getConnection(enterId);
		try {
			ps = con.prepareStatement(sql);
			//执行
			ps.executeUpdate();
			//con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> objectList(Object o) {
		// TODO Auto-generated method stub
		String hql = "from"+o.getClass();
		return this.sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public void delete(Object o) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(o);
	}

	@Override
	public int getCount(String hql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createQuery(hql).list().size();
	}

	@SuppressWarnings("static-access")
	@Override
	public void clearReply(String sql,int enterId) {
		// TODO Auto-generated method stub
		con=jdbc.getConnection(enterId);
		try {
			ps = con.prepareStatement(sql);
			//执行
			ps.executeUpdate();
			//con.close();	//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getCountBySql(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().size();
	}

}
