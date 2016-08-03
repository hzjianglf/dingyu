package com.ibm.hibernate;

/**
 * MonitorMessageFlow entity. @author MyEclipse Persistence Tools
 */

public class MonitorMessageFlow implements java.io.Serializable {

	// Fields

	private String mmfid;
	private String flowname;
	private String eventsourceaddress;
	private String eventname;
	private String creationtime;
	private Integer counter;
	private String localtransactionid;
	private String nodelabel;
	private String nodetype;
	private String terminal;
	private String appdatatype;
	private String appdatafilepath;

	// Constructors

	/** default constructor */
	public MonitorMessageFlow() {
	}

	/** minimal constructor */
	public MonitorMessageFlow(String mmfid) {
		this.mmfid = mmfid;
	}

	/** full constructor */
	public MonitorMessageFlow(String mmfid, String flowname,
			String eventsourceaddress, String eventname, String creationtime,
			Integer counter, String localtransactionid, String nodelabel,
			String nodetype, String terminal, String appdatatype,
			String appdatafilepath) {
		this.mmfid = mmfid;
		this.flowname = flowname;
		this.eventsourceaddress = eventsourceaddress;
		this.eventname = eventname;
		this.creationtime = creationtime;
		this.counter = counter;
		this.localtransactionid = localtransactionid;
		this.nodelabel = nodelabel;
		this.nodetype = nodetype;
		this.terminal = terminal;
		this.appdatatype = appdatatype;
		this.appdatafilepath = appdatafilepath;
	}

	// Property accessors

	public String getMmfid() {
		return this.mmfid;
	}

	public void setMmfid(String mmfid) {
		this.mmfid = mmfid;
	}

	public String getFlowname() {
		return this.flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public String getEventsourceaddress() {
		return this.eventsourceaddress;
	}

	public void setEventsourceaddress(String eventsourceaddress) {
		this.eventsourceaddress = eventsourceaddress;
	}

	public String getEventname() {
		return this.eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getCreationtime() {
		return this.creationtime;
	}

	public void setCreationtime(String creationtime) {
		this.creationtime = creationtime;
	}

	public Integer getCounter() {
		return this.counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public String getLocaltransactionid() {
		return this.localtransactionid;
	}

	public void setLocaltransactionid(String localtransactionid) {
		this.localtransactionid = localtransactionid;
	}

	public String getNodelabel() {
		return this.nodelabel;
	}

	public void setNodelabel(String nodelabel) {
		this.nodelabel = nodelabel;
	}

	public String getNodetype() {
		return this.nodetype;
	}

	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}

	public String getTerminal() {
		return this.terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getAppdatatype() {
		return this.appdatatype;
	}

	public void setAppdatatype(String appdatatype) {
		this.appdatatype = appdatatype;
	}

	public String getAppdatafilepath() {
		return this.appdatafilepath;
	}

	public void setAppdatafilepath(String appdatafilepath) {
		this.appdatafilepath = appdatafilepath;
	}

}