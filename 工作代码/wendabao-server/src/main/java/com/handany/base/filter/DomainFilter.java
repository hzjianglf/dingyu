package com.handany.base.filter;

import java.io.IOException;
import java.util.Enumeration;

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

/**
 * 跨域过滤
 * 
 * @author lhb
 *
 */
public class DomainFilter implements Filter {

	
	private static Logger logger = LoggerFactory.getLogger(DomainFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletResponse response = (HttpServletResponse) resp;
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Headers",
					"DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			
			HttpServletRequest request = (HttpServletRequest)req;
			
			request.setCharacterEncoding("utf-8");
			
			printLog(req);
			
		} finally {
			chain.doFilter(req, resp);
		}
	}
	
	private void printLog(ServletRequest req){
		
		String params = "";
		
		HttpServletRequest request = (HttpServletRequest)req;
		
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
		String paraName=(String)enu.nextElement();  
			params+=("&"+paraName+"="+request.getParameter(paraName));  
		}
		
		logger.debug("请求：{} ==> 参数 {}",request.getRequestURL().toString(),params);
		
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		

	}

}
