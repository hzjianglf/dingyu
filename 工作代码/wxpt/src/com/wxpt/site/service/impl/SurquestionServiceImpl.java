package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.SurquestionDao;
import com.wxpt.site.entity.Surquestion;

import com.wxpt.site.service.SurquestionService;

public class SurquestionServiceImpl implements SurquestionService {

@Autowired
SurquestionDao surquestionDao;
	@Transactional
	public List<Surquestion> findAll(int enterId) {
		// TODO Auto-generated method stub
		return surquestionDao.findAll(enterId) ;
	}


	@Override
	@Transactional
	public List<Surquestion> getAllSurquestion(String sql, int page,
			int pageSize) {
		// TODO Auto-generated method stub
		return surquestionDao.getAllSurquestion(sql, page, pageSize);
	}


	@Override
	@Transactional
	public List<Surquestion> getAllSurquestion(String sql) {
		// TODO Auto-generated method stub
		return surquestionDao.getAllSurquestion(sql);
	}


	@Override
	@Transactional
	public void addSurquestion(String sql) {
		// TODO Auto-generated method stub
		surquestionDao.addSurquestion(sql);
		
	}


	@Override
	@Transactional
	public void updateSurquestion(String sql, int enterId) {
		// TODO Auto-generated method stub
		surquestionDao.updateSurquestion(sql, enterId);
		
	}


	@Override
	@Transactional
	public void deleteSurquestion(String sql, int enterId) {
		// TODO Auto-generated method stub
		surquestionDao.deleteSurquestion(sql, enterId);
	}

	
	
}
