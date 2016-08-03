package com.whcyz.controller;

import com.jfinal.core.Controller;
import com.whcyz.bean.JsonResult;
import com.whcyz.common.Constant;
import com.whcyz.model.Account;

public class BaseController extends Controller {
	/**
	 * 得到modelId
	 * @return
	 */
	public Integer getParamModelId() {
		return getParaToInt("id");	
	}
	/**
	 * 得到session中的用户信息
	 * @return
	 */
	public Account getSessionAccount() {
		return getSessionAttr(Constant.USER_SESSION_KEY);	
	}
	/**
	 * 得到session中的用户ID
	 * @returnger
	 */
	public Integer getSessionAccountId() {
		Account user=getSessionAccount();
		if(user==null){
			return null;
		}
		return user.getId();	
	}
	/**
	 * 得到session中的用户permission
	 * @return
	 */
	public Integer getSessionAccountPermission() {
		Account user=getSessionAccount();
		if(user==null){
			return null;
		}
		return user.getPermission();	
	}
	/**
	 * 判断是否是超管
	 * @return
	 */
	public boolean isAdmin(){
		Integer permission=getSessionAccountPermission();
		return (permission!=null&&permission==Account.PERMISSION_ADMIN);
	}
	/**
	 * 判断是否是编辑
	 * @return
	 */
	public boolean isEditor(){
		Integer permission=getSessionAccountPermission();
		return (permission!=null&&permission==Account.PERMISSION_EDITOR);
	}
	/**
	 * 判断是否是普通用户
	 * @return
	 */
	public boolean isNormal(){
		Integer permission=getSessionAccountPermission();
		return (permission!=null&&permission==Account.PERMISSION_NORMAL);
	}
	/**
	 * 得到session中的用户ID
	 * @return
	 */
	public String getSessionAccountNickname() {
		Account user=getSessionAccount();
		if(user==null){
			return null;
		}
		return user.getNickname();	
	}
	/**
	 * 得到session中的用户ID
	 * @return
	 */
	public String getSessionAccountUsername() {
		Account user=getSessionAccount();
		if(user==null){
			return null;
		}
		return user.getUsername();	
	}
	/**
	 * 返回成功json信息
	 */
	public void renderJsonData(boolean success,Object data,String msg) {
		renderJson(new JsonResult(success, data, msg));
	}
	/**
	 * 返回json信息
	 */
	public void renderJsonData(boolean success,Object data) {
		renderJson(new JsonResult(success, data));
	}
	/**
	 * 返回成功json信息
	 */
	public void renderJsonSuccessData(Object data) {
		renderJson(new JsonResult(true, data));
	}
	/**
	 * 返回失败json信息
	 */
	public void renderJsonFailData(Object data,String msg) {
		renderJson(new JsonResult(false, data,msg));
	}
	/**
	 * 返回成功json信息
	 */
	public void renderJsonSuccess() {
		renderJson("success",true);
	}
	/**
	 * 返回成功json信息
	 */
	public void renderJsonFail(String msg) {
		setAttr("success", false);
		setAttr("msg",msg);
		renderJson();
	}
	/**
	 * 返回失败json信息
	 */
	public void renderJsonFail() {
		renderJson("success", false);
	}
	/**
	 * 设置错误信息msg
	 * @param msg
	 */
	public void setErrorMsg(String msg){
		setAttr("success",false);
		setAttr("msg",msg);
	}
}
