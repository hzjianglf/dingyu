package com.handany.bm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.handany.base.common.CommonUtils;
import com.handany.base.controller.BaseController;
import com.handany.bm.service.BmBusiParamService;
import com.handany.rbac.common.UserContextManager;
@Controller
@RequestMapping(value="/bm/busiParam")
public class BmBusiParamController extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(BmBusiParamController.class);
	
	@Autowired
	private BmBusiParamService busiParamService;
	
	@RequestMapping(value="selectAll")	
	public String selectAll(){
		
		try {
			List<Map<String,Object>> list = busiParamService.selectAll();
			writeObject("list",list);
			setSuccess(true);
			
		} catch (Exception e) {
			setSuccess(false);
			logger.error("",e);
		}
		return transView("/manage/admin/sysParam");
	}
	
	@RequestMapping(value="save")
	public void save(String code,String value){
		
		Map<String,Object> map = new HashMap<String,Object>();
	
		map.put("code", code);
		map.put("value", value);
		map.put("lastModified", CommonUtils.getCurrentDate());
		map.put("lastUser", UserContextManager.getLoginUser().getId());
		try {
			busiParamService.save(map);
			setSuccess(true);
		} catch (Exception e) {
			setSuccess(false);
			logger.error("",e);
		}
	} 
	
	@RequestMapping(value="query")
	public void query(String code){
		try {
			Map map = busiParamService.getParam(code);
			writeObject("param", map);
			setSuccess(true);
		} catch (Exception e) {
			setSuccess(false);
		}
	}
}
