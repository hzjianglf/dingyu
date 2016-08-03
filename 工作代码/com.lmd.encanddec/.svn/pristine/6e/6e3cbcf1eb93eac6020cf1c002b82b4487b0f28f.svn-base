package com.lmd.encanddec.manager;

import javax.jws.WebService;

import com.lmd.encanddec.service.JjmServiceManagerService;

import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;

@BizLog
public interface JjmServiceManager extends JjmServiceManagerService{

	@MetaInfo(
	        name = "加密服务",
	        desc = "将s传入的字符串进行加密",
	        params = {
	            @Parameter(name = "str", desc = "传入参数（字符串）", type = String.class)
	        },
	        result = @Result("字符串")
	    )
	@BizLog(title="加密", bizKey="#{p[0]}")
	String encrypt(String str);//加密
	@MetaInfo(
	        name = "解密服务",
	        desc = "将s传入的字符串进行解密",
	        params = {
	            @Parameter(name = "str", desc = "传入参数（字符串）", type = String.class),
	            @Parameter(name = "charSet", desc = "字符编码", type = String.class)
	        },
	        result = @Result("字符串")
	    )
	@BizLog(title="解密", bizKey="#{p[0],p[1}")
	String decrypt(String str,String charSet);//解密，参数chaset为编码
}
