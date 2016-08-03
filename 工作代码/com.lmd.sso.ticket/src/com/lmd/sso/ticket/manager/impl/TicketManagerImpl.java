package com.lmd.sso.ticket.manager.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.lmd.sso.ticket.client.SSOClient;
import com.lmd.sso.ticket.manager.TicketManager;
@Service("ticketManager")
public class TicketManagerImpl implements TicketManager{

	@Override
	public String getTicket(String server, String username, String password,
			String service) {
		
		// TODO 自动生成的方法存根
//		return SSOClient.getTicket(server, username, password, service);
		String str = SSOClient.getTicket(server, username, password, service);
		return str;
	}
	
	
}
	
	
