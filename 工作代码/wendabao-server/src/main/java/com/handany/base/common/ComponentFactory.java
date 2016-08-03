package com.handany.base.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 组件工厂
 * @author lhb
 *
 */
public class ComponentFactory {
	
	private static BeanFactory factory;
	
	public static Object getBean(String name){
		return getBean(name,Object.class);
	}
	
	public static <T> T getBean(String name,Class<T> t){
		HttpServletRequest request = HttpUtil.getRequest();
		if(request != null){
			ServletContext context = request.getServletContext();
			WebApplicationContext wac = WebApplicationContextUtils.
                    getRequiredWebApplicationContext(context);
			
			return wac.getBean(name,t);
		}else{
			if(factory == null){
				factory = new ClassPathXmlApplicationContext("/components/components.xml");
			}
			return factory.getBean(name,t);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(null==getBean("dataSource"));
	}
}
