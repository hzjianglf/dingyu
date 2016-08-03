package com.handany.bm.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.handany.bm.model.BmAgent;


/**
 * 
 * @author Administrator
 *
 */
public interface BmAgentService {
	/**
	 * 查询代理商列表
	 * @param queryMap
	 * @return
	 */
	PageInfo<BmAgent> queryAgents(Map<String, Object> queryMap);
	
	/**
	 * 删除代理商
	 * @return
	 */
	int deleteAgent(String id);
	
	/**
	 * 通过id查询代理商信息
	 * @param id
	 * @return
	 */
	BmAgent queryById(String id);
	
	/**
	 * 通过用户id查询代理商信息
	 * @param userId
	 * @return
	 */
	BmAgent queryByUserId(String userId);
	
	/**
	 * 保存代理商信息
	 * @param agent
	 * @return
	 */
	int saveAgent(BmAgent agent);
}
