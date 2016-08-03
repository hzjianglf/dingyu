package cn.udrm.dev.demo.service;

import net.risesoft.soa.framework.bizlog.annotation.BizLog;
import cn.udrm.dev.demo.model.Order;
import egov.appservice.asf.annotation.MetaInfo;
import egov.appservice.asf.annotation.Parameter;
import egov.appservice.asf.annotation.Result;

@BizLog
public interface OrderManagerService {

	@MetaInfo(
	        name = "保存订单对象",
	        desc = "将传入的订单对象持久化到数据库中",
	        params = {
	            @Parameter(name = "order", desc = "订单对象", type = Order.class)
	        }
	    )
	@BizLog(title="保存订单对象", bizKey="#{p[0].orderId}")
	void saveOrder(Order order);
	
	@MetaInfo(
	        name = "删除指定的订单",
	        desc = "根据orderId删除订单对象",
	        params = {
	            @Parameter(name = "orderId", desc = "订单唯一标识", type = String.class)
	        }
	    )
	@BizLog(title="删除指定的订单", bizKey="#{p[0]}")
	void deleteOrder(String orderId);
	
	@MetaInfo(
	        name = "获得指定的订单",
	        desc = "根据orderId获得订单对象",
	        params = {
	            @Parameter(name = "orderId", desc = "订单唯一标识", type = String.class)
	        },
	        result = @Result("订单对象")
	    )
	@BizLog(title="获得指定的订单", bizKey="#{p[0]}")
	Order getOrder(String orderId);
}
