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
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Canton;
import com.wxpt.site.dao.CantonDao;

public class CantonDaoImpl extends ParentDaoImpl implements CantonDao {

	@Autowired
	SessionFactory sessionFactory;
	
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public List<Canton> getCantonList(int enterId) {
		try{
			String sql = "select * from wxpt"+enterId+".canton";
		List<Canton> list=new ArrayList<Canton>();
		System.out.println(sql);
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Canton.class);
		list = query.list();
		return list;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}

	@Override
	
	public List<Canton> getCantonList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from wxpt"+enterId+".canton";
		List<Canton> list=new ArrayList<Canton>();
		System.out.println(sql);
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Canton.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		list = query.list();
		return list;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}

	@Override
	public int getCantonCount(int enterId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		int row=0;
				try{
					String sql = "select * from wxpt"+enterId+".canton";
				List<Canton> list=new ArrayList<Canton>();
				System.out.println(sql);
				/*String sql="SELECT * FROM webchat.enter_infor";*/
				System.out.println(sql);
				Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Canton.class);
				
				list = query.list();
				
				row=list.size();
				return row;
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return row;
			}
	}

	@Override
	public Canton getCantonByID(int enterId,int areaId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Canton canton=null;
				try{
					String sql = "select * from wxpt"+enterId+".canton where canton_id="+areaId;
					
				List<Canton> list=new ArrayList<Canton>();
				System.out.println(sql);
				/*String sql="SELECT * FROM webchat.enter_infor";*/
				System.out.println(sql);
				Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Canton.class);
				
				list = query.list();
				if(list.size()>0){
					canton=list.get(0);	
				}
				return canton;
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return canton;
			}
	}

	@Override
	public void deleteById(int enterId,int areaId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM wxpt"+enterId+".canton WHERE canton_id ="+areaId;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	@Override
	public Canton getCantonByName(int enterId,String cantonName) {
		// TODO Auto-generated method stub
		Canton canton=null;
		try{
			String sql = "select * from wxpt"+enterId+".canton where canton_name="+cantonName;
		List<Canton> list=new ArrayList<Canton>();
		System.out.println(sql);
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Canton.class);
		
		list = query.list();
		if(list.size()>0){
			canton=list.get(0);	
		}
		return canton;
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return canton;
	}
	}
   @Transactional
	@Override
	public void addCanton(int enterId, Canton canton) {
		
		 String sql="INSERT INTO wxpt"+enterId+".canton(canton_name, add_time, update_time, user_id, canton_no) VALUES ('"+canton.getCantonName()+"','"+TimeUtil.getTime()+"','"+TimeUtil.getTime()+"',"+canton.getUpdateUser()+",'"+canton.getCantonNo()+"')";
	        System.out.println(sql);
			/*this.sessionFactory.getCurrentSession().save(luckUser);*/
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
	public void updateCanton(int enterId, Canton canton) {
		String sql="UPDATE wxpt"+enterId+".canton SET canton_name='"+canton.getCantonName()+"',update_time='"+TimeUtil.getTime()+"',update_user="+canton.getUpdateUser()+",canton_no='"+canton.getCantonNo()+"' WHERE canton_id="+canton.getCantonId()+"";
		System.out.println(sql);
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


}
