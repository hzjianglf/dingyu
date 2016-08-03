package com.handany.base.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.handany.base.model.PmSysInfo;

public interface SystemInfoService {
	/**
	 * 分页查询系统信息
	 * @param queryMap
	 * @return
	 */
	PageInfo<PmSysInfo> querySystemInfo(Map<String, Object> queryMap);
	
	/**
	 * 保存
	 * @param sysInfo
	 * @return
	 */
	int saveSystemInfo(PmSysInfo sysInfo);
	
	/**
	 * 查询单个
	 * @param version
	 * @param deviceType
	 * @return
	 */
	PmSysInfo getSystemInfo(String version, String deviceType);
}
