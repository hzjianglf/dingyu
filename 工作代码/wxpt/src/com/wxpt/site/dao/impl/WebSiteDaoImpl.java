package com.wxpt.site.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

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

@SuppressWarnings("unchecked")
public class WebSiteDaoImpl extends ParentDaoImpl implements WebSiteDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Templates> getTemplates(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(Templates.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<Templates> templatesList = query.list();
		return templatesList;
	}

	@Override
	public int getTemplatesCount(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.list().size();
	}

	@Override
	public UserTemplate getUserTemplate(String sql) {
		try {
			// TODO Auto-generated method stub
			UserTemplate userTemplate = (UserTemplate) this.sessionFactory
					.getCurrentSession().createSQLQuery(sql)
					.addEntity(UserTemplate.class).list().get(0);
			return userTemplate;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public int executeSql(String sql) {
		try {

			Session session = (Session) this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.connection().createStatement().executeUpdate(sql);
			return 0;
		} catch (Exception e) {
			// TODO: handle exception
			return 1;
		}
	}

	@Override
	public TemplateProperty getTemplateProperty(String sql) {
		try {
			// TODO Auto-generated method stub
			return (TemplateProperty) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(TemplateProperty.class)
					.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<UserSiteMenu> getUserSiteMenuList(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(UserSiteMenu.class).list();
	}

	@Override
	public List<UserSiteMenu> getUserSiteMenuList(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(UserSiteMenu.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public List<UserSiteOption> getUserOptionList(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(UserSiteOption.class).list();
	}

	@Override
	public TemplateMenuType getTemplateMenuType(String sql) {
		try {
			// TODO Auto-generated method stub
			return (TemplateMenuType) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(TemplateMenuType.class)
					.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public List<UserSitePage> getUserSitePageList(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(UserSitePage.class);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public UserSitePagemeta getUserSitePageMetaList(String sql) {
		try {
			// TODO Auto-generated method stub
			return (UserSitePagemeta) this.sessionFactory.getCurrentSession()
					.createSQLQuery(sql).addEntity(UserSitePagemeta.class)
					.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public TemplatePageProperty getTemplatePagePropery(String sql) {
		// TODO Auto-generated method stub
		return (TemplatePageProperty) this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(TemplatePageProperty.class)
				.list().get(0);
	}

	@Override
	public List<UserSitePagemeta> getSitePageMetaList(String sql) {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql).addEntity(UserSitePagemeta.class)
				.list();
	}

	@Override
	public List<Page> getPageLists(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Page.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public List<PageStatistics> getpageStatisticLists(String sql, int page,
			int rows) {
		// TODO Auto-generated method stub
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(PageStatistics.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		return query.list();
	}
}
