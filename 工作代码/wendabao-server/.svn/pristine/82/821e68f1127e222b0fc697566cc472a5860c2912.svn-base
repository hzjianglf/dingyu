package com.handany.bm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.handany.base.common.CommonUtils;
import com.handany.base.container.CacheContainer;
import com.handany.base.controller.BaseController;
import com.handany.base.model.LoginInfo;
import com.handany.base.push.ShortMessageManager;
import com.handany.base.sequence.SerialNumberManager;
import com.handany.rbac.common.UserContextManager;

import com.handany.bm.model.${tableBean.tableNameCapitalized};
import com.handany.bm.service.${tableBean.tableNameCapitalized}Service;

@Controller
@RequestMapping(value = "/bm/${tableBean.tableName?lower_case?remove_beginning("bm_")}")
public class ${tableBean.tableNameCapitalized}Controller extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(${tableBean.tableNameCapitalized}Controller.class);

	@Autowired
	private ${tableBean.tableNameCapitalized}Service ${tableBean.tableNameNoDash?lower_case?remove_beginning("bm")}Service;

	@Autowired
	private SerialNumberManager serialNumberManager;
	
	
	
}