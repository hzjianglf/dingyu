package lmd.res.count.service;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;

public interface ResCountManager {
	@MetaInfo(
	        name = "统计资源个数",
	        desc = "统计某一类资源的个数",
	        params = {
	            @Parameter(name = "table", desc = "表名", type = String.class),
	            @Parameter(name = "acnum", desc = "资源编号", type = String.class),
	        },
	        result = @Result("返回字符串 ")
	    )
	@BizLog(title="统计资源", bizKey="#{p[0]}")
	String countAc(String table,String acnum);
}
