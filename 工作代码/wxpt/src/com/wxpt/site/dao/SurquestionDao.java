package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Surquestion;

public interface SurquestionDao {
	//张莹添加
		public List< Surquestion> getAllSurquestion(String sql, int page, int pageSize) ;
		public List<Surquestion> getAllSurquestion(String sql);
	List<Surquestion> findAll(int enterId);
	public void addSurquestion(String sql);
	public void updateSurquestion(String sql,int enterId);
	public void deleteSurquestion(String sql, int enterId);

}
