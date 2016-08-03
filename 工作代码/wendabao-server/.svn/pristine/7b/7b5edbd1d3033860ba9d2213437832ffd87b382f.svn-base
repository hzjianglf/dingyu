package com.handany.base.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http工具，可以获取HttpServletRequest和HttpServletResponse
 * @author lhb
 *
 */
public class HttpUtil {
	
	public static HttpServletRequest getRequest(){
		return ThreadLocalContainer.get("request",HttpServletRequest.class);
	}
	
	public static HttpServletResponse getResponse(){
		return ThreadLocalContainer.get("response",HttpServletResponse.class);
	}
	
	public static String getParameter(String name){
		String contentType = getRequest().getContentType();
		
		if(contentType != null && contentType.indexOf("multipart/form-data")>-1){
			String paramValue = FileUploader.getParameter(name);
			if(paramValue == null){
				paramValue = getRequest().getParameter(name);
			}
			return paramValue;
		}else{
			return getRequest().getParameter(name);
		}
	}
	
}
