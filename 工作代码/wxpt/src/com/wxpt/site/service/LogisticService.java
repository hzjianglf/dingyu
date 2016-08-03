package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Logistics;


public interface LogisticService {
	
	public List<Logistics> getAll(String sql,int page,int rows);
	
	public int getcount(String sql);
	//添加
	public void save(String sql);
	
	public void update(String sql,int enterId);
	
	public Logistics getByLogId(String sql);
}
