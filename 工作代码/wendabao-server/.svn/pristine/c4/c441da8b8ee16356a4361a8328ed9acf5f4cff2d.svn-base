package com.handany.rbac.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.handany.base.common.ApplicationConfig;
import com.handany.base.common.CommonUtils;
import com.handany.base.container.CacheContainer;
import com.handany.base.controller.BaseController;
import com.handany.base.model.LoginInfo;
import com.handany.base.push.ShortMessageManager;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.base.util.VerifyCode;
import com.handany.bm.model.BmAgent;
import com.handany.bm.model.BmClassroom;
import com.handany.bm.model.BmStudent;
import com.handany.bm.model.BmTeacher;
import com.handany.bm.service.BmAgentService;
import com.handany.bm.service.BmClassroomService;
import com.handany.bm.service.BmStudentService;
import com.handany.bm.service.BmTeacherService;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.rbac.service.PmUserService;

@Controller
@RequestMapping(value = "/pm/user")
public class UserController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private PmUserService userService;
	
	@Autowired
	private BmTeacherService teacherService;
	
	@Autowired
	private BmStudentService studentService;
	
	@Autowired
	private BmClassroomService classroomService;
	
	@Autowired
	private BmAgentService agentService ;
	

	@Autowired
	private SerialNumberManager serialNumberManager;

	/**
	 * 登录
	 * 
	 * @param loginName
	 * @param password
	 */
	@RequestMapping(value = "/login")
	public void login(String loginName, String password, String userType, LoginInfo loginInfo) {
		PmUser user = null;

		try {
			user = userService.login(loginName, password);

			if ((user != null) && (user.getUserType().equals(userType))) {

				if (loginInfo == null) {
					loginInfo = new LoginInfo();
				}
				
				if ("1".equals(userType)) {
					BmStudent student = studentService.queryByUserId(user.getId());
					loginInfo.setRegion1(student.getRegion1());
					loginInfo.setRegion2(student.getRegion2());
					loginInfo.setRegion3(student.getRegion3());
				} else if ("2".equals(userType)) {
					BmTeacher teacher = teacherService.queryByUserId(user.getId());
					loginInfo.setRegion1(teacher.getRegion1());
					loginInfo.setRegion2(teacher.getRegion2());
					loginInfo.setRegion3(teacher.getRegion3());
				} 
				
				user.setLoginInfo(loginInfo);
				String tokenId = UserContextManager.login(user);

				writeObject("tokenId", tokenId);
				writeObject("loginUser", user);
				writeUserInfo(user);
				setSuccess(true);
				setErrorCode("0000");
			} else {
				setSuccess(false);
				setErrorCode("0001");
				setMessage("用户名或密码错误");
				logger.debug("用户名或密码错误");
			}
		} catch (Exception e) {
			setSuccess(false);
			setErrorCode("0002");
			setMessage("登录失败");
			logger.debug("登录", e);
		}

	}

	/**
	 * 注册用户时获取验证码
	 * 
	 * @param loginName
	 * @param sign
	 *            1:注册 2：修改手机号
	 */
	@RequestMapping(value = "/getVerifyCode")
	public void getVerifyCode(@RequestParam(value = "loginName", required = true) String loginName,
			@RequestParam(value = "sign", required = true) String sign) {
		try {
			if (loginName.trim().length() == 0) {
				setSuccess(false);
				setMessage("手机号为空！");
			}

			PmUser user = userService.queryPmUserByMobile(loginName);

			String verifyCode = null;
			boolean rst = true;
			// 验证码超时时间
			int expire = 2;

			if ("1".equals(sign)) {
				if (null == user) {
					if ("T".equals(ApplicationConfig.getConfig("isTest"))) {
						verifyCode = "123456";
						rst = true;
					} else {
						verifyCode = VerifyCode.nextRandom();
						// 发送短信验证码
						rst = ShortMessageManager.send(ApplicationConfig.getConfig("shopmsg.user.randomCode"),
								loginName, verifyCode, "2", String.valueOf(expire));
					}
					if (rst) {
						CacheContainer.put(com.handany.rbac.common.Constants.CACHE_MSG_VERIFYCODE + loginName,
								verifyCode, com.handany.base.common.Constants.SECONDS_MINUTE * expire);
						writeObject("verifyCode", verifyCode);
						setMessage("注册成功！");
						setSuccess(true);
					} else {
						setMessage("短信发送失败");
						setSuccess(false);
						logger.debug("短信发送失败");
					}

				} else {
					setSuccess(false);
					setErrorCode("0003");
					setMessage("该手机号已注册。如果您忘记密码，可以试试【找回密码】功能");
					logger.debug("该手机号已注册");
				}
			} else if ("2".equals(sign)) {
				if (null != user) {

					if ("T".equals(ApplicationConfig.getConfig("isTest"))) {
						verifyCode = "123456";
						rst = true;
					} else {
						verifyCode = VerifyCode.nextRandom();
						// 发送短信验证码
						rst = ShortMessageManager.send(ApplicationConfig.getConfig("shopmsg.user.randomCode"),
								loginName, verifyCode, String.valueOf(expire));
					}

					if (rst) {
						CacheContainer.put(com.handany.rbac.common.Constants.CACHE_MSG_VERIFYCODE + loginName,
								verifyCode, com.handany.base.common.Constants.SECONDS_MINUTE * expire);
						writeObject("verifyCode", verifyCode);
						setMessage("验证码发送成功！");
						setSuccess(true);
					} else {
						setMessage("短信发送失败");
						setSuccess(false);
						logger.debug("短信发送失败");
					}

				} else {
					setSuccess(false);
					setErrorCode("0003");
					setMessage("不存在该用户");
					logger.debug("不存在该用户");
				}
			}

		} catch (Exception e) {
			setSuccess(false);
			setErrorCode("0002");
			setMessage("获取验证码失败");
			logger.debug("获取验证码", e);
		}

	}

	/**
	 * 用户注册
	 * 
	 * @param loginName
	 * @param password
	 * @param code
	 */
	@RequestMapping(value = "/register")
	public void register(String loginName, String password, String code, String userType) {

		try {
			String verifyCode = CacheContainer.get(com.handany.rbac.common.Constants.CACHE_MSG_VERIFYCODE + loginName,
					String.class);
			if (code.equals(verifyCode)) {
				PmUser user = new PmUser();
				String id = serialNumberManager.nextSeqNo("pm_user");
				user.setId(id);
				user.setLoginName(loginName);
				user.setPassword(password);
				String name = "cy" + loginName.replace(loginName.substring(3, 8), "*****");
				user.setMobile(loginName);
				user.setName(name);
				user.setUserType(userType);
				user.setLastModified(CommonUtils.getCurrentDateStr());
				int count = userService.saveRegisterMsg(user);
				if (count > 0) {
					// 生成tokenId和放入缓存中
					String tokenId = UserContextManager.login(user);
					
					if ("2".equals(userType)) {
						BmTeacher teacher = new BmTeacher();
						teacher.setId(serialNumberManager.nextSeqNo("bm_teacher"));
						teacher.setUserId(user.getId());
						teacher.setLastModified(Calendar.getInstance().getTime());
						teacher.setLastUser(user.getId());
						teacher.setStatus("1");
						teacherService.saveTeacherInfo(teacher);
					} else if ("1".equals(userType)) {
						BmStudent student = new BmStudent();
						student.setId(serialNumberManager.nextSeqNo("bm_student"));
						student.setUserId(user.getId());
						student.setLastModified(Calendar.getInstance().getTime());
						student.setStatus("1");
					}
					
					writeObject("tokenId", tokenId);
					writeObject("user", user);
					writeUserInfo(user);
					setSuccess(true);
					setErrorCode("0000");
					setMessage("注册成功！");
					logger.debug("注册成功！");
				} else {
					setSuccess(false);
					setErrorCode("0001");
					setMessage("注册失败");
					logger.debug("注册失败");
				}
			} else {
				setSuccess(false);
				setErrorCode("0002");
				setMessage("验证码错误");
			}

		} catch (Exception e) {
			setSuccess(false);
			setErrorCode("0002");
			setMessage("注册失败");
			logger.debug("注册", e);
		}

	}

	/**
	 * 登录界面
	 */
	@RequestMapping(value = "/loginFace")
	public String loginFace() {
		return transView("/login");
	}

	/**
	 * 注册界面
	 */
	@RequestMapping(value = "/registerFace")
	public String registerFace() {
		setSuccess(true);
		return transView("/register");
	}

	/**
	 * 控制台界面
	 */
	@RequestMapping(value = "/manage")
	public String manageFace() {
		PmUser user = UserContextManager.getLoginUser();

		// try{
		// BmBackManage manager = managerService.queryBuUserId(user.getId());
		// if(manager != null){
		// writeObject("managerStatus", manager.getManageStatus());
		// String role = manager.getRole();
		//
		// writeObject("role", role);
		// }
		// }catch(Exception e){
		// logger.error("控制台界面", e);
		// }

		writeObject("user", user);
		return transView("/manage/manage");
	}

	/**
	 * 修改密码界面
	 */
	@RequestMapping(value = "/password")
	public String password() {

		PmUser user = UserContextManager.getLoginUser();

		writeObject("user", user);

		return transView("/manage/user/password");
	}

	/**
	 * 用户基本信息界面
	 */
	@RequestMapping(value = "/userCenter")
	public String userCenter() {

		PmUser user = UserContextManager.getLoginUser();
		
		try {
			user = userService.queryById(user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		writeObject("user", user);
		System.out.println(transView("/manage/user/center"));
		return transView("/manage/user/center");
	}

	/**
	 * 更换手机号界面
	 */
	@RequestMapping(value = "/mobile")
	public String mobile() {

		PmUser user = UserContextManager.getLoginUser();

		writeObject("user", user);

		return transView("/manage/user/mobile");
	}

	/**
	 * 保存用户基本信息
	 * 
	 * @param mobile
	 * @param name
	 * @param gender
	 */
	@RequestMapping(value = "/saveBaseMsg")
	public void saveBaseMsg(String name) {

		try {
			// String tokenId = UserContextManager.getTokenId();

			PmUser user = UserContextManager.getLoginUser();
			user.setName(name);

			int count = userService.saveRegisterMsg(user);
			if (count > 0) {

				UserContextManager.resetLoginUser(user);

				// writeObject("tokenId", tokenId);
				// writeObject("user", user);
				setSuccess(true);
				setErrorCode("0000");
				setMessage("保存用户基本信息成功！");
				logger.debug("保存用户基本信息成功！");
			} else {
				setSuccess(false);
				setErrorCode("0001");
				setMessage("保存用户基本信息失败");
				logger.debug("保存用户基本信息失败");
			}

		} catch (Exception e) {
			setSuccess(false);
			setErrorCode("0002");
			setMessage("保存用户基本信息失败");
			logger.debug("保存用户基本信息失败", e);
		}

	}

	/**
	 * 修改密码
	 * 
	 * @param oldPwd
	 * @param newPwd
	 * @param tokenId
	 * @param userId
	 */
	@RequestMapping(value = "/saveNewPwd")
	public void saveNewPwd(String oldPwd, String newPwd, String tokenId, String userId) {

		try {

			int count = userService.updatePwd(userId, oldPwd, newPwd);
			if (count > 0) {
				writeObject("tokenId", tokenId);
				setSuccess(true);
				setErrorCode("0000");
				setMessage("修改密码成功");
				logger.debug("修改密码成功！");
			} else {
				setSuccess(false);
				setErrorCode("0001");
				setMessage("旧密码错误");
				logger.debug("旧密码错误");
			}

		} catch (Exception e) {
			setSuccess(false);
			setErrorCode("0002");
			setMessage("修改密码异常失败");
			logger.debug("修改密码异常失败", e);
		}

	}

	/**
	 * 修改登录名手机号
	 * 
	 * @param oldMobile
	 * @param verifyCode
	 * @param tokenId
	 * @param newMobile
	 */
	@RequestMapping(value = "/saveNewMobile")
	public void saveNewMobile(String oldMobile, String verifyCode, String tokenId, String newMobile) {

		try {
			String code = CacheContainer.get(com.handany.rbac.common.Constants.CACHE_MSG_VERIFYCODE + oldMobile,
					String.class);
			if (code.equals(verifyCode)) {
				int count = userService.updateLoginName(oldMobile, newMobile);
				if (count > 0) {

					setSuccess(true);
					setErrorCode("0000");
					setMessage("修改登录名手机号成功");
					logger.debug("修改登录名手机号成功！");
				} else {
					setSuccess(false);
					setErrorCode("0001");
					setMessage("修改登录名手机号失败");
					logger.debug("修改登录名手机号失败");
				}

			} else {
				setSuccess(false);
				setErrorCode("0002");
				setMessage("验证码错误");
				logger.debug("验证码错误");
			}

		} catch (Exception e) {
			setSuccess(false);
			setErrorCode("0002");
			setMessage("改手机号已被注册过");
			logger.debug("修改登录名手机号异常失败-改手机号已被注册过", e);
		}

	}

	/**
	 * 忘记密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/forgetPwd")
	public String forgetPwd() {
		return transView("/forgetPwd");
	}

	/**
	 * 用户注册
	 * 
	 * @param loginName
	 * @param password
	 * @param code
	 */
	@RequestMapping(value = "/open/updateForgetPwd")
	public void updateForgetPwd(String loginName, String password, String code) {

		try {
			String verifyCode = CacheContainer.get(com.handany.rbac.common.Constants.CACHE_MSG_VERIFYCODE + loginName,
					String.class);
			if (code.equals(verifyCode)) {

				int count = userService.findPwd(password, loginName);
				if (count > 0) {

					setSuccess(true);
					setErrorCode("0001");
					setMessage("找回密码成功！");
					logger.debug("找回密码成功！");
				} else {
					setSuccess(false);
					setErrorCode("0001");
					setMessage("找回密码失败");
					logger.debug("找回密码失败");
				}
			} else {
				setSuccess(false);
				setErrorCode("0001");
				setMessage("验证码输入有误，请核对");
				logger.debug("找回密码失败");
			}

		} catch (Exception e) {
			setSuccess(false);
			setErrorCode("0002");
			setMessage("找回密码失败");
			logger.debug("找回密码", e);
		}

	}

	/**
	 * 通过电话联系方式查询用户信息
	 * 
	 * @param mobile
	 */
	@RequestMapping(value = "/queryUserByMobile")
	public void queryUserByMobile(String mobile) {
		try {
			PmUser user = userService.queryPmUserByMobile(mobile);
			writeObject("data", user);
			setErrorCode("0000");
			setMessage("查询用户信息成功！");
			setSuccess(true);
			logger.debug("查询用户信息成功！");

		} catch (Exception e) {
			setErrorCode("0002");
			setMessage("查询用户信息失败！");
			setSuccess(false);
			logger.error("查询用户信息失败！", e);
		}

	}

	/**
	 * 必须登录
	 */
	@RequestMapping(value = "/mustlogin")
	public String mustLogin() {
		setErrorCode("NEEDLOGIN");
		setMessage("请登录！");
		setSuccess(false);
		return transView("/login");
	}

	/**
	 * 验证tokenId是否有效
	 * 
	 * @param tokenId
	 */
	@RequestMapping(value = "/open/validateTokenId")
	public void validateTokenId(@RequestParam(required = true, value = "tokenId") String tokenId) {
		boolean rst = UserContextManager.validateTokenId(tokenId);
		if (rst) {
			writeObject("valid", "T");
		} else {
			writeObject("valid", "F");
		}
		setSuccess(true);
	}

	@RequestMapping(value = "logout")
	public void logout() {
		UserContextManager.logout();
		setSuccess(true);
	}

	
	public void writeUserInfo(PmUser user) {
		if ("1".equals(user.getUserType())) {
			BmStudent sInfo = studentService.queryByUserId(user.getId());
			writeObject("studentInfo", sInfo);
		} else if ("2".equals(user.getUserType())) {
			// 获取教师信息
			BmTeacher tInfo = teacherService.queryByUserId(user.getId());
			writeObject("teacherInfo", tInfo);
			
			// 获取教室信息
			BmClassroom classroomInfo = classroomService.queryClassroomByUserId(user.getId());
			writeObject("classroomInfo", classroomInfo);
		}
	}
}
