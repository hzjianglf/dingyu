package com.handany.bm.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.bm.dao.BmTeacherMapper;
import com.handany.bm.model.BmTeacher;
import com.handany.bm.service.BmTeacherService;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;

@Service
public class BmTeacherServiceImpl implements BmTeacherService {
    @Autowired
    private BmTeacherMapper bmTeacherMapper;

    @Autowired
    private SerialNumberManager serialNumberManager;

	@Override
	public BmTeacher queryByUserId(String userId) {
		return bmTeacherMapper.queryByUserId(userId);
	}

	@Override
	public int saveTeacherInfo(BmTeacher teacher) {
		if (null == teacher.getId()) {
			try {
				serialNumberManager.nextSeqNo("bm_teacher");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		teacher.setLastModified(Calendar.getInstance().getTime());
		return bmTeacherMapper.saveTeacher(teacher);
	}

	@Override
	public int saveApprovalInfo(String userId, String status, String approvalInfo) {
		BmTeacher teacher = new BmTeacher();
		teacher.setUserId(userId);
		teacher.setStatus(status);
		teacher.setApprovalInfo(approvalInfo);
		
		PmUser user = UserContextManager.getLoginUser();
		teacher.setLastUser(user.getId());
		teacher.setLastModified(CommonUtils.getCurrentDate());
		return bmTeacherMapper.saveApprovalInfo(teacher);
	}

	@Override
	public BmTeacher queryById(String id) {
		return bmTeacherMapper.queryById(id);
	}

	@Override
	public PageInfo<BmTeacher> queryTeachers(Map<String, Object> queryMap) {
		PageUtil.startPage();
		List<BmTeacher> teachers = bmTeacherMapper.queryTeachers(queryMap);
		PageInfo<BmTeacher> teachersPage = new PageInfo<BmTeacher>(teachers);
		
		return teachersPage;
	}
}
