package com.wxpt.action.site;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.site.dao.MenuDao;
import com.wxpt.site.entity.Menu;
import com.wxpt.site.entity.pojo.MenuP;
import com.wxpt.site.service.MenuService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({
		@Result(name = "success", type = "json", params = { "root", "result" }),
		@Result(name = "munu", location = "success.jsp") })
public class ManageMenuAction extends ParentAction {
	MenuService menuService;
	MenuDao menuDao;
	private String menuName;
	JSONArray jsonls;
	int count = 0;// 总记录数
	int rows;
	int page;
	int menuId;
	int menuParentId;
	private String  pId;
	String message;
	String menuType;
	String menuUrl;
	String menuKey;
	String menuIds;
	List<Menu> menuList = new ArrayList<Menu>();
	List<MenuP> menuAdd = new ArrayList<MenuP>();
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();
	String sql = "SELECT * FROM wxpt" + enterId + ".menu ";

	// 添加主菜单
	public void saveMenu() {
			String sql = "INSERT INTO wxpt" + enterId
					+ ".menu( `menu_name`,  `menu_parent_id`) " + "VALUES ('"
					+ menuName + "',0)";
		try {
			menuService.saveMenu(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void saveMenu2(){
	System.out.println(menuName);
		String sql="INSERT INTO wxpt"+enterId+".menu( `menu_name`, `menu_type`, `menu_parent_id`, `menu_url`, `menu_key`)" +
				" VALUES ('"+menuName+"','"+menuType+"',"+menuId+",'"+menuUrl+"','"+menuKey+"')";
	
	try {
		menuService.saveMenu(sql);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	// 查询
	public void selectAll() {
		sql +=" where `menu_parent_id`=0";
		menuList = menuService.getAll(sql);
		if (menuList != null) {
			for (int i = 0; i < menuList.size(); i++) {
				MenuP mp = new MenuP();
				mp.setMenuId(menuList.get(i).getMenuId());
				mp.setMenuName(menuList.get(i).getMenuName());
				if (i == 0) {
					mp.setMenuNum("菜单一");
				}
				if (i == 1) {
					mp.setMenuNum("菜单二");
				}
				if (i == 2) {
					mp.setMenuNum("菜单三");
				}
				menuAdd.add(mp);
			}
			count = menuList.size();
		}else{
			count=0;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonls = JSONArray.fromObject(menuAdd, jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
	}

	public String getCount() {
		int state = 0;
		sql += " where menu_parent_id=0";
		try {
			menuList = menuService.getAll(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (menuList != null) {
			int count = menuList.size();
			if (count == 1) {
				message = "菜单二";
			}
			if (count == 2) {
				message = "菜单三";
			}
			if (count >= 3) {
				message = "最多三个菜单";
				state = 1;
			}
		} else {
			count = 0;
			message = "菜单一";
		}

		super.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}

	// 修改主菜单
	public void updateMenu() {
		System.out.println(menuId);
		String sql = "UPDATE wxpt" + enterId + ".menu SET `menu_name`='"
				+ menuName + "' WHERE `menu_id`=" + menuId;
		try {
			menuDao.update(sql, enterId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 查询子菜单
	public void slectChild() {
		sql += " where menu_parent_id=" + menuId;
		menuList = menuService.getAll(sql);
		if (menuList != null) {
			for (int i = 0; i < menuList.size(); i++) {
				MenuP mp = new MenuP();
				mp.setMenuId(menuList.get(i).getMenuId());
				mp.setMenuName(menuList.get(i).getMenuName());
				mp.setMenuKey(menuList.get(i).getMenuKey());
				mp.setMenuUrl(menuList.get(i).getMenuUrl());
				mp.setMenuType(menuList.get(i).getMenuType());
				if (i == 0) {
					mp.setMenuNum("子菜单一");
				}
				if (i == 1) {
					mp.setMenuNum("子菜单二");
				}
				if (i == 2) {
					mp.setMenuNum("子菜单三");
				}if(i==3){
					mp.setMenuNum("子菜单四");
				}if(i==4){
					mp.setMenuNum("子菜单五");
				}
				menuAdd.add(mp);
			}
			count = menuList.size();
		}else{
			count=0;
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonls = JSONArray.fromObject(menuAdd, jsonConfig);
			System.out.println(jsonls);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		
	}
	
	public String chileCount(){

		int state = 0;
		sql += " where menu_parent_id="+menuId;
		menuList = menuService.getAll(sql);
		if (menuList != null) {
			int count = menuList.size();
			if (count == 1) {
				message = "子菜单二";
			}
			if (count == 2) {
				message = "子菜单三";
			}
			if (count == 3) {
				message = "子菜单四";
			}
			if (count == 4) {
				message = "子菜单五";
			}if(count==5){
				message="最多五个子菜单";
				state=1;
			}
		} else {
			count = 0;
			message = "菜单一";
		}

		super.out.print("{\"message\":\"" + message + "\",\"state\":\"" + state
				+ "\"}");
		super.out.flush();
		super.out.close();
		return "success";
	}
	public void updateChilMenu(){

	System.out.println(menuId);
	String sql = "UPDATE wxpt"+enterId+".menu SET `menu_name`='"+menuName+"',`menu_type`='"+menuType+"'," +
			"`menu_parent_id`="+menuParentId+",`menu_url`='"+menuUrl+"',`menu_key`='"+menuKey+"' WHERE `menu_id`="+menuId;
	try {
		menuDao.update(sql, enterId);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
}
	public void deleMenu(){
		System.out.println(menuIds);
		String menuId[]=menuIds.split(",");
		for(int i=0;i<menuId.length;i++){
			String sql="DELETE FROM wxpt"+enterId+".menu WHERE `menu_id`="+menuId[i];
			menuService.delete(sql);
		}
	}
	
	public void deleteChilMenu(){
		String sql="DELETE FROM wxpt"+enterId+".menu WHERE `menu_id`="+menuId;
		menuService.delete(sql);
	}
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<MenuP> getMenuAdd() {
		return menuAdd;
	}

	public void setMenuAdd(List<MenuP> menuAdd) {
		this.menuAdd = menuAdd;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}


	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuKey() {
		return menuKey;
	}

	public void setMenuKey(String menuKey) {
		this.menuKey = menuKey;
	}
	public int getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(int menuParentId) {
		this.menuParentId = menuParentId;
	}
	public String getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

}
