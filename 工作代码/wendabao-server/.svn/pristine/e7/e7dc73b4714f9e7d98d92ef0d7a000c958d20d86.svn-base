package com.handany.bm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmStudentMapper;
import com.handany.bm.model.BmStudent;
import com.handany.bm.service.BmStudentService;

@Service
public class BmStudentServiceImpl implements BmStudentService {
    @Autowired
    private BmStudentMapper bmStudentMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public BmStudent queryByUserId(String userId) {
		return bmStudentMapper.queryByUserId(userId);
	}

	@Override
	public int save(BmStudent student) {
		if (student.getId().isEmpty()) {
			try {
				serialNumberManager.nextSeqNo("bm_student");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		student.setLastModified(CommonUtils.getCurrentDate());
		
		return bmStudentMapper.save(student);
	}

	@Override
	public PageInfo<BmStudent> queryStudents(Map<String, Object> queryMap) {
		PageUtil.startPage();
		List<BmStudent> list = bmStudentMapper.queryStudents(queryMap);
		PageInfo<BmStudent> page = new PageInfo<BmStudent>(list);
		
		return page;
	}

	@Override
	public BmStudent queryById(String id) {
		return bmStudentMapper.queryById(id);
	}

}
