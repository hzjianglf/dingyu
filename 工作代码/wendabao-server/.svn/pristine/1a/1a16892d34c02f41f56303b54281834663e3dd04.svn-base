package com.handany.bm.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmQaLog;

public interface BmQaLogMapper {
	int insertQaLog(BmQaLog log);
	
	int updateQaLog(BmQaLog log);
	
	
	BmQaLog queryQaLog(String id);
	
	
	List<BmQaLog> queryQaLogByUserId(String userId);
	
	List<BmQaLog> queryQaLogByTeacherId(String userId);
	
	BigDecimal queryQaLogTime(Map<String, Object> map);
}