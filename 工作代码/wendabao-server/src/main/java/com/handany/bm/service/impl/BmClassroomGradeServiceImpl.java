package com.handany.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.base.common.CommonUtils;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmClassroomGradeMapper;
import com.handany.bm.model.BmClassroomGrade;
import com.handany.bm.service.BmClassroomGradeService;

@Service
public class BmClassroomGradeServiceImpl implements BmClassroomGradeService {
    @Autowired
    private BmClassroomGradeMapper bmClassroomGradeMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public int deleteClassroomGradeByClassroomId(String classroomId) {
		return bmClassroomGradeMapper.deleteByClassroomId(classroomId);
	}

	@Override
	public int saveClassroomGrades(List<BmClassroomGrade> gradeList) {
		return bmClassroomGradeMapper.saveClassroomGrades(gradeList);
	}

}
