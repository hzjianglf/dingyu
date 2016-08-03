package com.whcyz.model;

import com.jfinal.plugin.activerecord.Model;

public class Station extends Model<Station>{
	private static final long serialVersionUID = 1L;
	/**
	 * 绑定表
	 */
	public static final String TABLE="t_station";
	public static Station dao=new Station();
	public Integer getId(){
		return this.get("n_id");
	}
	
}
