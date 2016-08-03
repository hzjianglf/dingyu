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
import com.wxpt.common.TimeUtil;
import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.dao.AreaDao;

public class AreaDaoImpl implements AreaDao {
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public List<Area> getAreaList(int enterId) {
		// TODO Auto-generated method stub
		String sql="select * from wxpt"+enterId+".area ";
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Area.class)
				.list();
	}

	@Override
	public List<Area> getAreaList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from wxpt"+enterId+".area ";
			List<Area> list=new ArrayList<Area>();
			System.out.println(sql);
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Area.class);
			query.setFirstResult(page);
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
	public int getAreaCount(int enterId) {
		// TODO Auto-generated method stub
		int row=0;
		try {
			String sql="select * from wxpt"+enterId+".area ";
			List<Area> list=new ArrayList<Area>();
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Area.class);
			
			row = query.list().size();
			return row;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return row;
		}
	}

	@Override
	public Area getAreaByID(int enterId,int areaId) {
		Area area=null;
		try {
			// TODO Auto-generated method stub
			String sql = "select * from wxpt"+enterId+".area where area_id = " + areaId;
			List<Area> list=new ArrayList<Area>();
			
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Area.class);
			
			list= query.list();
			if(list.size()>0){
				area=list.get(0);
					}
			return area;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		return area;
		
	}

	@Override
	public void deleteById(int enterId,int areaId) {
		// TODO Auto-generated method stub
		try {
			String sql = "DELETE FROM wxpt"+enterId+".area WHERE `area_id` ="+areaId;
			this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Area getAreaByNo(int enterId,String keyword) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
		//	String hql = "from Area where areaNo = '" + keyword+"'";
			String sql="SELECT * FROM wxpt"+enterId+".area WHERE `area_no`='"+keyword+"'";
			Area area = (Area) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(Area.class).list().get(0);
			return area;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public Area getAreaByName(int enterId,String areaName) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			String hql = "from Area where areaName = '" + areaName+"'";
			Area area = (Area) this.sessionFactory.getCurrentSession()
					.createQuery(hql).list().get(0);
			return area;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public void addArea(int enterId, Area area) {
		// TODO Auto-generated method stub
		
			
		 String sql="INSERT INTO wxpt"+enterId+".area(`area_id`, `area_name`, " +
		 		"`add_time`, `update_time`, `user_id`, `update_user`, " +
		 		"`location_x`, `location_y`, `area_no`) VALUES ("+area.getAreaId()+",'"+area.getAreaName()+"'," +
		 		"'"+TimeUtil.getTime()+"','"+TimeUtil.getTime()+"',"+area.getUpdateUser()+","+area.getUpdateUser()+"," +
		 		"'"+area.getLocationX()+"','"+area.getLocationY()+"','"+area.getAreaNo()+"')";
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
	public void updateArea(int enterId, Area area) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String sql="UPDATE `area` SET `area_name`='"+area.getAreaName()+"',`update_time`='"+TimeUtil.getTime()+"',`user_id`="+area.getUpdateUser()+",`update_user`="+area.getUpdateUser()+",`location_x`='"+area.getLocationX()+"',`location_y`='"+area.getLocationY()+"',`area_no`='"+area.getAreaNo()+"' WHERE area_id="+area.getAreaId()+"";
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	

}
