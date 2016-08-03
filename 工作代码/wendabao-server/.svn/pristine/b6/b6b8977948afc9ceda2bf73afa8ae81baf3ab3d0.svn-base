package com.handany.bm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.handany.bm.model.BmQaTime;

public interface BmQaTimeMapper {
	List<BmQaTime> queryAvailableQaTimes(@Param("region1") String region1,
			@Param("region2") String region2, @Param("region3") String region3);
	
	List<BmQaTime> queryAllQaTimes(@Param("region1") String region1,
			@Param("region2") String region2, @Param("region3") String region3);
	
	int saveQaTime(BmQaTime qaTime);
	
	int deleteQaTime(String id);
}