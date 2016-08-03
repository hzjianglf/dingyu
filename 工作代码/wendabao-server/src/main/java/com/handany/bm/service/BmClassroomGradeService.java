package com.handany.bm.service;

import java.util.List;
import java.util.Map;

import com.handany.bm.model.BmClassroomGrade;


/**
 * 
 * @author Administrator
 *
 */
public interface BmClassroomGradeService {	
	int deleteClassroomGradeByClassroomId(String classroomId);
	
	int saveClassroomGrades(List<BmClassroomGrade> gradeList);
}
