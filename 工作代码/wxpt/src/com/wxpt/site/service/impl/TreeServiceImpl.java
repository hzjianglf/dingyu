package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.TreeDao;
import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Role;
import com.wxpt.site.service.TreeService;

public class TreeServiceImpl implements TreeService {
	
	TreeDao treeDao;
	
	public TreeDao getTreeDao() {
		return treeDao;
	}

	public void setTreeDao(TreeDao treeDao) {
		this.treeDao = treeDao;
	}



	@Override
	@Transactional
	public List<Privilege> getprivilegebyId(int id) {
		// TODO Auto-generated method stub
		return treeDao.getprivilegebyId(id);
	}

	@Override
	@Transactional
	public List<Role> rolefindAll(int roleidshezhiint) {
		
		return treeDao.rolefindAll(roleidshezhiint);
	}

	@Override
	@Transactional
	public void roleupdate(Role ro) {
		treeDao.roleupdate(ro);
		
	}

	@Override
	@Transactional
	public void addquanxian(Role rro) {
		// TODO Auto-generated method stub
		treeDao.addquanxian(rro);
	}

	@Override
	@Transactional
	public void updatequanxian(Role rro) {
		treeDao.updatequanxian(rro);
		
	}


	@Override
	@Transactional
	public void deljuese(int roleidshanint) {
		
		treeDao.deljuese(roleidshanint);
	}

	@Override
	@Transactional
	public Privilege getPrivilegebyprivilegeStatement(String gongnengid) {
		// TODO Auto-generated method stub
		return treeDao.getPrivilegebyprivilegeStatement(gongnengid);
	}

	@Override
	@Transactional
	public List<Privilege> getprivilegeparentid(String cunquanxian) {
		// TODO Auto-generated method stub
		return treeDao.getprivilegeparentid(cunquanxian);
	}




	

}
