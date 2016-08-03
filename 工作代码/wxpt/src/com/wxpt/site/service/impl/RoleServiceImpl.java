package com.wxpt.site.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.RoleDao;
import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;
import com.wxpt.site.service.RoleService;



public class RoleServiceImpl implements RoleService {
	RoleDao roleDao;
	@Override
	@Transactional
	public void deleteEmploree(String ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public boolean isExistName(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public int queryCount(String sql) {
		// TODO Auto-generated method stub
		return roleDao.queryCount(sql);
	}

	@Override
	@Transactional
	public int getroleCount(String sql) {
		// TODO Auto-generated method stub
		return roleDao.getroleCount(sql);
	}

	@Override
	@Transactional
	public List<Role> rolefindAll(String sql2,int start, int number) {
		// TODO Auto-generated method stub
		return roleDao.rolefindAll(sql2, start, number);
	}

	@Override
	@Transactional
	public List<Privilege> getPrivilege(String privilegeList) {
		// TODO Auto-generated method stub
		return roleDao.getPrivilege(privilegeList);
	}

	@Override
	@Transactional
	public List<Role> rolefindAll2() {
		// TODO Auto-generated method stub
		return null;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> getRole(String sql) {
		// TODO Auto-generated method stub
		return roleDao.getRole(sql);
	}
	@Transactional
	@Override
	public Role getById(String sql) {
		// TODO Auto-generated method stub
		return roleDao.getById(sql);
	}
	@Transactional
	@Override
	public Privilege getByPrivilegeId(String sql) {
		// TODO Auto-generated method stub
		return roleDao.getByPrivilegeId(sql);
	}
	
	
	
	
	
}
