package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Canton;

public interface CantonDao {
	public void addCanton(int enterId,Canton canton);
	public void updateCanton(int enterId,Canton canton);
	public List<Canton> getCantonList(int enterId);
	public List<Canton> getCantonList(int enterId,int page,int rows);
	public int getCantonCount(int enterId);
	public Canton getCantonByID(int enterId,int cantonId);
	public void deleteById(int enterId,int cantonId);
	public Canton getCantonByName(int enterId,String cantonName);
}
