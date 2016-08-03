package com.whcyz.model;

import java.util.Date;

import com.jfinal.plugin.activerecord.Model;
import com.whcyz.util.DateUtil;
/**
 * 网站账户 用户
 * @author 牟孟孟
 *
 */
public class Account extends Model<Account> {
	private static final long serialVersionUID = 1L;
	/**
	 * 绑定表
	 */
	public static final String TABLE="account";
	public static Account dao=new Account();
	/**
	 * 账户类型定义 个人账户
	 */
	public static final int TYPE_PERSON=1;
	/**
	 * 账户类型定义 企业账户
	 */
	public static final int TYPE_CONPANY=2;
	
	/**
	 * 权限组-超管
	 */
	public static final int PERMISSION_ADMIN=1;
	/**
	 * 权限组-普通用户
	 */
	public static final int PERMISSION_NORMAL=2;
	/**
	 * 权限组-编辑
	 */
	public static final int PERMISSION_EDITOR=3;
	/**
	 * 权限组-所有  用户permissioncheck
	 */
	public static final int PERMISSION_ALL=-1;
	public Integer getId(){
		return this.get("id");
	}
	public String getUsername(){
		return this.get("username");
	}
	
	public String getNickname(){
		return this.get("nickname");
	}
	public void setNickname(String nickname){
		this.set("nickname",nickname);
	}
	public Integer getPermission(){
		return this.get("permission");
	}


}
