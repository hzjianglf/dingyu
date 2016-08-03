package com.handany.rbac.dao;

import java.util.List;
import java.util.Map;

import com.handany.rbac.model.PmUser;

public interface PmUserMapper {
	
	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	PmUser login(Map map);	
	
	/**
	 * 根据手机号查询用户信息 可用来（验证手机号是否被注册过）
	 * @param mobile
	 * @return
	 */
	PmUser queryPmUserByMobile(String mobile);
	
	/**
	 * 保存修改注册信息
	 * @param user
	 * @return
	 */
	int saveRegisterMsg(PmUser user);
	
	/**
	 * 修改密码
	 * @param map
	 * userId
	 * oldPwd
	 * newPwd
	 * @return
	 */
	int updatePwd(Map map);
	
	/**
	 * 修改登录名手机号
	 * @param map
	 * @return
	 */
	int updateLoginName(Map map);
	
	/**
	 * 找回密码
	 * @param map
	 * @return
	 */
	int findPwd(Map map);
	
	/**
	 * 通过用户id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PmUser queryById(String id);
	
}
