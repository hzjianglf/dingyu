package lmd.jcg.gsm.manager;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;

public interface GsmManager {
	@MetaInfo(
	        name = "登录名短信发送",
	        desc = "根据登录名来发送短信",
	        params = {
	            @Parameter(name = "username", desc = "用户登录名", type = String.class),
	            @Parameter(name = "content", desc = "短信内容", type = String.class),
	        },
	        result = @Result("返回字符串,只返回1表示发送成功，0表示用户手机号码不存在或错误,-1表示用户不存在或服务器连接失败,  例如：zhangsan:1")
	    )
	@BizLog(title="登录名短信发送", bizKey="#{p[0]}")
	String sendByLoginName(String username,String content);
	@MetaInfo(
	        name = "手机号短信发送",
	        desc = "根据手机号来发送短信",
	        params = {
	            @Parameter(name = "mobile", desc = "用户手机号", type = String.class),
	            @Parameter(name = "content", desc = "短信内容", type = String.class),
	        },
	        result = @Result("返回字符串,1表示发送成功， 例如：138***:1 ")
	    )
	@BizLog(title="手机号短信发送", bizKey="#{p[0]}")
	String sendByMobile(String mobile,String content);
}
