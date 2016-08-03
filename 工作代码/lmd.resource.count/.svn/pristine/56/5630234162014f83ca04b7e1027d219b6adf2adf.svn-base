package lmd.res.count.service;

import java.util.List;
import java.util.Map;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;

public interface QueryManager {

	@MetaInfo(
	        name = "sql查询",
	        desc = "sql查询获取返回数据泛型List《String》",
	        params = {
	            @Parameter(name = "sql", desc = "sql", type = String.class)
	        },
	        result = @Result("返回List<Map<String key,String value>> ")
	    )
	@BizLog(title="sql查询", bizKey="#{p[0]}")
	public List<Map>  getBySql(String sql);
	
	@MetaInfo(
	        name = "根据唯一标识查询",
	        desc = "查询获取返回数据泛型List《String》",
	        params = {
	            @Parameter(name = "key", desc = "key", type = String.class)
	        },
	        result = @Result("返回List<Map<String key,String value>> ")
	    )
	@BizLog(title="根据唯一标识查询", bizKey="#{p[0]}")
	public List<Map>  getByKey(String key);
	
	@MetaInfo(
	        name = "sql查询",
	        desc = "sql查询获取返回数据json字符串",
	        params = {
	            @Parameter(name = "sql", desc = "sql", type = String.class)
	        },
	        result = @Result("返回json字符串 ")
	    )
	@BizLog(title="sql查询", bizKey="#{p[0]}")
	public String  queryBySql(String sql);
}
