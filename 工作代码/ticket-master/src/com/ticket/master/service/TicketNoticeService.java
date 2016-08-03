package com.ticket.master.service;

public interface TicketNoticeService {
	  /**
		 * 1接口：出票通知
		 * 
		 */
public String outTicketNotice(String type,String xml);
  /**
	 * 2接口：退废票通知
	 * 
	 */
  public String refundTicketNotice(String type,String xml);
  /**
	 * 3接口：改签通知
	 * 
	 */
  public String updateTicketNotice(String type,String xml);
}
