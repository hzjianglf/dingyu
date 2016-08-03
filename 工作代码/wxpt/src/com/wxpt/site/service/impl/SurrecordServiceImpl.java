package com.wxpt.site.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.SurrecordDao;
import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surrecord;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.SurrecordDao;
import com.wxpt.site.entity.Surrecord;

import com.wxpt.site.service.SurrecordService;

public class SurrecordServiceImpl implements SurrecordService {

	@Autowired
	SurrecordDao surrecordDao;
	@Transactional
	
	public Suroption findByOptionId(int optionId,int enterId) {
		// TODO Auto-generated method stub
		return surrecordDao.findByOptionId(optionId,enterId);
	}
	@Override
	
	public void save(String sql, int enterId) {
		// TODO Auto-generated method stub
		surrecordDao.save(sql, enterId);
	}

	@Override
	@Transactional
	public List<Surrecord> getAllSurrecord(String sql) {
		// TODO Auto-generated method stub
		return surrecordDao.getAllSurrecord(sql);
	}
	public SurrecordDao getSurrecordDao() {
		return surrecordDao;
	}
	public void setSurrecordDao(SurrecordDao surrecordDao) {
		this.surrecordDao = surrecordDao;
	}
	@Override
	@Transactional
	public void deleteSurrecord(String sql, int enterId) {
		// TODO Auto-generated method stub
		surrecordDao.deleteSurrecord(sql, enterId);
	}


}
