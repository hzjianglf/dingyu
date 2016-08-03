package com.handany.bm.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.handany.bm.model.BmTeacher;


/**
 * 
 * @author Administrator
 *
 */
public interface BmTeacherService {	
	BmTeacher queryByUserId(String userId);
	
	BmTeacher queryById(String id);
	
	int saveTeacherInfo(BmTeacher teacher);
	
	/**
	 * 保存教师审批信息
	 * @param userId
	 * @param approvalInfo
	 * @return
	 */
	int saveApprovalInfo(String userId, String status, String approvalInfo);
	
	/**
	 * 查询教师列表
	 * @param queryMap
	 * @return
	 */
	PageInfo<BmTeacher> queryTeachers(Map<String, Object> queryMap);
}
