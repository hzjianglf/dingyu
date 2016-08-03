package com.handany.base.common;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handany.base.container.CacheContainer;
import com.handany.base.dao.SystemInfoMapper;

/**
 * 
 * @author lhb
 *
 */
public class PmSysInfoManager {
	private static Logger logger = LoggerFactory.getLogger(PmSysInfoManager.class);
	public static final String CACHE_SYS_INFO = "CACHE_SYS_INFO:";
	
	/**
	 * 从数据库中更新系统信息
	 */
	public static Map<String, String> refresh(String deviceType){
		
		Map<String, String> map= query(deviceType);

		if(map != null){
			CacheContainer.put(CACHE_SYS_INFO+deviceType, map);
		}
		
		return map;
	}
	
	private static Map<String,String> query(String deviceType){
		
		SystemInfoMapper sim = ComponentFactory.getBean("systemInfoMapper", SystemInfoMapper.class);
		Map<String, String> map= null;
		try {
			map= sim.getCurrentSystemInfo(deviceType);
		} catch (Exception e) {
			logger.error("",e);
		}
		
		return map;
	}
	
	
	public static void main(String[] args) {
	}
	
	/**
	 * 获得当前系统信息
	 * @return
	 */
	public static Map<String,String> getSysInfo(String deviceType){
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = CacheContainer.get(CACHE_SYS_INFO + deviceType, Map.class);
		if(map == null){
			map = refresh(deviceType);
		}
		return map;
	}
	
}
