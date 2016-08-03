package com.handany.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.handany.base.cache.redis.RedisClientFactory;
import com.handany.base.common.ComponentFactory;
import com.handany.base.common.ThreadLocalContainer;

/**
 * 线程资源过滤
 * @author lhb
 *
 */
public class ResourceFilter implements Filter{

	
	private static Logger logger = LoggerFactory.getLogger(ResourceFilter.class); 

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		try{
			
//			logger.debug("请求开始ThreadLocalContainer清空");
		
			
			ThreadLocalContainer.clear();
			ThreadLocalContainer.put("request", (HttpServletRequest)req);
			ThreadLocalContainer.put("response", (HttpServletResponse)resp);
			chain.doFilter(req, resp);
			
			RedisClientFactory redisClientFactory = ComponentFactory.getBean("redisClientFactory",RedisClientFactory.class);
			if(redisClientFactory != null){
				redisClientFactory.releaseThreadResource();
			}	
		}finally{

			
			
			ThreadLocalContainer.clear();
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
