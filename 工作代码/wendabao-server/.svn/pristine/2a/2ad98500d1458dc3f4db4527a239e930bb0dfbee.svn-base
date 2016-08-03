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
import com.handany.base.controller.BaseController;
import com.handany.base.model.LoginInfo;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.bm.model.BmClassroom;
import com.handany.bm.model.BmTeacher;
import com.handany.bm.service.BmClassroomService;
import com.handany.bm.service.BmFavoriteService;
import com.handany.bm.service.BmTeacherService;

@Controller
@RequestMapping(value = "/bm/teacher")
public class BmTeacherController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BmTeacherController.class);

	@Autowired
	private BmTeacherService teacherService;
	
	@Autowired
	private BmClassroomService classroomService;
	
	@Autowired
	private BmFavoriteService favoriteService;

	@Autowired
	private SerialNumberManager serialNumberManager;
	
	
	/**
	 * 保存教师信息
	 * 
	 */
	@RequestMapping("/saveTeacher")
	public void saveTeacher(String id, 
			String name,            /* 姓名  */
			String gender,          /* 性别 */
			String age,             /* 教师年龄 */
			String school,          /* 教师所在学校 */
			String certificateNo,   /* 教师资格证编码 */
			String certificatePic,  /* 资格证图片 */
			String course,          /* 教学科目 */
			String intro,           /* 教师介绍 */
			String schoolAge,       /* 教师教龄 */
			String userPic,         /* 教师头像 */
			String grade,           /* 教师级别 */
			String resume,          /* 执教经历 */
			String region1,
			String region2,
			String region3,
			String region1Txt,
			String region2Txt,
			String region3Txt) {
		try {
			BmTeacher teacher = null;
			PmUser user = UserContextManager.getLoginUser();
			if ((id == null) || (id.trim().length() == 0)) {
				teacher = teacherService.queryByUserId(user.getId());
				if (teacher == null) {
					teacher = new BmTeacher();
					teacher.setId(serialNumberManager.nextSeqNo("bm_teacher"));
					teacher.setUserId(user.getId());
				}
			} else {
				teacher = teacherService.queryById(id);
			}
			
			BmClassroom classroom = null;
			if ((name != null) && (name.trim().length() > 0)) {
				teacher.setName(name.trim());
				
				classroom = classroomService.queryClassroomByUserId(teacher.getUserId());
				if (classroom != null) {
					classroom.setName(name + "教室");
				}
			}
			
			if ((gender != null) && (gender.trim().equals("F") || gender.trim().equals("M"))) {
				teacher.setGender(gender.trim());
			}
			
			if ((age != null) && (age.trim().length() > 0)) {
				teacher.setAge(age.trim());
			}
			
			if ((school != null) && (school.trim().length() > 0)) {
				teacher.setSchool(school.trim());
			}
			
			if ((certificateNo != null) && (certificateNo.trim().length() > 0)) {
				teacher.setCertificateNo(certificateNo.trim());
			}
			
			if ((certificatePic != null) && (certificatePic.trim().length() > 0)) {
				teacher.setCertificatePic(certificatePic.trim());
			}
			
			if ((course != null) && (course.trim().length() > 0)) {
				teacher.setCourse(course.trim());
			}
			
			if ((intro != null) && (intro.trim().length() > 0)) {
				teacher.setIntro(intro.trim());
			}
			
			if ((schoolAge != null) && (schoolAge.trim().length() > 0)) {
				teacher.setSchoolAge(new BigDecimal(schoolAge));
			}
			
			if ((userPic != null) && (userPic.trim().length() > 0)) {
				teacher.setUserPic(userPic.trim());
			}
			
			if ((grade != null) && (grade.trim().length() > 0)) {
				teacher.setGrade(grade.trim());
			}
			
			if ((resume != null) && (resume.trim().length() > 0)) {
				teacher.setResume(resume.trim());
				
				if ("1".equals(teacher.getStatus()) || "5".equals(teacher.getStatus())) {
					teacher.setStatus("3");
				}
			}
			
			if ((region1 != null) && (region1.trim().length() > 0)) {
				teacher.setRegion1(region1.trim());
			}
			
			if ((region2 != null) && (region2.trim().length() > 0)) {
				teacher.setRegion2(region2.trim());
			}
			
			if ((region3 != null) && (region3.trim().length() > 0)) {
				teacher.setRegion3(region3.trim());
			}
			
			if ((region1Txt != null) && (region1Txt.trim().length() > 0)) {
				teacher.setRegion1Txt(region1Txt.trim());
			}
			
			if ((region2Txt != null) && (region2Txt.trim().length() > 0)) {
				teacher.setRegion2Txt(region2Txt.trim());
			}
			
			if ((region3Txt != null) && (region3Txt.trim().length() > 0)) {
				teacher.setRegion3Txt(region3Txt.trim());
			}

			teacher.setLastModified(Calendar.getInstance().getTime());
			teacher.setLastUser(user.getId());
			
			teacherService.saveTeacherInfo(teacher);
			if (classroom != null) {
				classroomService.saveClassroom(classroom);
			}
			
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception ex) {
			logger.error("出现异常", ex);
			setSuccess(false);
			setErrorCode("0002");
			setMessage("保存教师信息失败");
		}
	}
	
	@RequestMapping("/saveApprovalInfo")
	public void saveApprovalInfo(
			@RequestParam(required = true) String tokenId,
			@RequestParam(required = true) String teacherUserId,
			@RequestParam(required = true) String status, /* 审批结果：1：通过， 0：未通过 */
			@RequestParam(required = true) String approvalInfo) {
		try {
			if ("1".equals(status.trim())) {
				teacherService.saveApprovalInfo(teacherUserId, "4", approvalInfo);
			} else {
				teacherService.saveApprovalInfo(teacherUserId, "5", approvalInfo);
			}
			
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			logger.error("保存审批信息失败", e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage("保存审批信息失败");
		}
	}
	
	
	@RequestMapping("/getTeacher")
	public void getTeacher(@RequestParam(required = true) String tokenId) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			BmTeacher teacher = teacherService.queryByUserId(user.getId());
			writeObject("teacherInfo", teacher);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception ex) {
			logger.error("获取教师信息失败", ex);
			setSuccess(false);
			setErrorCode("0002");
			setMessage("获取教师信息失败");
		}
	}
	
	@RequestMapping("/getTeacherByUserId")
	public void getTeacherByUserId(@RequestParam(required = true) String userId) {
		try {
			BmTeacher teacher = teacherService.queryByUserId(userId);
			if (teacher == null) {
				String msg = "未查询到符合要求的教师信息";
				logger.error(msg + " 参数：userId=" + userId);
				setSuccess(false);
				setErrorCode("0001");
				setMessage(msg);
			} else {
				writeObject("teacherInfo", teacher);
				
				PmUser user = UserContextManager.getLoginUser();
				int count = favoriteService.isInFavorites(user.getId(), userId);
				if (count > 0) {
					writeObject("isInFavorites", true);
				} else {
					writeObject("isInFavorites", false);
				}
				
				setSuccess(true);
				setErrorCode("0000");
			}
		} catch (Exception e) {
			String msg = "查询教师信息失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	@RequestMapping("/queryTeachers")
	public void queryTeachers(
			@RequestParam(required = true) String tokenId,
			String status,
			String region1,
			String region2,
			String region3,
			String name,
			String mobile,
			@RequestParam(required = true) String start) {
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
			
			if ((status != null) && (status.trim().length() > 0)) {
				queryMap.put("status", status);
			}
			
			queryMap.put("region1", region1);
			queryMap.put("region2", region2);
			queryMap.put("region3", region3);
			queryMap.put("name", name);
			queryMap.put("mobile", mobile);
			
			PageInfo<BmTeacher> page = teacherService.queryTeachers(queryMap);
			
			writeData(page);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询教师列表失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	/**
	 * 得到教室列表
	 */
	@RequestMapping("/getTeacherList")
	public String getTeacherList(){
		return transView("/manage/teacher/teachersMgmt");
	}
	/**
	 * 得到教师详情
	 */
	@RequestMapping("/getTeacherDetail")
	public String getStudentDetail(){
		return transView("/manage/teacher/teacherDetail");
	}
	
	@RequestMapping("/queryById")
	public void queryById(@RequestParam(required = true) String id) {
		try {
			BmTeacher teacher = teacherService.queryById(id);
			writeObject("teacher", teacher);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询教师详情失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
}