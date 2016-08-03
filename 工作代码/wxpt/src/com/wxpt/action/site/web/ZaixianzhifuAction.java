package com.wxpt.action.site.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.site.service.ZaixianzhifuService;

/*@Results({
	@Result(name = "ss", location = "/WEB-INF/content/site/web/order-send.jsp")
	})*/
public class ZaixianzhifuAction extends ActionSupport {
	@Autowired
    ZaixianzhifuService zaixianzhifuservice;
	private int enterId=GetCurrentUser.getEnterId();//
	
    //response.sendRedirect("apage.jsp");//重定向到apage.jsp

	public String jump() {

		
		
		
			return "ss";
	
		
	}
	
	
	
	
	
	
//////////////////以下银联用	
	private String version = "20070129";//。。

	private String Priv1 =null ;//先传一个死的，测试用的..
	private String price="0.01";//125fen为测试数据
	private String status = null;//交易状态
	private String amount;//交易金额
	private String checkvalue;//密文
	private String currencycode = "156";//人民币。。
	private String merid = "808080231802790";//商户号 户头。。
	private String orderno="0020131014140814";//订单号。。
	private String transdate = new SimpleDateFormat("yyyyMMdd").format(new Date());//交易时间
	private String transtype = "0001";//交易类型。。
	
	
	//字符串去零
	  public  String removeTailZero() {
			 String s = price;
			 String send="";
			 int a = s.indexOf(".");
			 if(a>=0){
				 String s2=s.replace(".", "=");
				 String ss[]=s2.split("="); 
				 String ss1 = ss[0];
				 String ss2 = ss[1];
				if(ss1.length()==1){
					ss1="000000000"+ss1;
				}else if(ss1.length()==2){
					ss1="00000000"+ss1;
				}else if(ss1.length()==3){
					ss1="0000000"+ss1;
				}else if(ss1.length()==4){
					ss1="000000"+ss1;
				}else if(ss1.length()==5){
					ss1="00000"+ss1;
				}else if(ss1.length()==6){
					ss1="0000"+ss1;
				}else if(ss1.length()==7){
					ss1="000"+ss1;
				}else if(ss1.length()==8){
					ss1="00"+ss1;
				}else if(ss1.length()==9){
					ss1="0"+ss1;
				}
				else if(ss1.length()==10){
					ss1=ss1;
				}

				if(ss2.length()==1){
					ss2=ss2+"0";
				}else if(ss2.length()==2){
					ss2=ss2;
				}else{
					int a2 = s2.indexOf("=");
					ss2 = s2.substring(a2+1,a2+3);
				}
				send = ss1+ss2;
			 }
			 
			 if(a<0){
				if(s.length()==1){
					send="000000000"+s+"00";
				}else if(s.length()==2){
					
					send="00000000"+s+"00";
				}else if(s.length()==3){
					
					send="0000000"+s+"00";
				}else if(s.length()==4){
					
					send="000000"+s+"00";
				}else if(s.length()==5){
					
					send="00000"+s+"00";
				}else if(s.length()==6){
					
					send="0000"+s+"00";
				}else if(s.length()==7){
					
					send="000"+s+"00";
				}else if(s.length()==8){
					
					send="00"+s+"00";
				}else if(s.length()==9){
					
					send="0"+s+"00";
				}else if(s.length()==10){
					
					send=s+"00";
				}   
			 }
			 
			 return send;
			}

		//银联签名
		public  void getOrder()
		{
			//单词的大小写很敏感
			//TransAmt = String.format("%012d", Integer.parseInt(this.removeTailZero()));
			//修改价格  测试
			amount = this.removeTailZero();
			
			
			//amount = String.format("%012d", Integer.parseInt(this.removeTailZero()));
			//amount = "000000000100";
		
			Priv1="wangxin";
			
			chinapay.PrivateKey key1=new chinapay.PrivateKey();
			chinapay.SecureLink t1;
			boolean flag;

		     flag=key1.buildKey("808080231802790", 0, this.getClass().getResource("/MerPrK_808080231802790_20121210141220.key").getPath()); //注意要用哪一个商户号签名就要用对应的key文件。
		  // flag=key1.buildKey("808080231802790", 0, "C:\\MerPrK_808080231802790_20121210141220.key"); //注意要用哪一个商户号签名就要用对应的key文件。

			if (flag==false) 
			{
				System.out.println("build key error!!!");
			    return ;
			}

			t1=new chinapay.SecureLink (key1);
			checkvalue=t1.Sign(merid+orderno+amount+currencycode+transdate+transtype+Priv1); //获得对应商户的签名数据
		      return;
		}
		
		
		//接受银联反馈，修改数据库中，订单的支付状态
		public void  receiveOrder()
		{
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
				    {
						
					/*	order.setState(1);
						order.setTransDate(TimeUtil.getTime());
				    	orderService.setUpdateOrder(order);
				    */
				    	return;
				    
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

		
		//返回给消费者的界面
		public String findSuccess()
		{
			//linkList = linkService.findAll(hql, 1, 5);
			return "success";
		}	
//////////////////以上银联用end		  


		
		
		
//////////////////////////////////////////////////////////以下是支付宝	
		private String ordermum="2013101802540";
	

		UtilDate date = new UtilDate();//当前系统时间
		String paygateway = "https://www.alipay.com/cooperate/gateway.do?"; //支付接口(不可修改)//跳转到支付宝的url

		String service = "create_partner_trade_by_buyer";//标准实物接口服务参数(不可修改)
		String sign_type = "MD5"; //编码机制，目前采用MD5加密
		String out_trade_no = date.getOrderNum(); //商户网站订单,客户可以自己定义，目前采用时间作为订单号////////////
		String input_charset = "GBK";  //页面编码，此时默认GBK
		/*查询合作者ID和安全校验码：登陆签约支付宝账户--->点击”商家服务“，可以看到我的商家服务【如果没有看到，说明合同还没有生效
		  ,需要确认合同生效时间】
		*/
		String partner = "2088101568345155"; //支付宝合作伙伴id (账户内提取)/////////////////..
		String key = "xu6xamwvgk5b51ahco9sgpbxy1e49ve9"; //支付宝安全校验码(账户内提取)////////////////..
		String seller_email = "alipay-test03@alipay.com"; //卖家支付宝帐户,例如：gwl25@126.com13188888376..
		
		/******以上是账户信息，以下是商品信息**************************/
		String body = "0020131014140814"; //+"out_trade_no"支付宝（订单号:"+out_trade_no+")商品描述，推荐格式：商品名称（订单编号：订单编号）,例如：支付宝（订单号：2008122500120）
		String subject = "电脑"; //商品名称，如果想显示多个商品名称，可以将多个商品叠加传入。
		String pricez= "0.01"; //订单总价，范围：0.01～100000000.00（小数点后面最多两位）例如：23.80  
		String quantity = "1";  //一般情况可以默认为1，具体可以参看开发文档 jiao yi shu liang
		String show_url = "";   //商品展示地址，例如：http://www.alipay.com；如果锁定具体商品页面，可以传入（http://www.alipay.com?pid=变量）
		String payment_type = "1";//支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
		String discount = "0";   //折扣价格，不能直接传入百分比，范围：-10000000.00<i<10000000.00
		
		/******物流信息和支付宝通知，一般商城不需要通知，请删除此参数，并且在payment.java里面相应删除参数********/
		String logistics_type = "EMS";  //物流配送方式：POST(平邮)、EMS(EMS)、EXPRESS(其他快递)///////////
		String logistics_fee = "0.01";  //物流配送费用x
		String logistics_payment = "SELLER_PAY";     //物流配送费用付款方式：SELLER_PAY(卖家支付)、BUYER_PAY(买家支付)、BUYER_PAY_AFTER_RECEIVE(货到付款)
		String notify_url = "http://192.168.16.114:8080/vshop/alipay_notify.jsp"; //通知接收URL，需要写返回处理文件的绝对路径，例如："http://localhost:8081/jsp_shi_gbk/alipay_notify.jsp"
		String return_url = "http://192.168.16.114:8080/vshop/alipay_return.jsp	"; //支付完成后跳转返回的网址URL,需要写返回处理文件的绝对路径，例如：http://localhost:8080/jsp_shi_gbk/alipay_return.jsp	
	
		String ItemUrl;

		
		
	//////支付宝接受返回的验证后台	
		String notify_id;

		@SuppressWarnings("unchecked")
		public String alipay_notify(){
		
          HttpServletRequest request=ServletActionContext.getRequest();
			
            String partner = ""; //partner合作伙伴id（必须填写） (账户内提取)
			String privateKey = ""; //partner 的对应交易安全校验码（必须填写）
			//**********************************************************************************
			//如果您服务器不支持https交互，可以使用http的验证查询地址
			//***注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接
			//String alipayNotifyURL = "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"
			String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
					+ "partner="
					+ partner
					+ "&notify_id="
					+ request.getParameter("notify_id");
			//**********************************************************************************
			//获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
			String responseTxt = CheckURL.check(alipayNotifyURL);

			Map params = new HashMap();
			//获得POST 过来参数设置到新的params中
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";				
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化（现在已经使用）
				//valueStr=new String (valueStr.getBytes("ISO-8859-1"),"GBK"); 
				params.put(name, valueStr);
			}
			
			String mysign = com.wxpt.action.site.web.SignatureHelper.sign(params, privateKey);
			// 交易状态更多信息请看文档介绍， 该文件不允许包含html代码，请注意，只能做数据业务操作.
			if (mysign.equals(request.getParameter("sign")) && responseTxt.equals("true") ){
				
			/*	if(request.getParameter("trade_status").equals("WAIT_BUYER_PAY")){
					//交易已创建，等待买家付款。
					//在这里可以写入数据处理,
					return "";
					
					//out.println("success");
				}else
					*/
					
					if(request.getParameter("trade_status").equals("WAIT_SELLER_SEND_GOODS")){
					//买家付款成功，等待卖家发货   1：已支付
					zaixianzhifuservice.fukuanchenggong(enterId,ordermum);//request.getParameter("out_trade_no");
					
					String url = "http://192.168.16.121:8080/vshop/site/web/order!getAllSendOrder";


				    //你要转向的页面的地址. 
				    HttpServletResponse response = ServletActionContext.getResponse();
				    
				    try {
						response.sendRedirect(url);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 

					return "fukuanchenggong";
					//在这里可以写入数据处理,
					//out.println("success");
				}/*else if(request.getParameter("trade_status").equals("WAIT_BUYER_CONFIRM_GOODS")){
					//卖家已发货， 等待买家确认
					//zaixianzhifuservice.yifahuo(ordermum);//request.getParameter("out_trade_no");fan hui de ding dan hao
					return "";
					//在这里可以写入数据处理,
					//out.println("success");
				}else if(request.getParameter("trade_status").equals("TRADE_FINISHED")){
					//交易成功结束
					
					
					//在这里可以写入数据处理,
					//out.println("success");
				}*/
			}
			else
			{
				//out.println("fail");
				return "";
			}
		 
			return "";
		 
	}
		
		
////////////////////////////////////////////////////////已上是支付宝end		
		
		
		
		
		
		
		

	String zhifu;
	public String getZhifu() {
		return zhifu;
	}


	public void setZhifu(String zhifu) {
		this.zhifu = zhifu;
	}


	public String xuanzhifu(){
	
		try {
			if(zhifu==null){
				
			}
			
			if(zhifu.equals("1")){

				ItemUrl = Payment.CreateUrl(paygateway, service, sign_type,
						out_trade_no, input_charset, partner, key, seller_email,
						body, subject, pricez, quantity, show_url, payment_type,
						discount, logistics_type, logistics_fee, logistics_payment,
						return_url, notify_url);
				
				return "zhifubao";
				
			}
			if(zhifu.equals("2")){
			
				getOrder();
			
				return "yinlian";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "";

	}
	
	
////set get 
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPriv1() {
		return Priv1;
	}

	public void setPriv1(String priv1) {
		Priv1 = priv1;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCheckvalue() {
		return checkvalue;
	}

	public void setCheckvalue(String checkvalue) {
		this.checkvalue = checkvalue;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getMerid() {
		return merid;
	}

	public void setMerid(String merid) {
		this.merid = merid;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
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

/////////////支付宝用set  get
	public UtilDate getDate() {
		return date;
	}

	public void setDate(UtilDate date) {
		this.date = date;
	}

	public String getPaygateway() {
		return paygateway;
	}

	public void setPaygateway(String paygateway) {
		this.paygateway = paygateway;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getInput_charset() {
		return input_charset;
	}

	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPricez() {
		return pricez;
	}

	public void setPricez(String pricez) {
		this.pricez = pricez;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getLogistics_type() {
		return logistics_type;
	}

	public void setLogistics_type(String logistics_type) {
		this.logistics_type = logistics_type;
	}

	public String getLogistics_fee() {
		return logistics_fee;
	}

	public void setLogistics_fee(String logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	public String getLogistics_payment() {
		return logistics_payment;
	}

	public void setLogistics_payment(String logistics_payment) {
		this.logistics_payment = logistics_payment;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String getItemUrl() {
		return ItemUrl;
	}

	public void setItemUrl(String itemUrl) {
		ItemUrl = itemUrl;
	}

	
	
	
	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}


	
	public ZaixianzhifuService getZaixianzhifuservice() {
		return zaixianzhifuservice;
	}

	public void setZaixianzhifuservice(ZaixianzhifuService zaixianzhifuservice) {
		this.zaixianzhifuservice = zaixianzhifuservice;
	}


	public String getOrdermum() {
		return ordermum;
	}

	public void setOrdermum(String ordermum) {
		this.ordermum = ordermum;
	}


	
}
