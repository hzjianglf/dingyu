package cn.udrm.dev.demo.bizlog;


import net.risesoft.soa.framework.bizlog.BizLogContext;
import net.risesoft.soa.framework.bizlog.spi.BizLogModelCreator;

import org.apache.commons.lang.StringUtils;

import cn.udrm.dev.demo.manager.impl.OrderManagerImpl;

public class DemoBizLogModelCreator implements BizLogModelCreator<DemoBizLogModel> {

	@Override
	public DemoBizLogModel create(BizLogContext context) {
		DemoBizLogModel m = new DemoBizLogModel();
		
		OrderManagerImpl bean = (OrderManagerImpl)context.getRawObject();
		String orderId = bean.getCurrentOrderId4BizLog();
		m.setOrderId(StringUtils.isBlank(orderId) ? "orderId" : orderId );
		return m;
	}

}
