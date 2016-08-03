package cn.udrm.dev.demo.manager.impl;

import java.util.List;

import net.risesoft.soa.framework.dao.support.PageResultJson;
import net.risesoft.soa.framework.util.json.JSONException;
import net.risesoft.soa.framework.util.json.JSONWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.udrm.dev.demo.dao.OrderDao;
import cn.udrm.dev.demo.manager.OrderManager;
import cn.udrm.dev.demo.model.Order;
import cn.udrm.dev.demo.service.OrderManagerService;

@Service("orderManager")
@Transactional
public class OrderManagerImpl implements OrderManager, OrderManagerService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderManagerImpl.class);
	
	private String currentOrderId4BizLog;
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public void saveOrder(Order order) {
		this.setCurrentOrderId4BizLog(order.getOrderId());
		orderDao.save(order);
	}

	@Override
	public void deleteOrder(String orderId) {
		this.setCurrentOrderId4BizLog(orderId);
		orderDao.delete(orderId);
	}

	@Override
	public Order getOrder(String orderId) {
		this.setCurrentOrderId4BizLog(orderId);
		return orderDao.loadById(orderId);
	}

	@Override
	public String searchOrders(String queryString, int pageNo, int pageSize, String orderBy, boolean isAsc) throws Exception {
		OrderPageResultJson<Order> page = new OrderPageResultJson<Order>(pageNo, pageSize); 
		orderDao.find(page, queryString, orderBy, isAsc);
		String json = page.getPageResultJson(null);
		return json;
	}

	class OrderPageResultJson<E> extends PageResultJson<E>{
		public OrderPageResultJson(int pageNo, int pageSize) {
			super(pageNo, pageSize);
		}

		public OrderPageResultJson(int pageNo, List<E> list, int pageSize, int totalCount) {
			super(pageNo, list, pageSize, totalCount);
		}

		@Override
		public void outputJson(JSONWriter json, E e) throws JSONException {
			Order o = (Order)e;
			json.key("orderId").value(o.getOrderId());
			json.key("orderName").value(o.getOrderName());
			json.key("address").value(o.getAddress());
			json.key("orderDesc").value(o.getOrderDesc());
			json.key("createDate").value(o.getCreateDate());
			json.key("updateDate").value(o.getUpdateDate());
		}
		
	}

	public String getCurrentOrderId4BizLog() {
		return currentOrderId4BizLog;
	}

	public void setCurrentOrderId4BizLog(String currentOrderId4BizLog) {
		this.currentOrderId4BizLog = currentOrderId4BizLog;
	}
	
	
}
