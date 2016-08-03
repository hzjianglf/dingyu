package com.lmd.cxf.service;

import javax.jws.WebService;

import rtx.RTXSvrApi;

import com.lmd.cxf.service.RtxService;
@WebService
public class RtxServiceImpl implements RtxService{

	/**
     * 添加组织信息
     * @param deptId String		部门ID
     * @param DetpInfo String	部门信息
     * @param DeptName String	部门名称
     * @param ParentDeptId String 	父部门ID
     * @param type String 	0:只删除当前组织 1:删除下级组织及用户
     * @return int  0 操作成功 非0为失败
     */
	public String addDept(String deptId, String DetpInfo, String DeptName,
			String ParentDeptId) {
		int iRet = -1;
		String status="";
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init()){   
    		iRet = RtxsvrapiObj.addDept(deptId,DetpInfo,DeptName,ParentDeptId);
    		if (iRet == 0){
    			System.out.println("添加部门成功");
    			status="success";
    		}
    		else {
    			System.out.println("添加'"+DeptName+"'部门失败");
    			status="error";
    		}

	    }	
    	RtxsvrapiObj.UnInit();
		return status;
	}

	/**
     * 新增用户
     * @param UserName string 用户帐号
     * @param DeptID String 部门ID	
     * @param chsName String 用户姓名
     * @param pwd String 密码
     * @return int  0 操作成功 非0为失败
     */
	public String addUser(String userName, String deptID, String chsName,
			String pwd) {
		int iRet = -1;
		String status="";
		RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init()){ 
    		if("null".equals(userName)
    				||"null".equals(deptID)
    				||"null".equals(chsName)
    				||"null".equals(pwd)
    				||null==userName
    				||null==deptID
    				||null==chsName
    				||null==pwd){
    		System.out.println("存在脏数据userName: "+userName+"deptID: "+deptID+"chsName:  "+chsName+"pwd:  "+pwd);
    		System.out.println("跳过并继续初始化");
    		status="success";
    		}else{
    			iRet =RtxsvrapiObj.addUser(userName,deptID,chsName,pwd);
        		if (iRet==0){
        			System.out.println("添加   "+chsName+"   用户成功");
        			status="success";
        		}
        		else {
        			System.out.println("添加用户失败");
        			status="error";
        		}
    		}
	    }	
    	RtxsvrapiObj.UnInit();
		return status;
	}

	/**
     * 发送消息提醒
     * @param receivers String 接收人(多个接收人以逗号分隔)
     * @param title String 消息标题
     * @param msg String 消息内容
     * @param type String 0:普通消息 1:紧急消息
     * @param delayTime String 显示停留时间(毫秒) 0:为永久停留(用户关闭时才关闭)
     * @return int 0:操作成功 非0:操作不成功
     */
	public String sendNotify(String receivers, String title, String msg,
			String type, String delayTime) {
		
    	int iRet= -1;
    	String status="";
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.sendNotify(receivers,title,msg, type,delayTime);
    		if (iRet == 0)
    		{
    			System.out.println("发送成功");
    			status="success";
    		}
    		else 
    		{
    			System.out.println("发送失败");
    			status="error";
    		}

    	}
    	RtxsvrapiObj.UnInit();
		return status;
	}

	/**
	    * 发送短信
	    * @param sender String 发送人
	    * @param receiver String 接收人(RTX用户或手机号码均可,最多128个)
	    * @param smsInfo String 短信内容
	    * @param autoCut int 是否自动分条发送 0:否 1:是
	    * @param noTitle int 是否自动填写标题 0:自动 1:制定
	    * @return int 成功返回0，失败返回其他
	    */
	public String sendSms(String sender, String receiver, String smsInfo,
			int autoCut, int noTitle) {
    	int iRet= -1;
    	String status="";
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.sendSms(sender, receiver, smsInfo,autoCut, noTitle);
    		if (iRet == 0)
    		{
    			System.out.println("发送成功");
    			status="success";
    		}
    		else 
    		{
    			System.out.println("发送失败");
    			status="error";
    		}

    	}
    	RtxsvrapiObj.UnInit();
		return status;
	}

	/**
     * 删除组织信息
     * @param deptId String	部门
     * @param type String 0:只删除当前组织 1:删除下级组织及用户
     * @return int  0 操作成功 非0为失败
     */
	public String deleteDept(String deptId, String type) {
		int iRet = -1;
		String status="";
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{   
    		iRet = RtxsvrapiObj.deleteDept(deptId,type);
    		
    		if (iRet == 0)
    		{
    			System.out.println("删除部门成功");
    			status="success";
    		}
    		else 
    		{
    			System.out.println("删除部门失败");
    			status="error";
    		}

	    }	
    	RtxsvrapiObj.UnInit();
		return status;
	}

	/**
     * 删除用户信息
     * @param String UserName用户帐号
     * @return int  0 操作成功 非0为失败
     */
	public String deleteUser(String userName) {
		int iRet = -1;
		String status="";
    	RTXSvrApi  RtxsvrapiObj = new RTXSvrApi();        		
    	if( RtxsvrapiObj.Init())
    	{  iRet = RtxsvrapiObj.userIsExist(userName); 
	    	if(iRet==0){
	    		iRet =RtxsvrapiObj.deleteUser(userName);
	    		if (iRet==0)
	    		{
	    			System.out.println("删除成功");
	    			status="success";
	    		}
	    		else 
	    		{
	    			System.out.println("删除失败");
	    			status="error";
	    		}
	    	}else{
	    		status="success";//如果用户不存在直接返回success,因为有可能是删除部门的时候已经删除了
	    	}
    		

	    }	
    	RtxsvrapiObj.UnInit();
		return status;
	}

}
