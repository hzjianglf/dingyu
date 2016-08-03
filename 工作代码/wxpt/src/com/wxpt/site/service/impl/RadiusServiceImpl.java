package com.wxpt.site.service.impl;

import java.util.List;

import javax.persistence.TableGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.entity.Radius;
import com.wxpt.site.dao.AreaDao;
import com.wxpt.site.dao.IndustryDao;
import com.wxpt.site.dao.RadiusDao;
import com.wxpt.site.service.AreaService;
import com.wxpt.site.service.IndustryService;
import com.wxpt.site.service.RadiusService;

public class RadiusServiceImpl extends ParentServieImpl implements
		RadiusService {
	@Autowired
	RadiusDao radiusDao;

	@Transactional
	@Override
	public List<Radius> getRadiusList(int enterId) {
		// TODO Auto-generated method stub
		return radiusDao.getRadiusList(enterId);
	}

	@Transactional
	@Override
	public List<Radius> getRadiusList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		return radiusDao.getRadiusList(enterId,page, rows);
	}

	@Transactional
	@Override
	public int getRadiusCount(int enterId) {
		// TODO Auto-generated method stub
		return radiusDao.getRadiusCount(enterId);
	}

	@Transactional
	@Override
	public Radius getRadiusByID(int enterId,int radiusId) {
		// TODO Auto-generated method stub
		return radiusDao.getRadiusByID(enterId,radiusId);
	}

	@Override
	@Transactional
	public void deleteById(int enterId,int radiusId) {
		// TODO Auto-generated method stub
		radiusDao.deleteById(enterId,radiusId);
	}

	@Override
	@Transactional
	public void updateRadius(int enterId, Radius radius) {
		// TODO Auto-generated method stub
		radiusDao.updateRadius(enterId, radius);
	}

}
