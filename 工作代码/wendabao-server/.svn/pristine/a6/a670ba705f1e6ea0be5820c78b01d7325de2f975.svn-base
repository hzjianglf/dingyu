package com.handany.bm.dao;

import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmClassroom;

public interface BmClassroomMapper {
	BmClassroom queryClassroomByUserId(String userId);
	
	BmClassroom queryClassroomById(String id);
	
	int saveClassroom(BmClassroom classroom);
	
	
	int updateStatus(Map<String, String> map);
	
	
	List<BmClassroom> queryClassroom(Map<String, String> queryMap);
        
        List<BmClassroom> queryClassrooms(Map<String, Object> queryMap);
}