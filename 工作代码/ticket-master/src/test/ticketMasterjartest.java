package test;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.ticket.master.common.Md5;
/**
 * @票务平台接口方法
 * 
 */
public class ticketMasterjartest {
	
	   //机票类
	   String endpoint = "http://192.168.16.118:8080/ticket-master/services/VetechAsmsB2BTicketWebService";
	   //保险类
	   String insurance = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BInsuranceWebService";
	   //基础数据类
	  String getcityinfo = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BDataBaseWebService";
	  // 公共资料类
	 String publicType = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BCommonWebService";
	  //酒店类
		String hotelStore = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BHotelWebService";
	  //支付类
	  String pay = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BAccountWebService";
	  /*
		 * 机票类
		 */
	    /**
	     * 航班单程查询接口 searchTicket 
	     * @param account
	     * @param md5
	     * @param xml
	     * @return
	     */
	  public static void main(String[] args) {
			ticketMasterjartest tmj = new ticketMasterjartest();
			String temp = "";
			temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+"<Gngj>1</Gngj>"
					+ "</Request>";
			/*temp = "<Request>"
					+"<ResponseDataType>1</ResponseDataType>"
					+ "<Start>0</Start>"
					+ "<Count>10</Count>"
					+ "<CityThreeCode>PVG</CityThreeCode>"
					+ "</Request>";*/
			/*temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+"<UserId>FXCSHI</UserId>"
					+"<Platform>B2B</Platform>"
					+ "</Request>";*/
			/*temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+"<LoginUserId>admin</LoginUserId>"
					+"<ParameterId>7734</ParameterId>"
					+"<Platform>B2B</Platform>"
					+ "</Request>"; */
			/*temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+"<LoginUserId>admin</LoginUserId>"
					+"<Category>1001</Category>"
					+ "</Request>"; */
			/*temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+" <Category>FXCSHI </Category>"
					+ "</Request>";*/
			/*temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+" <LoginUserId>admin</LoginUserId>"
					+ "</Request>";*/
		/*	temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+" <LoginUserId>admin</LoginUserId>"
					+" <Pt>B2B </Pt>"
					+" <Ywlx>1 </Ywlx>"
					+ "</Request>";*/
		/*	temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+" <LoginUserId>admin</LoginUserId>"
					+" <Category>1001</Category >"
					+ "</Request>";*/
			String temp1 = "<Request>"
					+ "<ResponseDataType>2</ResponseDataType>"
					+ "<LoginUserId>admin</LoginUserId>"
					+ "<DepartCity>SZX</DepartCity>"
					+ "<ArrivalCity>WNZ</ArrivalCity>"
					+ "<DepartDate>2014-02-28</DepartDate>"
					+ "<Airways></Airways>"
					+ "<PassengerType>1</PassengerType>"
					+ "<CabinType></CabinType>" 
					+ "<SortType></SortType>"
					+ "</Request>";


			String temp2 = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+ "<LoginUserId>admin</LoginUserId>"
					+ "<Head>31DEC(MON) SZXWNZ DIRECT ONLY</Head>"
					+ "<FlightNo>ZH9965</FlightNo>"
					+ "<Cabins>"
					+ "F7 P4 A4 YA BA MA HA KA LA JA QA ZA U5 NA"
					+ "</Cabins>"
					+ "<Voyage>SZXWNZ</Voyage>"
					+ "<DepartureTime>15:05</DepartureTime>"
					+ "<ArrivalTime>16:55</ArrivalTime>"
					+ "	<AircraftType>320</AircraftType>"
					+ "	<YPrice>480</YPrice>"
					+ "</Request>";
			String temp3 = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+ "<LoginUserId>admin</LoginUserId>"
					+ "<CityId>54915</CityId>"
					+ "<CheckInDate>2014-02-18</CheckInDate>"
					+ "<CheckOutDate>2014-02-26</CheckOutDate>"
					+ "</Request>";
	
			String xml = "<Request>"
						+ "<ResponseDataType>1</ResponseDataType>"
						+ "<LoginUserId>admin</LoginUserId>"
						+ "	<Start>0</Start>"
						+ "<Count>20</Count>"
						+ "<Fxsbh>admin</Fxsbh>"
						+ "<Fxscompid>FXGS</Fxscompid>"
						+ "</Request>";
		/*	String xml = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+ "<LoginUserId>admin</LoginUserId>"
					+ "	<Id>0</Id>"
					+ "</Request>";*/
		/*	String xml = "<Request>"
					+ "<ResponseDataType>2</ResponseDataType>"
						+ "<LoginUserId>admin</LoginUserId>"
						+ "<Start>0</Start>"
						+ "<Count>20</Count>"
				+ "	<ContactPhone> 13543534534</ContactPhone>"
				+ "	<Contact>张旺</Contact>"
				+ "	<OrderNumber> 140220112124846074</OrderNumber>"
				+ "	<OrderStatus>2</OrderStatus>"
				+ "	<OrderType>1</OrderType>"
				+ "	<StartDate> 2014-02-19</StartDate>"
				+ "	<EndDate> 2014-02-20</EndDate>"
					+ "</Request>";*/
				String dd ="<Request>"
						+ "<ResponseDataType>2</ResponseDataType>"
						+ "<LoginUserId>admin</LoginUserId>"
						+ "	<Triptype>1</Triptype>"
						+ "	<Cfcity>SZX</Cfcity>"
						+ "	<Ddcity>WNZ</Ddcity>"
						+ "	<Cfdate>2014-02-27</Cfdate>"
						+ "	<Contype>1</Contype>"
					+ "</Request>";
				
				

				tmj.queryTicketPolcy("admin", Md5.GetMd5(dd + Md5.GetMd5("21232f297a57a5a743894a0e4a801fc3")), dd);
			//	tmj.queryMoreCabin("admin", Md5.GetMd5(temp2 + Md5.GetMd5("21232f297a57a5a743894a0e4a801fc3")), temp2);
				//tmj.queryOffice_ggtz_detail("admin", Md5.GetMd5(xml + Md5.GetMd5("21232f297a57a5a743894a0e4a801fc3")), xml);

	  }
		public  String searchTicket(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("searchTicket");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 其余舱位匹配政策 queryMoreCabin  
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryMoreCabin(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryMoreCabin");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 其机票预定接口 queryMoreCabin 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String bookTicket(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("bookTicket");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 获取标准票价接口 queryMoreCabin
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryStandarPrice(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryStandarPrice");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 根据PNR PAT接口 queryPatByPNR 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryPatByPNR(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryPatByPNR");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 根据航段PAT接口 queryPatBySegment 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryPatBySegment(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryPatBySegment");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 选择PAT价格更改订单信息 editOrderByPat
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String editOrderByPat(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("editOrderByPat");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 获取实时政策接口 queryPolcy
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryPolcy(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryPolcy");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 分销订单重选政策接口 changedPolicy
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String changedPolicy(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("changedPolicy");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * PNR下单 createOrderByPnr
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String createOrderByPnr(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("createOrderByPnr");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 机票订单查询 queryTicketOrder
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryTicketOrder(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryTicketOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 机票订单详细查询  queryTicketOrderDetail
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryTicketOrderDetail(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryTicketOrderDetail");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 修改订单证件号码  editIdentityNo
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String editIdentityNo(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("editIdentityNo");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 修改订配送信息、行程单收件地址  editDeliveryInfo
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String editDeliveryInfo(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("editDeliveryInfo");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 修改订配送信息、行程单收件地址  editDeliveryInfo
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String getDeliveryAddress(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("getDeliveryAddress");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 退废票申请  applyTicketRefund
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String applyTicketRefund(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("applyTicketRefund");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 退废订单查询   queryTicketReturnOrder
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryTicketReturnOrder(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryTicketReturnOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 退废订单详查询    queryTicketReturnOrderDetail
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryTicketReturnOrderDetail(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryTicketReturnOrderDetail");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 改签申请    applyChangeTicket
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String applyChangeTicket(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("applyChangeTicket");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 改签订单查询    queryChangeOrder
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryChangeOrder(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryChangeOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 改签订单详查询     queryChangeOrderDetail
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryChangeOrderDetail(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryChangeOrderDetail");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 取消机票订单     cancelTicketOrder
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String cancelTicketOrder(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("cancelTicketOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 查询航班政策     queryTicketPolcy
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryTicketPolcy(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryTicketPolcy");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}

		/**
		 * 分销商出票清单查询     queryFxsTicketCp
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryFxsTicketCp(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(endpoint);
				call.setOperationName("queryFxsTicketCp");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}

		/**
		 * 支付类
		 * 
		 * 订单支付及代扣支付接口     queryFxsTicketCp
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String xykTopay(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(pay);
				call.setOperationName("xykTopay");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 保险类
		 * 
		 * 保险接口      queryInsuranceSpecies
		 * 
		 * 
		 */
		public  String queryInsuranceSpecies(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("queryInsuranceSpecies");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 保险订单申请   createInsuranceOrder 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String createInsuranceOrder(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("createInsuranceOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 保险订单查询   queryInsuranceOrder 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryInsuranceOrder(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("queryInsuranceOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 保险承保  createInsurance 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String createInsurance(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("createInsurance");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 保险退保  refunInsurance 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String refunInsurance(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("refunInsurance");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 取消保险订单  cancelInsuranceOrder  
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String cancelInsuranceOrder(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("cancelInsuranceOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 保险日报  queryInsuranceJournal   
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryInsuranceJournal(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("queryInsuranceJournal");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 保险订单详情查询   queryInsuranceDetail   
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public  String queryInsuranceDetail(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(insurance);
				call.setOperationName("queryInsuranceDetail");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**基础类
		 * 
		 * 接口：获得城市信息     queryB_city
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryB_city(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryB_city");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口：获得航空公司 queryAirway
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryAirway(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryAirway");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 接口：获取友情链接，文字，图片 queryLinks
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryLinks(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryLinks");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口：查询分销常用参数 queryParameter
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryParameter(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryParameter");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口：取基础数据接口 queryDatebase
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryDatebase(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryDatebase");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口：取数据字典接口 queryBClass
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryBClass(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryBClass");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口：取支付方式、支付科目数据接口queryPaySubject
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryPaySubject(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryPaySubject");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口：取分销可使用的支付方式、支付科目数据接口queryCanPayPaySubject
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryCanPayPaySubject(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryCanPayPaySubject");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口：取分销商基础数据接口queryFxsDatebase
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryFxsDatebase(String account, String md5, String xml)
		{
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(getcityinfo);
				call.setOperationName("queryFxsDatebase");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] {account,md5, xml});
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 酒店类
		 * 
		 * 接口：获取酒店动态信息 queryHotel
		 * 
		 */
		public String queryHotel(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(hotelStore);
				call.setOperationName("queryHotel");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 获取酒店静态数据地址   getHotelStaticDataXmlURL
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String getHotelStaticDataXmlURL(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(hotelStore);
				call.setOperationName("getHotelStaticDataXmlURL");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 酒店订单详情查询   queryHotelOrderDetail 
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryHotelOrderDetail(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(hotelStore);
				call.setOperationName("queryHotelOrderDetail");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 酒店订单列表查询   queryHotelOrderList  
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryHotelOrderList(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(hotelStore);
				call.setOperationName("queryHotelOrderList");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 酒店订单取消   cancelHotelOrder   
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String cancelHotelOrder(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(hotelStore);
				call.setOperationName("cancelHotelOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 酒店订单提交   submitHotelOrder    
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String submitHotelOrder(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(hotelStore);
				call.setOperationName("submitHotelOrder");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		/**
		 * 接口分类：公共资料
		 * 
		 * 接口：获取后台发布的图片 queryPic
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryPic(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(publicType);
				call.setOperationName("queryPic");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}

		/**
		 * 接口：帮助中心菜单查询 queryHelpMenu
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryHelpMenu(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(publicType);
				call.setOperationName("queryHelpMenu");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}

		/**
		 * 接口：帮助中心信息详细查询  queryHelpContent
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryHelpContent(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(publicType);
				call.setOperationName("queryHelpContent");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 接口：查询所有公告列表  queryOffice_ggtz
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryOffice_ggtz(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(publicType);
				call.setOperationName("queryOffice_ggtz");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
		
		/**
		 * 接口：查询公告信息详细  queryOffice_ggtz_detail
		 * 
		 * @param account
		 * @param md5
		 * @param xml
		 * @return
		 */
		public String queryOffice_ggtz_detail(String account, String md5, String xml) {
			try {
				// 直接引用远程的wsdl文件
				Service service = new Service();
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(publicType);
				call.setOperationName("queryOffice_ggtz_detail");// WSDL里面描述的接口名称
				call.addParameter("account",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("md5",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.addParameter("xml",
						org.apache.axis.encoding.XMLType.XSD_STRING,
						javax.xml.rpc.ParameterMode.IN);// 接口的参数
				call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
				String result = (String) call.invoke(new Object[] { account, md5,
						xml });
				// 给方法传递参数，并且调用方法
				System.out.println("result is " + result);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.toString());
				return e.toString();
			}
		}
}
