package com.lmd.sso.ticket.manager;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;


public interface TicketManager {
	@MetaInfo(
	        name = "获取票据服务",
	        desc = "获取票据服务",
	        params = {
	            @Parameter(name = "server", desc = "server", type = String.class),
	            @Parameter(name = "username", desc = "username", type = String.class),
	            @Parameter(name = "password", desc = "password", type = String.class),
	            @Parameter(name = "service", desc = "service", type = String.class)
	        },
	        result = @Result("字符串")
	    )
	@BizLog(title="获取票据", bizKey="#{p[0],p[1},p{2},p{3}")
	String getTicket(String server,String username,String password,String service);
}
