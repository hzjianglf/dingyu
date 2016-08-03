package com.wxpt.site.service.impl;

import java.util.List;

import javax.persistence.TableGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.dao.AreaDao;
import com.wxpt.site.dao.IndustryDao;
import com.wxpt.site.service.AreaService;
import com.wxpt.site.service.IndustryService;

public class AreaServiceImpl extends ParentServieImpl implements
		AreaService {
	@Autowired
	AreaDao areaDao;

	@Transactional
	@Override
	public List<Area> getAreaList(int enterId) {
		// TODO Auto-generated method stub
		return areaDao.getAreaList(enterId);
	}


	@Override
	@Transactional
	public List<Area> getAreaList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		return areaDao.getAreaList(enterId,page, rows);
	}

	@Transactional
	@Override
	public int getAreaCount(int enterId) {
		// TODO Auto-generated method stub
		return areaDao.getAreaCount(enterId);
	}

	
	@Override
	public Area getAreaByID(int enterId,int AreaId) {
		// TODO Auto-generated method stub
		return areaDao.getAreaByID(enterId,AreaId);
	}

	@Override
	@Transactional
	public void deleteById(int enterId,int AreaId) {
		// TODO Auto-generated method stub
		areaDao.deleteById(enterId,AreaId);
	}

	@Override
	@Transactional
	public Area getAreaByNo(int enterId,String keyword) {
		// TODO Auto-generated method stub
		return areaDao.getAreaByNo(enterId,keyword);
	}

	@Override
	@Transactional
	public Area getAreaByName(int enterId,String areaName) {
		// TODO Auto-generated method stub
		return areaDao.getAreaByName(enterId,areaName);
	}

	@Override
	@Transactional
	public void addArea(int enterId, Area area) {
		// TODO Auto-generated method stub
		areaDao.addArea(enterId, area);
	}

	@Override
	@Transactional
	public void updateArea(int enterId, Area area) {
		// TODO Auto-generated method stub
		areaDao.updateArea(enterId, area);
	}

}
