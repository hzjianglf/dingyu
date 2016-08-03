package com.handany.base.push;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handany.base.cache.redis.RedisClientFactory;
import com.handany.base.common.ApplicationConfig;
import com.handany.base.common.ComponentFactory;
import com.handany.base.util.SerializeUtil;


import redis.clients.jedis.Jedis;

/**
 * 百度推送服务
 * 
 * @author lhb
 *
 */
public class MessagePushManager {

	public static Logger logger = LoggerFactory.getLogger(MessagePushManager.class);

	private static ExecutorService executor = Executors.newFixedThreadPool(5);
	

	public static void push(){
		
	}
	

	static class PushTask implements Runnable {

		MessageContent mc;

		public PushTask(MessageContent mc) {
			this.mc = mc;
		}

		@Override
		public void run() {
			
			logger.debug("push a message ...");
			
			BaiduPush.push(mc);
		}

	}



}
