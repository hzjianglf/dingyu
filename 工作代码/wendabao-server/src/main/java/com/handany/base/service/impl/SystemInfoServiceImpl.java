package com.handany.base.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.PageUtil;
import com.handany.base.dao.SystemInfoMapper;
import com.handany.base.model.PmSysInfo;
import com.handany.base.service.SystemInfoService;

@Service
public class SystemInfoServiceImpl implements SystemInfoService {

	@Autowired
	private SystemInfoMapper systemInfoMapper;
	
	@Override
	public PageInfo<PmSysInfo> querySystemInfo(Map<String, Object> queryMap) {
		PageUtil.startPage();
		List<PmSysInfo> pmSysInfoList = systemInfoMapper.getAllSystemInfo(queryMap);
		PageInfo<PmSysInfo> page = new PageInfo<PmSysInfo>(pmSysInfoList);
		
		return page;
	}

	@Override
	public int saveSystemInfo(PmSysInfo sysInfo) {
		return systemInfoMapper.saveSystemInfo(sysInfo);
	}

	@Override
	public PmSysInfo getSystemInfo(String version, String deviceType) {
		return systemInfoMapper.getSystemInfo(version, deviceType);
	}
}
