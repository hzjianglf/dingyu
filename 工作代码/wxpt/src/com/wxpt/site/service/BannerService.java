package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.EnterInfor;

public interface BannerService {
	public List<EnterInfor> getBanner(String sql);
}
