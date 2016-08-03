package com.handany.base.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.handany.base.model.PmSysInfo;

/**
 * 
 * @author lhb
 *
 */
public interface SystemInfoMapper {

	Map<String,String> getCurrentSystemInfo(String deviceType) throws Exception;
	
	List<PmSysInfo> getAllSystemInfo(Map<String, Object> queryMap);
	
	int saveSystemInfo(PmSysInfo sysInfo);
	
	PmSysInfo getSystemInfo(@Param("version") String version, 
			@Param("deviceType") String deviceType);
}
