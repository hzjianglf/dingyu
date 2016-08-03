package com.lmd.cxf.service;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rtx.RTXSvrApi;

import com.lmd.cxf.service.IService;

@WebService
public class ServiceImpl implements IService {
	private Logger log = LoggerFactory.getLogger(ServiceImpl.class);
	public String get(int id) {


    	String deptId= "3";
    	String DetpInfo = "测试11111";
    	String DeptName = "TestDept11";
    	String ParentDeptId = "0";
    	
    	int iRet = -1;
    	
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.addDept(deptId,DetpInfo,DeptName,ParentDeptId);
    		
    		if (iRet == 0)
    		{
    			System.out.println("添加部门成功");
    			
    		}
    		else 
    		{
    			System.out.println("添加部门失败");
    		}

	    }	
    	RtxsvrapiObj.UnInit();
    	
    
		return "success";
	}
}
