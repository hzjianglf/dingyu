package com.handany.bm.dao;

import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmTeacher;

public interface BmTeacherMapper {
	BmTeacher queryByUserId(String userId);
	
	BmTeacher queryById(String id);
	
	int saveTeacher(BmTeacher teacher);
	
	int saveApprovalInfo(BmTeacher teacher);
	
	List<BmTeacher> queryTeachers(Map<String, Object> map);
}