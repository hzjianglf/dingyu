package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.Canton;
import com.wxpt.site.entity.Radius;

public interface RadiusDao {
	public void updateRadius(int enterId, Radius radius);
	public List<Radius> getRadiusList(int enterId);
	public List<Radius> getRadiusList(int enterId,int page,int rows);
	public int getRadiusCount(int enterId);
	public Radius getRadiusByID(int enterId,int radiusId);
	public void deleteById(int enterId,int radiusId);
}
