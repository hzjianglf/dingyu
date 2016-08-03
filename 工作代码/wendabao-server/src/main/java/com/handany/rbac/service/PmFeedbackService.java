package com.handany.rbac.service;

import java.util.List;
import java.util.Map;

import com.handany.rbac.model.PmFeedback;

public interface PmFeedbackService {
	
	/**
	 * 保存反馈意见
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public int saveFeedback(PmFeedback feedback) throws Exception;
	
	/**
	 * 查询意见反馈
	 * @return
	 * @throws Exception
	 */
	public List<Map> queryList() throws Exception;
}
