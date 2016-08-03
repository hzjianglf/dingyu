package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.StatisticsDao;
import com.wxpt.site.entity.SiteStatistics;
import com.wxpt.site.service.StatistiesService;

public class StatistiesServiceImpl  implements StatistiesService{
	@Autowired
	StatisticsDao statisticsDao;
	@Transactional
	public void saveStatistice(SiteStatistics siteStatistics) {
		// TODO Auto-generated method stub
		statisticsDao.saveStatistice(siteStatistics);
	}
	@Transactional 
	public List<SiteStatistics> getAll() {
		// TODO Auto-generated method stub
		List<SiteStatistics> stList=statisticsDao.getAll();
		
		return stList;
	}
	@Transactional 
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return statisticsDao.getTotalCount(hql);
	}
	@Transactional 
	public List<SiteStatistics> getAll(String hql, int start, int number) {
		// TODO Auto-generated method stub
		return statisticsDao.getAll(hql,start,number);
	}
	@Transactional 
	public int getUserId(){
		return statisticsDao.getUserId();
	}
}
