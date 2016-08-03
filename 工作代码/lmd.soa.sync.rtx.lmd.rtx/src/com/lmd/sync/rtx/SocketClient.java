package com.lmd.sync.rtx;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class SocketClient {

	public static final String IP = "10.10.10.207";//服务器地址 
	public static final int PORT = 8093;//服务器端口号  
	public static void main(String[] args) {  
		System.out.println(handler("test12;111111;测试12").get("status")); 
	}

	public static Map<String, String> handler(String str){
		Map<String, String> map = new HashMap<String, String>();
			try {
				//实例化一个Socket，并指定服务器地址和端口
				Socket client = new Socket(IP, PORT);
				//开一个线程，负责读和写
				WriteHandlerThread wt = new WriteHandlerThread(client,str);
				wt.start();
				//轮询
				while(true){
					if("success".equals(wt.returnRecive()) || "error".equals(wt.returnRecive())){
						map.put("status", wt.returnRecive());
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return map;
	}
	
	
}  
