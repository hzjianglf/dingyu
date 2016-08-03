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
import com.wxpt.site.entity.Radius;
import com.wxpt.site.dao.RadiusDao;

public class RadiusDaoImpl implements RadiusDao {
	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@SuppressWarnings("unchecked")
	@Override
	public List<Radius> getRadiusList(int enterId) {
		// TODO Auto-generated method stub
		try{
			String sql = "select * from wxpt"+enterId+".radius";
			List<Radius> list=new ArrayList<Radius>();
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Radius.class);
			
			list = query.list();
			return list;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Radius> getRadiusList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		try{
		String sql = "select * from wxpt"+enterId+".radius";
		List<Radius> list=new ArrayList<Radius>();
		System.out.println(sql);
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Radius.class);
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

	@SuppressWarnings("unchecked")
	@Override
	public int getRadiusCount(int enterId) {
		// TODO Auto-generated method stub
		int row=0;
		try{
			String sql = "select * from wxpt"+enterId+".radius";
			List<Radius> list=new ArrayList<Radius>();
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Radius.class);
			
			list = query.list();
			row=list.size();
			return row;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return row;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Radius getRadiusByID(int enterId,int radiusId) {
		// TODO Auto-generated method stub
		Radius radius=null;
		try{
			String sql = "select * from wxpt"+enterId+".radius where radius_id="+radiusId;
			List<Radius> list=new ArrayList<Radius>();
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Radius.class);
			
			list = query.list();
			if(list.size()!=0){
				radius=list.get(0);
			}
			return radius;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return radius;
		}
	}

	@Override
	public void deleteById(int enterId,int radiusId) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM wxpt"+enterId+".radius  WHERE `radius_id` ="+radiusId;
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void updateRadius(int enterId, Radius radius) {
		// TODO Auto-generated method stub
		//String sql="UPDATE wxpt"+enterId+".canton SET canton_name='"+canton.getCantonName()+"',update_time='"+TimeUtil.getTime()+"',update_user="+canton.getUpdateUser()+",canton_no='"+canton.getCantonNo()+"' WHERE canton_id="+canton.getCantonId()+"";
		String sql="UPDATE wxpt"+enterId+".radius SET radius_content='"+radius.getRadiusContent()+"',`center_name`='"+radius.getCenterName()+"' where radius_id ="+radius.getRadiusId()+"";
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
