package com.handany.rbac.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.rbac.dao.PmFeedbackMapper;
import com.handany.rbac.model.PmFeedback;
import com.handany.rbac.service.PmFeedbackService;

@Service
public class PmFeedbackServiceImpl implements PmFeedbackService{
	@Autowired
	private PmFeedbackMapper feedbackMapper;

	/**
	 * 保存反馈意见
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	@Override
	public int saveFeedback(PmFeedback feedback) throws Exception {
		// TODO Auto-generated method stub
		return feedbackMapper.saveFeedback(feedback);
	}
	
	/**
	 * 查询意见反馈
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map> queryList() throws Exception {
		// TODO Auto-generated method stub
		return feedbackMapper.queryList();
	}

}
