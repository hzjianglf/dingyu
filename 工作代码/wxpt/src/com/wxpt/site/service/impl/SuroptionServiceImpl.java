package com.wxpt.site.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.SuroptionDao;
import com.wxpt.site.entity.Suroption;

import com.wxpt.site.service.SuroptionService;

public class SuroptionServiceImpl implements SuroptionService{
	@Override
	@Transactional
	public List<Suroption> getAllSuroption(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return suroptionDao.getAllSuroption(sql, page, pageSize);
	}
	public SuroptionDao getSuroptionDao() {
		return suroptionDao;
	}
	public void setSuroptionDao(SuroptionDao suroptionDao) {
		this.suroptionDao = suroptionDao;
	}

@Autowired
SuroptionDao suroptionDao;
	@Transactional
	public List<Suroption> findByquestionId(Integer surquestionId,Integer enterId) {
		// TODO Auto-generated method stub
		return suroptionDao.findByquestionId(surquestionId,enterId);
	}
	@Override
	@Transactional
	public void addSuroption(String sql) {
		// TODO Auto-generated method stub
		suroptionDao.addSuroption(sql);
	}
	@Override
	@Transactional
	public void updateSurSuroption(String sql, int enterId) {
		// TODO Auto-generated method stub
		suroptionDao.updateSurSuroption(sql, enterId);
	}
	@Override
	@Transactional
	public void deleteSurquestion(String sql, int enterId) {
		// TODO Auto-generated method stub
		suroptionDao.deleteSurquestion(sql, enterId);
	}
	@Override
	@Transactional
	public Suroption getSuroption(String sql) {
		// TODO Auto-generated method stub
  return suroptionDao.getSuroption(sql);
	}
	@Override
	@Transactional
	public List<Suroption> getAllSuroptionList(String sql) {
		// TODO Auto-generated method stub
		return suroptionDao.getAllSuroptionList(sql);
	}




}
