package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;


public interface RoleService {
	  void deleteEmploree(String ids);
	   int getCount();
	   public boolean isExistName(String sql);
	   int queryCount(String sql) ;
	    int getroleCount(String sql);
		public List<Role> rolefindAll(String sql2,int start, int number);
		List<Privilege> getPrivilege(String privilegeList);

		List<Role> rolefindAll2();
		
		
		/*获取全部角色*/
		public List<Role>  getRole(String sql);
		
		/*角色Id*/
		public Role getById(String sql);
		
		/*权限*/
		public Privilege getByPrivilegeId(String sql);
}
