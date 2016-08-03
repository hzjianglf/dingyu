package com.whcyz.model;

import com.jfinal.plugin.activerecord.Model;
/**
 * 创业人物
 * @author Administrator
 *
 */
public class Person extends Model<Person> {
	private static final long serialVersionUID = 1L;
	/**
	 * 绑定表
	 */
	public static final String TABLE="person";
	public static Person dao=new Person();
	public Integer getId(){
		return this.get("id");
	}
	public Integer getCompanyId(){
		return this.get("companyId");
	}
}
