package com.ticket.master.service;
/*/公共资料类接口/*/
public interface VetechAsmsB2BCommonWebService {
	/**
	 * 接口1：获取后台发布的图片
	 * 
	 */
	public String queryPic (String account, String md5, String xml);
	/**
	 * 接口2：帮助中心菜单查询
	 * 
	 */
	public String queryHelpMenu (String account, String md5, String xml);
	/**
	 * 接口3：帮助中心信息详细查询
	 * 
	 */
	public String queryHelpContent (String account, String md5, String xml);
	/**
	 * 接口4：查询所有公告列表
	 * 
	 */
	public String queryOffice_ggtz(String account, String key, String xml);
	/**
	 * 接口5：查询公告信息详细 
	 * 
	 */
	public String queryOffice_ggtz_detail(String account, String key, String xml);
}
