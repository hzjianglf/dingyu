package com.whcyz.model;

import com.jfinal.plugin.activerecord.Model;
/**
 * 创业公司
 * @author Administrator
 *
 */
public class Company extends Model<Company> {
	private static final long serialVersionUID = 1L;
	/**
	 * 绑定表
	 */
	public static final String TABLE="company";
	public static Company dao=new Company();
	public Integer getId(){
		return this.get("id");
	}
}
