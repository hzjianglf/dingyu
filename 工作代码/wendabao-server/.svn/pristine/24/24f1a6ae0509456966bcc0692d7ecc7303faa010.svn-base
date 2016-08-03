package com.handany.bm.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.bm.model.BmQaLog;
import com.handany.bm.model.BmStudent;
import com.handany.bm.service.BmQaLogService;
import com.handany.bm.service.BmStudentService;

@Controller
@RequestMapping(value = "/bm/qa_log")
public class BmQaLogController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BmQaLogController.class);

	@Autowired
	private BmQaLogService qalogService;
	
	@Autowired
	private BmStudentService studentService;

	@Autowired
	private SerialNumberManager serialNumberManager;
	
	
	/**
	 * 存储新的辅导记录
	 * 
	 */
	@RequestMapping(value = "/saveNewLog")
	public void saveNewLog(
			@RequestParam(required = true) String studentId, 
			@RequestParam(required = true) String tokenId, 
			@RequestParam(required = true) String classroomId, 
			String qaType) {
		BmQaLog log = new BmQaLog();
		
		try {
			log.setId(serialNumberManager.nextSeqNo("bm_qa_log"));
			log.setLastModified(Calendar.getInstance().getTime());
			log.setQaClassroom(classroomId);
			log.setQaStart(Calendar.getInstance().getTime());
			PmUser user = UserContextManager.getLoginUser();
			log.setQaStudent(studentId);
			log.setQaTeacher(user.getId());
			log.setQaType(qaType);
			
			qalogService.saveNewQaLog(log);
			
			writeObject("qaLog", log);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			e.printStackTrace();
			setSuccess(false);
			setErrorCode("0002");
			setMessage("更新失败");
		}
	}
	
	
	/**
	 * 更新辅导记录
	 * 
	 */
	/**
	 * @param id
	 */
	@RequestMapping("/updateLog")
	public void updateLog(@RequestParam(required = true) String id) {
		try {
			BmQaLog log = qalogService.queryQaLog(id);
			if (log == null) {
				setSuccess(false);
				setErrorCode("0001");
				setMessage("系统查询不到对应的记录");
			} else {
				Date start = log.getQaStart();
				Date end = Calendar.getInstance().getTime();
				log.setQaEnd(end);
				int length = Math.round((end.getTime() - start.getTime()) / 1000);
				log.setQaLength(new BigDecimal(length));
				qalogService.updateQaLog(log);
				
				BmStudent student = studentService.queryByUserId(log.getQaStudent());
				
				if (student.getQaTime() == null) {
					student.setQaTime(new BigDecimal(ApplicationConfig.getConfig("student.defaultFreeQaTime"))); // 设置学生的初始免费时间
				}
				
				if (new BigDecimal(0).compareTo(student.getQaTime()) == -1) {
					BigDecimal qaTime = student.getQaTime();
					qaTime = qaTime.subtract(new BigDecimal(length));
					student.setQaTime(qaTime);
					studentService.save(student);
				}
				
				setSuccess(true);
				setErrorCode("0000");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setSuccess(false);
			setErrorCode("0002");
			setMessage("更新失败");
		}
	}
	
	/**
	 * 查询学生答疑记录
	 * @param tokenId
	 * @param start
	 */
	@RequestMapping("/getStudentQaLog")
	public void getStudentQaLog(@RequestParam(required = true) String tokenId,
			@RequestParam(required = true) String start) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			
			PageInfo<BmQaLog> dataList = qalogService.queryQaLogByUserId(user.getId());
			writeData(dataList);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询答疑记录失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	/**
	 * 查询教师答疑记录明细
	 * @param tokenId
	 * @param start
	 */
	@RequestMapping("/getTeacherQaLog")
	public void getTeacherQaLog(@RequestParam(required = true) String tokenId,
			@RequestParam(required = true) String start) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			
			PageInfo<BmQaLog> data = qalogService.queryQaLogByTeacherId(user.getId());
			writeData(data);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询答疑记录失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	/**
	 * 获取教师本周答疑时间
	 * @param tokenId
	 */
	@RequestMapping("/getQaLogTimeThisWeek")
	public void getQaLogTimeThisWeek(@RequestParam(required = true) String tokenId) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("qaTeacher", user.getId());
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			queryMap.put("qaStart", cal.getTime());
			BigDecimal qaLength = qalogService.queryQaLogTime(queryMap);
			
			if (qaLength == null) {
				qaLength = new BigDecimal(0);
			}
			
			writeObject("qaLength", qaLength);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询本周答疑记录失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	/**
	 * 获取教师本月答疑记录
	 * @param tokenId
	 */
	@RequestMapping("/getQaLogTimeThisMonth")
	public void getQaLogTimeThisMonth(@RequestParam(required = true) String tokenId) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("qaTeacher", user.getId());
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			queryMap.put("qaStart", cal.getTime());
			BigDecimal qaLength = qalogService.queryQaLogTime(queryMap);
			
			if (qaLength == null) {
				qaLength = new BigDecimal(0);
			}
			
			writeObject("qaLength", qaLength);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询本月答疑记录失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	/**
	 * 获取教师全部答疑记录时间
	 * @param tokenId
	 */
	@RequestMapping("/getQaLogTime")
	public void getQaLogTime(@RequestParam(required = true) String tokenId) {
		try {
			PmUser user = UserContextManager.getLoginUser();
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("qaTeacher", user.getId());
			
			// 获取全部答疑时间
			BigDecimal qaLengthAll = qalogService.queryQaLogTime(queryMap);
			
			if (qaLengthAll == null) {
				qaLengthAll = new BigDecimal(0);
			}
	
			// 获取本周答疑时间
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_WEEK, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			queryMap.put("qaStart", cal.getTime());
			BigDecimal qaLengthThisWeek = qalogService.queryQaLogTime(queryMap);
			
			if (qaLengthThisWeek == null) {
				qaLengthThisWeek = new BigDecimal(0);
			}
			
			// 获取本月答疑时间
			cal = Calendar.getInstance();
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			
			queryMap.put("qaStart", cal.getTime());
			BigDecimal qaLengthThisMonth = qalogService.queryQaLogTime(queryMap);
			
			if (qaLengthThisMonth == null) {
				qaLengthThisMonth = new BigDecimal(0);
			}
			
			
			writeObject("qaLengthAll", qaLengthAll);
			writeObject("qaLengthThisWeek", qaLengthThisWeek);
			writeObject("qaLengthThisMonth", qaLengthThisMonth);
			
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询本月答疑记录失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}
	
	
	
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(1452847676000l);
		System.out.println(cal.getTime());
	}
}