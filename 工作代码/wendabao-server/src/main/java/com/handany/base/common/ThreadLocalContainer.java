package com.handany.base.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象容器 作用范围为当前线程
 * @author lhb
 *
 */
public class ThreadLocalContainer {

	public static ThreadLocal<Map> local = new ThreadLocal<Map>();
	
	public static void put(String name,Object value){
		Map container = local.get();
		
		if(container == null){
			container = new HashMap();
			local.set(container);
		}
		
		container.put(name, value);
		
	}
	
	public static Object get(String name){

//		Map container = local.get();
//		if(container == null){
//			return null;
//		}else{
//			return container.get(name);
//		}
		
		return get(name,Object.class);
	}
	
	public static void remove(String name){
		Map container = local.get();
		if(container != null){
			container.remove(name);
		}
	}
	
	
	public static <T> T get(String name,Class<T> t){

		Map container = local.get();
		if(container == null){
			return null;
		}else{
			return (T)container.get(name);
		}
	}
	
	public static void clear(){
		Map container = local.get();
		if(container != null){
			container.clear();
			local.remove();
		}
		
	}
}
