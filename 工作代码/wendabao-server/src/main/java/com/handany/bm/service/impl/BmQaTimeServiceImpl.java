package com.handany.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmQaTimeMapper;
import com.handany.bm.model.BmQaTime;
import com.handany.bm.service.BmQaTimeService;

@Service
public class BmQaTimeServiceImpl implements BmQaTimeService {
    @Autowired
    private BmQaTimeMapper bmQaTimeMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public List<BmQaTime> queryAvailableQaTimes(String region1, String region2, String region3) {
		return bmQaTimeMapper.queryAvailableQaTimes(region1, region2, region3);
	}

	@Override
	public PageInfo<BmQaTime> queryAllQaTimes(String region1, String region2, String region3) {
		PageUtil.startPage();
		List<BmQaTime> list = bmQaTimeMapper.queryAllQaTimes(region1, region2, region3);
		PageInfo<BmQaTime> page = new PageInfo<BmQaTime>(list);
		return page;
	}

	@Override
	public int saveQaTime(BmQaTime qaTime) {
		if (qaTime.getId() == null) {
			try {
				qaTime.setId(serialNumberManager.nextSeqNo("bm_qa_time"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return bmQaTimeMapper.saveQaTime(qaTime);
	}

	@Override
	public int deleteQaTime(String id) {
		return bmQaTimeMapper.deleteQaTime(id);
	}
}
