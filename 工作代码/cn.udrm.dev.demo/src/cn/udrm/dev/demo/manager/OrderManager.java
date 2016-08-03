package cn.udrm.dev.demo.manager;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import cn.udrm.dev.demo.service.OrderManagerService;

@BizLog
public interface OrderManager extends OrderManagerService {

	@BizLog(title="查询订单", bizKey="#{p[0]}")
	String searchOrders(String queryString, int pageNo, int pageSize, String orderBy, boolean isAsc) throws Exception;
	
}
