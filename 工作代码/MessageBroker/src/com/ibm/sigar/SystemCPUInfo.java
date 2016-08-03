package com.ibm.sigar;

import java.util.ArrayList;
import java.util.List;

public class SystemCPUInfo {

	private String vendor = null;
	private String model = null;
	private String mhz = null;
	private String totalCPUs = null;
	private String usertime = null;
	private String systime = null;
	private String idletime = null;
	private String waittime = null;
	private String nicetime = null;
	private String combined = null;
	private String irqtime = null;
	
	List<SystemMemInfo> memList = new ArrayList<SystemMemInfo>();
	
	public List<SystemMemInfo> getMemList() {
		return memList;
	}	
	public void setMemList(List<SystemMemInfo> memList) {
		this.memList = memList;
	}	
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMhz() {
		return mhz;
	}
	public void setMhz(String mhz) {
		this.mhz = mhz;
	}
	public String getTotalCPUs() {
		return totalCPUs;
	}
	public void setTotalCPUs(String totalCPUs) {
		this.totalCPUs = totalCPUs;
	}
	public String getUsertime() {
		return usertime;
	}
	public void setUsertime(String usertime) {
		this.usertime = usertime;
	}
	public String getSystime() {
		return systime;
	}
	public void setSystime(String systime) {
		this.systime = systime;
	}
	public String getIdletime() {
		return idletime;
	}
	public void setIdletime(String idletime) {
		this.idletime = idletime;
	}
	public String getWaittime() {
		return waittime;
	}
	public void setWaittime(String waittime) {
		this.waittime = waittime;
	}
	public String getNicetime() {
		return nicetime;
	}
	public void setNicetime(String nicetime) {
		this.nicetime = nicetime;
	}
	public String getCombined() {
		return combined;
	}
	public void setCombined(String combined) {
		this.combined = combined;
	}
	public String getIrqtime() {
		return irqtime;
	}
	public void setIrqtime(String irqtime) {
		this.irqtime = irqtime;
	}
	
	
}
