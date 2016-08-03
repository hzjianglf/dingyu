package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.EnterDao;
import com.wxpt.site.dao.impl.EnterDaoImpl;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Shiyong;
import com.wxpt.site.service.EnterService;

public class enterServiceImpl implements EnterService{
	EnterDao enterDao;
	
	public EnterDao getEnterDao() {
		return enterDao;
	}
	public void setEnterDao(EnterDao enterDao) {
		this.enterDao = enterDao;
	}
	@Transactional
	@Override
	public List<EnterInfor> getAll(String sql,int page, int rows) {
		// TODO Auto-generated method stub
		return enterDao.getAll(sql,page, rows);
	}
	@Transactional
	@Override
	public int enterCount(String sql) {
		// TODO Auto-generated method stub
		return enterDao.enterCount(sql);
	}
	@Transactional
	@Override
	public void deleEnter(String sql) {
		// TODO Auto-generated method stub
		enterDao.deleEnter(sql);
	}
	@Transactional
	@Override
	public void updateEnter(String sql) {
		// TODO Auto-generated method stub
		enterDao.updateEnter(sql);
	}
	@Transactional
	@Override
	public EnterInfor getById(int enterId) {
		// TODO Auto-generated method stub
		return enterDao.getById(enterId);
	}
	@Transactional
	@Override
	public EnterInfor getById2(int enterId) {
		// TODO Auto-generated method stub
		return enterDao.getById2(enterId);
	}
	@Transactional
	public List<EnterInfor> getAllEnter() {
		// TODO Auto-generated method stub
		return enterDao.getAllEnter();
	}
	@Override
	@Transactional
	public List<EnterInfor> getByEnterInfor(String sql) {
		// TODO Auto-generated method stub
		return enterDao.getByEnterInfor(sql);
	}
	@Transactional
	@Override
	public void updateVshopBanenr(String sql) {
		// TODO Auto-generated method stub
		enterDao.updateVshopBanenr(sql);
	}
	@Transactional
	@Override
	public void addQyPeiZhi(String sql) {
		// TODO Auto-generated method stub
		enterDao.addQyPeiZhi(sql);
	}
	@Transactional
	@Override
	public List<EnterInfor> getCount(String sql) {
		// TODO Auto-generated method stub
		return enterDao.getCount(sql);
	}
	@Override
	public List<Shiyong> getall(String sql) {
		// TODO Auto-generated method stub
		return enterDao.getall(sql);
	}
	@Override
	public void updateAll(String sql, int enterId) {
		// TODO Auto-generated method stub
		enterDao.updateAll(sql, enterId);
	}
	
		

}
