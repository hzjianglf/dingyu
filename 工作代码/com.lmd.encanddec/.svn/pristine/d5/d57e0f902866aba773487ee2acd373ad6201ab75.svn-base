package com.lmd.encanddec.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 如果所有的Bean都在Spring容器内部，似乎不用把Bean拿出来，这个类仅仅是为了兼容旧的代码。
 * 
 * @author ZhaoBin
 */
public class SpringUtil implements ApplicationContextAware {

	@Autowired
	private static ApplicationContext applicationContext = null;
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		return (T)applicationContext.getBean(beanName);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}
	
}
