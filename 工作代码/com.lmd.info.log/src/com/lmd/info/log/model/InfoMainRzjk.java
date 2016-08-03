package com.lmd.info.log.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "InfoMainRzjk")
@Table(name = "INFO_MAIN_RZJK")
public class InfoMainRzjk {

	@Id
	private String id = null;
	
	@Basic(optional = true)
	private String acnum = null;
	
	@Basic(optional = true)
	private String acversion = null;
	
	@Basic(optional = true)
	private String acshow = null;
	
	@Basic(optional = true)
	private String shortacnum = null;
	
	@Basic(optional = true)
	private String xtmc = null;
	
	@Basic(optional = true)
	private String cjdw = null;
	
	@Basic(optional = true)
	private String sj = null;
	
	@Basic(optional = true)
	private String rzlx = null;
	
	@Basic(optional = true)
	private String dyqk = null;
	
	@Basic(optional = true)
	private String jklx = null;
	
	@Basic(optional = true)
	private String dyrz = null;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAcnum() {
		return acnum;
	}

	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}

	public String getAcversion() {
		return acversion;
	}

	public void setAcversion(String acversion) {
		this.acversion = acversion;
	}

	public String getAcshow() {
		return acshow;
	}

	public void setAcshow(String acshow) {
		this.acshow = acshow;
	}

	public String getShortacnum() {
		return shortacnum;
	}

	public void setShortacnum(String shortacnum) {
		this.shortacnum = shortacnum;
	}

	public String getXtmc() {
		return xtmc;
	}

	public void setXtmc(String xtmc) {
		this.xtmc = xtmc;
	}

	public String getCjdw() {
		return cjdw;
	}

	public void setCjdw(String cjdw) {
		this.cjdw = cjdw;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getRzlx() {
		return rzlx;
	}

	public void setRzlx(String rzlx) {
		this.rzlx = rzlx;
	}

	public String getDyqk() {
		return dyqk;
	}

	public void setDyqk(String dyqk) {
		this.dyqk = dyqk;
	}

	public String getJklx() {
		return jklx;
	}

	public void setJklx(String jklx) {
		this.jklx = jklx;
	}

	public String getDyrz() {
		return dyrz;
	}

	public void setDyrz(String dyrz) {
		this.dyrz = dyrz;
	}
	
	
}
