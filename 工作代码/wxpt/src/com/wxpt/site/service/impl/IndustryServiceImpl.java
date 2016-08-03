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

public class IndustryServiceImpl implements
		IndustryService {
	@Autowired
	IndustryDao industryDao;

	@Transactional
	@Override
	public List<Industry> getIndustryList(int enterId) {
		// TODO Auto-generated method stub
		return industryDao.getIndustryList(enterId);
	}

	@Transactional
	@Override
	public List<Industry> getIndustryList(int enterId,int page, int rows) {
		// TODO Auto-generated method stub
		return industryDao.getIndustryList(enterId,page, rows);
	}

	@Transactional
	@Override
	public int getIndustryCount(int enterId) {
		// TODO Auto-generated method stub
		return industryDao.getIndustryCount(enterId);
	}

	@Transactional
	@Override
	public Industry getIndustryByID(int enterId,int industryId) {
		// TODO Auto-generated method stub
		return industryDao.getIndustryByID(enterId,industryId);
	}

	@Override
	public void deleteById(int enterId,int industryId) {
		// TODO Auto-generated method stub
		industryDao.deleteById(enterId,industryId);
	}

	@Override
	public Industry getIndustryByNo(int enterId,String keyword) {
		// TODO Auto-generated method stub
		return industryDao.getIndustryByNo(enterId,keyword);
	}

	/*@Override
	public Industry getIndustryByName(int enterId,String industryName) {
		// TODO Auto-generated method stub
		return industryDao.getIndustryByName(enterId,industryName);
	}*/

	@Override
	@Transactional
	public void addIndustry(int enterId, Industry industry) {
		// TODO Auto-generated method stub
		industryDao.addIndustry(enterId, industry);
	}

	@Override
	@Transactional
	public void updateIndustry(int enterId, Industry industry) {
		// TODO Auto-generated method stub
		industryDao.updateIndustry(enterId, industry);
	}
}
