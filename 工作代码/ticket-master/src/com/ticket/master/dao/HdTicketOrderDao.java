package com.ticket.master.dao;

import com.ticket.master.entity.HdTicketOrder;

public interface HdTicketOrderDao {
	public HdTicketOrder findByOrderId(String hql);

	public void update(HdTicketOrder ho);
}
