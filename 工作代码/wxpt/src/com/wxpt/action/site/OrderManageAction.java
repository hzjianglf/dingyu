package com.wxpt.action.site;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.common.PageBean;
import com.wxpt.site.dao.OrderManageDao;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.Product;
import com.wxpt.site.service.LogisticService;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.OrderManageService;

@Results({ @Result(name = "orderover", location = "/WEB-INF/content/site/web/pay-over.jsp"), })
public class OrderManageAction extends ActionSupport {
	@Autowired
	OrderManageService orderManageService;
	@Autowired
	OrderManageDao orderManageDao;
	@Autowired
	LogisticService logisticService;
	@Autowired
	MemberService memberService;
	private int countPage = 0;// 用于在浏览装的 求总的页数
	private int rows = 10;// 每页显示的记录数
	private int total;
	private int listCount; // 总数据条数
	private int pageCount;// 总页数
	private int page = 0;
	private PageBean pageBean = new PageBean();
	private Order order;
	JSONArray jsonArray;
	private String sql;
	private Product product;
	private String product_num;
	private String orderNum;
	Logistics logistic;
	Member member;
	private int enterId=GetCurrentUser.getEnterId();//
	private String username;

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getProduct_num() {
		return product_num;
	}

	public void setProduct_num(String product_num) {
		this.product_num = product_num;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	// 支付成功测试
	public void getOrder() {

		sql = "UPDATE wxpt" + enterId
				+ ".order SET `pay_type`=1 WHERE `order_num`=" + orderNum;

		try {
			orderManageDao.getOrder(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * {title : '收货人电话',field : 'receive_phone',width : '200',align : 'center'},
	 * {title : '收货状态',field : 'send_type',width : '200',align : 'center'},
	 * {title : '物流方式',field : 'Logistics_name',width : '200',align : 'center'},
	 * {title : '会员名称',field : 'memberName',width : '200',align : 'center'},
	 * {title : '订单状态',field : 'order_type',width : '200',align : 'center'},
	 * {title : '订单备注',field : 'order_remark',width : '200',align : 'center'},
	 */
	public void spiltOrderManage() throws IOException {
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		pageBean = orderManageService.spiltOrderManager(page, rows,
				product_num, username, enterId);
		List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		for (int i = 0; i < pageBean.getList().size(); i++) {
			order = (Order) pageBean.getList().get(i);
			map = new HashMap<String, Object>();
			String order_product = order.getOrderProductId();
			String[] orderId = order_product.split(";");
			String productName = "";
			String productName1 = "";
			for (int j = 0; j < orderId.length; j++) {
				int order_product_id = Integer.parseInt(orderId[j]);
				sql = "select * from wxpt" + enterId
						+ ".product where product_id=" + order_product_id;
				try {
					product = orderManageService.productObject(sql);
					System.out.println(product);
					if (product == null) {

					} else {
						productName = productName + product.getProductName()
								+ ";";
						productName1 = productName.substring(0,
								productName.lastIndexOf(";"));
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			map.put("order_id", order.getOrderId());
			map.put("productName", productName1);
			// 商品编号
			map.put("product_num", order.getOrderNum());
			map.put("order_time", order.getOrderTime());
			map.put("pay_money", order.getPayMoney());
			map.put("pay_time", order.getPayTime());
			if (order.getPayType() == 0) {
				map.put("pay_type", "未支付");
			} else {
				map.put("pay_type", "已支付");
			}
			map.put("receive_person", order.getReceivePerson());
			map.put("receive_address", order.getReceiveAddress());
			map.put("receive_phone", order.getReceivePhone());
			if (order.getSendType() == 0) {
				map.put("send_type", "未发货");
			} else {
				map.put("send_type", "已发货");
			}
			// 物流方式
			String sql="SELECT * FROM wxpt"+enterId+".logistics WHERE `logistics_id`="+order.getLogistics().getLogisticsId();
			logistic=logisticService.getByLogId(sql);
			map.put("Logistics_name", logistic.getLogisticsWay());
			// 会员
			member=memberService.jiedong(enterId,order.getMember().getMemberId());
			String name = member.getUsername();
			map.put("memberName", name);
			map.put("order_leave", order.getOrderLeave());
			// 订单状态
			if (order.getOrderType() == 0) {
				map.put("order_type", "正常");
			} else {
				map.put("order_type", "取消");
			}
			// 收货状态
			if (order.getTakeType() == 0) {
				map.put("takeType", "等待收货");

			} else {
				map.put("takeType", "已经收货");
			}
			// 发货时间
			String sendTime = order.getSendTime();
			if (sendTime == null) {
				map.put("sendTime", "无");
			} else {
				map.put("sendTime", order.getSendTime());
			}
			map.put("order_remark", order.getOrderRemark());
			jsonList.add(map);
		}
		jsonArray = JSONArray.fromObject(jsonList);
		listCount = pageBean.getRecordCnt();
		out.print("{\"total\":" + listCount + ",\"rows\":" + jsonArray + "}");
		out.close();
	}

	public String execute() throws Exception {
		return "show";
	}

	private int send_type;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// 商家发货
	private String sql1;
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	Map<String, Object> map = new HashMap<String, Object>();

	public void sendORcancel_type() throws IOException {
		PrintWriter out = ServletActionContext.getResponse().getWriter();

		// 发货

		sql = "UPDATE wxpt" + enterId
				+ ".order SET `send_type`=1 WHERE `order_id`=" + id;

		sql1 = "select * from wxpt" + enterId + ".order where `order_id`=" + id;
		try {
			order = orderManageService.orderObject(sql1);

			int sendType = order.getSendType();
			int payType = order.getPayType();
			sql = "UPDATE wxpt" + enterId
					+ ".order SET `send_type`=1 WHERE `order_id`=" + id;
			if (payType == 0) {
				map.put("a", 3);

			} else {
				if (sendType == 1) {
					map.put("a", "2");
				} else {
					orderManageService.operateOrder(sql,enterId);
					map.put("a", "1");
				}

			}

		} catch (Exception e) {
			map.put("a", "0");
		}
		JSONObject jsonResult = JSONObject.fromObject(map);
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	private List<Product> listProduct;

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public OrderManageDao getOrderManageDao() {
		return orderManageDao;
	}

	public void setOrderManageDao(OrderManageDao orderManageDao) {
		this.orderManageDao = orderManageDao;
	}

}
