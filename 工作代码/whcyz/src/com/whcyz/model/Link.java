package com.whcyz.model;

import com.jfinal.plugin.activerecord.Model;
/**
 * 友情链接
 * @author 牟孟孟
 *
 */
public class Link extends Model<Link> {
	private static final long serialVersionUID = 1L;
	/**
	 * 绑定表
	 */
	public static final String TABLE="link";
	public static Link dao=new Link();
	public Integer getId(){
		return this.get("id");
	}
}
