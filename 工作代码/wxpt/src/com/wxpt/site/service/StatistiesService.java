package com.wxpt.site.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.entity.SiteStatistics;


public interface StatistiesService {
	public void saveStatistice(SiteStatistics siteStatistics);
	public List<SiteStatistics> getAll();
	public int getTotalCount(String hql);
	public List<SiteStatistics> getAll(String hql, int start, int number);
	public int getUserId();
}
