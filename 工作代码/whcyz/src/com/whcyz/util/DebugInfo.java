package com.whcyz.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.whcyz.common.Constant;


public class DebugInfo {
	private static Log log=LogFactory.getLog(DebugInfo.class);
	public static void log(String className,String msg){
		log.info("["+className+"]:"+"["+msg+"] "+DateUtil.format(new Date()));
	}
	public static void println(String info){
		if(Constant.devMode){
			System.out.println(info);
		}
	}
	public static void println(Object info){
		DebugInfo.println(info.toString());
	}
	public static void print(String info){
		if(Constant.devMode){
			System.out.print(info);
		}
	}
	public static void print(Object info){
		DebugInfo.print(info.toString());
	}

}
