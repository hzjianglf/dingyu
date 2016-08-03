package com.wxpt.site.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wxpt.site.dao.BannerDao;
import com.wxpt.site.entity.EnterInfor;


public class BannerDaoImpl implements BannerDao{
	@Autowired
	SessionFactory sessionFactory;
	public List<EnterInfor> getBanner(String sql){
		List<EnterInfor> enterList = new ArrayList<EnterInfor>();
		try {
			enterList = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(EnterInfor.class).list();
		} catch (HibernateException e) {
			enterList = null;
		}
		return enterList;
	}
}
