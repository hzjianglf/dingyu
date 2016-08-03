package com.wxpt.site.service;


import com.wxpt.site.entity.Suroption;
import com.wxpt.site.entity.Surrecord;


import java.util.List;

import com.wxpt.site.entity.Surrecord;


public interface SurrecordService {
	public List<Surrecord> getAllSurrecord(String sql);
	public void deleteSurrecord(String sql,int enterId);
	public Suroption findByOptionId(int optionId,int enterId);

	public void save(String sql, int enterId);

}
