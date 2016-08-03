package com.whcyz.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.interceptor.NoUrlPara;
import com.jfinal.upload.UploadFile;
import com.whcyz.model.Account;
import com.whcyz.permission.Permission;
import com.whcyz.permission.PermissionInterceptor;
import com.whcyz.service.impl.AccountService;
import com.whcyz.service.impl.ArticleService;
import com.whcyz.service.impl.CommentService;
import com.whcyz.service.impl.CompanyService;
import com.whcyz.service.impl.LinkService;
import com.whcyz.service.impl.PersonService;
/**
 * 后台管理
 * @author Administrator
 *
 */
@Before(PermissionInterceptor.class)
public class AdminController extends BaseController {
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	@Before(NoUrlPara.class)
	public void index(){
		render("index.jsp");
	}
	@Permission({Account.PERMISSION_ADMIN,Account.PERMISSION_EDITOR})
	public void home(){    
		setAttr("articleCount",ArticleService.me.getCount());
		setAttr("companyCount",CompanyService.me.getCount());
		setAttr("personCount",PersonService.me.getCount());
		setAttr("linkCount",LinkService.me.getCount());
		setAttr("accountCount",AccountService.me.getCount());
		setAttr("commentCount",CommentService.me.getCount());
		render("home.jsp");
	}
	
	
	
}
