package com.ibm.struts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.sigar.SubInfoFromBrokerTop;
import com.ibm.sigar.SystemCPUInfo;
import com.opensymphony.xwork2.ActionSupport;

public class GetSystemInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private String qmgrname = null;	
	Map<String, String> qmgrMap = new LinkedHashMap<String, String>();
	SystemCPUInfo sysInfo = new SystemCPUInfo(); 			
	List<SystemCPUInfo> sysList = new ArrayList<SystemCPUInfo>();
	
	public String getQmgrname() {
		return qmgrname;
	}

	public void setQmgrname(String qmgrname) {
		this.qmgrname = qmgrname;
	}

	public Map<String, String> getQmgrMap() {
		return qmgrMap;
	}

	public void setQmgrMap(Map<String, String> qmgrMap) {
		this.qmgrMap = qmgrMap;
	}
	
	public List<SystemCPUInfo> getSysList() {
		return sysList;
	}

	public void setSysList(List<SystemCPUInfo> sysList) {
		this.sysList = sysList;
	}

	public SystemCPUInfo getSysInfo() {
		return sysInfo;
	}

	public void setSysInfo(SystemCPUInfo sysInfo) {
		this.sysInfo = sysInfo;
	}

	/**
	 * @return
	 */
	public String execute() {
		
		BrokerInfoDAO dao = new BrokerInfoDAO();
		BrokerInfo broker = new BrokerInfo();
		broker = (BrokerInfo) dao.findByQmgrname(qmgrname).iterator().next();				
		
		setQmgrname(qmgrname);
		SubInfoFromBrokerTop sub = new SubInfoFromBrokerTop(broker);
		sysInfo = sub.receiveSystem();
				
		sysList.add(sysInfo);
		

		List<BrokerInfo> list = dao.findAll();
		for (Iterator<BrokerInfo> i = list.iterator(); i.hasNext();) {
			BrokerInfo brokerInfo = (BrokerInfo) i.next();
			qmgrMap.put(brokerInfo.getQmgrname(), brokerInfo.getQmgrname());
		}
		
		return SUCCESS;
	}
	
	/**
	 * @return
	 */
	public String select() {
		BrokerInfoDAO dao = new BrokerInfoDAO();
		List<BrokerInfo> list = dao.findAll();

		for (Iterator<BrokerInfo> i = list.iterator(); i.hasNext();) {
			BrokerInfo brokerInfo = (BrokerInfo) i.next();
			qmgrMap.put(brokerInfo.getQmgrname(), brokerInfo.getQmgrname());
		}

		return SUCCESS;
	}
}