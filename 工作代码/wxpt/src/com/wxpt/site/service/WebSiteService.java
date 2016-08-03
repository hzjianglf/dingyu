package com.wxpt.site.service;

import java.util.List;

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

public interface WebSiteService extends ParentService{
	public List<Templates> getTemplates(String sql,int page,int rows);

	public int getTemplatesCount(String sql);

	public UserTemplate getUserTemplate(String sql);

	public int executeSql(String sql);

	public TemplateProperty getTemplateProperty(String sql);

	public List<UserSiteMenu> getUserSiteMenuList(String sql);

	public List<UserSiteMenu> getUserSiteMenuList(String sql, int page, int rows);

	public List<UserSiteOption> getUserOptionList(String sql);

	public TemplateMenuType getTemplateMenuType(String sql);

	public List<UserSitePage> getUserSitePageList(String sql, int page, int rows);

	public UserSitePagemeta getUserSitePageMetaList(String sql);
	public List<UserSitePagemeta> getSitePageMetaList(String sql);
	public TemplatePageProperty getTemplatePagePropery(String sql);

	public List<Page> getPageLists(String sql, int page, int rows);

	public List<PageStatistics> getpageStatisticLists(String sql, int page,
			int rows);
	
}
