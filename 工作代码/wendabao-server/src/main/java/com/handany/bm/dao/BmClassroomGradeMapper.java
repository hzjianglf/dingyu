package com.handany.bm.dao;

import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmClassroomGrade;

public interface BmClassroomGradeMapper {
	int saveClassroomGrades(List<BmClassroomGrade> list);
	
	int deleteByClassroomId(String classroomId);
}