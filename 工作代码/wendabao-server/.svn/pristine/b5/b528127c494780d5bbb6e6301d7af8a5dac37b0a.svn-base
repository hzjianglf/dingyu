package com.handany.base.push;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.handany.base.common.CommonUtils;

public class MessageContent implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String channelId;
	
	private String title;
	
	private String description;
	
	private String deviceType;
	
	private int openType;
	
	/**
	 * 消息编码 code
	 */
	private String c;
	
	/**
	 * 发送者
	 * sender
	 * @return
	 */
	private String s;
	
	/**
	 * 接收者
	 * receiver
	 */
	private String r;
	
	/**
	 * 消息时间
	 * msg_time		
	 */
	private String mt;
	
	/**
	 * 返回内容
	 * content
	 */
	private Map<String,String> ct = new HashMap<String,String>();
	/**
	 * 消息编码 code
	 */
	public String getC() {
		return c;
	}
	/**
	 * 消息编码 code
	 */
	public void setC(String c) {
		this.c = c;
	}



	public void putMsgContent(Map<String,String> msgContent){
		this.ct.putAll(msgContent);
	}
	
	/**
	 * 发送者
	 * sender
	 * @return
	 */
	public String getS() {
		if(s == null){
			s = "sys";
		}
		return s;
	}
	/**
	 * 发送者
	 * sender
	 * @return
	 */
	public void setS(String s) {
		this.s = s;
	}
	/**
	 * 接收者
	 * receiver
	 */
	public String getR() {
		return r;
	}
	/**
	 * 接收者
	 * receiver
	 */
	public void setR(String r) {
		this.r = r;
	}
	/**
	 * 消息时间
	 * msg_time		
	 */
	public String getMt() {
		if(mt == null){
			mt = CommonUtils.getDateMinuteStr();
		}
		return mt;
	}
	/**
	 * 消息时间
	 * msg_time		
	 */
	public void setMt(String mt) {
		this.mt = mt;
	}
	/**
	 * 返回内容
	 * content
	 */
	public Map<String, String> getCt() {
		return ct;
	}


	
	/**
	 * 添加返回内容
	 * @param name
	 * @param value
	 */
	public void addContentItem(String name,String value){
		this.ct.put(name, value);
	}
	public String channelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String title() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String description() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String deviceType() {
		return deviceType;
	}
	
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	

	

	public int getOpenType() {
		return openType;
	}
	public void setOpenType(int openType) {
		this.openType = openType;
	}
	public String toJsonString(){
		
		Map map = new HashMap();
		
		map.put("open_type", openType);
		
		if("3".equals(deviceType)){//安卓
			map.put("title", title);
			map.put("description", description);
			map.put("custom_content", ct);
			
		}else if("4".equals(deviceType)){//iOS
			map.putAll(ct);
			Map aps = new HashMap();
			aps.put("alert", title);
		//	aps.put("badge", "0");
		//	aps.put("sound", "");
			map.put("aps", aps);
		}
		
		
		return JSON.toJSONString(map);
	}
	
}
