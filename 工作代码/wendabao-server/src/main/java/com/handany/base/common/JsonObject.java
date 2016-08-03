package com.handany.base.common;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.handany.base.model.LoginInfo;

/**
 * json数据对象
 * @author lhb
 *
 */
public class JsonObject {
	

	

	private Header header = new Header();



	private JSONObject objects = new JSONObject ();
	
	
	public JSONObject getObjects(){
		return objects;
	}
	
	
	public void putAll(Map<String,Object> map){
		this.objects.putAll(map);
	}
	
	public void put(String name,Object value){
		this.objects.put(name, value);
	}
	

	public void addObject(String name,Object obj){
		objects.put(name, obj);
	}

	public Object getObject(String name){
		return objects.get(name);
	}
	
	public Object removeObject(String name){
		return objects.remove(name);
	}
	public JSONObject toJsonObj(){
		
		objects.put("header", header);
		
		return objects;
	}
	
	public Header getHeader(){
		return header;
	}
	
	
	public class Header{
		
		boolean success;
		
		String message = "";
		
		String error_code="";
		
		String url="";
		
		String response_time="";
		
		LoginInfo loginInfo = null;

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getError_code() {
			return error_code;
		}

		public void setError_code(String error_code) {
			this.error_code = error_code;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getResponse_time() {
			return response_time;
		}

		public void setResponse_time(String response_time) {
			this.response_time = response_time;
		}

		public LoginInfo getLoginInfo() {
			return loginInfo;
		}

		public void setLoginInfo(LoginInfo loginInfo) {
			this.loginInfo = loginInfo;
		}

	
		
		
	}
	
}
