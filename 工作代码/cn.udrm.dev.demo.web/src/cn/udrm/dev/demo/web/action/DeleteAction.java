package cn.udrm.dev.demo.web.action;


import java.util.Date;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.udrm.dev.demo.manager.OrderManager;
import cn.udrm.dev.demo.model.Order;
import java.util.UUID;

public class DeleteAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(DeleteAction.class);	
	@Autowired
	private OrderManager orderManager;
	private boolean success = false;  
	@Override
	public String execute() {		
		try {
			String uid= getServletRequest().getParameter("id");
			System.out.println(">>>>uid>>>>>>"+uid);
			
//			Order order = new Order();
//			order.setOrderId("111111112");
//			orderManager.saveOrder(order);		
					//orderManager.getOrder(uid)   ;//searchOrders(queryString, pageNo, pageSize, orderBy, isAsc);
			orderManager.deleteOrder("'"+uid+"'");
//			JSONObject object = new JSONObject();		    
//	    	object.put("success", true);
//			getServletResponse().getWriter().write("{success:true}");
//			getServletResponse().getWriter().write("{success:true}");
	    	return SUCCESS;
//			return  "orderlist";
//	    	return this.setJson("{success:true}");
		} catch (Exception e) {
			log.error("列表订单是发生错误！", e);
			return  "orderlist";
		}
	}
}
