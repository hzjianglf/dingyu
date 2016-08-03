package com.wxpt.action.site;

import com.opensymphony.xwork2.ActionSupport;

public class ManageSearchAction extends ActionSupport{
	private String city;
	private String x;
	private String y;
	private String matter;
	public String getSearch(){
		return "search";
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getMatter() {
		return matter;
	}
	public void setMatter(String matter) {
		this.matter = matter;
	}
}
