package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.LogisticDao;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.service.LogisticService;


public class LogisticServiceImpl implements LogisticService {
	LogisticDao logisticDao;
	@Transactional
	@Override
	public List<Logistics> getAll(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return logisticDao.getAll(sql, page, rows);
	}
	@Transactional
	@Override
	public int getcount(String sql) {
		// TODO Auto-generated method stub
		return logisticDao.getcount(sql);
	}

	public LogisticDao getLogisticDao() {
		return logisticDao;
	}

	public void setLogisticDao(LogisticDao logisticDao) {
		this.logisticDao = logisticDao;
	}
	@Transactional
	@Override
	public void save(String sql) {
		// TODO Auto-generated method stub
		logisticDao.save(sql);
	}
	@Transactional
	@Override
	public void update(String sql,int enterId) {
		// TODO Auto-generated method stub
		logisticDao.update(sql,enterId);
	}
	@Transactional
	@Override
	public Logistics getByLogId(String sql) {
		// TODO Auto-generated method stub
		return logisticDao.getByLogId(sql);
	}

}
