package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Suroption;

public interface SuroptionDao {
	public List<Suroption> getAllSuroption(String sql,
			int page, int pageSize) ;
	public List< Suroption> getAllSuroptionList(String sql) ;
public	List<Suroption> findByquestionId(Integer surquestionId,Integer enterId);
public void addSuroption(String sql);
public void updateSurSuroption(String sql,int enterId);
public void deleteSurquestion(String sql,int enterId);
public Suroption getSuroption(String sql);

}
