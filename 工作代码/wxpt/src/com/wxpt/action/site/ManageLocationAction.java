package com.wxpt.action.site;

import com.opensymphony.xwork2.ActionSupport;

public class ManageLocationAction extends ActionSupport{
	private String starxy;
	private String endxy;
	private String starx;
	private String stary;
	private String endx;
	private String endy;
	private String zhuxy;
	private String starName;
	private String endName;
	private String zhux;
	private String zhuy;
	public String daohang(){
		String [] star=starxy.split(";");
		starx=star[0];
		stary=star[1];
		String [] end=endxy.split(";");
		endx=end[0];
		endy=end[1];
		String [] zhu=zhuxy.split(";");
		zhux=zhu[0];
		zhuy=zhu[1];
		return "daohang";
	}
	public String getStarxy() {
		return starxy;
	}
	public void setStarxy(String starxy) {
		this.starxy = starxy;
	}
	public String getEndxy() {
		return endxy;
	}
	public void setEndxy(String endxy) {
		this.endxy = endxy;
	}
	public String getStarx() {
		return starx;
	}
	public void setStarx(String starx) {
		this.starx = starx;
	}
	public String getStary() {
		return stary;
	}
	public void setStary(String stary) {
		this.stary = stary;
	}
	public String getEndx() {
		return endx;
	}
	public void setEndx(String endx) {
		this.endx = endx;
	}
	public String getEndy() {
		return endy;
	}
	public void setEndy(String endy) {
		this.endy = endy;
	}
	public String getStarName() {
		return starName;
	}
	public void setStarName(String starName) {
		this.starName = starName;
	}
	public String getEndName() {
		return endName;
	}
	public void setEndName(String endName) {
		this.endName = endName;
	}
	public String getZhuxy() {
		return zhuxy;
	}
	public void setZhuxy(String zhuxy) {
		this.zhuxy = zhuxy;
	}
	public String getZhux() {
		return zhux;
	}
	public void setZhux(String zhux) {
		this.zhux = zhux;
	}
	public String getZhuy() {
		return zhuy;
	}
	public void setZhuy(String zhuy) {
		this.zhuy = zhuy;
	}
	
}
