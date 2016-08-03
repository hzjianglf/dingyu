package com.ibm.struts;

import org.apache.struts2.ServletActionContext;

import com.ibm.hibernate.EsbTimeout;
import com.ibm.hibernate.EsbTimeoutDAO;
import com.opensymphony.xwork2.ActionSupport;

public class SetSocketTimeout extends ActionSupport {
	
	private static final long serialVersionUID = 1L;	
	
	EsbTimeoutDAO dao = new EsbTimeoutDAO();
	
	private String tcpipservertimeout;
	private String tcpipclienttimeout;
	private String result;
	
	public String getTcpipservertimeout() {
		return tcpipservertimeout;
	}

	public void setTcpipservertimeout(String tcpipservertimeout) {
		this.tcpipservertimeout = tcpipservertimeout;
	}

	public String getTcpipclienttimeout() {
		return tcpipclienttimeout;
	}

	public void setTcpipclienttimeout(String tcpipclienttimeout) {
		this.tcpipclienttimeout = tcpipclienttimeout;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * set the part of throughout control
	 * 
	 */	
	public String setsockettimeout() {
//		String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/Timeout.properties";
//		tcpipservertimeout = CommonUtils.readValue(filePath, "tcpipservertimeout");		
//		tcpipclienttimeout = CommonUtils.readValue(filePath, "tcpipclienttimeout");
		tcpipservertimeout = dao.findByType("TCPIPSERVER");
		tcpipclienttimeout = dao.findByType("TCPIPCLIENT");
		
		return SUCCESS;
	}
	
	public String savesockettimeout() {
//		String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/Timeout.properties";
//		CommonUtils.writeProperties(filePath, "tcpipservertimeout", tcpipservertimeout);
//		CommonUtils.writeProperties(filePath, "tcpipclienttimeout", tcpipclienttimeout);		
		if (!tcpipservertimeout.equalsIgnoreCase("")) {
			EsbTimeout t1 = new EsbTimeout();		
			t1.setTid(3);
			t1.setNodetype("TCPIPSERVER");
			t1.setNodetimeout(tcpipservertimeout);
			dao.save(t1);
		}

		if (!tcpipclienttimeout.equalsIgnoreCase("")) {
			EsbTimeout t2 = new EsbTimeout();		
			t2.setTid(4);
			t2.setNodetype("TCPIPCLIENT");
			t2.setNodetimeout(tcpipclienttimeout);
			dao.save(t2);
		}
		
		result = "±£´æ³É¹¦¡£";
		return SUCCESS;
	}	
}
