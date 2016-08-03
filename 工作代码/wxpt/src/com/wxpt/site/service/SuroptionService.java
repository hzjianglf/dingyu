package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Suroption;

public interface SuroptionService{
	//张莹添加
public List< Suroption> getAllSuroption(String sql, int page, int pageSize) ;
public List< Suroption> getAllSuroptionList(String sql) ;
public	List<Suroption> findByquestionId(Integer surquestionId,Integer enterid);
public void addSuroption(String sql);
public void updateSurSuroption(String sql,int enterId);
public void deleteSurquestion(String sql,int enterId);
public Suroption getSuroption(String sql);

}
