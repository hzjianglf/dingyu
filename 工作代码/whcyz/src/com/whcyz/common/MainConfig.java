package com.whcyz.common;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.whcyz.controller.AccountController;
import com.whcyz.controller.AdminController;
import com.whcyz.controller.ArticleController;
import com.whcyz.controller.CommentController;
import com.whcyz.controller.CompanyController;
import com.whcyz.controller.HomeController;
import com.whcyz.controller.LinkController;
import com.whcyz.controller.PersonController;
import com.whcyz.controller.StationController;
import com.whcyz.controller.UploadController;
import com.whcyz.model.Account;
import com.whcyz.model.Article;
import com.whcyz.model.Comment;
import com.whcyz.model.Company;
import com.whcyz.model.Link;
import com.whcyz.model.Person;
import com.whcyz.model.Station;
import com.whcyz.util.DebugInfo;

public class MainConfig extends JFinalConfig {
	@Override
	/*
	 * 配置常量
	 * @see com.jfinal.config.JFinalConfig#configConstant(com.jfinal.config.Constants)
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("config.cfg");
		Constant.devMode=getPropertyToBoolean("devMode", true);
		me.setDevMode(Constant.devMode);
		me.setViewType(ViewType.JSP);
		me.setError404View(Constant.error404PagePath);
		me.setError500View(Constant.error404PagePath);
		me.setBaseViewPath(Constant.baseViewPath);
		me.setUrlParaSeparator(Constant.URLPARASEPARATOR);
		me.setUploadedFileSaveDirectory(Constant.uploadSaveDir);
		me.setMaxPostSize(Constant.MAXPOSTSIZE);
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		DebugInfo.log("MainConfig", "初始化配置全局拦截器...");
		me.add(new SessionInViewInterceptor(true));


	}

	@Override

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		DebugInfo.log("MainConfig", "初始化配置插件...");
		DebugInfo.log("MainConfig", "初始化配置数据源...");
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password").trim());
		StatFilter statFilter = new StatFilter();
		dp.addFilter(statFilter);
		WallFilter wall = new WallFilter();
		wall.setDbType("mysql");
		dp.addFilter(wall);
		me.add(dp);
		DebugInfo.log("MainConfig", "初始化配置数据源数据表实体绑定...");
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		arp.setShowSql(Constant.devMode);
		arp.addMapping("account", Account.class);
		arp.addMapping("person", Person.class);
		arp.addMapping("company", Company.class);
		arp.addMapping("link", Link.class);
		arp.addMapping("article", Article.class);
		arp.addMapping("comment", Comment.class);
		arp.addMapping("t_station", Station.class);
		me.add(arp);
		//配置ehcache插件
		//me.add(new EhCachePlugin());

	}

	@Override

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		DebugInfo.log("MainConfig", "初始化配置路由器...");
		me.add("/", HomeController.class);
		
		me.add("/upload", UploadController.class);
		me.add("/admin", AdminController.class,"admin");
		me.add("/account", AccountController.class,"account");
		me.add("/person", PersonController.class,"person");
		me.add("/company", CompanyController.class,"company");
		me.add("/article", ArticleController.class,"article");
		me.add("/comment", CommentController.class,"comment");
		me.add("/link", LinkController.class,"link");
		
	}

	public static void main(String[] args) {
		DebugInfo.log("MainConfig", "服务器准备启动... 80端口");
		JFinal.start("WebRoot", 80, "/", 5);

	}

}
