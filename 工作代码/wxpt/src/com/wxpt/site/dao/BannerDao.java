package com.wxpt.site.dao;

import java.util.List;

import com.wxpt.site.entity.EnterInfor;


public interface BannerDao {
	public List<EnterInfor> getBanner(String sql);
}
