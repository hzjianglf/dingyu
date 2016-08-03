package com.handany.bm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmAgentMapper;
import com.handany.bm.model.BmAgent;
import com.handany.bm.service.BmAgentService;

@Service
public class BmAgentServiceImpl implements BmAgentService {
    @Autowired
    private BmAgentMapper bmAgentMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public PageInfo<BmAgent> queryAgents(Map<String, Object> queryMap) {
		PageUtil.startPage();
		List<BmAgent> agentList = bmAgentMapper.queryAgent(queryMap);
		PageInfo<BmAgent> pageInfo = new PageInfo<BmAgent>(agentList);
		
		return pageInfo;
		
	}

	@Override
	public int deleteAgent(String id) {
		return bmAgentMapper.deleteAgent(id);
	}

	@Override
	public BmAgent queryById(String id) {
		return bmAgentMapper.queryById(id);
	}

	@Override
	public BmAgent queryByUserId(String userId) {
		return bmAgentMapper.queryByUserId(userId);
	}

	@Override
	public int saveAgent(BmAgent agent) {
		if (null == agent.getId()) {
			try {
				agent.setId(serialNumberManager.nextSeqNo("bm_agent"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		agent.setLastModified(CommonUtils.getCurrentDate());
		return bmAgentMapper.saveAgent(agent);
	}

}
