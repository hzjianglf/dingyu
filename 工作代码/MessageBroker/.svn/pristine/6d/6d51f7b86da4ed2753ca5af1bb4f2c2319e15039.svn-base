package com.ibm.struts;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

public class SetAlertNotification extends ActionSupport {
	
	private static final Logger log = LoggerFactory.getLogger(SetAlertNotification.class);
	
	private String smtpserver = null;	
	private String emailid = null;
	private String emailpwd = null;
	private String qmgrname = null;
	private String connectionmode = null;
	private String mqhostname = null;
	private String mqport = null;
	private String mqchannel = null;
	
	private String result = null;
	
	public String getSmtpserver() {
		return smtpserver;
	}

	public void setSmtpserver(String smtpserver) {
		this.smtpserver = smtpserver;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getEmailpwd() {
		return emailpwd;
	}

	public void setEmailpwd(String emailpwd) {
		this.emailpwd = emailpwd;
	}

	public String getQmgrname() {
		return qmgrname;
	}

	public void setQmgrname(String qmgrname) {
		this.qmgrname = qmgrname;
	}

	public String getConnectionmode() {
		return connectionmode;
	}

	public void setConnectionmode(String connectionmode) {
		this.connectionmode = connectionmode;
	}

	public String getMqhostname() {
		return mqhostname;
	}

	public void setMqhostname(String mqhostname) {
		this.mqhostname = mqhostname;
	}

	public String getMqport() {
		return mqport;
	}

	public void setMqport(String mqport) {
		this.mqport = mqport;
	}

	public String getMqchannel() {
		return mqchannel;
	}

	public void setMqchannel(String mqchannel) {
		this.mqchannel = mqchannel;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String setnotification() {
		String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/Alert.properties";
		smtpserver = CommonUtils.readValue(filePath, "smtpserver");		
		emailid = CommonUtils.readValue(filePath, "emailid");
		emailpwd = CommonUtils.readValue(filePath, "emailpwd");
		
		qmgrname = CommonUtils.readValue(filePath, "qmgrname");
		connectionmode = CommonUtils.readValue(filePath, "connectionmode");
		mqhostname = CommonUtils.readValue(filePath, "mqhostname");
		mqport = CommonUtils.readValue(filePath, "mqport");
		mqchannel = CommonUtils.readValue(filePath, "mqchannel");
		
		return SUCCESS;
	}
	
	public String savenotification() {
		String filePath = ServletActionContext.getServletContext().getRealPath("/config") + "/Alert.properties";
		CommonUtils.writeProperties(filePath, "smtpserver", smtpserver);
		CommonUtils.writeProperties(filePath, "emailid", emailid);
		CommonUtils.writeProperties(filePath, "emailpwd", emailpwd);
		
		CommonUtils.writeProperties(filePath, "qmgrname", qmgrname);
		CommonUtils.writeProperties(filePath, "connectionmode", connectionmode);
		CommonUtils.writeProperties(filePath, "mqhostname", mqhostname);
		CommonUtils.writeProperties(filePath, "mqport", mqport);
		CommonUtils.writeProperties(filePath, "mqchannel", mqchannel);
		
		result = "±£´æ³É¹¦£¡";		
		return SUCCESS;
	}

}
