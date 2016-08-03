package com.handany.rbac.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.handany.base.common.CommonUtils;
import com.handany.base.common.PageUtil;
import com.handany.base.controller.BaseController;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;
import com.handany.rbac.model.PmFeedback;
import com.handany.rbac.model.PmUser;
import com.handany.rbac.service.PmFeedbackService;

@Controller
@RequestMapping("/pm/feedback")
public class PmFeedbackController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private PmFeedbackService feedBackService;

	@Autowired
	private SerialNumberManager serialNumberManager;
	
	@RequestMapping(value = "/feedback")
	public String feedback() {
		return transView("/manage/user/writeFeedback");
	}
	
	/**
	 * 保存意见反馈
	 * @param opinion
	 * @param tokenId
	 */
	@RequestMapping( value="/save")
	public void saveFeedback(String opinion){
		try{
				
			PmUser user = UserContextManager.getLoginUser();
			
			PmFeedback feedback = new PmFeedback();	
			
			String id = serialNumberManager.nextSeqNo("pm_feedback");
			feedback.setId(id);
			feedback.setUserId(user.getId());
			feedback.setOpinion(opinion);
			feedback.setLastModified(CommonUtils.getCurrentDateStr());

			int count = feedBackService.saveFeedback(feedback);
			if(count > 0){
				setSuccess(true);
				setErrorCode("0000");
				setMessage("保存意见反馈成功");
				
			}else{
				setSuccess(false);
				setErrorCode("0001");
				setMessage("保存意见反馈失败");
			}
			
		}catch(Exception e){
			setSuccess(false);
			setErrorCode("0002");
			setMessage("保存意见反馈失败");
			logger.debug("保存意见反馈", e);
			
		}
		
	}
	/**
	 * 查询意见反馈
	 * @param opinion
	 * @param tokenId
	 */
	@RequestMapping( value="/queryList")
	public String queryList(HttpServletRequest request){
		try{
			
		   PageUtil.startPage(request);			
		   List<Map> list = feedBackService.queryList();
		   PageInfo<Map> pageInfo = new PageInfo<Map>(list);
		   writeObject("data", pageInfo);
		   setSuccess(true);
		   setErrorCode("0000");
		   setMessage("查询意见反馈成功");
		   logger.debug("查询意见反馈成功");
		   return transView("/manage/admin/feedBackManage");
		}catch(Exception e){
			setSuccess(false);
			setErrorCode("0002");
			setMessage("查询意见反馈异常失败");
			logger.error("查询意见反馈", e);
			return transView("/manage/admin/feedBackManage");
		}
	}
}
