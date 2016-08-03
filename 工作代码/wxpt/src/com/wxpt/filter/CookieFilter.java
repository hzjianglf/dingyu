package com.wxpt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFilter  implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse)res;
		HttpServletRequest request=(HttpServletRequest)req;
		try {
			Cookie cookies[]= request.getCookies();
			Cookie c = null;
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().equals("wxpts")){
						c= cookies[i];
						break;
				}
			}
			if(c!=null){
				//证明存在  登录未超时
				c.setMaxAge(60*60*24*365);
				c.setPath("/");
				response.addCookie(c);
				
				
			}
			chain.doFilter(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

	

}
