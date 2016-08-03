package com.wxpt.action.site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.JDBC_test;
import com.wxpt.site.dao.EnterDao;
import com.wxpt.site.dao.impl.EnterDaoImpl;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;
import com.wxpt.site.entity.pojo.EnterInforp;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.RoleService;
import com.wxpt.site.service.UserService;

@Results({ @Result(name = "success", type = "json", params = { "root", "result" }) })
@ParentPackage("json-default")
public class ManageEnterAction extends ParentAction {
	JSONArray jsonls;
	int count;// 总记录数
	int rows;
	int page;
	int roleId;
	Role role;
	EnterService enterService;
	RoleService roleService;
	UserService userService;
	ManageUser user;
	EnterDao enterDao;
	Privilege privilege;
	private String enterId;
	private String queryName;
	private String startTime;
	private String endTime;
	private String userName;
	private String enterName;
	private String enter;
	private String url;
	private String token;
	private String phone;
	private String email;
	private String address;
	private String state;
	private int enId;
	EnterInfor enters;
	List<Role> roleList = new ArrayList<Role>();
	List<EnterInforp> enterList = new ArrayList<EnterInforp>();
	List<String> lists = new ArrayList<String>();
	JDBC_test jdbc = new JDBC_test();
	Connection con = null;
	PreparedStatement pstmt = null;

	public String getList() {
		String sql = "SELECT * FROM webchat.enter_infor WHERE `enter_infor_id` in(SELECT `enterid` FROM  webchat.manage_user WHERE `user_type`=1)";
		// String sql = "SELECT * FROM webchat.enter_infor where 1=1";
		if (queryName != null && queryName != ""
				&& queryName.equals("") == false) {
			System.out.println(queryName);
			sql += " and enter_name  LIKE  '%" + queryName + "%'";
		} else if (startTime != null && endTime != null
				&& !startTime.equals("点击此处选择时间") && !endTime.equals("点击此处选择时间")) {
			startTime = startTime + " 00:00:00";
			endTime = endTime + " 23:59:59";
			sql += " and datediff(str_to_date(regist_time,'%Y/%m/%d %H:%i:%s'),str_to_date('"
					+ startTime
					+ "','%Y/%m/%d %H:%i:%s') )>=0 and datediff(str_to_date('"
					+ endTime
					+ "','%Y/%m/%d %H:%i:%s'),str_to_date(regist_time,'%Y/%m/%d %H:%i:%s') )>=0 ";
		}
		List<EnterInfor> list = enterService.getAll(sql, page, rows);
		for (int i = 0; i < list.size(); i++) {
			/*
			 * String sql=""; String
			 * sql0="SELECT * FROM webchat.manage_user WHERE `enterid`="
			 * +list.get(i).getEnterInforId()+" and user_type=1";
			 * user=userService.getByEnterId(sql0); if(user.getUserType()==1){
			 */
			EnterInforp ep = new EnterInforp();
			ep.setAppId(list.get(i).getAppId());
			ep.setAppSecret(list.get(i).getAccessToken());
			ep.setAccessToken(list.get(i).getAccessToken());
			ep.setEmail(list.get(i).getEmail());
			ep.setEnterAddress(list.get(i).getEnterAddress());
			ep.setEnterInforId(list.get(i).getEnterInforId());
			ep.setEnterName(list.get(i).getEnterName());
			// ep.setEnterPerson(list.get(i).getEnterPerson());
			ep.setEnterPhone(list.get(i).getEnterPhone());
			ep.setPowerCode(list.get(i).getPowerCode());
			ep.setPowerTime(list.get(i).getPowerTime());
			ep.setRegistTime(list.get(i).getRegistTime());
			ep.setState(list.get(i).getState());
			ep.setToken(list.get(i).getToken());
			ep.setUrl(list.get(i).getUrl());
			ep.setAppId(list.get(i).getAppId());
			ep.setAppSecret(list.get(i).getAppSecret());
			ep.setBanenr("<img  width='180' height='180' src='/wxpt/web/images/"
					+ list.get(i).getBanner() + "' ///>");
			String sql1 = "SELECT * FROM webchat.manage_user WHERE `enterid`="
					+ list.get(i).getEnterInforId();
			user = userService.getByEnterId(sql1);
			ep.setUserName(user.getManageUserName());
			String sql2 = "SELECT * FROM webchat.role WHERE `role_id`="
					+ user.getRoleList();
			role = roleService.getById(sql2);
			ep.setRoleId(role.getRoleName());
			if (user.getStatus() == 0) {
				ep.setZhuangtai("正常");
			} else {
				ep.setZhuangtai("关闭");
			}
			if (list.get(i).getEnterPerson() == null
					|| list.get(i).getEnterPerson().equals("null")) {
				ep.setYonghu("正常用户");
			} else {
				ep.setQixian(list.get(i).getEnterPerson());
				ep.setYonghu("试用用户");
				endTime = this.addDate(list.get(i).getRegistTime(),
						Integer.parseInt(list.get(i).getEnterPerson()));
				ep.setEnterPerson(endTime);
			}

			/*
			 * String privileges = role.getPrivilegeList(); String quanxian =
			 * ""; if (privileges.equals("null")) { quanxian = null; } else {
			 * String[] p = privileges.split(","); for (int j = 0; j < p.length;
			 * j++) { System.out.println(p[j]); String sql3 =
			 * "SELECT * FROM webchat.privilege WHERE `privilege_id`=" + p[j];
			 * privilege = roleService.getByPrivilegeId(sql3); if (j == 0) {
			 * quanxian += "1、" + privilege.getPrivilegeName(); } else {
			 * quanxian += "," + (j + 1) + "、" + privilege.getPrivilegeName(); }
			 * 
			 * } } ep.setPrivelege(quanxian);
			 */
			enterList.add(ep);
			/* } */
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			// jsonConfig.setExcludes(new String[] { "userTemplates" });
			jsonls = JSONArray.fromObject(enterList);
			String str = jsonls.toString();
			// System.out.println(str.indexOf(",\"productState\":2"));
			str = str.replaceAll(",\"state\":0", ",\"state\":\"授权未通过\"");
			str = str.replaceAll(",\"state\":1", ",\"state\":\"授权通过\"");
			// count = enterService.enterCount(sql);

			count = enterService.getCount(sql).size();
			super.out.print("{\"total\":" + count + ",\"rows\":"
					+ str.toString() + "}");
			super.out.flush();
			super.out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		return "success";
	}

	public static String addDate(String dateStr, int Month) {
		Calendar ca = Calendar.getInstance();
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dd;
		try {
			dd = sdf.parse(dateStr);
			ca.setTime(dd);
			ca.add(Calendar.MONTH, Month);
			s = sdf.format(ca.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

	@SuppressWarnings("null")
	public String delete() {
		String ids[] = enterId.split(",");
		for (int i = 0; i < ids.length; i++) {
			enters = enterService.getById(enId);
			String qyIds = enters.getChilendQyId();
			if (qyIds == null || qyIds.equals("") || qyIds.equals("null")) {

			} else {

				String[] qy = qyIds.split(";");
				String chileId = "";
				for (int j = 0; j < qy.length; j++) {
					if (Integer.parseInt(qy[j]) == Integer.parseInt(ids[i])) {
						chileId = chileId;
					} else {
						chileId += qy[j] + ";";
					}
				}
				String update = "UPDATE webchat.enter_infor SET `chilend_qy_id`='"
						+ chileId + "' WHERE `enter_infor_id`=" + enId;
				enterService.updateVshopBanenr(update);

			}
			String sql0 = "DELETE FROM webchat.enter_infor WHERE `enter_infor_id`="
					+ Integer.parseInt(ids[i]);
			enterService.deleEnter(sql0);
			String sql1 = "DELETE FROM webchat.manage_user WHERE `enterid`="
					+ Integer.parseInt(ids[i]);
			enterService.deleEnter(sql1);
			// 删除数据库
			String delete = "drop database wxpt" + Integer.parseInt(ids[i])
					+ "";
			con = jdbc.getConnection2();
			try {
				pstmt = con.prepareStatement(delete);
				pstmt.executeUpdate();
				con.close(); // 关闭数据库连接
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "success";
	}

	// 更新
	public String update() {
		System.out.println(enterId);
		EnterInfor enterInfor = enterService.getById(Integer.parseInt(enterId));
		enterInfor.setEmail(email);
		enterInfor.setEnterAddress(address);
		enterInfor.setEnterName(enter);
		enterInfor.setEnterPerson(enterName);
		enterInfor.setToken(token);
		enterInfor.setEnterPhone(phone);
		enterInfor.setUrl(url);
		enterInfor.setUserName(userName);
		System.out.println(state);
		if (state.equals("授权未通过")) {
			enterInfor.setState(0);

		} else if (state.equals("授权通过")) {
			enterInfor.setState(1);
		}
		String sql = "UPDATE enter_infor SET `user_name`='"
				+ enterInfor.getEnterName() + "',`enter_name`='"
				+ enterInfor.getEnterName() + "',`enter_person`='"
				+ enterInfor.getEnterPerson() + "',`enter_phone`='"
				+ enterInfor.getEnterPhone() + "',`enter_address`='"
				+ enterInfor.getEnterAddress() + "'," + "`email`='"
				+ enterInfor.getEmail() + "',`url`='" + enterInfor.getUrl()
				+ "',`token`='" + enterInfor.getToken() + "', state='"
				+ enterInfor.getState() + "' WHERE `enter_infor_id`="
				+ enterInfor.getEnterInforId() + "";
		enterDao.updateEnter(sql);
		// enterService.updateEnter(enterInfor);
		return "success";
	}

	// 获取角色
	public void getRole() {
		String sql = "SELECT * FROM webchat.role ";
		String str;
		try {
			roleList = roleService.rolefindAll(sql, page - 1, rows);
			JsonConfig jsonConfig = new JsonConfig();
			jsonls = JSONArray.fromObject(roleList);
			count = roleService.getRole(sql).size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
	}

	public void updateRole() {
		/*
		 * String sql = "UPDATE  webchat.enter_infor SET `role_id` =" + roleId +
		 * " WHERE `enter_infor_id`=" + enterId;
		 */
		String sql = "UPDATE webchat.manage_user SET `role_list`=" + roleId
				+ " WHERE `enterid`=" + enterId;
		try {
			enterDao.updateEnter(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String qixian;

	// 修改期限
	public void updateQiXian() {
		if(qixian==""||qixian.equals("")){
			qixian="null";
		}
		String sql = "UPDATE webchat.enter_infor SET `enter_person`='" + qixian
				+ "' WHERE `enter_infor_id`=" + enterId;
		enterService.updateVshopBanenr(sql);
	}

	// 修改状态
	public void updateState() {
		String sql;
		String sql1 = "SELECT * FROM webchat.manage_user WHERE `enterid`="
				+ enterId;
		user = userService.getByEnterId(sql1);
		if (user.getStatus() == 0) {
			sql = "UPDATE webchat.manage_user SET `status`=1 WHERE `enterid`="
					+ enterId;
		} else {
			sql = "UPDATE webchat.manage_user SET `status`=0 WHERE `enterid`="
					+ enterId;
		}
		enterService.updateVshopBanenr(sql);

	}

	public String getQixian() {
		return qixian;
	}

	public void setQixian(String qixian) {
		this.qixian = qixian;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public EnterService getEnterService() {
		return enterService;
	}

	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getEnterId() {
		return enterId;
	}

	public void setEnterId(String enterId) {
		this.enterId = enterId;
	}

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnterName() {
		return enterName;
	}

	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}

	public String getEnter() {
		return enter;
	}

	public void setEnter(String enter) {
		this.enter = enter;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String find() {
		System.out.println("Ds");
		return "dsdssd";
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public EnterDao getEnterDao() {
		return enterDao;
	}

	public void setEnterDao(EnterDao enterDao) {
		this.enterDao = enterDao;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ManageUser getUser() {
		return user;
	}

	public void setUser(ManageUser user) {
		this.user = user;
	}

	public int getEnId() {
		return enId;
	}

	public void setEnId(int enId) {
		this.enId = enId;
	}

}
