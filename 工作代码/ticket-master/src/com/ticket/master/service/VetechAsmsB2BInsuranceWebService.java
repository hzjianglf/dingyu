package com.ticket.master.service;

/**
 * 
 * @author Server保险类接口
 * 
 */
public interface VetechAsmsB2BInsuranceWebService {
	/**
	 * 保险接口
	 * 
	 * @param account
	 * @param key
	 * @param xml
	 * @return
	 */
	/*接口1：	保险接口 */
	public String queryInsuranceSpecies(String account, String md5, String xml);
	/*接口2：	保险订单申请  */
	public String createInsuranceOrder (String account, String md5, String xml);
	/*接口3：	保险订单查询  */
	public String  queryInsuranceOrder(String account, String key, String xml);
	/*接口4：	保险承保  */
	public String   createInsurance(String account, String key, String xml);
	/*接口5：	保险退保 */
	public String  refunInsurance(String account, String key, String xml);
	/*接口6：	取消保险订单  */
	public String  cancelInsuranceOrder (String account, String md5, String xml);
	/*接口7：	保险日报  */
	public String  queryInsuranceJournal (String account, String md5, String xml);
	/*接口8：	保险订单详情查询  */
	public String  queryInsuranceDetail(String account, String key, String xml);
}
