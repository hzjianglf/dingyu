package com.handany.rbac.service;

import java.util.List;
import java.util.Map;

import com.handany.rbac.model.PmUser;

public interface PmUserService {

	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public PmUser login(String loginName,String password) throws Exception;
	
	/**
	 * 验证手机号是否被注册过
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public PmUser queryPmUserByMobile(String mobile) throws Exception;
	
	/**
	 * 保存修改注册信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int saveRegisterMsg(PmUser user) throws Exception;
	
	/**
	 * 修改密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(String userId, String oldPwd, String newPwd) throws Exception;
	
	/**
	 * 修改手机号
	 * @param oldMobile
	 * @param newMobile
	 * @return
	 * @throws Exception
	 */
	public int updateLoginName(String oldMobile, String newMobile) throws Exception;
	
	/**
	 * 找回密码
	 * @param newPwd
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	public int findPwd(String newPwd, String loginName) throws Exception;
	
	/**
	 * 通过用户id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PmUser queryById(String id) throws Exception;
	
	/**
	 * 清理缓存
	 * @param user
	 * @throws Exception
	 */
	public void clearCache(PmUser user) throws Exception;
	
}
