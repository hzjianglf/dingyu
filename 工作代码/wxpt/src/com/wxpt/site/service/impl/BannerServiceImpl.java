package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.BannerDao;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.service.BannerService;

public class BannerServiceImpl implements BannerService{
	@Autowired
	BannerDao bannerDao;
	//@Transactional
	public List<EnterInfor> getBanner(String sql){
		return bannerDao.getBanner(sql);
	}
}
