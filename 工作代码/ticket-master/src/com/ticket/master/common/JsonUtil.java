package com.ticket.master.common;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JsonUtil {

	
	/**
	* @Title: JsonStrTrim
	* @author : jsw
	* @date : 2012-12-7
	* @time : 上午09:19:18
	* @Description: 传入string 类型的 json字符串 去处字符串中的属性值的空格
	* @param jsonStr
	* @return
	* @exception:(异常说明)
	*/
	
	
	
	
		public static void main(String[] args) {
			try {
				String result = "{'id':{'a':'0','b':'1'},'users':[{'toid':1,'tosex':2,'realname':'zs'},{'toid':44,'tosex':55,'realname':'zs'}]}";

				JSONObject json = new JSONObject();
				System.out.println("取id值:" + json.get("id"));
				System.out.println("取id中a值:" + json.getJSONObject("id").get("a"));
				System.out.println("取id中b值:" + json.getJSONObject("id").get("b"));
				JSONArray jsonArray = json.getJSONArray("users");
				int iSize = jsonArray.size();
				System.out.println("json中数组Size:" + iSize);
				for (int i = 0; i < iSize; i++) {
					JSONObject jsonObj = jsonArray.getJSONObject(i);
					System.out.println("[" + i + "]toid=" + jsonObj.get("toid"));
					System.out.println("[" + i + "]tosex=" + jsonObj.get("tosex"));
					System.out.println();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	
	public JSONObject JsonStrTrim(String jsonStr){
		
		JSONObject reagobj = JSONObject.fromObject(jsonStr);
		// 取出 jsonObject 中的字段的值的空格
		Iterator itt = reagobj.keys();
		
		while (itt.hasNext()) {
			
			String key = itt.next().toString();
			String value = reagobj.getString(key);
			
			if(value == null){				
				continue ;		
			}else if("".equals(value.trim())){				
				continue ;
			}else{
				reagobj.put(key, value.trim());
			}
		}
		return reagobj;
	}
	
	/**
	* @Title: JsonStrTrim
	* @author : jsw
	* @date : 2012-12-7
	* @time : 上午09:21:48
	* @Description: 传入jsonObject 去除当中的空格
	* @param jsonStr
	* @return
	* @exception:(异常说明)
	*/
	public JSONObject JsonStrTrim(JSONObject jsonStr){
		
		JSONObject reagobj = jsonStr ;
		// 取出 jsonObject 中的字段的值的空格
		Iterator itt = reagobj.keys();
		
		while (itt.hasNext()) {
			
			String key = itt.next().toString();
			String value = reagobj.getString(key);
			
			if(value == null){				
				continue ;		
			}else if("".equals(value.trim())){				
				continue ;
			}else{
				reagobj.put(key, value.trim());
			}
		}
		return reagobj;
	}
	
	
	/**
	* @Title: JsonStrTrim
	* @author : jsw
	* @date : 2012-12-7
	* @time : 上午11:48:59
	* @Description: 将 jsonarry 的jsonObject 中的value值去处前后空格
	* @param arr
	* @return
	* @exception:(异常说明)
	*/
	public JSONArray JsonStrTrim(JSONArray arr){
		
		if( arr != null && arr.size() > 0){
			for (int i = 0; i < arr.size(); i++) {
				
				JSONObject obj = (JSONObject) arr.get(i);
				// 取出 jsonObject 中的字段的值的空格
				Iterator itt = obj.keys();
				
				while (itt.hasNext()) {
					
					String key = itt.next().toString();
					String value = obj.getString(key);
					
					if(value == null){				
						continue ;		
					}else if("".equals(value.trim())){				
						continue ;
					}else{
						obj.put(key, value.trim());
					}
				}
				arr.set(i,  obj );				
			}
		}
		return arr;
	}
	
}
