package com.handany.bm.service;

import java.math.BigDecimal;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.handany.bm.model.BmQaLog;


/**
 * 
 * @author Administrator
 *
 */
public interface BmQaLogService {	
	int saveNewQaLog(BmQaLog log);
	
	int updateQaLog(BmQaLog log);
	
	BmQaLog queryQaLog(String id);
	
	PageInfo<BmQaLog> queryQaLogByUserId(String userId);
	
	PageInfo<BmQaLog> queryQaLogByTeacherId(String userId);
	
	BigDecimal queryQaLogTime(Map<String, Object> queryMap);
}
