package com.lmd.cxf.service;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface RtxService {
	/*创建部门信息*/
	public @WebResult(name="String") String addDept(@WebParam(name="deptId") String deptId,@WebParam(name="DetpInfo") String DetpInfo,@WebParam(name="DeptName") String DeptName,@WebParam(name="ParentDeptId") String ParentDeptId);
	/*添加用户信息*/
	public @WebResult(name="String") String addUser(@WebParam(name="userName") String userName,@WebParam(name="deptID") String deptID,@WebParam(name="chsName") String chsName,@WebParam(name="pwd") String pwd);
	/*发送消息*/
	public @WebResult(name="String") String sendNotify(@WebParam(name="receivers") String receivers,@WebParam(name="title") String title,@WebParam(name="msg") String msg, @WebParam(name="delayTime") String type,String delayTime);
	/*发送短信*/
	public @WebResult(name="String") String sendSms(@WebParam(name="sender") String sender, @WebParam(name="receiver") String receiver, @WebParam(name="smsInfo") String smsInfo,@WebParam(name="autoCut") int autoCut, @WebParam(name="noTitle") int noTitle);
	/*删除部门*/
	public @WebResult(name="String") String deleteDept(@WebParam(name="deptId") String deptId,@WebParam(name="type") String type);
	/*删除用户*/
	public @WebResult(name="String") String deleteUser(@WebParam(name="UserName") String userName);
}
