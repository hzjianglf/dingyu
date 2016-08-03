package com.handany.bm.dao;


import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmStudent;

public interface BmStudentMapper {
	BmStudent queryByUserId(String userId);
	
	int save(BmStudent student);
	
	List<BmStudent> queryStudents(Map<String, Object> queryMap);
	
	BmStudent queryById(String id);
}