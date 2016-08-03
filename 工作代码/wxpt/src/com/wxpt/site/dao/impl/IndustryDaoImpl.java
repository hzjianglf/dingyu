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
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.dao.IndustryDao;

public class IndustryDaoImpl implements IndustryDao{

	@Autowired
	
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public List<Industry> getIndustryList(int enterId) {
		// TODO Auto-generated method stub
		
		
		String sql="SELECT * FROM wxpt"+enterId+".industry ";
		List<Industry> list=new ArrayList<Industry>();
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Industry.class);
		
		list = query.list();
		return list;
		
	}
	@Override
	public List<Industry> getIndustryList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
	
		String sql="SELECT * FROM wxpt"+enterId+".industry ";
		List<Industry> list=new ArrayList<Industry>();
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Industry.class);
		
		list = query.list();
		return list;
	}
	@Override
	public int getIndustryCount(int enterId) {
		// TODO Auto-generated method stub
		int row=0;

		String sql="SELECT * FROM wxpt"+enterId+".industry ";
		List<Industry> list=new ArrayList<Industry>();
		/*String sql="SELECT * FROM webchat.enter_infor";*/
		System.out.println(sql);
		Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Industry.class);
		
		list = query.list();
		row=list.size();
		return row;
	}
	@Override
	public Industry getIndustryByID(int enterId,int industryId) {
		try {
			// TODO Auto-generated method stub
			String sql = "select * from wxpt"+enterId+".industry where industry_id = " + industryId;
			List<Industry> list=new ArrayList<Industry>();
			Industry Industry=null;
			/*String sql="SELECT * FROM webchat.enter_infor";*/
			System.out.println(sql);
			Query query=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Industry.class);		
			list = query.list();
			Industry=list.get(0);
			return Industry;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	@Override
	public void deleteById(int enterId,int industryId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM wxpt"+enterId+".`industry` WHERE `industry_id` ="+industryId;
		this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
	@Override
	public Industry getIndustryByNo(int enterId,String keyword) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			String hql = "SELECT * FROM wxpt"+enterId+".industry WHERE  industry_no = '" + keyword+"'";
			Industry industry = (Industry) this.sessionFactory
					.getCurrentSession().createSQLQuery(hql).addEntity(Industry.class).list().get(0);
			return industry;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	/*@Override
	public Industry getIndustryByName(int enterId,String industryName) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			String hql = "from Industry where industryName = '" + industryName+"'";
			Industry industry = (Industry) this.sessionFactory
					.getCurrentSession().createQuery(hql).list().get(0);
			return industry;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}*/
	@Override
	public void addIndustry(int enterId, Industry industry) {
		// TODO Auto-generated method stub
     String sql="INSERT INTO wxpt"+enterId+".industry(`industry_id`, `industry_name`, `add_time`, `update_time`, `user_id`, `update_user`, `industry_no`) VALUES ("+industry.getIndustryId()
    		 +",'"+industry.getIndustryName()+"','"+TimeUtil.getTime()+"','"+TimeUtil.getTime()+"',"+industry.getUpdateUser()+","+industry.getUpdateUser()+",'"+industry.getIndustryNo()+"')";
		
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
	public void updateIndustry(int enterId, Industry industry) {
		// TODO Auto-generated method stub
		String sql="UPDATE industry SET industry_name='"+industry.getIndustryName()+"',update_time='"+TimeUtil.getTime()+"',user_id="+industry.getUpdateUser()+",update_user="+industry.getUpdateUser()+",industry_no='"+industry.getIndustryNo()+"' WHERE industry_id="+industry.getIndustryId()+"";
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
