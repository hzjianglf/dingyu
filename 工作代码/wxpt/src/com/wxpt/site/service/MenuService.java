package com.wxpt.site.service;

import java.util.List;

import com.wxpt.site.entity.Menu;

public interface MenuService {
	//添加菜单
	public void saveMenu(String sql);
	
	//查询
	public List<Menu> getAll(String sql);
	
	//修改
	public void update(String sql,int enterId);
	
	//删除
	public void delete(String sql);
	
	public List<Menu> zfindAll(int enterId);

	public List<Menu> zfindByMenuParentId(int enterId,int menuParentId);
}
