package com.wxpt.action.site.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.site.dao.OrderDao;
import com.wxpt.site.entity.Appraise;
import com.wxpt.site.entity.BuyProduct;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.Logistics;
import com.wxpt.site.entity.Order;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ShoppingAddress;
import com.wxpt.site.entity.ShoppingCar;
import com.wxpt.site.entity.pojo.BuyProduct2;
import com.wxpt.site.entity.pojo.ShoppingCar1;
import com.wxpt.site.entity.pojo.order2;
import com.wxpt.site.entity.pojo.order3;
import com.wxpt.site.entity.pojo.product2;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.LogisticService;
import com.wxpt.site.service.ManageService;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.OrderService;
import com.wxpt.site.service.ProductService;

@Results({ @Result(name = "success", location = "/web/success.jsp"),
		@Result(name = "error", location = "/web/error.jsp"),
		@Result(name = "orderNum", location = "/web/orderNum.jsp") })
public class OrderAction extends ActionSupport {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderDao orderDao;
	ManageService manageService;
	ProductService productService;
	@Autowired
	LogisticService logisticService;
	@Autowired
	MemberService memberService;
	Logistics logistic;
	Product product;
	private int sid;
	private int sum;
	private int no;
	private int lid;
	private String name;
	private String address;
	private String phone;
	private int shoppingAddressId;
	private int logisticsId;
	private int shifou;
	private String time;
	private String save_address;
	private String leave;
	private String orderNum;
	private double zongjia;
	private Order order;
	private List<Order> lorder;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat orderno2 = new SimpleDateFormat("yyyyMMddmmss");
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	private ShoppingCar1 sc;
	private ShoppingCar sc1;
	private ShoppingAddress sa;
	private List<ShoppingCar> lshop;
	private List<ShoppingAddress> lsa;
	private List<Logistics> lll;
	private List<product2> lp2=new ArrayList<product2>();
	private List<order2> lo2;
	private product2 p2;
	private Product ppp;
	private order2 o2;
	private BuyProduct bp;
	private BuyProduct2 bp2;
	private List<BuyProduct> lbp;
	private List<order3> lo3;
	static final int PAGE_SIZE = 1;// 每页数据条数
	private int listCount; // 服务总数据条数
	private int pageCount;// 总页数
	private int currentpage = 1;// 当前页
	private int listCount1; // 产品总数据条数
	private String ordernum;
	private String cancel_reason;
	private int size;
	private EnterInfor enter;
	private String ppId;
	private String ppsum;
	private String memberName;
	private List<BuyProduct2> lbp2;
	private Appraise app;
	private int lshopsize;
	protected JSONArray jsonls;
	protected PrintWriter out = null;
	private int enterId = GetCurrentUser.getEnterId();//
	int mid = GetCurrentUser.getUserID();
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		try {
			sc = orderService.getOne(enterId, 1, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (sc != null) {
			orderDao.getChangeCar(enterId, mid, 52, sc.getProductSum() + 2);
		} else {
			orderService.save(enterId, mid, 52, 3);
		}

		return null;
	}

	public String getDelete() {
		try {
			orderService.getDelete(enterId, mid, sid);
			getShopping();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "shopping";
	}

	public String getShopping() {
		lshop = new ArrayList<ShoppingCar>();
		lshop = orderService.getListShoppingCar(enterId, mid);
		lshopsize = lshop.size();
		dsum = 0.00;
		for (int i = 0; i < lshop.size(); i++) {
			double dd = 0.00;
			String sql = "SELECT * FROM wxpt" + enterId
					+ ".product WHERE `product_id`="
					+ lshop.get(i).getProduct().getProductId();
			product = productService.getProduct(sql).get(0);

			if (product.getCheapPrice() == 0) {
				dd = product.getPrice()
						* lshop.get(i).getProductSum();
			} else {
				dd = product.getCheapPrice()
						* lshop.get(i).getProductSum();
			}
			dsum = dsum + dd;
		}
		return "shopping";
	}

	public String getChangeShoppingCar() {
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
		ppId = ppId.replaceAll(" ", "");
		ppsum = ppsum.replaceAll(" ", "");
		String[] sumid = ppId.split(",");
		String[] sumsum = ppsum.split(",");
		size = 0;
		for (int i = 0; i < sumid.length; i++) {
			ppp = new Product();
			int pid = 0;
			pid = Integer.parseInt(sumid[i].toString());
			int sss = 0;
			sss = Integer.parseInt(sumsum[i].toString());
			System.out.println(sss);
			ppp = manageService.getByIdProduct(pid, enterId);
			System.out.println(ppp.getInventoryNum());
			if (sss > ppp.getInventoryNum()) {
				size = 1;
				return "error";
			}
		}
		if (size == 0) {
			for (int i = 0; i < sumid.length; i++) {
				int pid = 0;
				pid = Integer.parseInt(sumid[i].toString());
				int sss = 0;
				sss = Integer.parseInt(sumsum[i].toString());
				orderDao.getChangeShoppingCar(enterId, mid, pid, sss);
			}
		}
		/*
		 * out.print(size); out.flush(); out.close();
		 */
		return "success";
	}

	public String getJiesuan() {
		lsa = new ArrayList<ShoppingAddress>();
		lsa = orderService.getlsa(enterId, mid);
		lll = new ArrayList<Logistics>();
		lll = orderService.getll(enterId);
		try {
			no = lsa.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getShopping();
		return "jiesuan";
	}

	public String getaddress() {
		lsa = new ArrayList<ShoppingAddress>();
		lsa = orderService.getlsa(enterId, mid);
		return "address";
	}

	public String getChangeAddress() {
		lsa = new ArrayList<ShoppingAddress>();
		lsa = orderService.getlsa(enterId, mid);
		sa = new ShoppingAddress();
		for (int i = 0; i < lsa.size(); i++) {
			if (lsa.get(i).getShoppingAddressId() == lid) {
				sa = lsa.get(i);
			}
		}
		return "edit";
	}

	public String getUpdate() {
		orderDao.getChangeShoppingAddress(enterId, lid, name, address, phone);
		getaddress();
		return "address";
	}

	public String getDeleteAddress() {
		orderService.getDeleteAddress(enterId, lid);
		getaddress();
		return "address";
	}

	public String getSave() {
		return "save";
	}

	public String getSaveAddress() {
		orderService.saveAddress(enterId, mid, name, address, phone);
		getaddress();
		return "address";
	}

	public String getSaveOrder() {
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
		lshop = new ArrayList<ShoppingCar>();
		lshop = orderService.getListShoppingCar(enterId, mid);
		time = orderno2.format(new Date());
		String x = Integer.toString((int) (Math.random() * 9));
		time = time + x;
		sa = new ShoppingAddress();
		try {
			sa = orderService.getOneShoppingAddress(enterId, shoppingAddressId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String product_id = "";
		for (int i = 0; i < lshop.size(); i++) {
			product_id = product_id
					+ lshop.get(i).getProduct().getProductId().toString() + ";";
		}
		try {
			product_id = product_id.substring(0, product_id.lastIndexOf(";"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String savetime = df.format(new Date());
		zongjia = zongjia
				+ orderService.getOneLogistics(enterId, logisticsId)
						.getLogisticsPrice();
		if (sa.getShoppingAddressId() != null) {
			orderService.saveOrder(enterId, product_id, time, savetime,
					zongjia, 0, sa.getName(), sa.getAddress(), sa.getPhone(),
					0, logisticsId, mid, leave);
		} else {
			orderService.saveOrder(enterId, product_id, time, savetime,
					zongjia, 0, name, address, phone, 0, logisticsId, mid,
					leave);
		}
		if (save_address != null) {
			orderService.saveAddress(enterId, mid, name, address, phone);
		}
		int b = orderService.getOrder(enterId, time).getOrderId();
		for (int i = 0; i < lshop.size(); i++) {
			orderService.getSaveBuy(enterId, lshop.get(i).getProduct()
					.getProductId(), b, lshop.get(i).getProductSum());
		}
		orderService.getDeleteShopping(enterId, mid);
		// String url="../../site/web/order!getLastOneOrder?orderNum="+time;
		// try {
		// response.sendRedirect(url);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		/*
		 * out.print(time); out.flush(); out.close();
		 */
		return "orderNum";
	}
	private String amount;//订单金额
	private String merid;//商户号
	private String ordernos;//订单编号
	private String currencycode;//币种
	private String transdate;//交易日期
	private String transtype;//交易类型
	private String versions;//版本号
	private String BgRetUrl;//（后台交易接收URL ，长度不要超过80 个字节，必填）
	private String PageRetUrl;//页面交易接收 URL ，长度不要超过80 个字节，必填
	private String GateId;//（支付网关号，可选）
	private String Priv1;//（商户私有域，长度不要超过60 个字节）
	private String ChkValue;//（256 字节长的ASCII 码,为此次交易提交关键数据的数字签名，必填）
	@Autowired
	EnterService enterService;
	public String getLastOneOrder() {
		EnterInfor enter=enterService.getById(enterId);
		order = new Order();
		order = orderService.getOrder(enterId, orderNum);
		time = "";
		zongjia = 0.0;
		time = order.getOrderNum();
		zongjia = order.getPayMoney();
		amount=makeAmount((int)(zongjia*100)+"");//订单交易金额，12 位长度，左补0，必填,单位为分
		merid=enter.getMerId();
		ordernos="000"+order.getOrderNum();
		currencycode=enter.getCuryId();
		transtype=enter.getTranstType();
		String str=order.getOrderTime();
		String strs[]=str.split(" ");
		transdate=strs[0].replaceAll("-", "");
		versions=enter.getVersion();
		BgRetUrl="www.uniqyw.com/wxpt/site/web/order!getRecieveOrder";
		PageRetUrl="";
		GateId=enter.getGateId();
		Priv1=enter.getPrivl();
		chinapay.PrivateKey key1=new chinapay.PrivateKey();
		chinapay.SecureLink t1;
		boolean flag;
		
		String baseDiskPath = request.getSession().getServletContext().getRealPath(File.separator)+"/web/images/merKey"+enterId+".key";
		flag=key1.buildKey(merid,0,baseDiskPath);
	    // flag=key1.buildKey(merid, 0, this.getClass().getResource(file.getAbsolutePath()).getPath()); //注意要用哪一个商户号签名就要用对应的key文件。
	  // flag=key1.buildKey("808080231802790", 0, "C:\\MerPrK_808080231802790_20121210141220.key"); //注意要用哪一个商户号签名就要用对应的key文件。

		if (flag==false) 
		{
			System.out.println("build key error!!!");
		    //return ;
		}
		t1=new chinapay.SecureLink (key1);
		ChkValue=t1.Sign(merid+ordernos+amount+currencycode+transdate+transtype+Priv1); //获得对应商户的签名数据
		return "over";
	}
	private String status;
	private String checkvalue;
	private String orderno;
	public void getRecieveOrder(){
//接受支付后银联方返回的数据
		try
		{
		chinapay.PrivateKey key=new chinapay.PrivateKey();
		chinapay.SecureLink t;
		boolean flag;
		boolean flag1;
		String msg="";
		flag=key.buildKey("999999999999999",0, this.getClass().getResource("/PgPubk.key").getPath());
		//flag=key.buildKey("999999999999999",0, "C:\\PgPubk.key");
		if (flag==false) 
		{
			System.out.println("build key error!");
			
			return;
		}

		t=new chinapay.SecureLink (key);
		flag1=t.verifyTransResponse(merid,orderno,amount,currencycode,transdate,transtype,status,checkvalue);

		if (flag1==false)
		{
			msg="交易验证失败!";
			System.out.println("交易验证失败!");
			return;
		}
		else
		{
			//交易成功，修改订单的支付状态
		//	order = orderService.findByOrderId(orderno);
			
			//System.out.println(order.getState()+"-=-=-=-=-=-=-=-=-=");
			/*if(order.getState() == 1)
			{
				System.out.println("已经支付");
				return;
			}else
			{*/
				if(status.equals("1001"))
			    {//更改支付状态
					order = orderService.getOrder(enterId, orderno);
					order.setPayType(1);
					
			    	orderService.updateOrder(enterId, order.getOrderId(), 0);
			    
			  
			    
			    }
			    else
			    {
			    	System.out.println("没有支付成功");
			    	return;
			    }
				
			//}
	
		}
	}
	catch(Exception e)
	{
			e.printStackTrace();
		    
	}
	return;
	
	}
	public String makeAmount(String str){//订单交易金额，12 位长度，左补0，必填,单位为分
		String amounts="";
		int length=str.length();
		if(length<12){
			int bushu=12-length;
			for(int i=0;i<bushu;i++){
				amounts+="0";
			}
			amounts=amounts+str;
		}
		return amounts;
	}
	public String getAllNewOrder() {
		size = 0;

		try {
			memberName=memberService.getOne(mid,enterId).getUsername();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lorder = new ArrayList<Order>();
		lorder = orderService.getAllNewOrder(enterId, mid);
		listCount = lorder.size();
		lorder = new ArrayList<Order>();
		this.PageListNew((currentpage - 1) * PAGE_SIZE);
		lbp = new ArrayList<BuyProduct>();
		lo3 = new ArrayList<order3>();
		for (int i = 0; i < lorder.size(); i++) {
			bp = new BuyProduct();
			order3 o3 = new order3();
			try {
				lbp = orderService.getOneByOrderId(enterId, lorder.get(i)
						.getOrderId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (lbp.size() != 0) {
				// lbp.add(bp);
				o3.setLbp(lbp);
				o3.setOrder(lorder.get(i));
				lo3.add(o3);
			}
		}
		size = lbp.size();
		return "new";
	}

	public String getAllSendOrder() {
		size = 0;
		/*
		 * try { memberName = URLDecoder.decode(memberName,"utf-8"); } catch
		 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		memberName=memberService.getOne(mid,enterId).getUsername();
		lorder = new ArrayList<Order>();
		lorder = orderService.getAllSendOrder(enterId, mid);
		listCount = lorder.size();
		lorder = new ArrayList<Order>();
		this.PageListSend((currentpage - 1) * PAGE_SIZE);
		lbp = new ArrayList<BuyProduct>();
		lo3 = new ArrayList<order3>();
		for (int i = 0; i < lorder.size(); i++) {
			bp = new BuyProduct();
			order3 o3 = new order3();
			try {
				lbp = orderService.getOneByOrderId(enterId, lorder.get(i)
						.getOrderId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (lbp.size() != 0) {
				// lbp.add(bp);
				o3.setLbp(lbp);
				o3.setOrder(lorder.get(i));
				lo3.add(o3);
			}

		}
		size = lbp.size();
		return "send";
	}

	public String getAllTakeOrder() {
		size = 0;
		try {
			memberName=memberService.getOne(mid,enterId).getUsername();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lorder = new ArrayList<Order>();
		lorder = orderService.getAllTakeOrder(enterId, mid);
		listCount = lorder.size();
		lorder = new ArrayList<Order>();
		this.PageListTake((currentpage - 1) * PAGE_SIZE);
		lbp = new ArrayList<BuyProduct>();
		lo3 = new ArrayList<order3>();
		for (int i = 0; i < lorder.size(); i++) {
			bp = new BuyProduct();
			order3 o3 = new order3();
			try {
				lbp = orderService.getOneByOrderId(enterId, lorder.get(i)
						.getOrderId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (lbp.size() != 0) {
				// lbp.add(bp);
				o3.setLbp(lbp);
				o3.setOrder(lorder.get(i));
				lo3.add(o3);
			}

		}
		size = lbp.size();
		return "take";
	}

	public String getAllOverOrder() {
		size = 0;
		try {
			memberName=memberService.getOne(mid,enterId).getUsername();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lorder = new ArrayList<Order>();
		lorder = orderService.getAllOverOrder(enterId, mid);
		listCount = lorder.size();
		lorder = new ArrayList<Order>();
		this.PageListOver((currentpage - 1) * PAGE_SIZE);
		lbp = new ArrayList<BuyProduct>();
		lo3 = new ArrayList<order3>();
		for (int i = 0; i < lorder.size(); i++) {
			bp = new BuyProduct();
			order3 o3 = new order3();
			int id = lorder.get(i).getOrderId();
			try {
				lbp = orderService.getOneByOrderId(enterId, lorder.get(i)
						.getOrderId());
				lbp2 = new ArrayList<BuyProduct2>();
				for (int j = 0; j < lbp.size(); j++) {
					bp = new BuyProduct();
					bp = lbp.get(j);
					System.out.println(bp.getProduct().getProductId());
					bp2 = new BuyProduct2();
					app = new Appraise();
					try {
						app = orderService.getOneAppraise(enterId, mid, bp
								.getProduct().getProductId(), id);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (app != null) {
						bp2.setProduct(bp.getProduct());
						bp2.setBuyProductId(bp.getBuyProductId());
						bp2.setBuyProductSum(bp.getBuyProductSum());
						bp2.setOrder(bp.getOrder());
						bp2.setType(1);
					} else {
						bp2.setProduct(bp.getProduct());
						bp2.setBuyProductId(bp.getBuyProductId());
						bp2.setBuyProductSum(bp.getBuyProductSum());
						bp2.setOrder(bp.getOrder());
						bp2.setType(0);
					}
					lbp2.add(bp2);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (lbp2.size() != 0) {
				// lbp.add(bp);
				o3.setLbp2(lbp2);
				o3.setOrder(lorder.get(i));
				lo3.add(o3);
			}

		}
		try {
			size = lbp2.size();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "overorder";
	}

	public void PageListNew(int statePage) {
		lorder = orderService.getAllNewOrderFen(enterId, mid, statePage,
				PAGE_SIZE);
		if (listCount % PAGE_SIZE == 0) {
			pageCount = listCount / PAGE_SIZE;
		} else {
			pageCount = listCount / PAGE_SIZE + 1;
		}
	}

	public void PageListSend(int statePage) {
		lorder = orderService.getAllSendOrderFen(enterId, mid, statePage,
				PAGE_SIZE);
		if (listCount % PAGE_SIZE == 0) {
			pageCount = listCount / PAGE_SIZE;
		} else {
			pageCount = listCount / PAGE_SIZE + 1;
		}
	}

	public void PageListTake(int statePage) {
		lorder = orderService.getAlltakeOrderFen(enterId, mid, statePage,
				PAGE_SIZE);
		if (listCount % PAGE_SIZE == 0) {
			pageCount = listCount / PAGE_SIZE;
		} else {
			pageCount = listCount / PAGE_SIZE + 1;
		}
	}

	public void PageListOver(int statePage) {
		lorder = orderService.getAllOverOrderFen(enterId, mid, statePage,
				PAGE_SIZE);
		if (listCount % PAGE_SIZE == 0) {
			pageCount = listCount / PAGE_SIZE;
		} else {
			pageCount = listCount / PAGE_SIZE + 1;
		}
	}

	public String getPay() {
		order = new Order();
		order = orderService.getOrder(enterId, ordernum);
		time = "";
		zongjia = 0.00;
		time = order.getOrderNum();
		zongjia = order.getPayMoney();
		return "over";
	}

	public String getCannelOrder() {
		order = new Order();
		order = orderService.getOrder(enterId, ordernum);
		return "canner";
	}

	public String getAllShopping() {
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
		size = 0;
		try {
			size = orderService.getAllShopping(enterId, mid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * jsonls = JSONArray.fromObject(size); System.out.println(jsonls);
		 */
		/*
		 * out.print(size); out.flush(); out.close();
		 */
		return "success";
	}

	public String getChangeNewPage() {
		getAllNewOrder();
		return "new1";
	}

	public String getChangeSendPage() {
		getAllSendOrder();
		return "send1";
	}

	public String getChangeTakePage() {
		getAllTakeOrder();
		return "take1";
	}

	public String getChangeOverPage() {
		getAllOverOrder();
		return "overorder1";
	}

	public String getCannelOrderEnter() {
		orderService.getCannelOrder(enterId, ordernum, cancel_reason);
		getAllNewOrder();
		return "new";
	}

	public String getSelectOrder() {
		order = new Order();
		enter = new EnterInfor();
		lbp = new ArrayList<BuyProduct>();
		order = orderService.getOrder(enterId, ordernum);
		String sql="SELECT * FROM wxpt"+enterId+".logistics WHERE `logistics_id`="+order.getLogistics().getLogisticsId();
		logistic=logisticService.getByLogId(sql);
		lbp = orderService.getOneByOrderId(enterId, order.getOrderId());
		for(int i=0;i<lbp.size();i++){
			String sql2="SELECT * FROM wxpt"+enterId+".product WHERE `product_id`="+lbp.get(i).getProduct().getProductId();
			product=productService.getProduct(sql2).get(0);
			product2 pro=new product2();
			pro.setCheapPrice(product.getCheapPrice());
			pro.setProductXimage(product.getProductXimage());
			pro.setProductId(product.getProductId());
			pro.setSum(lbp.get(i).getBuyProductSum());
			pro.setPrice(product.getPrice());
			pro.setProductName(product.getProductName());
			lp2.add(pro);
		}
		
		
	
		
		
		enter = orderService.getOneEnterinfor(enterId);
		zongjia = 0.0;
		zongjia = order.getPayMoney();
		// double d=order.getPayMoney();
		// double dd=order.getLogistics().getLogisticsPrice();
		// zongjia=0.0;
		// zongjia=d+dd;
		return "newselect";
	}

	public String getEnterOrder() {
		orderService.getChangeEnterOrder(enterId, ordernum);
		order = new Order();
		lbp = new ArrayList<BuyProduct>();
		order = orderService.getOrder(enterId, ordernum);
		
		lbp = orderService.getOneByOrderId(enterId, order.getOrderId());
		for (int i = 0; i < lbp.size(); i++) {
			int sell = 0;
			int sum = 0;
			sell = lbp.get(i).getProduct().getSellNum()
					+ lbp.get(i).getBuyProductSum();
			sum = lbp.get(i).getProduct().getInventoryNum()
					- lbp.get(i).getBuyProductSum();
			orderService.getChangeProduct(enterId, lbp.get(i).getProduct()
					.getProductId(), sum, sell);
		}
		getAllOverOrder();
		return "overorder";
	}

	public String getDeleteOrder() {
		orderService.getDeleteOrder(enterId, ordernum);
		getAllOverOrder();
		return "overorder";
	}

	public ShoppingCar getSc1() {
		return sc1;
	}

	public void setSc1(ShoppingCar sc1) {
		this.sc1 = sc1;
	}

	public ShoppingCar1 getSc() {
		return sc;
	}

	public void setSc(ShoppingCar1 sc) {
		this.sc = sc;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getLid() {
		return lid;
	}

	public void setLid(int lid) {
		this.lid = lid;
	}

	private String jiage;

	public String getJiage() {
		return jiage;
	}

	public void setJiage(String jiage) {
		this.jiage = jiage;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public List<Logistics> getLll() {
		return lll;
	}

	public void setLll(List<Logistics> lll) {
		this.lll = lll;
	}

	public List<ShoppingAddress> getLsa() {
		return lsa;
	}

	public void setLsa(List<ShoppingAddress> lsa) {
		this.lsa = lsa;
	}

	private Double dsum;

	public Double getDsum() {
		return dsum;
	}

	public void setDsum(Double dsum) {
		this.dsum = dsum;
	}

	public List<ShoppingCar> getLshop() {
		return lshop;
	}

	public void setLshop(List<ShoppingCar> lshop) {
		this.lshop = lshop;
	}

	public ShoppingAddress getSa() {
		return sa;
	}

	public void setSa(ShoppingAddress sa) {
		this.sa = sa;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getShoppingAddressId() {
		return shoppingAddressId;
	}

	public void setShoppingAddressId(int shoppingAddressId) {
		this.shoppingAddressId = shoppingAddressId;
	}

	public int getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(int logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSave_address() {
		return save_address;
	}

	public void setSave_address(String save_address) {
		this.save_address = save_address;
	}

	public int getShifou() {
		return shifou;
	}

	public void setShifou(int shifou) {
		this.shifou = shifou;
	}

	public double getZongjia() {
		return zongjia;
	}

	public void setZongjia(double zongjia) {
		this.zongjia = zongjia;
	}

	public List<Order> getLorder() {
		return lorder;
	}

	public void setLorder(List<Order> lorder) {
		this.lorder = lorder;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public List<product2> getLp2() {
		return lp2;
	}

	public void setLp2(List<product2> lp2) {
		this.lp2 = lp2;
	}

	public List<order2> getLo2() {
		return lo2;
	}

	public void setLo2(List<order2> lo2) {
		this.lo2 = lo2;
	}

	public product2 getP2() {
		return p2;
	}

	public void setP2(product2 p2) {
		this.p2 = p2;
	}

	public order2 getO2() {
		return o2;
	}

	public void setO2(order2 o2) {
		this.o2 = o2;
	}

	public BuyProduct getBp() {
		return bp;
	}

	public void setBp(BuyProduct bp) {
		this.bp = bp;
	}

	public List<BuyProduct> getLbp() {
		return lbp;
	}

	public void setLbp(List<BuyProduct> lbp) {
		this.lbp = lbp;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getListCount1() {
		return listCount1;
	}

	public void setListCount1(int listCount1) {
		this.listCount1 = listCount1;
	}

	public static int getPageSize() {
		return PAGE_SIZE;
	}

	public List<order3> getLo3() {
		return lo3;
	}

	public void setLo3(List<order3> lo3) {
		this.lo3 = lo3;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getCancel_reason() {
		return cancel_reason;
	}

	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

	/*
	 * public Enterinfor getEnter() { return enter; } public void
	 * setEnter(Enterinfor enter) { this.enter = enter; }
	 */
	public String getPpId() {
		return ppId;
	}

	public void setPpId(String ppId) {
		this.ppId = ppId;
	}

	public String getPpsum() {
		return ppsum;
	}

	public void setPpsum(String ppsum) {
		this.ppsum = ppsum;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public List<BuyProduct2> getLbp2() {
		return lbp2;
	}

	public void setLbp2(List<BuyProduct2> lbp2) {
		this.lbp2 = lbp2;
	}

	public BuyProduct2 getBp2() {
		return bp2;
	}

	public void setBp2(BuyProduct2 bp2) {
		this.bp2 = bp2;
	}

	public Appraise getApp() {
		return app;
	}

	public void setApp(Appraise app) {
		this.app = app;
	}

	public int getLshopsize() {
		return lshopsize;
	}

	public void setLshopsize(int lshopsize) {
		this.lshopsize = lshopsize;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Product getPpp() {
		return ppp;
	}

	public void setPpp(Product ppp) {
		this.ppp = ppp;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public SimpleDateFormat getOrderno2() {
		return orderno2;
	}

	public void setOrderno(SimpleDateFormat orderno2) {
		this.orderno2 = orderno2;
	}

	public EnterInfor getEnter() {
		return enter;
	}

	public void setEnter(EnterInfor enter) {
		this.enter = enter;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LogisticService getLogisticService() {
		return logisticService;
	}

	public void setLogisticService(LogisticService logisticService) {
		this.logisticService = logisticService;
	}

	public Logistics getLogistic() {
		return logistic;
	}

	public void setLogistic(Logistics logistic) {
		this.logistic = logistic;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMerid() {
		return merid;
	}

	public void setMerid(String merid) {
		this.merid = merid;
	}

	public String getOrdernos() {
		return ordernos;
	}

	public void setOrdernos(String ordernos) {
		this.ordernos = ordernos;
	}

	public String getTransdate() {
		return transdate;
	}

	public void setTransdate(String transdate) {
		this.transdate = transdate;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getBgRetUrl() {
		return BgRetUrl;
	}

	public void setBgRetUrl(String bgRetUrl) {
		BgRetUrl = bgRetUrl;
	}

	public String getPageRetUrl() {
		return PageRetUrl;
	}

	public void setPageRetUrl(String pageRetUrl) {
		PageRetUrl = pageRetUrl;
	}

	public String getGateId() {
		return GateId;
	}

	public void setGateId(String gateId) {
		GateId = gateId;
	}

	public String getPriv1() {
		return Priv1;
	}

	public void setPriv1(String priv1) {
		Priv1 = priv1;
	}

	public String getChkValue() {
		return ChkValue;
	}

	public void setChkValue(String chkValue) {
		ChkValue = chkValue;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCheckvalue() {
		return checkvalue;
	}

	public void setCheckvalue(String checkvalue) {
		this.checkvalue = checkvalue;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

}
