package com.lmd.encanddec.service;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;

public interface PermissionService {
	@MetaInfo(
	        name = "验证权限",
	        desc = "将用户名和wsdl地址",
	        params = {
	            @Parameter(name = "username", desc = "用户登录名", type = String.class),
	            @Parameter(name = "wsdl", desc = "所请求得wsdl地址", type = String.class)
	        },
	        result = @Result("布尔")
	    )
	@BizLog(title="验证权限", bizKey="#{p[0],p[1]}")
	Boolean hasPermission(String username,String wsdl);
}
