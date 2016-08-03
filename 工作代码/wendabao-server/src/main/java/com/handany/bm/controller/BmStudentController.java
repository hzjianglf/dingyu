package com.handany.bm.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.ApplicationConfig;
import com.handany.base.controller.BaseController;
import com.handany.base.model.LoginInfo;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.bm.model.BmStudent;
import com.handany.bm.service.BmStudentService;

@Controller
@RequestMapping(value = "/bm/student")
public class BmStudentController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BmStudentController.class);

	@Autowired
	private BmStudentService studentService;
	

	@Autowired
	private SerialNumberManager serialNumberManager;
	
	@RequestMapping("/saveStudent")
	public void saveStudent(@RequestParam(required = true) String tokenId, String name,
			String region1, String region2, String region3,
			String region1Txt, String region2Txt, String region3Txt,
			String grade) {
		
		try {
			PmUser user = UserContextManager.getLoginUser();
			BmStudent student = studentService.queryByUserId(user.getId());
			if (student == null) {
				student = new BmStudent();
				student.setId(serialNumberManager.nextSeqNo("bm_student"));
				student.setQaTime(new BigDecimal(ApplicationConfig.getConfig("student.defaultFreeQaTime"))); // 设置学生的初始免费时间
			}
			
			student.setGrade(grade);
			student.setName(name);
			student.setRegion1(region1);
			student.setRegion2(region2);
			student.setRegion3(region3);
			student.setRegion1Txt(region1Txt);
			student.setRegion2Txt(region2Txt);
			student.setRegion3Txt(region3Txt);
			student.setUserId(user.getId());
			student.setLastModified(Calendar.getInstance().getTime());
			studentService.save(student);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			logger.error("保存学生信息失败", e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage("保存学生信息失败");
		}
	}
	
	/**
	 * 查询可用答疑时间
	 * @param tokenId
	 */
	@RequestMapping("/getQaTime")
	public void getQaTime(@RequestParam(required = true) String tokenId) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			
			BmStudent student = studentService.queryByUserId(user.getId());
			writeObject("qaTime", student.getQaTime());
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询可用答疑时间失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	/**
	 * 得到学生列表
	 */
	@RequestMapping("/getStudentList")
	public String getStudentList(){
		return transView("/manage/student/studentsMgmt");
	}
	/**
	 * 得到学生详情
	 */
	@RequestMapping("/getStudentDetail")
	public String getStudentDetail(){
		return transView("/manage/student/studentDetail");
	}
	
	/**
	 * 查询学生列表
	 * @param start      分页起始记录数
	 * @param region1         区域编码1
	 * @param region2         区域编码2
	 * @param region3         区域编码3
	 * @param name       用户名称
	 * @param mobile     用户登录手机号
	 */
	@RequestMapping("/queryStudents")
	public void queryStudents(@RequestParam(required = true) String start,
			String region1, String region2, String region3,
			String name, String mobile) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			if ("3".endsWith(user.getUserType())) {
				LoginInfo loginInfo = user.getLoginInfo();
				
				if (loginInfo.getRegion1() != null) {
					region1 = loginInfo.getRegion1();
				}
				
				if (loginInfo.getRegion2() != null) {
					region2 = loginInfo.getRegion2();
				}
				
				if (loginInfo.getRegion3() != null) {
					region3 = loginInfo.getRegion3();
				}
			}
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("region1", region1);
			queryMap.put("region2", region2);
			queryMap.put("region3", region3);
			queryMap.put("name", name);
			queryMap.put("mobile", mobile);
			
			PageInfo<BmStudent> page = studentService.queryStudents(queryMap);
			writeData(page);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询学生列表失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	/**
	 * 根据id查询学生信息
	 * @param id
	 */
	@RequestMapping("queryById")
	public void queryById(@RequestParam(required = true) String id) {
		try {
			BmStudent student = studentService.queryById(id);
			writeObject("student", student);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询学生详情失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
}