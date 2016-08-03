package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.ParentDao;
import com.wxpt.site.service.ParentService;



public class ParentServieImpl implements ParentService {

	@Autowired
	ParentDao parentDao;

	@Transactional
	
	public void add(int enterId,Object o) {
		// TODO Auto-generated method stub
		parentDao.add(enterId,o);
	}

	@Transactional
	
	public void update(int enterId,Object o) {
		// TODO Auto-generated method stub
		parentDao.update(enterId,o);
	}

	@Transactional
	
	public List<Object> objectList(Object o) {
		// TODO Auto-generated method stub
		return parentDao.objectList(o);
	}
	@Transactional
	
	public void delete(Object o) {
		// TODO Auto-generated method stub
		parentDao.delete(o);
	}

	@Transactional
	public int getCount(String hql) {
		// TODO Auto-generated method stub
		return parentDao.getCount(hql);
	}
	@Transactional
	@Override
	public void clearReply(String sql,int enterId) {
		// TODO Auto-generated method stub
		parentDao.clearReply(sql,enterId);
	}

	@Override
	public int getCountBySql(String sql) {
		// TODO Auto-generated method stub
		return parentDao.getCountBySql(sql);
	}

}
