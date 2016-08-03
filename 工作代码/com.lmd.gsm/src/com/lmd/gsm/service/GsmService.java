package com.lmd.gsm.service;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;

public interface GsmService {
	@MetaInfo(
	        name = "短信发送服务",
	        desc = "发送短信",
	        params = {
	            @Parameter(name = "sender", desc = "短信发送者", type = String.class),
	            @Parameter(name = "username", desc = "短信接收者", type = String.class),
	            @Parameter(name = "content", desc = "短信内容", type = String.class)
	        },
	        result = @Result("字符串")
	    )
	@BizLog(title="短信发送", bizKey="#{p[0],p[1],p[2]}")
	String gsmSend(String sender,String username,String content);
}
