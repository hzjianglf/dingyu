package com.wxpt.common;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListenFile implements ServletContextListener {

	   private Timer timer = null; 
	     
	    public void contextDestroyed(ServletContextEvent arg0) { 
	        timer.cancel(); 
	    } 

	    public void contextInitialized(ServletContextEvent arg0) { 
	    	String path = arg0.getServletContext().getRealPath("web")+"/";// 文件保存目录路径  
	    	MyTaskFile t  = new MyTaskFile();  
	        t.setPath(path);
	        timer = new Timer(); 
	        //设置任务计划，启动和间隔时间 
//	        timer.schedule(new MyTaskFile(), 0, 1000*60*60); 
	        timer.schedule(new MyTaskFile(), 0, 1000*60*60); 
	        
	    } 

}
