package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.SiteStatistics;


public interface StatisticsDao {
	public void saveStatistice(SiteStatistics siteStatistics);
	public List<SiteStatistics> getAll();
	public int getTotalCount(String hql);
	public List<SiteStatistics> getAll(String hql, int start, int number);
	public int getUserId();
}
