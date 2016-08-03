package com.handany.bm.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.ParamManager;
import com.handany.base.container.CacheContainer;
import com.handany.base.controller.BaseController;
import com.handany.base.model.LoginInfo;
import com.handany.base.push.ShortMessageManager;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmUser;
import com.handany.rbac.service.PmUserService;

import sun.security.provider.MD5;

import com.handany.bm.model.BmAgent;
import com.handany.bm.model.BmStudent;
import com.handany.bm.model.BmTeacher;
import com.handany.bm.service.BmAgentService;

@Controller
@RequestMapping(value = "/bm/agent")
public class BmAgentController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(BmAgentController.class);

	@Autowired
	private BmAgentService agentService;

	@Autowired
	private PmUserService userService;

	@Autowired
	private SerialNumberManager serialNumberManager;

	@RequestMapping("/queryAgent")
	public void queryAgent(@RequestParam(required = true) String start,String region1,String region2,String region3,String name) {
		
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("region1", region1);
			queryMap.put("region2", region2);
			queryMap.put("region3", region3);
			queryMap.put("name", name);
		try {
			PageInfo<BmAgent> page = agentService.queryAgents(queryMap);
			writeData(page);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询代理商列表失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0002");
			setMessage(msg);
		}
	}

	@RequestMapping("/getAgentDetail")
	public void getAgentDetail(@RequestParam(required = true) String id) {
		try {
			BmAgent agent = agentService.queryById(id);
			writeData(agent);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询代理商详情失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0001"); 
			setMessage(msg);
		}
	}
	@RequestMapping("/getAgentDetailByUserId")
	public void getAgentDetailByUserId(@RequestParam(required = true) String id) {
		try {
			BmAgent agent = agentService.queryByUserId(id);
			writeData(agent);
			setSuccess(true);
			setErrorCode("0000");
		} catch (Exception e) {
			String msg = "查询代理商详情失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0001"); 
			setMessage(msg);
		}
	}

	@RequestMapping("/saveAgent")
	public void saveAgent(BmAgent agent, String mobile) {
		PmUser user = null;
		if (agent.getId() == null) {
			// 添加
			try {
				user = userService.queryPmUserByMobile(mobile);
			} catch (Exception e) {
				String msg = "查找用户失败,请重试";
				logger.error(msg, e);
				setSuccess(false);
				setErrorCode("0001");
				setMessage(msg);
				return;
			}
			if (user == null) {
				//不存在该用户
				try {
					String id = serialNumberManager.nextSeqNo("pm_user");
					user = new PmUser();
					user.setId(id);
					user.setLoginName(mobile);
					user.setPassword(CommonUtils.MD5("111111"));
					String name = "agent" + mobile;
					user.setMobile(mobile);
					user.setName(name);
					// 代理商 3
					user.setUserType("3");
					user.setLastModified(CommonUtils.getCurrentDateStr());
				} catch (Exception e) {
					String msg = "生成UserId失败，请重试";
					logger.error(msg, e);
					setSuccess(false);
					setErrorCode("0002");
					setMessage(msg);
					return;
				}

				int count = 0;
				try {
					count = userService.saveRegisterMsg(user);
				} catch (Exception e) {
					String msg = "用户注册失败，请重试";
					logger.error(msg, e);
					setSuccess(false);
					setErrorCode("0003");
					setMessage(msg);
					return;
				}
				if (count <= 0) {
					setSuccess(false);
					setErrorCode("0004");
					setMessage("代理商注册失败");
					logger.debug("代理商注册失败");
					return;
				}
			} else {
				//该用户存在
				//该用户是否已经是代理商
				boolean isAgent = agentService.queryByUserId(user.getId()) == null ?
						false:true;
				if(isAgent){
					String msg = "该用户已经是代理商，请不要重复添加";
					setSuccess(false);
					setErrorCode("0008");
					setMessage(msg);
					logger.debug(msg);
					return;
				}
				user.setUserType("3");
				try {
					userService.saveRegisterMsg(user);
				} catch (Exception e) {
					setSuccess(false);
					setErrorCode("0005");
					setMessage("修改用户标志失败，请重试");
					logger.debug("修改用户标志失败，请重试");
					return;
				}
			}

			agent.setUserId(user.getId());
			try {
				agent.setId(serialNumberManager.nextSeqNo("bm_agent"));
			} catch (Exception e) {
				String msg = "生成AgentId失败";
				logger.error(msg, e);
				setSuccess(false);
				setErrorCode("0006");
				setMessage(msg);
			}
		}
		//
		try {
			int count = agentService.saveAgent(agent);
			if (count < 1) {
				String msg = "修改代理商失败";
				setSuccess(false);
				setErrorCode("0007");
				setMessage(msg);
				return;
			}
			String msg = "代理商保存成功";
			setSuccess(true);
			setErrorCode("0000");
			setMessage(msg);

		} catch (Exception e) {
			String msg = "修改代理商失败";
			logger.error(msg, e);
			setSuccess(false);
			setErrorCode("0008");
			setMessage(msg);
		}
	}

	@RequestMapping("/agentList")
	public String agentList() {
		return transView("/manage/user/agentList");
	}

	@RequestMapping("/agentDetail")
	public String agentDetail() {
		return transView("/manage/user/agentDetail");
	}

}