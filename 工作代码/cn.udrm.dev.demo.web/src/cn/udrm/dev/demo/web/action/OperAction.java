package cn.udrm.dev.demo.web.action;


import java.util.Date;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.udrm.dev.demo.manager.OrderManager;
import cn.udrm.dev.demo.model.Order;
import java.util.UUID;

public class OperAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(OperAction.class);	
	@Autowired
	private OrderManager orderManager;
	private boolean success = false;  
//	@Override
//	public String execute() {		
//		try {
//			String ordername= getServletRequest().getParameter("orderName");
//			String address= getServletRequest().getParameter("address");
//			String orderdesc= getServletRequest().getParameter("orderDesc");
//			System.out.println(">>>>ordername>>>>>>"+ordername+">>>>>>address>>>>>>"+address);
//			Order order = new Order();
//			order.setOrderId(UUID.randomUUID().toString());
//			order.setOrderName(ordername);
//			order.setAddress(address);
//			order.setOrderDesc(orderdesc);
//			order.setUpdateDate(new Date());
//			order.setCreateDate(new Date());
//			orderManager.saveOrder(order);
////			JSONObject object = new JSONObject();		    
////	    	object.put("success", true);
////			getServletResponse().getWriter().write("{success:true}");
//			getServletResponse().getWriter().write("{success:true}");
////	    	return SUCCESS;
//	    	return this.setJson("{success:true}");
//		} catch (Exception e) {
//			log.error("列表订单是发生错误！", e);
//			return this.setJson(false, "列表订单是发生错误！" + e.getMessage()); 
//		}
//	}
	
	public String add(){		
		try {
			String ordername= getServletRequest().getParameter("orderName");
			String address= getServletRequest().getParameter("address");
			String orderdesc= getServletRequest().getParameter("orderDesc");
			String uid= getServletRequest().getParameter("id");
			Order order = new Order();
			if(uid!=null&&!("").equals(uid)){
				order.setOrderId(uid);
			}else{
				order.setOrderId(UUID.randomUUID().toString());
			}
			order.setOrderName(ordername);
			order.setAddress(address);
			order.setOrderDesc(orderdesc);
			order.setUpdateDate(new Date());
			order.setCreateDate(new Date());
			orderManager.saveOrder(order);
//			JSONObject object = new JSONObject();		    
//	    	object.put("success", true);
//			getServletResponse().getWriter().write("{success:true}");
			getServletResponse().getWriter().write("{success:true}");
//	    	return SUCCESS;
	    	return this.setJson("{success:true}");
		} catch (Exception e) {
			log.error("列表订单是发生错误！", e);
			return this.setJson(false, "列表订单是发生错误！" + e.getMessage()); 
		}
	}
	
//	public String delete(){		
//		try {
//			String uid= getServletRequest().getParameter("id");
//			System.out.println(">>>>uid>>>>>>"+uid);
//			Order order=orderManager.getOrder(uid)   ;//searchOrders(queryString, pageNo, pageSize, orderBy, isAsc);
//			System.out.println(">>>>>>>getorder>>>>>>>"+order.getOrderName());
//			orderManager.deleteOrder(uid);
////			JSONObject object = new JSONObject();		    
////	    	object.put("success", true);
////			getServletResponse().getWriter().write("{success:true}");
//			getServletResponse().getWriter().write("{success:true}");
////	    	return SUCCESS;
//	    	return this.setJson("{success:true}");
//		} catch (Exception e) {
//			log.error("列表订单是发生错误！", e);
//			return this.setJson(false, "列表订单是发生错误！" + e.getMessage()); 
//		}
//	}
}
