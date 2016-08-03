package com.handany.bm.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.handany.bm.model.BmQaTime;


/**
 * 
 * @author Administrator
 *
 */
public interface BmQaTimeService {	
	List<BmQaTime> queryAvailableQaTimes(String region1, String region2, String region3);
	
	PageInfo<BmQaTime> queryAllQaTimes(String region1, String region2, String region3);
	
	int saveQaTime(BmQaTime qaTime);
	
	int deleteQaTime(String id);
}
