package com.handany.base.cache.redis;

import java.util.Map;

import com.handany.base.cache.ICache;
import com.handany.base.cache.ICacheManager;

import redis.clients.jedis.Jedis;

public class CacheManagerImpl implements ICacheManager{

	private Map<String,Integer> dbMap;
	
	private RedisClientFactory clientFactory;
	
	public RedisClientFactory getClientFactory() {
		return clientFactory;
	}

	public void setClientFactory(RedisClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public Map<String, Integer> getDbMap() {
		return dbMap;
	}

	public void setDbMap(Map<String, Integer> dbMap) {
		this.dbMap = dbMap;
	}

	@Override
	public ICache getCache(String cacheName) {
		
		Jedis client = clientFactory.getRedisClient();
		
		int dbIndex = dbMap.get(cacheName) == null ? 0:dbMap.get(cacheName).intValue();
		
		client.select(dbIndex);
		
		ICache cache = new CacheImpl(client,this);
		return cache;
	}
	
	

	
}
