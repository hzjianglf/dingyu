package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.FansDao;
import com.wxpt.site.entity.Fans;
import com.wxpt.site.entity.FansImage;
import com.wxpt.site.entity.FansImageVisit;
import com.wxpt.site.entity.FansUser;
import com.wxpt.site.service.FansService;

public class FansServiceImpl extends ParentServieImpl implements FansService {

	@Autowired
	FansDao fansDao;

	@Transactional
	@Override
	public Fans getFansByName(int enterId,String fansName) {
		// TODO Auto-generated method stub
		return fansDao.getFansByName(enterId,fansName);
	}
	@Transactional
	@Override
	public void deleteFans(int enterId, Fans fans) {
		fansDao.deleteFans(enterId, fans);
		
	}
	@Transactional
	@Override
	public void addFans(int enterId, Fans fans) {
		fansDao.addFans(enterId,fans);
		
	}
	@Transactional
	public void updateFans(int enterId, Fans fans) {
		fansDao.updateFans(enterId,fans);
		
	}


	@Transactional
	public Fans getFansByName(String fansName) {
		// TODO Auto-generated method stub
		return fansDao.getFansByName(fansName);
	}

	@Transactional
	public FansUser getFansUserByName(int enrterId,String fromUsername) {
		// TODO Auto-generated method stub
		return fansDao.getFansUserByName(enrterId,fromUsername);
	}

	@Transactional
	public List<FansImage> getList(String hql,int page, int rows) {
		// TODO Auto-generated method stub
		return fansDao.getList(hql,page, rows);
	}

	@Transactional
	public List<FansUser> getFansUserList(String hql) {
		// TODO Auto-generated method stub
		return fansDao.getFansUserList(hql);
	}

	@Transactional
	public List<FansUser> getFansUserList2(String hql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return fansDao.getFansUserList2(hql, page, pageSize);
	}
	@Transactional
	public List<FansImageVisit> getFansImageVisitList(String hql) {
		// TODO Auto-generated method stub
		return fansDao.getFansImageVisitList(hql);
	}

	@Transactional
	public List<FansUser> getAllFans(String sql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return fansDao.getAllFans(sql, page, pageSize);
	}

	@Transactional
	public List<FansUser> getFansCount(String sql) {
		// TODO Auto-generated method stub
		return fansDao.getFansCount(sql);
	}
	


	@Transactional
	@Override
	public int getFansImageCount(String hql) {
		// TODO Auto-generated method stub
		return fansDao.getFansImageCount(hql);
	}
	@Override
	@Transactional
	public int getupdateFansImage(int enterId,String sql) {
		// TODO Auto-generated method stub
		return fansDao.getupdateFansImage(enterId,sql);
	}
	@Transactional
	@Override
	public void addFansUser(int enterId, FansUser fanuser) {
		// TODO Auto-generated method stub
		fansDao.addFansUser(enterId, fanuser);
	}
	@Transactional
	@Override
	public void addFansImage(int enterId, FansImage fansImage) {
		// TODO Auto-generated method stub
		fansDao.addFansImage(enterId, fansImage);
	}

	public void updateFansUser(int enterId, FansUser fanuser) {
		// TODO Auto-generated method stub
		fansDao.updateFansUser(enterId, fanuser);
	}
	@Transactional
	@Override
	public void addFans(String sql) {
		// TODO Auto-generated method stub
		fansDao.addFans(sql);
	}
	@Transactional
	@Override
	public void updateFans(int enterId, String sql) {
		// TODO Auto-generated method stub
		fansDao.updateFans(enterId, sql);
	}
	@Transactional
	@Override
	public void deleFans(String sql) {
		// TODO Auto-generated method stub
		fansDao.deleFans(sql);
	}
	@Override
	@Transactional
	public List<FansImage> getList2(String hql) {
		// TODO Auto-generated method stub
		return fansDao.getList2(hql);
	}
	@Override
	public void addFansImageVisit(int enterId, FansImageVisit fansImageVisit) {
		// TODO Auto-generated method stub
		fansDao.addFansImageVisit(enterId, fansImageVisit);
		
	}
	@Override
	public int getFansImageVisit(int enterId,String hql) {
		// TODO Auto-generated method stub
		return fansDao.getFansImageVisit(enterId, hql);
	}
	@Transactional
	@Override
	public Fans getFansByNameValue(String sql) {
		// TODO Auto-generated method stub
		return fansDao.getFansByNameValue(sql);
	}



}
