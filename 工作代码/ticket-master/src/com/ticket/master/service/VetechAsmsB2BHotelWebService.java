package com.ticket.master.service;

public interface VetechAsmsB2BHotelWebService {
	/**
	 * 接口1: 获取酒店动态信息
	 */

	public String queryHotel (String account, String md5, String xml);
	/**
	 * 接口2: 获取酒店静态数据地址
	 */

	public String getHotelStaticDataXmlURL(String account, String md5, String xml);
	/**
	 * 接口3: 酒店订单详情查询
	 */

	public String queryHotelOrderDetail (String account, String md5, String xml);
	/**
	 * 接口4: 酒店订单列表查询
	 */

	public String queryHotelOrderList (String account, String md5, String xml);
	/**
	 * 接口5: 酒店订单取消
	 */

	public String cancelHotelOrder (String account, String md5, String xml);
	/**
	 * 接口6: 酒店订单提交
	 */

	public String submitHotelOrder (String account, String md5, String xml);
}
