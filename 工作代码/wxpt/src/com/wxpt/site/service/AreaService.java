package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Industry;

public interface AreaService extends ParentService{
	public void addArea(int enterId,Area area);
	public void updateArea(int enterId,Area area);
	public List<Area> getAreaList(int enterId);
	public List<Area> getAreaList(int enterId,int page,int rows);
	public int getAreaCount(int enterId);
	public Area getAreaByID(int enterId,int areaId);
	public void deleteById(int enterId,int areaId);
	public Area getAreaByNo(int enterId,String keyword);
	public Area getAreaByName(int enterId,String areaName);
}
