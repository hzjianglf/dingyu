package com.ticket.master.service;

import java.util.List;

public interface ParentService {
	public String getMothod(String methodName, String xml, String account,
			String md5);

	public String getMothod2(String methodName, String xml, String account,
			String md5);

	public String getInsuranceSpecies(String methodName, String xml,
			String account, String md5);

	public List<String> getQueryTicketOrder(String methodName, String xml,
			String account, String md5);

	public String queryTicketOrderMoth(String methodName, String xml,
			String account, String md5);

	public String getPayInterface(String methodName, String xml,
			String account, String md5);

	public String getHotelService(String methodName, String xml,
			String account, String md5);

	public String getgonggongService(String methodName, String xml,
			String account, String md5);

	/**
	 * 三方调用接口 返回结果集进行处理
	 * */
	public String getThreeMothod(String methodName, String xml, String account,
			String md5);

	/**
	 * 机票查询 结果处理
	 * */
	public String searchTcketxml(String account, String Xml, String methodName,
			String requestXml);

	public String searchTcketJson(String account, String threeMothod,
			String string, String xml);

	/**
	 * 其余舱位匹配政策 xml处理
	 * */
	public String queryMoreCabinXml(String account, String Xml,
			String methodName, String requestXml);

	public String queryMoreCabinJson(String account, String json,
			String methodName, String requestXml);

	/**
	 * 获取实时政策接口 xml处理
	 * */
	public String queryPolcyXml(String account, String Xml, String methodName,
			String requestXml);

	public String queryPolcyJson(String account, String json,
			String methodName, String requestXml);

	/**
	 * 机票订单查询 xml处理
	 */
	public String queryTicketOrderXml(String account, String Xml,
			String methodName, String requestXml);

	public String queryTicketOrderJson(String account, String json,
			String methodName, String requestXml);

	/**
	 * 退废订单详查询 xml处理
	 * */
	public String queryTicketReturnOrderDetailXml(String account, String Xml,
			String methodName, String requestXml);

	public String queryTicketReturnOrderDetailJson(String account, String json,
			String methodName, String requestXml);

	/**
	 * 分销商出票清单查询 xml处理
	 */
	public String queryFxsTicketCpXml(String account, String Xml,
			String methodName, String requestXml);

	public String queryFxsTicketCpJson(String string, String xml,
			String account, String md5);
	/**
	 * 
	 * @param account
	 * @param md5
	 * @param xml
	 * @param string
	 * @return
	 */
	public String getBookTicketRequestXml(String account, String md5, String requestXml,
			String methodName);

}
