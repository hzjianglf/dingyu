package com.handany.base.cache.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handany.base.cache.ICache;
import com.handany.base.util.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * redis 简易封装
 * 
 * @author lhb
 *
 */
public class CacheImpl implements ICache {

	private static Logger logger = LoggerFactory.getLogger(CacheImpl.class);

	private Jedis client;

	private CacheManagerImpl manager;
	/**
	 * 缓存对象标识
	 */
	private static final String CACHE_OBJECT = "OBJECT";
	/**
	 * 超时时间标识
	 */
	private static final String CACHE_EXPIRE = "EXPIRE";

	CacheImpl(Jedis client, CacheManagerImpl manager) {
		this.client = client;
		this.manager = manager;
	}

	/**
	 * 模糊查找缓存中对象
	 * 
	 * @param pattern
	 * @return
	 */
	public <T> List<T> getList(String pattern, Class<T> t) {

		List<T> list = new ArrayList<T>();

		try {

			Set<byte[]> keys = client.keys(pattern.getBytes());
			if (keys != null && keys.size() > 0) {
				for (byte[] key : keys) {
					byte[] value = null;
					if ("hash".equals(client.type(key))) {
						value = client.hget(key, CACHE_OBJECT.getBytes());
					}
					if ("string".equals(key)) {
						value = client.get(key);
					} else {

					}
					if (value != null) {
						Object obj = SerializeUtil.unserialize(value);
						if (obj.getClass().equals(t)) {
							list.add((T) obj);
						}

					}

				}
			}
		} catch (Exception ex) {
			manager.getClientFactory().monitorException(ex);
		}

		return list;

	}

	/**
	 * 对于有生存时间的缓存对象，执行get后将重置其生存时间为初始值
	 */
	@Override
	public Object get(String key) {

		Object cacheObj = null;
		try {
			long ttl = client.ttl(key.getBytes()).longValue();

			if (ttl == -2) {
				return null;
			}

			if (ttl > -1) {
				byte[] obj = client.hget(key.getBytes(), CACHE_OBJECT.getBytes());
				byte[] expire = client.hget(key.getBytes(), CACHE_EXPIRE.getBytes());

				if (obj != null) {
					int expireSeconds = Integer.valueOf(new String(expire));
					if (expireSeconds > 0) {
						client.expire(key, expireSeconds);
					}

					cacheObj = SerializeUtil.unserialize(obj);

				}
			} else if (ttl == -1) {
				byte[] bytes = client.get(key.getBytes());
				if (bytes != null) {
					cacheObj = SerializeUtil.unserialize(bytes);
				}

			}
		} catch (Exception ex) {
			manager.getClientFactory().monitorException(ex);
		}

		return cacheObj;

	}

	@Override
	public void remove(String key) {
		try {
			client.del(key);
		} catch (Exception ex) {
			manager.getClientFactory().monitorException(ex);
		}
	}

	@Override
	public void clear() {
		try {
			client.flushDB();
		} catch (Exception ex) {
			manager.getClientFactory().monitorException(ex);
		}
	}

	/**
	 * 设置带超时时间的缓存对象，
	 */
	@Override
	public void put(String key, Object value, int expireSeconds) {

		if (key == null || value == null) {
			return;
		}

		Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();

		map.put(CACHE_EXPIRE.getBytes(), String.valueOf(expireSeconds).getBytes());

		map.put(CACHE_OBJECT.getBytes(), SerializeUtil.serialize(value));

		try {
			client.hmset(key.getBytes(), map);

			client.expire(key.getBytes(), expireSeconds);

		} catch (Exception ex) {
			manager.getClientFactory().monitorException(ex);
		}
	}

	@Override
	public void put(String key, Object value) {

		if (key == null || value == null) {
			return;
		}

		try {
			client.set(key.getBytes(), SerializeUtil.serialize(value));

		} catch (Exception ex) {
			manager.getClientFactory().monitorException(ex);
		}

	}

	/**
	 * 获得redis客户端对象
	 * 
	 * @return
	 */
	public Jedis getRedisClient() {
		return this.client;
	}

}
