package com.ibm.struts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.hibernate.BrokerInfo;
import com.ibm.hibernate.BrokerInfoDAO;
import com.ibm.sigar.BrokerProcInfo;
import com.ibm.sigar.SubInfoFromBrokerTop;
import com.opensymphony.xwork2.ActionSupport;

public class GetBrokerInfoAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;	
	private String qmgrname = null;	
	Map<String, String> qmgrMap = new LinkedHashMap<String, String>();	
	List<BrokerProcInfo> brokerProcList = new ArrayList<BrokerProcInfo>();
	private int brokerProcNum = 0;	
	private int mqProceNum = 0;
	
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

	public int getBrokerProcNum() {
		return brokerProcNum;
	}

	public void setBrokerProcNum(int brokerProcNum) {
		this.brokerProcNum = brokerProcNum;
	}

	public int getMqProceNum() {
		return mqProceNum;
	}

	public void setMqProceNum(int mqProceNum) {
		this.mqProceNum = mqProceNum;
	}

	public List<BrokerProcInfo> getBrokerProcList() {
		return brokerProcList;
	}

	public void setBrokerProcList(List<BrokerProcInfo> brokerProcList) {
		this.brokerProcList = brokerProcList;
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
		brokerProcList = sub.receiveBroker();
		
		brokerProcNum = 0;
		for (Iterator<BrokerProcInfo> j = brokerProcList.iterator(); j.hasNext();) {
			BrokerProcInfo brokerProc = (BrokerProcInfo)j.next();
			if (brokerProc.getCommand().contains("bip") ||  brokerProc.getCommand().contains("DataFlowEngine")) {
				brokerProcNum++;
			}
		}
		
		mqProceNum = brokerProcList.size() - brokerProcNum;
		
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