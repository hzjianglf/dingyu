package com.handany.bm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handany.base.common.CommonUtils;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmClassroomCourseMapper;
import com.handany.bm.model.BmClassroomCourse;
import com.handany.bm.service.BmClassroomCourseService;

@Service
public class BmClassroomCourseServiceImpl implements BmClassroomCourseService {
    @Autowired
    private BmClassroomCourseMapper bmClassroomCourseMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public int deleteClassroomCoursesByClassroomId(String classroomId) {
		return bmClassroomCourseMapper.deleteByClassroomId(classroomId);
	}

	@Override
	public int saveClassroomCourses(List<BmClassroomCourse> courseList) {
		return bmClassroomCourseMapper.saveClassroomCourses(courseList);
	}

}
