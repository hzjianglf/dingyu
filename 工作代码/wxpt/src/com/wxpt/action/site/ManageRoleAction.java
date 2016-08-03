package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.wxpt.site.entity.pojo.*;
import com.wxpt.site.entity.*;
import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.site.service.RoleService;
@SuppressWarnings("serial")   
@ParentPackage("json-default") 
/*@Results({ 
	 @Result(name="aaa", type="json", params={"root","result"})
	 
})*/


public class ManageRoleAction extends ParentAction {
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	JSONArray jsona;
	RoleService roleService;
	private String rows;//每页显示的记录数  
 	private String page;//当前第几页  
 	private int listCount;	//总数据条数
 	private int pageCount;//总页数
	private int currentpage=1;//当前页
	
    ////////获取角色	
   List<Role> listrole;
  /* GetCookie cookies=new GetCookie();
	int enterId=cookies.getCookie();*/
	//int enterId=62;
	//查权限角色
	public void find() {
         String sql="select * from webchat.role";
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
		
			e.printStackTrace();
		}

		int intPage = Integer.parseInt((page == null || page == "0") ? "1":page); //当前第几页 //每页显示条数  
	    int number = Integer.parseInt((rows == null || rows == "0") ? "10":rows);//每页显示条数
	    int start = (intPage-1)*number;
		listCount=roleService.getroleCount( sql);//一共得条数
		String sql2="select * from webchat.role ";
	    listrole = roleService.rolefindAll(sql2,intPage-1,number);
	    List<Role2> listrole2 = new ArrayList<Role2>();
	     System.out.println(listrole.size());
	    List<Privilege> p2;
	    String pp2="";
	    for(int i=0;i<listrole.size();i++){
	    	  p2 = new ArrayList<Privilege>();
	    	pp2 = new String(); 
	    	Role2 r2 = new Role2();
	    	//r2.setEmployees(listrole.get(i).getEnterInfor());
	    	//r2.setEmployees(listrole.get(i).get)
	    	r2.setRoleId(listrole.get(i).getRoleId());
	    	r2.setRoleName(listrole.get(i).getRoleName());
	    	r2.setRoleParentId(listrole.get(i).getRoleParentId());
	    	r2.setRoleStatement(listrole.get(i).getRoleStatement());
	    	System.out.println(listrole.get(i).getPrivilegeList());
	    	String s = listrole.get(i).getPrivilegeList();
	    	
	    	
	    		if(s.length()!=0){
	    			String s1 = s.substring(0,s.lastIndexOf(","));	
		       	   p2	=roleService.getPrivilege(s1);
		       	   System.out.println(p2);
			       	if(p2.size()!=0){
		       		 for(int j=0;j<p2.size();j++){ 
			    		 //pppp=p2.get(index)
		       			 System.out.println(p2.get(j).getPrivilegeName());
			    		 pp2 += p2.get(j).getPrivilegeName()+",";
			    	 } 
		       	   } 
		    	}else{
		    		 pp2= "该角色无权限";
		    	}
	    	
	   

	    	r2.setPrivilegeList(pp2);
	    	 listrole2.add(r2);
	    	 System.out.println(listrole2);
	    }
	    
	    
		if (listCount%number==0) {
			pageCount=listCount/number;
		}
		else {
			pageCount=listCount/number+1;
		}		
	  //  System.out.println(listd.size()+"fddffdf");
		JsonConfig jsonConfig=new JsonConfig(); 
		jsonConfig.setExcludes(new String[]{"employees"}); 
		JSONArray jsonArrayFromList  = JSONArray.fromObject(listrole2,jsonConfig); 
	    System.out.println(jsonArrayFromList.toString());
	       out.print("{\"total\":"+listCount+",\"rows\":" + jsonArrayFromList.toString() + "}");
		   out.flush();
		   out.close();

	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpServletResponse getResponse() {
		return response;
	}


	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}


	public JSONArray getJsona() {
		return jsona;
	}


	public void setJsona(JSONArray jsona) {
		this.jsona = jsona;
	}


	public RoleService getRoleService() {
		return roleService;
	}


	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public int getListCount() {
		return listCount;
	}


	public void setListCount(int listCount) {
		this.listCount = listCount;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getCurrentpage() {
		return currentpage;
	}


	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}


	public List<Role> getListrole() {
		return listrole;
	}


	public void setListrole(List<Role> listrole) {
		this.listrole = listrole;
	}
	
	
	
	
}
