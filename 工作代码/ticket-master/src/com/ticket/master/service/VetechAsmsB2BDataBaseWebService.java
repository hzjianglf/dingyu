package com.ticket.master.service;
/*基础数据类接口*/
public interface VetechAsmsB2BDataBaseWebService {
	/**
	 * 接口1：获取友情链接，文字，图片
	 * 
	 */
	public String queryLinks (String account, String md5, String xml);
	/**
	 * 接口2：查询分销常用参数
	 * 
	 */
	public String queryParameter (String account, String md5, String xml);
	/**
	 * 接口3：获得城市信息
	 * 
	 */
	public String queryB_city(String account, String md5, String xml);
	/**
	 * 接口4：获得航空公司
	 * 
	 */
	public String queryAirway(String account, String md5, String xml);
	/**
	 * 接口5：取基础数据接口
	 * 
	 */
	public String queryDatebase (String account, String md5, String xml);
	/**
	 * 接口6：取数据字典接口
	 * 
	 */
	public String queryBClass(String account, String md5, String xml);
	/**
	 * 接口7：取支付方式、支付科目数据接口 
	 * 
	 */
	public String queryPaySubject (String account, String md5, String xml);
	/**
	 * 接口8：取分销可使用的支付方式、支付科目数据接口 
	 * 
	 */
	public String queryCanPayPaySubject (String account, String md5, String xml);
	/**
	 * 接口9：取分销商基础数据接口 
	 * 
	 */
	public String queryFxsDatebase (String account, String md5, String xml);
}
