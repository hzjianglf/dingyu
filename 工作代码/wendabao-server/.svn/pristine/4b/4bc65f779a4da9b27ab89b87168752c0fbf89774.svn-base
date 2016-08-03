package com.handany.base.container;

import com.handany.base.cache.ICache;
import com.handany.base.cache.ICacheManager;
import com.handany.base.common.ComponentFactory;

/**
 * 缓存容器
 * @author lhb
 *
 */
public class CacheContainer {

	
	public static final String CACHE_CONTAINER = "LRUCache";
	
	private static ICacheManager cacheManager = null;
	
	static{
		
		cacheManager = (ICacheManager)ComponentFactory.getBean("cacheManager");
		
	}
	
	
	
	
	/**
	 * 将对象放入缓存容器
	 * @param name
	 * @param object
	 */
	public static void put(String name,Object object){
		
		cacheManager.getCache(CACHE_CONTAINER).put(name, object);
	}
	
	/**
	 * 将对象放入缓存容器，并设置超时时间
	 * @param name
	 * @param object
	 * @param expireSeconds
	 */
	public static void put(String name,Object object,int expireSeconds){
		
		cacheManager.getCache(CACHE_CONTAINER).put(name, object,expireSeconds);
	}
	
	/**
	 * 从缓存容器中获得对象
	 * @param name
	 * @return
	 */
	public static <T> T get(String name,Class<T> t){
		
		Object obj = cacheManager.getCache(CACHE_CONTAINER).get(name);
		
		if(obj == null){
			return null;
		}else{
			return (T)obj;
		}
	}
	
	/**
	 * 清除一个缓存元素
	 * @param name
	 */
	public static void remove(String name){
		cacheManager.getCache(CACHE_CONTAINER).remove(name);
	}
	
	/**
	 * 获得ICache对象
	 * @return
	 */
	public static ICache getCache(){
		return cacheManager.getCache(CACHE_CONTAINER);
	}
	

}
