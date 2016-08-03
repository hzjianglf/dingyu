package com.handany.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.handany.base.common.HttpUtil;
import com.handany.base.common.JsonObject;
import com.handany.base.common.WriteJsonContainer;


/**
 * Controller基类
 * @author lhb
 *
 */
public class BaseController {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public static final String DEFAULT_PAGE = "DEFAULT_PAGE";
	
	private static Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@ModelAttribute
	private void init(HttpServletRequest request,HttpServletResponse response){
		this.request = request;
		this.response = response;
	}

//
//	/**
//	 * 根据loginName获得百度云推送ChannelId
//	 * @return
//	 */
//	protected String getChannelIdByLoginName(String loginName){
//		
//		if(loginName == null){
//			
//			throw new RuntimeException("loginName is null when invoke getChannelIdByLoginName !");
//			
//		}
//		
//		String channelId = CacheContainer.get(MessagePushManager.CACHE_CHANNELID + loginName, String.class);
//		
//		if(channelId == null || channelId.trim().length() == 0 ){
//			logger.error("loginName[{}] 's channelId is null",loginName);
//		}
//		
//		if( "null".equals(channelId)){
//			logger.error("loginName[{}] 's channelId is a \"null\" string ",loginName);
//		}
//		
//		return channelId;
//	}
//	
	protected String getDeviceType(){
		return HttpUtil.getParameter("deviceType");
	}
	
	
	/**
	 * 根据前端类型转换view
	 * @param viewPath
	 * @return
	 */
	protected String transView(String viewPath){
		
		String prefix = "";
		if(getDeviceType() == null){
			prefix = "web";
		}else{
			prefix = "phone";
		}
		return prefix + viewPath;
		
	}
	
	/**
	 * 获得HttpServletRequest对象
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return request;
	}




	/**
	 * 获得HttpServletResponse对象
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return response;
	}
	
	
	/**
	 * 添加基本对象到json输出
	 * @param key
	 * @param obj
	 */
	protected void writeObject(String key,Object obj){
		
		JsonObject jObj = getJsonObject();
		
		jObj.addObject(key, obj);
	}
	
	
	protected void writeData(Object obj){
		writeObject("data",obj);
	}
	
	private JsonObject getJsonObject(){
		JsonObject jObj = WriteJsonContainer.get();
		
		if(jObj == null){
			jObj = new JsonObject();
			WriteJsonContainer.set(jObj);
		}
		
		return jObj;
	}
	
	
	
	/**
	 * 
	 * @param success
	 */
	protected void setSuccess(boolean success){

		JsonObject jObj = getJsonObject();	
		jObj.getHeader().setSuccess(success);
		if(success){
			setErrorCode("0000");
		}
	}
	
	protected void setMessage(String message){
		JsonObject jObj = getJsonObject();
		jObj.getHeader().setMessage(message);
	}

	
	/**
	 * 设置报文错误码
	 * @param errorCode
	 */
	protected void setErrorCode(String errorCode){
		JsonObject jObj = getJsonObject();
		jObj.getHeader().setError_code(errorCode);
	}
	
	protected void writeJsonArrStr(String name,String jsonStr){
		JSONArray jarr = JSON.parseArray(jsonStr);
		getJsonObject().put(name, jarr);
	}

	protected void writeJsonObjStr(String name,String jsonStr){
		JSONObject jobj = JSON.parseObject(jsonStr);
		getJsonObject().put(name,jobj);
	}
	
}
