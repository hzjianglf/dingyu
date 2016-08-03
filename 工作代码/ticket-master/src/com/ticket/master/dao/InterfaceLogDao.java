package com.ticket.master.dao;

import java.util.List;

import com.ticket.master.entity.InterfaceLog;
import com.ticket.master.entity.UserInterface;

public interface InterfaceLogDao {
	public List<InterfaceLog> getInterfaceLog(String sql);
	public UserInterface getInterfaceByInterfaceName(String userName,int interfaceId);
	public int getInterfaceCountByInterfaceName(String userName,int interfaceId);
}
