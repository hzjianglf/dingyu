package com.wxpt.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.wxpt.site.dao.WebSiteDao;
import com.wxpt.site.entity.Page;
import com.wxpt.site.entity.PageStatistics;
import com.wxpt.site.entity.TemplateMenuType;
import com.wxpt.site.entity.TemplatePageProperty;
import com.wxpt.site.entity.TemplateProperty;
import com.wxpt.site.entity.Templates;
import com.wxpt.site.entity.UserSiteMenu;
import com.wxpt.site.entity.UserSiteOption;
import com.wxpt.site.entity.UserSitePage;
import com.wxpt.site.entity.UserSitePagemeta;
import com.wxpt.site.entity.UserTemplate;
import com.wxpt.site.service.WebSiteService;

public class WebSiteServiceImpl extends ParentServieImpl implements WebSiteService {
	@Autowired
	WebSiteDao webSiteDao;

	@Transactional
	@Override
	public List<Templates> getTemplates(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return webSiteDao.getTemplates(sql, page, rows);
	}

	@Transactional
	@Override
	public int getTemplatesCount(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getTemplatesCount(sql);
	}

	@Transactional
	@Override
	public UserTemplate getUserTemplate(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getUserTemplate(sql);
	}

	@Transactional
	@Override
	public int executeSql(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.executeSql(sql);
	}

	@Transactional
	@Override
	public TemplateProperty getTemplateProperty(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getTemplateProperty(sql);
	}

	@Transactional
	@Override
	public List<UserSiteMenu> getUserSiteMenuList(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getUserSiteMenuList(sql);
	}

	@Transactional
	@Override
	public List<UserSiteMenu> getUserSiteMenuList(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return webSiteDao.getUserSiteMenuList(sql, page, rows);
	}

	@Transactional
	@Override
	public List<UserSiteOption> getUserOptionList(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getUserOptionList(sql);
	}

	@Transactional
	@Override
	public TemplateMenuType getTemplateMenuType(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getTemplateMenuType(sql);
	}

	@Transactional
	@Override
	public List<UserSitePage> getUserSitePageList(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return webSiteDao.getUserSitePageList(sql, page, rows);
	}
	@Transactional
	@Override
	public UserSitePagemeta getUserSitePageMetaList(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getUserSitePageMetaList(sql);
	}
	@Override
	public TemplatePageProperty getTemplatePagePropery(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getTemplatePagePropery(sql);
	}
	@Transactional
	@Override
	public List<UserSitePagemeta> getSitePageMetaList(String sql) {
		// TODO Auto-generated method stub
		return webSiteDao.getSitePageMetaList(sql);
	}

	@Override
	public List<Page> getPageLists(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return webSiteDao.getPageLists(sql,page,rows);
	}

	@Override
	public List<PageStatistics> getpageStatisticLists(String sql, int page,
			int rows) {
		// TODO Auto-generated method stub
		return webSiteDao.getpageStatisticLists(sql,page,rows);
	}
}
