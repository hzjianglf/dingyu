package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.MenuDao;
import com.wxpt.site.entity.Menu;
import com.wxpt.site.service.MenuService;

public class MenuServiceImpl implements MenuService{
	MenuDao menuDao;
	@Transactional
	@Override
	public void saveMenu(String sql) {
		// TODO Auto-generated method stub
		menuDao.saveMenu(sql);
	}
	public MenuDao getMenuDao() {
		return menuDao;
	}
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}
	@Transactional
	@Override
	public List<Menu> getAll(String sql) {
		// TODO Auto-generated method stub
		return menuDao.getAll(sql);
	}
	@Transactional
	@Override
	public void update(String sql,int enterId) {
		// TODO Auto-generated method stub
		menuDao.update(sql,enterId);
	}
	@Transactional
	@Override
	public void delete(String sql) {
		// TODO Auto-generated method stub
		menuDao.delete(sql);
	}
	@Transactional
	public List<Menu> zfindAll(int enterId) {
		// TODO Auto-generated method stub
		return menuDao.zfindAll(enterId);
	}
	@Transactional
	public List<Menu> zfindByMenuParentId(int enterId,int menuParentId) {
		// TODO Auto-generated method stub
		return menuDao.zfindByMenuParentId(enterId,menuParentId);
	}
	

}
