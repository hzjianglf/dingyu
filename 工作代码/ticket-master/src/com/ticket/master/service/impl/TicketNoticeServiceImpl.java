package com.ticket.master.service.impl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ticket.master.common.TimeUtil;
import com.ticket.master.dao.impl.ParentDaoImpl;
import com.ticket.master.service.TicketNoticeService;

public class TicketNoticeServiceImpl implements TicketNoticeService{
	ParentDaoImpl pdi = new ParentDaoImpl();
	public String outTicketNotice(String type, String xml) {
		// 出票通知
		//判断逻辑
		//解析xml获取orderId
		//根据orderId判断时哪个用户的数据
		//将type和xml发送给该用户，即，访问用户的接口
		String rxml=
				"<Response>" +
				"<Status>1</Status>" +
				"<Error>byz123</Error>" +
				"</Response>";
		String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
				+ "("
				+ 3
				+ ","
				+ 55
				+ ",'"
				+ TimeUtil.getSysTime()
				+ "','"
				+ xml
				+ "','"
				+ rxml
				+ "')";
		pdi.allSql(insertSql);
		
		return rxml;
	}

	public String refundTicketNotice(String type, String xml) {
		// 退废票通知
		//判断逻辑
		String rxml=
				"<Response>" +
				"<Status>1</Status>" +
				"<Error>byz123</Error>" +
				"</Response>";
		String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
				+ "("
				+ 3
				+ ","
				+ 56
				+ ",'"
				+ TimeUtil.getSysTime()
				+ "','"
				+ xml
				+ "','"
				+ rxml
				+ "')";
		pdi.allSql(insertSql);
			return rxml;
	}

	public String updateTicketNotice(String type, String xml) {
		// 改签通知
		//判断逻辑
		String rxml=
				"<Response>" +
				"<Status>1</Status>" +
				"<Error>byz123</Error>" +
				"</Response>";
		String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
				+ "("
				+ 3
				+ ","
				+ 57
				+ ",'"
				+ TimeUtil.getSysTime()
				+ "','"
				+ xml
				+ "','"
				+ rxml
				+ "')";
		pdi.allSql(insertSql);
			return rxml;
	}

}
