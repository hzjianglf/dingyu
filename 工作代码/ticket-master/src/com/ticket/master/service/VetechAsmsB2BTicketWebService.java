package com.ticket.master.service;

/**
 * @author zg 这个接口是告诉服务器你的WebService哪些方法可以被用户调用的 定票類
 */
public interface VetechAsmsB2BTicketWebService {
	/**
	 * 接口1: 航班单程查询接口
	 */

	public String searchTicket(String account, String md5, String xml);

	/**
	 * 接口2: 其余舱位匹配政策
	 */

	public String queryMoreCabin(String account, String md5, String xml);

	/**
	 * 接口3: 机票预定接口
	 */

	public String bookTicket(String account, String md5, String xml);

	/**
	 * 接口4: 获取标准票价接口
	 */
	public String queryStandarPrice(String account, String md5, String xml);

	/**
	 * 接口5: 根据PNR PAT接口
	 */

	public String queryPatByPNR(String account, String md5, String xml);

	/**
	 * 接口6: 根据航段PAT接口
	 */

	public String queryPatBySegment(String account, String md5, String xml);

	/**
	 * 接口7: 选择PAT价格更改订单信息
	 */

	public String editOrderByPat(String account, String md5, String xml);

	/**
	 * 接口8: 获取实时政策接口
	 */

	public String queryPolcy(String account, String md5, String xml);

	/**
	 * 接口9: 分销订单重选政策接口
	 */

	public String changedPolicy(String account, String md5, String xml);

	/**
	 * 接口10: PNR下单
	 */
	public String createOrderByPnr(String account, String md5, String xml);

	/**
	 * 接口11，机票订单查询
	 */
	public String queryTicketOrder(String account, String md5, String xml);

	/**
	 * 接口12，订单详细查询
	 */
	public String queryTicketOrderDetail(String account, String md5, String xml);

	/**
	 * 接口13，修改订单证件号码
	 */
	public String editIdentityNo(String account, String md5, String xml);

	/**
	 * 接口14：修改订配送信息、行程单收件地址
	 */
	public String editDeliveryInfo(String account, String md5, String xml);

	/**
	 * 接口15：获取配送自取地址接口
	 */
	public String getDeliveryAddress(String account, String md5, String xml);

	/**
	 * 接口16：退废票申请
	 */
	public String applyTicketRefund(String account, String md5, String xml);

	/**
	 * 接口17：退废订单查询
	 */
	public String queryTicketReturnOrder(String account, String md5, String xml);

	/**
	 * 接口18：退废订单详查询
	 */
	public String queryTicketReturnOrderDetail(String account, String md5,
			String xml);

	/**
	 * 接口19：改签申请
	 */
	public String applyChangeTicket(String account, String md5, String xml);

	/**
	 * 接口20：改签订单查询
	 */
	public String queryChangeOrder(String account, String md5, String xml);

	/**
	 * 接口21：改签订单详查询
	 */
	public String queryChangeOrderDetail(String account, String md5, String xml);

	/**
	 * 接口22：取消订单
	 */
	public String cancelTicketOrder(String account, String md5, String xml);

	/**
	 * 接口23：分销商出票清单查询
	 */
	public String queryFxsTicketCp(String account, String md5, String xml);
	/**
	 * 接口24：查询航班政策
	 */
	public String queryTicketPolcy(String account, String md5, String xml);
	
}