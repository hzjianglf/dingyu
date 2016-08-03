<%@page import="org.slf4j.LoggerFactory"%>
<%@page import="org.slf4j.Logger"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.handany.base.util.ImageCompressUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
    
Logger logger = LoggerFactory.getLogger("Test");    	
String name = request.getParameter("name");
    
    	
    	try{
    	//ImageCompressUtil.zipImageFile("/data/images/201511/"+name, 160, 160, 1f, "_test");
    	File file = new File("/data/images/201511/"+name+".txt");
    	logger.debug("文件是否存在：{}", file.exists());
    	//boolean rst = file.createNewFile();
    	//logger.debug("文件创建：{}", rst);
    	
    	//logger.debug("文件是否存在：{}", file.exists());
    	
    	//logger.debug("文件绝对路径：{}", file.getAbsolutePath());
    	
    	FileOutputStream fis = new FileOutputStream("/data/images/201511/"+name+".txt");
    	logger.debug("11111");
    	fis.write("helloworld".getBytes());
    	logger.debug("22");
    	fis.flush();
    	logger.debug("33");
    	fis.close();
    	logger.debug("44");
    	}catch(Throwable ex){
    		logger.error("", ex);
    	}
%>