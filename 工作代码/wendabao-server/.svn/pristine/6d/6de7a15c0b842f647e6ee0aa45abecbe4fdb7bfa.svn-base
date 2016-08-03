package com.handany.base.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handany.base.common.ApplicationConfig;
import com.handany.base.push.MessagePushManager;


public class StartupServlet extends HttpServlet{

	private static Logger logger = LoggerFactory.getLogger(StartupServlet.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2957136911128227906L;

	@Override
	public void init() throws ServletException {
	
	
		
		
		//初始化系统参数 
		try {
			ApplicationConfig.init();
			com.handany.base.common.Constants.IMAGE_SERVER=ApplicationConfig.getConfig("image_server");
		} catch (Exception e) {
			logger.error("系统参数初始化失败",e);
			System.exit(-1);
		}
		
		//启动消息推送 
		//MessagePushManager.start();
		
	
		
	}
	
	@Override
	public void destroy() {
		//MessagePushManager.stop();
	
	}
	
}
