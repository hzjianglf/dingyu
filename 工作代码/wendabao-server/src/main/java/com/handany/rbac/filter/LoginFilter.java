package com.handany.rbac.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.handany.base.common.HttpUtil;
import com.handany.base.common.JsonObject;
import com.handany.rbac.common.UserContextManager;

/**
 * Servlet Filter implementation class SessionFilter
 */
public class LoginFilter implements Filter {
	
	private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	// 登录网址
	private String loginUrl = "/pm/user/login.do";
	
	// 不拦截网址，可以配置多个网址，以英文逗号（,）分隔
	private String excludeUrl;
	

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 *  
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		
		boolean send = false; // 是否跳转登陆页
		boolean isCheck = true; // 是否检查已登录
		
		String requestUrl = req.getRequestURI();
		try {
			requestUrl = requestUrl.replaceFirst(req.getContextPath(), "");
		
			
			if (requestUrl.indexOf("/open/")>-1 || this.excludeUrl.indexOf(requestUrl)>-1) {
				isCheck = false;
			}
		
			
			if (isCheck && UserContextManager.getLoginUser() == null) {
				send = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (send) {
				
				logger.debug("未登录,访问url:{}",requestUrl);
				
				if("json".equals(HttpUtil.getParameter("requestDataType"))){
					sendMustLoginJson();
				}else{
					req.getRequestDispatcher(loginUrl).forward(request, response);
				}
			} else {
				chain.doFilter(request, response);

			}
		}
	}
	
	private void sendMustLoginJson() throws IOException{
		JsonObject jsonObj = new JsonObject();
		jsonObj.getHeader().setSuccess(false);
		jsonObj.getHeader().setError_code("NEEDLOGIN");
		jsonObj.getHeader().setMessage("尚未登录，请登录");
		HttpUtil.getResponse().setContentType("text/json;charset=utf-8");
		HttpUtil.getResponse().getWriter().print(JSON.toJSONString(jsonObj.toJsonObj(),SerializerFeature.DisableCircularReferenceDetect));
	}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		if (fConfig.getInitParameter("loginUrl") != null) {
			this.loginUrl = fConfig.getInitParameter("loginUrl");
		}
		
		if (fConfig.getInitParameter("excludeUrl") != null) {
			this.excludeUrl = fConfig.getInitParameter("excludeUrl");
			logger.debug("excludeUrl:{}",this.excludeUrl);
//			this.excludeUrlArray = excludeUrl.split(",");
		}
	}
	
	public static void main(String[] args) {
		String excludeUrl = "/pm/user/login.do,/pm/user/mustlogin.do,/pm/verifycode.do,/sys/sysInfo.do,/pm/register.do,/pm/user/findPassword.do";
		
		System.out.println(excludeUrl.indexOf("/pm/user/findPassword.do"));
	}
}
