package com.ibm.struts;

import com.ibm.hibernate.EsbTimeout;
import com.ibm.hibernate.EsbTimeoutDAO;
import com.opensymphony.xwork2.ActionSupport;

public class SetHttpTimeout extends ActionSupport {
	
	private static final long serialVersionUID = 1L;	
	
	EsbTimeoutDAO dao = new EsbTimeoutDAO();
	
	private String httptimeout;
	private String soaptimeout;
	private String result;
	
	public String getHttptimeout() {
		return httptimeout;
	}

	public void setHttptimeout(String httptimeout) {
		this.httptimeout = httptimeout;
	}

	public String getSoaptimeout() {
		return soaptimeout;
	}

	public void setSoaptimeout(String soaptimeout) {
		this.soaptimeout = soaptimeout;
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
	public String sethttptimeout() {
		//String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/Timeout.properties";
		//httptimeout = CommonUtils.readValue(filePath, "httptimeout");		
		//soaptimeout = CommonUtils.readValue(filePath, "soaptimeout");
		httptimeout = dao.findByType("HTTPRequest");
		soaptimeout = dao.findByType("SOAPRequest");
			
		return SUCCESS;
	}
	
	public String savehttptimeout() {
		//String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/Timeout.properties";
		//CommonUtils.writeProperties(filePath, "httptimeout", httptimeout);
		//CommonUtils.writeProperties(filePath, "soaptimeout", soaptimeout);		
		if (!httptimeout.equalsIgnoreCase("")) {
			EsbTimeout t1 = new EsbTimeout();		
			t1.setTid(1);
			t1.setNodetype("HTTPRequest");
			t1.setNodetimeout(httptimeout);
			dao.save(t1);
		}

		if (!soaptimeout.equalsIgnoreCase("")) {
			EsbTimeout t2 = new EsbTimeout();		
			t2.setTid(2);
			t2.setNodetype("SOAPRequest");
			t2.setNodetimeout(soaptimeout);
			dao.save(t2);
		}
		
		result = "±£´æ³É¹¦¡£";
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		EsbTimeoutDAO dao = new EsbTimeoutDAO();
		EsbTimeout t = new EsbTimeout();
		
		t.setTid(1);
		t.setNodetype("SOAPRequest");
		t.setNodetimeout("30");
		dao.save(t);
		System.out.println(dao.findByType("SOAPRequest"));
	}
}
