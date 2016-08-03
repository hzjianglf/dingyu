package com.handany.base.cache;

/**
 * 缓存管理器接口
 * @author lhb
 *
 */
public interface ICacheManager {

	public ICache getCache(String cacheName);
	
}
