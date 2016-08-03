package com.handany.rbac.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.dao.PmUserMapper;
import com.handany.rbac.model.PmUser;
import com.handany.rbac.service.PmUserService;
@Service
public class PmUserServiceImpl implements PmUserService{
	
	@Autowired
	private PmUserMapper userMapper;
	
	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Override
	public PmUser login(String loginName, String password) throws Exception {
		Map map = new HashMap();
		map.put("loginName", loginName);
		map.put("password", password);
		

		return userMapper.login(map);
	}
	
	/**
	 * 验证手机号是否被注册过
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	@Override
	public PmUser queryPmUserByMobile(String mobile) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.queryPmUserByMobile(mobile) ;
	}

	/**
	 * 保存修改注册信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public int saveRegisterMsg(PmUser user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.saveRegisterMsg(user);
	}

	/**
	 * 修改密码
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updatePwd(String userId, String oldPwd, String newPwd) throws Exception {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("oldPwd", oldPwd);
		map.put("newPwd", newPwd);
		
		return userMapper.updatePwd(map);
	}
	
	/**
	 * 修改手机号
	 * @param oldMobile
	 * @param newMobile
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateLoginName(String oldMobile, String newMobile) throws Exception {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("oldMobile", oldMobile);
		map.put("newMobile", newMobile);
		return userMapper.updateLoginName(map);
	}

	/**
	 * 找回密码
	 * @param newPwd
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	@Override
	public int findPwd(String newPwd, String loginName) throws Exception {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("newPwd", newPwd);
		map.put("loginName", loginName);
		return userMapper.findPwd(map);
	}
	
	/**
	 * 通过用户id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public PmUser queryById(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.queryById(id);
	}
	
	/**
	 * 清理缓存
	 * @param user
	 * @throws Exception
	 */
	@Override
	public void clearCache(PmUser user) throws Exception {
		
		UserContextManager.resetLoginUser(user);
	}

}
