package com.handany.base.common;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageHelper;

/**
 * PageHelper分页封装
 * @author lhb
 *
 */
public class PageUtil {

	
	public static void startPage(){
		startPage(HttpUtil.getRequest());
	}
	
	public static void startPage(HttpServletRequest request){
		int currentPage = 1;
		int start = getNumber(request,"start");
		int length = getNumber(request,"length");
		
		if(length <=0 ){
			length = 10;
		}
		
		currentPage = (start/length) + 1;
		PageHelper.startPage(currentPage, length);
	}
	
	private static int getNumber(HttpServletRequest request,String name){
		
		String value = request.getParameter(name);
		
		
		if(value == null || value.trim().length() == 0){
			return 0;
		}
		
		int num = 0;
		
		try{
			num = Integer.parseInt(value);
		}catch(Exception ex){
			num = 0;
		}
		return num;
		
	}
}
