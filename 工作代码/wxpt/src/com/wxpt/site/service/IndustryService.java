package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Industry;

public interface IndustryService {
	public void addIndustry(int enterId,Industry industry);
	public void updateIndustry(int enterId,Industry industry);
	public List<Industry> getIndustryList(int enterId);
	public List<Industry> getIndustryList(int enterId,int page,int rows);
	public int getIndustryCount(int enterId);
	public Industry getIndustryByID(int enterId,int industryId);
	public void deleteById(int enterId,int industryId);
	public Industry getIndustryByNo(int enterId,String keyword);
	//public Industry getIndustryByName(int enterId,String industryName);
}
