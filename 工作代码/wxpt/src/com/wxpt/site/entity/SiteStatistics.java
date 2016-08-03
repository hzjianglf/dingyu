package com.wxpt.site.entity;

/**
 * SiteStatistics entity. @author MyEclipse Persistence Tools
 */

public class SiteStatistics implements java.io.Serializable {

	// Fields

	private Integer SId;
	private String page;
	private String domain;
	private Integer visitors;
	private Integer pageviews;
	private Integer ips;
	private Integer entrances;
	private Integer outwards;
	private Integer exits;
	private Double stayTime;
	private Double exitRate;
	private Integer userId;
	private String recordTime;
	private Integer type;

	// Constructors

	/** default constructor */
	public SiteStatistics() {
	}

	/** minimal constructor */
	public SiteStatistics(String page, Integer visitors, Integer pageviews,
			Integer ips, Integer entrances, Integer outwards, Integer exits,
			Double stayTime, Double exitRate, Integer userId,
			String recordTime, Integer type) {
		this.page = page;
		this.visitors = visitors;
		this.pageviews = pageviews;
		this.ips = ips;
		this.entrances = entrances;
		this.outwards = outwards;
		this.exits = exits;
		this.stayTime = stayTime;
		this.exitRate = exitRate;
		this.userId = userId;
		this.recordTime = recordTime;
		this.type = type;
	}

	/** full constructor */
	public SiteStatistics(String page, String domain, Integer visitors,
			Integer pageviews, Integer ips, Integer entrances,
			Integer outwards, Integer exits, Double stayTime, Double exitRate,
			Integer userId, String recordTime, Integer type) {
		this.page = page;
		this.domain = domain;
		this.visitors = visitors;
		this.pageviews = pageviews;
		this.ips = ips;
		this.entrances = entrances;
		this.outwards = outwards;
		this.exits = exits;
		this.stayTime = stayTime;
		this.exitRate = exitRate;
		this.userId = userId;
		this.recordTime = recordTime;
		this.type = type;
	}

	// Property accessors

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Integer getVisitors() {
		return this.visitors;
	}

	public void setVisitors(Integer visitors) {
		this.visitors = visitors;
	}

	public Integer getPageviews() {
		return this.pageviews;
	}

	public void setPageviews(Integer pageviews) {
		this.pageviews = pageviews;
	}

	public Integer getIps() {
		return this.ips;
	}

	public void setIps(Integer ips) {
		this.ips = ips;
	}

	public Integer getEntrances() {
		return this.entrances;
	}

	public void setEntrances(Integer entrances) {
		this.entrances = entrances;
	}

	public Integer getOutwards() {
		return this.outwards;
	}

	public void setOutwards(Integer outwards) {
		this.outwards = outwards;
	}

	public Integer getExits() {
		return this.exits;
	}

	public void setExits(Integer exits) {
		this.exits = exits;
	}

	public Double getStayTime() {
		return this.stayTime;
	}

	public void setStayTime(Double stayTime) {
		this.stayTime = stayTime;
	}

	public Double getExitRate() {
		return this.exitRate;
	}

	public void setExitRate(Double exitRate) {
		this.exitRate = exitRate;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRecordTime() {
		return this.recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}