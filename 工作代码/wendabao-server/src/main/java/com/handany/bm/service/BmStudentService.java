package com.handany.bm.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.handany.bm.model.BmStudent;


/**
 * 
 * @author Administrator
 *
 */
public interface BmStudentService {	
	BmStudent queryByUserId(String userId);
	
	int save(BmStudent student);
	
	/**
	 * 查询学生列表
	 * @param queryMap
	 * @return
	 */
	PageInfo<BmStudent> queryStudents(Map<String, Object> queryMap);
	
	/**
	 * 根据id查询学生信息
	 * @param id
	 * @return
	 */
	BmStudent queryById(String id);
}
