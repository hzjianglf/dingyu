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
import com.wxpt.site.dao.SurveyDao;

import com.wxpt.site.entity.Survey;

public class SurveyDaoImpl implements SurveyDao{
	@Autowired
	SessionFactory sessionFactory;
	protected Session session = null;
	JDBC_test jdbc=new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;
	@Override
	public List<Survey> getAllSurvey(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		 List<Survey> list = new ArrayList<Survey>();
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Survey.class);
		query.setFirstResult((page-1)*pageSize);
		query.setMaxResults(pageSize);
		list=query.list();
		return list;
	}
	@Override
	public List<Survey> getAllSurveyList(String sql) {
		// TODO Auto-generated method stub
		 List<Survey> list = new ArrayList<Survey>();
			Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
					.addEntity(Survey.class);
			
			list=query.list();
			return list;
	}
	@Override
	public void deletedeleteSurvey(String sql, int enterId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				try {
					this.sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	@Override
	public void saveSurVey(Survey sur,int enterId) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO wxpt"+enterId+".survey( `survey_user`, `survey_time`, `survey_code`, `survey_user_name`, `survey_user_phone`, `survey_user_qq`, `survey_user_email`, `survey_uer_sex`, `survey_user_age`, `survey_user_edu`, `survey_user_work`) " +
				"VALUES ('"+sur.getSurveyUser()+"','"+sur.getSurveyTime()+"',"+sur.getSurveyCode()+",'"+sur.getSurveyUserName()+"','"+sur.getSurveyUserPhone()+"','"+sur.getSurveyUserQq()+"','"+sur.getSurveyUserEmail()+"','"+sur.getSurveyUerSex()+"',"+sur.getSurveyUserAge()+",'"+sur.getSurveyUserEdu()+"','"+sur.getSurveyUserWork()+"')";
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
	public Survey findByFromUsername(String fromUsername, int enterId) {
		String sql="SELECT * FROM wxpt"+enterId+".survey WHERE `survey_user`=  '"+fromUsername+"'";
		List<Survey> list=new ArrayList<Survey>();
		try {
			list=this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Survey.class).list();
			return list.get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	

}
