package com.handany.base.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.handany.rbac.common.UserContextManager;

public class WriteJsonInterceptor implements HandlerInterceptor {

	private static Logger logger = LoggerFactory.getLogger(WriteJsonInterceptor.class);

	private static SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	
	
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object arg2, Exception arg3)
			throws Exception {

		WriteJsonContainer.release();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView view)
			throws Exception {
		
		
		String dataType = request.getParameter("requestDataType");
		

		try {
			JsonObject jsonObj = WriteJsonContainer.get();
			
			
			if (jsonObj != null ) {

				jsonObj.addObject("tokenId", UserContextManager.getTokenId());
				
				if("json".equalsIgnoreCase(dataType) || view == null){
					
					if(view != null){
						view.clear();
					}
					
					wrapHeader(request,jsonObj);
					String jsonStr = null;
					
					
					
					if(request.getParameter("deviceType") == null){
						
					
						jsonStr = JSON.toJSONString(jsonObj.toJsonObj(),SerializerFeature.DisableCircularReferenceDetect);
					}else{
						jsonStr = JSON.toJSONString(jsonObj.toJsonObj(),SerializerFeature.DisableCircularReferenceDetect);
					}
					
					
					logger.debug("response json : {}",jsonStr);
					response.setContentType("text/json;charset=utf-8");
					response.getWriter().print(jsonStr);
				}else{
					Map<String,Object> objects = jsonObj.getObjects();
					for(Map.Entry<String, Object> entry:objects.entrySet()){
						request.setAttribute(entry.getKey(), entry.getValue());
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			WriteJsonContainer.release();
		}
		
		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse arg1, Object arg2) throws Exception {


		WriteJsonContainer.release();
		
		return true;
	}
	
	
	private void wrapHeader(HttpServletRequest req,JsonObject jsonObj){
		
		jsonObj.getHeader().setResponse_time(dateFormate.format(Calendar.getInstance().getTime()));
		String url =  getUrl(req);
		jsonObj.getHeader().setUrl(url);

	}

	
	private String getUrl(HttpServletRequest req){
		
		String url =  req.getRequestURL().toString();
		
		if(req.getQueryString() != null){
			url += "?"+req.getQueryString();
		}
		
		return url;
	}
	
}
