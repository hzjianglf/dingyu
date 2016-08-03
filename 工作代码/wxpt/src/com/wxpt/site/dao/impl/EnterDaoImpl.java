package com.wxpt.site.dao.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.EnterDao;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Role;
import com.wxpt.site.entity.Shiyong;

public class EnterDaoImpl implements EnterDao{
	@Autowired
	SessionFactory sessionFactory;
	PreparedStatement pstmt = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	protected Session session = null;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public List<EnterInfor> getAll(String sql,int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<EnterInfor> list=new ArrayList<EnterInfor>();
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(EnterInfor.class);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);
		list = query.list();
		return list;
	}
	@Override
	public int enterCount(String sql) {
		// TODO Auto-generated method stub
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		List<EnterInfor> list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(EnterInfor.class).list();
		int count=list.size();
		return count;
	}
	@Override
	public void deleEnter(String sql) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	@SuppressWarnings("static-access")
	@Override
	public void updateEnter(String sql) {
		// TODO Auto-generated method stub
		/*String sql="UPDATE enter_infor SET `user_name`='"+enter.getEnterName()+"',`enter_name`='"+enter.getEnterName()+"',`enter_person`='"+enter.getEnterPerson()+"',`enter_phone`='"+enter.getEnterPhone()+"',`enter_address`='"+enter.getEnterAddress()+"'," +
				"`email`='"+enter.getEmail()+"',`url`='"+enter.getUrl()+"',`token`='"+enter.getToken()+"', state='"+enter.getState()+"'appId'"+enter.getAppId()+
				"'appSecret'"+enter.getAppSecret()+"'accessToken'"+enter.getAccessToken()+"' WHERE `enter_infor_id`="+enter.getEnterInforId()+"";*/
		try {
		con=jdbc.getConnection2();
			pstmt = con.prepareStatement(sql);
			//执行
			pstmt.executeUpdate();
				//关闭数据库连接
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@SuppressWarnings("static-access")
	@Override
	public void updateEnter2(EnterInfor enter) {
		// TODO Auto-generated method stub
		String sql="UPDATE enter_infor SET `token`='"+enter.getToken()+"' WHERE `enter_infor_id`="+enter.getEnterInforId()+"";
		con=jdbc.getConnection2();
		try {
			pstmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			/*pstmt.setString(1, userName);
			pstmt.setString(2, userNum);
			pstmt.setInt(3, userId);*/
			//执行
			pstmt.executeUpdate();
				//关闭数据库连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public EnterInfor getById(int enterId) {
		// TODO Auto-generated method stub
		String sql="select * from webchat.enter_infor WHERE `enter_infor_id`="+enterId;
		return (EnterInfor) this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(EnterInfor.class).list().get(0);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EnterInfor> getAllEnter() {
		try {
			String sql = "SELECT * FROM webchat.enter_infor ";
			return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
					.addEntity(EnterInfor.class).list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	public EnterInfor getById2(int enterId) {
		List<EnterInfor> elist=null;
		EnterInfor e=null;
		try {
			String sql="select * from webchat.enter_infor WHERE enter_infor_id= "+enterId+"" ;
			elist=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(EnterInfor.class).list();
			if(elist.size()>0){
				e=elist.get(0);
			}
		} catch (HibernateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
	@Override
	public List<EnterInfor> getByEnterInfor(String sql) {
				List<EnterInfor> enforlist=null;
				EnterInfor enterInfor=null;
						try {
							// TODO Auto-generated method stub
							 Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(EnterInfor.class);		
								
								enforlist=query.list();
								
						} catch (HibernateException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return enforlist;
	}
	@Override
	public void updateVshopBanenr(String sql) {
		// TODO Auto-generated method stub
		try {
			con=jdbc.getConnection2();
				pstmt = con.prepareStatement(sql);
				//执行
				pstmt.executeUpdate();
					//关闭数据库连接
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	@Override
	public void addQyPeiZhi(String sql) {
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
	@Override
	public List<EnterInfor> getCount(String sql) {
		// TODO Auto-generated method stub
		try {
			return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
					.addEntity(EnterInfor.class).list();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Shiyong> getall(String sql) {
		// TODO Auto-generated method stub
		List<Shiyong> list=new ArrayList<Shiyong>();
		 return (List<Shiyong>) this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Shiyong.class).list();
	}
	@SuppressWarnings("static-access")
	@Override
	public void updateAll(String sql, int enterId) {
		// TODO Auto-generated method stub
		try {
			con=jdbc.getConnection(enterId);
				pstmt = con.prepareStatement(sql);
				//执行
				pstmt.executeUpdate();
					//关闭数据库连接
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

}
