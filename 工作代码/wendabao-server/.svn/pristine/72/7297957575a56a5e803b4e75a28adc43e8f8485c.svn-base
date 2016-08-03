package com.handany.bm.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmQaLogMapper;
import com.handany.bm.model.BmQaLog;
import com.handany.bm.service.BmQaLogService;

@Service
public class BmQaLogServiceImpl implements BmQaLogService {
    @Autowired
    private BmQaLogMapper bmQaLogMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public int saveNewQaLog(BmQaLog log) {
		return bmQaLogMapper.insertQaLog(log);
	}

	@Override
	public int updateQaLog(BmQaLog log) {
		return bmQaLogMapper.updateQaLog(log);
	}

	@Override
	public BmQaLog queryQaLog(String id) {
		return bmQaLogMapper.queryQaLog(id);
	}

	@Override
	public PageInfo<BmQaLog> queryQaLogByUserId(String userId) {
		PageUtil.startPage();
		List<BmQaLog> bmQaLogList = bmQaLogMapper.queryQaLogByUserId(userId);
		PageInfo<BmQaLog> pageList = new PageInfo<BmQaLog>(bmQaLogList);
		
		return pageList;
	}

	@Override
	public PageInfo<BmQaLog> queryQaLogByTeacherId(String userId) {
		PageUtil.startPage();
		List<BmQaLog> logList = bmQaLogMapper.queryQaLogByTeacherId(userId);
		PageInfo<BmQaLog> pageList = new PageInfo<BmQaLog>(logList);

		return pageList;
	}

	@Override
	public BigDecimal queryQaLogTime(Map<String, Object> queryMap) {
		return bmQaLogMapper.queryQaLogTime(queryMap);
	}

}
