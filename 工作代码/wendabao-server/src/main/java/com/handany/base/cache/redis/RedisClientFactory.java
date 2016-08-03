package com.handany.base.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * redis客户端工厂，提供threadlcoal支持
 * 
 * @author lhb
 *
 */
public class RedisClientFactory {

	private static Logger logger = LoggerFactory.getLogger(RedisClientFactory.class);

	private static ThreadLocal<Jedis> threadCache = new ThreadLocal<Jedis>();

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	/**
	 * 获得jedis客户端
	 * @return
	 */
	public Jedis getRedisClient() {

		Jedis client = threadCache.get();

		if (client == null) {
			client = jedisPool.getResource();
			threadCache.set(client);
		} else {
		}

		return client;

	}

	/**
	 * 释放jedis客户端	
	 */
	public void releaseThreadResource() {
		Jedis client = threadCache.get();

		if (client != null) {
			try {
				jedisPool.returnResourceObject(client);
			} catch (Exception ex) {
				logger.error("", ex);
			} finally {
				threadCache.remove();
			}
		}
	}

	public void destroy() {
		jedisPool.destroy();
	}

	/**
	 * 释放已经发生异常的jedis客户端
	 */
	public void releaseBrokenResource() {

		Jedis client = threadCache.get();
		if (client != null) {
			try {
				jedisPool.returnBrokenResource(client);
			} catch (Exception e) {
				
			} finally {
				try{
					client.close();
				}catch(Exception ex){}
				threadCache.remove();
			}
		}

	}

	/**
	 * 监控redis执行过程中的异常
	 * @param ex
	 */
	public void monitorException(Exception ex) {
		if (ex instanceof JedisException) {
			releaseBrokenResource();
			logger.error("This is Jedis client exception that has been handled . ",ex);
		}else{
			logger.error("",ex);
		}
	}
}
