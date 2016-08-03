package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.entity.Canton;
import com.wxpt.site.dao.CantonDao;
import com.wxpt.site.service.CantonService;

public class CantonServiceImpl extends ParentServieImpl implements CantonService{

	@Autowired
	CantonDao cantonDao;
	@Override
	public List<Canton> getCantonList(int enterId) {
		// TODO Auto-generated method stub
		return cantonDao.getCantonList(enterId);
	}

	@Override
	@Transactional
	public List<Canton> getCantonList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		return cantonDao.getCantonList(enterId,page, rows);
	}

	@Override
	public int getCantonCount(int enterId) {
		// TODO Auto-generated method stub
		return cantonDao.getCantonCount(enterId);
	}

	@Override
	public Canton getCantonByID(int enterId,int cantonId) {
		// TODO Auto-generated method stub
		return cantonDao.getCantonByID(enterId,cantonId);
	}

	@Override
	public void deleteById(int enterId,int cantonId) {
		// TODO Auto-generated method stub
		cantonDao.deleteById(enterId,cantonId);
	}

	@Override
	public Canton getCantonByName(int enterId,String cantonName) {
		// TODO Auto-generated method stub
		return cantonDao.getCantonByName(enterId,cantonName);
	}

	@Override
	public void addCanton(int enterId, Canton canton) {
		// TODO Auto-generated method stub
		cantonDao.addCanton(enterId, canton);
	}

	@Override
	public void updateCanton(int enterId, Canton canton) {
		// TODO Auto-generated method stub
		cantonDao.updateCanton(enterId, canton);
	}

	public CantonDao getCantonDao() {
		return cantonDao;
	}

	public void setCantonDao(CantonDao cantonDao) {
		this.cantonDao = cantonDao;
	}
	
	
	

}
