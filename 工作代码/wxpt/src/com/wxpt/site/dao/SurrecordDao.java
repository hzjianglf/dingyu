package com.wxpt.site.dao;


import java.util.List;

import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surrecord;


public interface SurrecordDao {
	public List<Surrecord> getAllSurrecord(String sql);
	public void deleteSurrecord(String sql,int enterId);
	public Suroption findByOptionId(int optionId, int enterId);

	public void save(String sql, int enterId);

}
