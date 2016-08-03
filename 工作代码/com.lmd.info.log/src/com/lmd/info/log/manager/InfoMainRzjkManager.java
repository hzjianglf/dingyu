package com.lmd.info.log.manager;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;

import com.lmd.info.log.model.InfoMainRzjk;

import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;

@BizLog
public interface InfoMainRzjkManager {

	@MetaInfo(
	        name = "保存日志对象",
	        desc = "将传入的日志对象持久化到数据库中",
	        params = {
	            @Parameter(name = "infoMainRzjk", desc = "日志对象", type = InfoMainRzjk.class)
	        }
	    )
	@BizLog(title="保存日志对象", bizKey="#{p[0].id}")
	String savaInfoMainRzjk(InfoMainRzjk infoMainRzjk);
}
