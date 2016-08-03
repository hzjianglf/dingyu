package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;


public interface TreeService {
	public List<Privilege> getprivilegebyId(int id);

	public List<Role> rolefindAll(int roleidshezhiint);

	public void roleupdate(Role ro);

	public void addquanxian(Role rro);

	public void updatequanxian(Role rro);

	public void deljuese(int roleidshanint);

	public Privilege getPrivilegebyprivilegeStatement(String gongnengid);

	public List<Privilege> getprivilegeparentid(String cunquanxian);

	



	

	
}
