package com.wxpt.site.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.MenuDao;
import com.wxpt.site.entity.Menu;

public class MenuDaoImpl implements MenuDao {
	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc = new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	List<Menu> menuList=new ArrayList<Menu>();
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void saveMenu(String sql) {
		// TODO Auto-generated method stub
		System.out.println(sql);
		//session=HibernateSessionFactory.getSession();  
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

	

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getAll(String sql) {
		// TODO Auto-generated method stub
		menuList=this.sessionFactory.getCurrentSession()
		.createSQLQuery(sql).addEntity(Menu.class).list();
		if(menuList.size()==0){
			return null;
		}else{
			return menuList;
		}
		
	}

	@Override
	public void update(String sql,int enterId) {
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
	public void delete(String sql) {
		// TODO Auto-generated method stub
		try {
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Menu> zfindAll(int enterId) {
		String sql="SELECT * FROM wxpt"+enterId+".menu WHERE `menu_parent_id`=0";
		return this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Menu.class).list();
	}
	@Override
	public List<Menu> zfindByMenuParentId(int enterId,int menuParentId) {
		String sql="SELECT * FROM wxpt"+enterId+".menu WHERE `menu_parent_id`="+menuParentId;
		return this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Menu.class).list();
	}
	
}
