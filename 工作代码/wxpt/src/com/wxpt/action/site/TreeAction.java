package com.wxpt.action.site;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Iterator;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.RoleService;
import com.wxpt.site.service.TreeService;
import com.wxpt.site.service.UserService;

public class TreeAction extends ParentAction {

	TreeService treeService;
	UserService userService;
	ManageUser user;
	private List jsonList;
	JSONArray results = null;
	protected PrintWriter out = null;
	RoleService roleService;

	// /////////////////////类型开始∨∨∨∨∨∨∨∨∨∨∨∨
	public void tree() throws Exception {
	}

	// ///////////////////权限开始∨∨∨∨∨∨∨∨∨∨∨∨

	private List<Privilege> listquanxian;

	private List<Privilege> listquanxian2;
	private List<Privilege> listquanxian3;

	private List<Privilege> listquanxian4;
	private List<Privilege> listquanxian5;
	private List<Privilege> listquanxian6;
	private List<Role> roleList = new ArrayList<Role>();

	public void quanxian() throws Exception {

		jsonList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 0);
		map.put("name", "quanxian");// name
		map.put("text", "权限设置");
		// map.put("checked", "false");

		map.put("state", "open");
		// //map.put("text2",0);
		/*
		 * list = treeService.gettypebyId(0); for(int i=0;i<list.size();i++){
		 */
		map.put("children", quanxian2(0));
		// }
		jsonList.add(map);
		results = JSONArray.fromObject(jsonList);
		super.out.print(results);
		System.out.println(results + "qqqqqqqqqqqqqqqqqq");
		super.out.flush();
		super.out.close();

		/* } */

		// return "tt";
	}

	public List<Map<String, Object>> quanxian2(int id) throws Exception {

		List<Map<String, Object>> childList2 = new ArrayList<Map<String, Object>>();

		listquanxian = treeService.getprivilegebyId(id);

		for (int i = 0; i < listquanxian.size(); i++) {// 2
			Map<String, Object> map = new HashMap<String, Object>();
			int getPrivilegeId = listquanxian.get(i).getPrivilegeId();
			// // int Psm =
			// Integer.parseInt(listquanxian.get(i).getPrivilegeStatement());

			if (treeService.getprivilegebyId(getPrivilegeId).size() != 0) {
				int idn = listquanxian.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian.get(i).getPrivilegeName());// name
				map.put("text", listquanxian.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				map.put("children", quanxian3(idn));

			} else {

				int idn = listquanxian.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian.get(i).getPrivilegeName());// name
				map.put("text", listquanxian.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				// map.put("children", tree3(idn));

			}
			childList2.add(map);
		}
		return childList2;

	}

	public List<Map<String, Object>> quanxian3(int id) throws Exception {

		List<Map<String, Object>> childList2 = new ArrayList<Map<String, Object>>();

		listquanxian2 = treeService.getprivilegebyId(id);

		for (int i = 0; i < listquanxian2.size(); i++) {// 2
			Map<String, Object> map = new HashMap<String, Object>();
			int getPrivilegeId = listquanxian2.get(i).getPrivilegeId();
			// // int Psm =
			// Integer.parseInt(listquanxian2.get(i).getPrivilegeStatement());
			if (treeService.getprivilegebyId(getPrivilegeId).size() != 0) {
				int idn = listquanxian2.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian2.get(i).getPrivilegeName());// name
				map.put("text", listquanxian2.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian2.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "closed");
				map.put("children", quanxian4(idn));

			} else {

				int idn = listquanxian2.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian2.get(i).getPrivilegeName());// name
				map.put("text", listquanxian2.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian2.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				// map.put("children", tree3(idn));

			}
			childList2.add(map);
		}
		return childList2;

	}

	public List<Map<String, Object>> quanxian4(int id) throws Exception {

		List<Map<String, Object>> childList2 = new ArrayList<Map<String, Object>>();

		listquanxian3 = treeService.getprivilegebyId(id);

		for (int i = 0; i < listquanxian3.size(); i++) {// 2
			Map<String, Object> map = new HashMap<String, Object>();
			int getPrivilegeId = listquanxian3.get(i).getPrivilegeId();
			// // int Psm =
			// Integer.parseInt(listquanxian3.get(i).getPrivilegeStatement());
			if (treeService.getprivilegebyId(getPrivilegeId).size() != 0) {
				int idn = listquanxian3.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian3.get(i).getPrivilegeName());// name
				map.put("text", listquanxian3.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				map.put("children", quanxian5(idn));

			} else {

				int idn = listquanxian3.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian3.get(i).getPrivilegeName());// name
				map.put("text", listquanxian3.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				// map.put("children", tree3(idn));

			}
			childList2.add(map);
		}
		return childList2;

	}

	public List<Map<String, Object>> quanxian5(int id) throws Exception {

		List<Map<String, Object>> childList2 = new ArrayList<Map<String, Object>>();

		listquanxian4 = treeService.getprivilegebyId(id);

		for (int i = 0; i < listquanxian4.size(); i++) {// 2
			Map<String, Object> map = new HashMap<String, Object>();
			int getPrivilegeId = listquanxian4.get(i).getPrivilegeId();
			// // int Psm =
			// Integer.parseInt(listquanxian3.get(i).getPrivilegeStatement());
			if (treeService.getprivilegebyId(getPrivilegeId).size() != 0) {
				int idn = listquanxian4.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian4.get(i).getPrivilegeName());// name
				map.put("text", listquanxian4.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				map.put("children", quanxian6(idn));

			} else {

				int idn = listquanxian4.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian4.get(i).getPrivilegeName());// name
				map.put("text", listquanxian4.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				// map.put("children", tree3(idn));

			}
			childList2.add(map);
		}
		return childList2;

	}

	public List<Map<String, Object>> quanxian6(int id) throws Exception {

		List<Map<String, Object>> childList2 = new ArrayList<Map<String, Object>>();

		listquanxian5 = treeService.getprivilegebyId(id);

		for (int i = 0; i < listquanxian5.size(); i++) {// 2
			Map<String, Object> map = new HashMap<String, Object>();
			int getPrivilegeId = listquanxian5.get(i).getPrivilegeId();
			// // int Psm =
			// Integer.parseInt(listquanxian3.get(i).getPrivilegeStatement());
			if (treeService.getprivilegebyId(getPrivilegeId).size() != 0) {
				int idn = listquanxian4.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian4.get(i).getPrivilegeName());// name
				map.put("text", listquanxian4.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				map.put("children", quanxian7(idn));

			} else {

				int idn = listquanxian5.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian5.get(i).getPrivilegeName());// name
				map.put("text", listquanxian5.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				// map.put("children", tree3(idn));

			}
			childList2.add(map);
		}
		return childList2;

	}

	public List<Map<String, Object>> quanxian7(int id) throws Exception {

		List<Map<String, Object>> childList2 = new ArrayList<Map<String, Object>>();

		listquanxian6 = treeService.getprivilegebyId(id);

		for (int i = 0; i < listquanxian6.size(); i++) {// 2
			Map<String, Object> map = new HashMap<String, Object>();
			int getPrivilegeId = listquanxian6.get(i).getPrivilegeId();
			// // int Psm =
			// Integer.parseInt(listquanxian3.get(i).getPrivilegeStatement());
			if (treeService.getprivilegebyId(getPrivilegeId).size() != 0) {
				int idn = listquanxian6.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian6.get(i).getPrivilegeName());// name
				map.put("text", listquanxian6.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");

			} else {

				int idn = listquanxian6.get(i).getPrivilegeId();
				map.put("id", getPrivilegeId);
				map.put("name", listquanxian6.get(i).getPrivilegeName());// name
				map.put("text", listquanxian6.get(i).getPrivilegeName());
				// map.put("text2",
				// listquanxian3.get(i).getPrivilegeStatement());
				// //map.put("text2", Psm);
				// map.put("checked", "false");
				map.put("state", "open");
				// map.put("children", tree3(idn));

			}
			childList2.add(map);
		}
		return childList2;

	}

	// ///////////权限结束∧∧∧∧∧∧∧∧∧

	// ////////////判断权限
	private String idd;
	private String yonghu;

	public String getYonghu() {
		return yonghu;
	}

	public void setYonghu(String yonghu) {
		this.yonghu = yonghu;
	}

	public String getIdd() {
		return idd;
	}

	public void setIdd(String idd) {
		this.idd = idd;
	}

	// 取权限

	private String gongnengid;

	public String getGongnengid() {
		return gongnengid;
	}

	public void setGongnengid(String gongnengid) {
		this.gongnengid = gongnengid;
	}

	// 判断权限
	public void panduanqiuanxian() {
		String value = "";
		yonghu = GetCurrentUser.getCookieValue("wxpts");

		try {

			if (yonghu.equals("admin")) {

				value = "1";
			} else {
				listrole = new ArrayList<Role>();
				/*
				 * Employee employeeshen =
				 * treeService.getEmployeeByName(yonghu); listrole
				 * =treeService.rolefindAll(employeeshen.getRole().getRoleId());
				 */
				String qx = listrole.get(0).getPrivilegeList();
				Privilege pst = treeService
						.getPrivilegebyprivilegeStatement(gongnengid);
				String gongnengid2 = String.valueOf(pst.getPrivilegeId());
				boolean qqxx = qx.contains("," + gongnengid2 + ",");

				if (qqxx == true) {
					value = "1";// 可以用
				} else {
					value = "0";// 不可以用
				}
			}
		} catch (Exception e) {

			// e.printStackTrace();
			value = "-1";
		} finally {
			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);
			} catch (Exception e2) {
			}
		}
	}

	// 设置权限
	private String cunquanxian;// js传过来的权限
	private List<Role> listrole;
	private String roleidshezhi;// jump-quanxian.jsp的dategrid
	private List<Privilege> pri = new ArrayList<Privilege>();
	private Set<String> pri2 = new HashSet<String>();
	String cunquanxian2 = new String();
	String cunquanxianhe = "";
	String cc;

	public void shezhiquanxian() {
		String value = "";
		int roleidshezhiint = Integer.parseInt(roleidshezhi);
		yonghu = GetCurrentUser.getCookieValue("wxpts");
		if (cunquanxian != null && cunquanxian.equals("") == false) {
			pri = treeService.getprivilegeparentid(cunquanxian);

			for (int i = 0; i < pri.size(); i++) {
				String pris = String.valueOf(pri.get(i).getPrivilegeParentId());
				pri2.add(pris);

			}
			Iterator iterator2 = pri2.iterator();
			while (iterator2.hasNext()) {
				String str = (String) iterator2.next();
				System.out.println(str);
				// cunquanxian2+=",";
				// cunquanxian2+=(String)iterator2.next();
			}
			System.out.println(cunquanxian2);

			cc = cunquanxian + ",";
		} else {

			cc = "";
		}

		try {
			Role ro = new Role();
			listrole = treeService.rolefindAll(roleidshezhiint);
			if (!cunquanxian.equals("")) {
				// ro.setEmployees(listrole.get(0).getEmployees());
				System.out.println(cc);
				ro.setPrivilegeList(cc);
				ro.setRoleId(listrole.get(0).getRoleId());
				ro.setRoleName(listrole.get(0).getRoleName());
				ro.setRoleParentId(listrole.get(0).getRoleParentId());
				ro.setRoleStatement(listrole.get(0).getRoleStatement());

			} else {
				// ro.setEmployees(listrole.get(0).getEmployees());
				ro.setPrivilegeList("");
				ro.setRoleId(listrole.get(0).getRoleId());
				ro.setRoleName(listrole.get(0).getRoleName());
				ro.setRoleParentId(listrole.get(0).getRoleParentId());
				ro.setRoleStatement(listrole.get(0).getRoleStatement());

			}
			treeService.roleupdate(ro);
			value = "1";
		} catch (Exception e) {

			e.printStackTrace();
			value = "-1";
		} finally {
			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);
			} catch (Exception e2) {
			}
		}

	}

	// ////////////判断权限end
	public String jump() {
		return "detail";
	}

	// 添加角色
	private String juesename;
	private String jueseshuoming;

	public void addjiaose() {
		String value = "";
		try {
			Role rro = new Role();
			rro.setRoleName(juesename);
			rro.setRoleParentId(1);
			rro.setRoleStatement(jueseshuoming);
			rro.setPrivilegeList("");
			treeService.addquanxian(rro);

			value = "1";

		} catch (Exception e) {

			e.printStackTrace();
			value = "-1";
		} finally {
			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);
			} catch (Exception e2) {
			}
		}

	}

	// 修改角色
	private String roleid;
	private String juesenamex;
	private String jueseshuomingx;

	public void updatejiaose() {

		String value = "";
		try {
			int roleidint = Integer.parseInt(roleid);
			List<Role> rrolist = new ArrayList<Role>();
			rrolist = treeService.rolefindAll(roleidint);
			Role rro2 = new Role();
			rro2.setRoleId(roleidint);
			rro2.setRoleName(juesenamex);
			rro2.setRoleParentId(rrolist.get(0).getRoleParentId());
			rro2.setRoleStatement(jueseshuomingx);
			rro2.setPrivilegeList(rrolist.get(0).getPrivilegeList());
			treeService.updatequanxian(rro2);

			value = "1";

		} catch (Exception e) {

			e.printStackTrace();
			value = "-1";
		} finally {
			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);
			} catch (Exception e2) {
			}
		}
	}

	// 删除角色
	private String roleidshan;
	EnterService enterService;

	public void deljuese() {
		String value = "";
		try {

			int roleidshanint = Integer.parseInt(roleidshan);
			// List<EnterInfor> el = new ArrayList<EnterInfor>();
			String sql = "SELECT * FROM webchat.manage_user WHERE `role_list`="
					+ roleidshan;
			user = userService.getByEnterId(sql);
			/*
			 * String
			 * sql="select * from enter_infor where  role_id="+roleidshan;
			 */
			/* el = (List<EnterInfor>) enterService.getByEnterInfor(sql); */
			if (user!=null) {
				value = "1";
			} else {
				treeService.deljuese(roleidshanint);
				value = "2";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {
			try {
				ServletActionContext.getResponse()
						.setCharacterEncoding("utf-8");
				PrintWriter out = ServletActionContext.getResponse()
						.getWriter();
				out.print(value);
			} catch (Exception e2) {
			}
		}
	}

	// set get

	public String getRoleidshan() {
		return roleidshan;
	}

	public EnterService getEnterService() {
		return enterService;
	}

	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Privilege> getPri() {
		return pri;
	}

	public void setPri(List<Privilege> pri) {
		this.pri = pri;
	}

	public Set<String> getPri2() {
		return pri2;
	}

	public void setPri2(Set<String> pri2) {
		this.pri2 = pri2;
	}

	public String getCunquanxian2() {
		return cunquanxian2;
	}

	public void setCunquanxian2(String cunquanxian2) {
		this.cunquanxian2 = cunquanxian2;
	}

	public String getCunquanxianhe() {
		return cunquanxianhe;
	}

	public void setCunquanxianhe(String cunquanxianhe) {
		this.cunquanxianhe = cunquanxianhe;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setRoleidshan(String roleidshan) {
		this.roleidshan = roleidshan;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getJuesenamex() {
		return juesenamex;
	}

	public void setJuesenamex(String juesenamex) {
		this.juesenamex = juesenamex;
	}

	public String getJueseshuomingx() {
		return jueseshuomingx;
	}

	public void setJueseshuomingx(String jueseshuomingx) {
		this.jueseshuomingx = jueseshuomingx;
	}

	public String getJuesename() {
		return juesename;
	}

	public void setJuesename(String juesename) {
		this.juesename = juesename;
	}

	public String getJueseshuoming() {
		return jueseshuoming;
	}

	public void setJueseshuoming(String jueseshuoming) {
		this.jueseshuoming = jueseshuoming;
	}

	public TreeService getTreeService() {
		return treeService;
	}

	public void setTreeService(TreeService treeService) {
		this.treeService = treeService;
	}

	public List<Map<String, Object>> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<Map<String, Object>> jsonList) {
		this.jsonList = jsonList;
	}

	public JSONArray getResults() {
		return results;
	}

	public void setResults(JSONArray results) {
		this.results = results;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public List<Privilege> getListquanxian() {
		return listquanxian;
	}

	public void setListquanxian(List<Privilege> listquanxian) {
		this.listquanxian = listquanxian;
	}

	public List<Privilege> getListquanxian2() {
		return listquanxian2;
	}

	public void setListquanxian2(List<Privilege> listquanxian2) {
		this.listquanxian2 = listquanxian2;
	}

	public List<Privilege> getListquanxian3() {
		return listquanxian3;
	}

	public void setListquanxian3(List<Privilege> listquanxian3) {
		this.listquanxian3 = listquanxian3;
	}

	public List<Privilege> getListquanxian4() {
		return listquanxian4;
	}

	public void setListquanxian4(List<Privilege> listquanxian4) {
		this.listquanxian4 = listquanxian4;
	}

	public List<Privilege> getListquanxian5() {
		return listquanxian5;
	}

	public void setListquanxian5(List<Privilege> listquanxian5) {
		this.listquanxian5 = listquanxian5;
	}

	public List<Privilege> getListquanxian6() {
		return listquanxian6;
	}

	public void setListquanxian6(List<Privilege> listquanxian6) {
		this.listquanxian6 = listquanxian6;
	}

	public String getRoleidshezhi() {
		return roleidshezhi;
	}

	public void setRoleidshezhi(String roleidshezhi) {
		this.roleidshezhi = roleidshezhi;
	}

	public List<Role> getListrole() {
		return listrole;
	}

	public void setListrole(List<Role> listrole) {
		this.listrole = listrole;
	}

	public String getCunquanxian() {
		return cunquanxian;
	}

	public void setCunquanxian(String cunquanxian) {
		this.cunquanxian = cunquanxian;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
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

}
