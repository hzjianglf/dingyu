package com.whcyz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.whcyz.controller.AccountController;
import com.whcyz.model.Account;
import com.whcyz.util.IPUtil;
import com.whcyz.util.MD5;
import com.whcyz.util.SqlKeyword;

public class AccountService extends BaseServiceImpl {
	public static AccountService me=new AccountService();
	@Override
	public String table() {
		return Account.TABLE;
	}

	@Override
	public Model<Account> dao() {
		return Account.dao;
	}

	//返回分页数据
		public Map<String, Object> getListByPage(AccountController c) {
			Map<String, Object> m = new HashMap<String, Object>();
			Page<Account> accountList = new Page<Account>(null, 0, 0, 0, 0);
			String sSearch = c.getPara("sSearch");					//查询内容
			String sSort = c.getPara("sSortDir_0");					//排序方式 desc asc
			int sSortNum = c.getParaToInt("iSortCol_0");			//排序字段序号
			String sSortCol = c.getPara("mDataProp_"+sSortNum);		//排序字段
			int length = c.getParaToInt("iDisplayLength");			//查询长度
			int start = (c.getParaToInt("iDisplayStart")/length)+1;	//开始位置
			String where = "";
			if(StrKit.notBlank(sSearch)){
				where = "(username like '%"+sSearch+"%' or nickname like '%"+sSearch+"%' or phone like '%"+sSearch+"%' or qq like '%"+sSearch+"%' or email like '%"+sSearch+"%')";
			}
			accountList = (Page<Account>) me.paginateWithWhereAndSort(start, length, sSortCol, sSort, SqlKeyword.ALL, where);
			m.put("iTotalDisplayRecords", accountList.getTotalRow());
			m.put("iTotalRecords", accountList.getTotalRow());
			m.put("data", accountList.getList());
			return m;
		}
		/**
		 * 检测登录
		 * @param username
		 * @param password
		 * @return
		 */
		public Account checkLogin(String username, String password) {
			Account account=findFirst("username=? and password=?", username,MD5.MD5Encode(password, "whcyz"));
			if(account==null){
				return null;
			}
			account.remove("password");
			return account;
		}
		
		/**
		 * 注册
		 * @param username
		 * @param password
		 * @return
		 */
		public Account doReg(String username, String password,String loginIp) {
			Account account=new Account().set("username", username).set("nickname", username).set("permission", Account.PERMISSION_NORMAL).set("registerTime", new Date()).set("password",MD5.MD5Encode(password, "whcyz"));
			Date loginTime=new Date();
			account.set("loginTime", loginTime);
			account.set("loginIp", loginIp);
			account.set("lastLoginTime", loginTime);
			account.set("lastLoginIp", loginIp);
			boolean success=this.save(account);
			if(!success){
				return null;
			}
			account.remove("password");
			return account;
		}

		public Account checkOldPassword(Integer id,String password) {
			Account account=findById(id);
			if(account==null){
				return null;
			}
			String pwdmd5=MD5.MD5Encode(password, "whcyz");
			if(!account.getStr("password").equals(pwdmd5)){
				return null;
			}
			return account;
		}



}
