package com.handany.base.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploader {

	
	private static Logger logger = LoggerFactory.getLogger(FileUploader.class);
	
	private static final String CONTAINER_NAME = "FileUploader";
	
	private List<FileItem> fileItemList = new ArrayList<FileItem>();
	
	private Map<String,String> params = new HashMap<String,String>();
	
	private FileItem item = new FileItem();
	
	
	
	private FileUploader(){
		
	}
	
	
	private  static FileUploader getContext(){
		FileUploader fu = ThreadLocalContainer.get(CONTAINER_NAME,FileUploader.class);
		if(fu == null){
			fu = new FileUploader();
			ThreadLocalContainer.put(CONTAINER_NAME,fu);
		}
		
		return fu;
	}
	
	public static void addFileItem(String fileName,String filePath,String smallPath){
		
		FileItem fi = new FileItem();
		fi.fileName = fileName;
		fi.filePath = filePath;
		fi.smallPath = smallPath;
		getContext().fileItemList.add(fi);
	}
	
	public static void addParam(String name,String value){
		getContext().params.put(name, value);
	}
	
	
	
	
	public static List<FileItem> getFileItemList() {
		return getContext().fileItemList;
	}

	public static FileItem getFileItem(){
		
		return getContext().item;
	}


	public static Map<String, String> getParams() {
		return getContext().params;
	}

	public static class FileItem{
		
		public String fileName;
		
		public String filePath;
		
		public String smallPath;
		
		
	}
	
	public static String getParameter(String name){
		return getContext().params.get(name);
	}
	
	
}
