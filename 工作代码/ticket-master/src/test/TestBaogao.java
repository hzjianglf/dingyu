package test;

import net.sf.json.xml.XMLSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.json.JSONArray;

import com.ticket.master.common.Md5;

public class TestBaogao {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
		//	String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BTicketWebService";
		//String endpoint = "http://192.168.16.114:8080/ticket-master/services/VetechAsmsB2BAccountWebService";
		String endpoint = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BTicketWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			//call.setOperationName("queryTicketOrderDetail ");// WSDL里面描述的接口名称
			/**/
			//call.setOperationName("searchTicket");// WSDL里面描述的接口名称
			call.setOperationName("queryTicketReturnOrderDetail");// WSDL里面描述的接口名称
			call.addParameter("type",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.addParameter("md5",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.addParameter("xml",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			String 
//			temp="<Request>" +
//					"<ResponseDataType>1</ResponseDataType>" +
//					"<LoginUserId>byz123</LoginUserId>" +
//					"<CheckInDate>2014-02-20</CheckInDate>" +
//					"<CheckOutDate>2014-02-22</CheckOutDate>" +
//					"<ArrivalEarlyTime>2014-02-20T14:00:00</ArrivalEarlyTime>" +
//					"<ArrivalLateTime>2014-02-20T16:00:00</ArrivalLateTime>" +
//					"<NotifyType>Phone</NotifyType>" +
//					"<NotifyLanguage>CN</NotifyLanguage>" +
//					"<NeedGuarantee>0</NeedGuarantee>" +
//					"<Currency>RMB</Currency>" +
//					"<PayType>1</PayType>" +
//					"<CityId>101010100</CityId>" +
//					"<HotelId></HotelId>" +
//					"<HotelName></HotelName>" +
//					"<TotalPrice>1700</TotalPrice>" +
//					"<BackAmount>200</BackAmount>" +
//					"<BackRates>2014-02-20,200,0.8</BackRates>" +
//					"<OrderRooms></OrderRooms>" +
//					"<Contacter></Contacter>" +
//					"<Gender>0</Gender>" +
//					"<Phone>0531-88546787</Phone>" +
//					"<Mobile>13356456545</Mobile>" +
//					"<Fax></Fax>" +
//					"<Email>33434532@qq.com</Email>" +
//					"<Nationality></Nationality>" +
//					"<IDType>0</IDType>" +
//					"<IDNo>371522198604035711</IDNo>" +
//					"<Roommand ></Roommand >" +
//					"<Smokedemand ></Smokedemand >" +
//					"<Beddomand ></Beddomand >" +
//					"<Addbed ></Addbed >" +
//					"<Bitedomand ></Bitedomand >" +
//					"<Bitedomand ></Bitedomand >" +
//					"<ParameterString1></ParameterString1>" +
//					"<ParameterString2></ParameterString2>" +
//					"<HotelSuretyID></HotelSuretyID>" +
//					"<SupplierID ></SupplierID >" +
//					"<GaranteeRulesTypeCode></GaranteeRulesTypeCode>" +
//					"<StartDate></StartDate>" +
//					"<EndDate></EndDate>" +
//					"<DateType></DateType>" +
//					"<WeekSet ></WeekSet >" +
//					"<Gage></Gage>" +
//					"<IsArriveTimeVouch></IsArriveTimeVouch>" +
//					"<ArriveStatTime></ArriveStatTime>" +
//					"<ArriveEndTime></ArriveEndTime>" +
//					"<IsTomorrow></IsTomorrow>" +
//					"<IsRoomCountVouch></IsRoomCountVouch>" +
//					"<RoomCount></RoomCount>" +
//					"<VouchMoneyType></VouchMoneyType>" +
//					"<IsChange></IsChange>" +
//					"<ChangeRule></ChangeRule>" +
//					"<TimeNum></TimeNum>" +
//					"<HourNum></HourNum>" +
//					"<Description></Description>" +
//					"<Remark></Remark>" +
//					"<SupplyHotelID></SupplyHotelID>" +
//					"<OrderID></OrderID>" +
//					"<IsHaveBreakFast></IsHaveBreakFast>" +
//					"<DayNum></DayNum>" +
//					"</Request>";
			temp="<Request>" +
					"<ResponseDataType>2</ResponseDataType>"+
					"<LoginUserId>byz123</LoginUserId>"+
					"<OrderID>748749845680140221</OrderID>"+
					"<PassIdeInfo>"+
					"<PassIde>"+
					"<PassengerID>JW1T8P1402210958380202</PassengerID>"+
					"<NewIdentityNo>371522198604035711</NewIdentityNo>"+
					"</PassIde>"+
					"</PassIdeInfo>"+
					"</Request>";
			
//			String result = (String) call
//					.invoke(new Object[] {
//							"SDJNHD18",
//							Md5.GetMd5(temp
//									+ Md5.GetMd5("28bbz8dhuh0dugt2egc4")), temp });
String temp2="<Request>" +//订单支付
		"<ResponseDataType>1</ResponseDataType>" +
		"<LoginUserId>byz123</LoginUserId>" +
		"<Zfkm>1006203</Zfkm>" +
		"<Zffs>1006305</Zffs>"+
		"<Autopay>1</Autopay>" +
		"<Pt>B2B</Pt>" +
		"<Hyid>1105261140058305</Hyid>" +
		"<YwType>1</YwType>" +
		"<Yymk>312013404</Yymk>" +
	    "<Cldh>746888528052140227</Cldh>" +
		"<IsYh>0</IsYh>" +
		"<TransAmt>665.00</TransAmt>" +
		"<Version>1</Version>"+
		"<Md5info>76E87EAE02BA06ECAEEB53E707CDA941</Md5info>" +
		"</Request>";

temp2="<Request>" +//申请退票,竟测试未出票不能退费
		"<ResponseDataType>1</ResponseDataType>"+
		"<LoginUserId>byz123</LoginUserId>"+
		"<OrderNo>746888528052140227</OrderNo>"+
		"<RefunType>2</RefunType>"+
		"<IsAgree>0</IsAgree>"+
		"<IsCanclePnr>0</IsCanclePnr>"+
		"<PassengerId>JDX4P41402271332520429</PassengerId>"+
		"<FlightId>140227133252153232000000647014</FlightId>"+
		"</Request>";
temp2="<Request>" +//退废订单查询
		"<ResponseDataType>1</ResponseDataType>" +
		"<LoginUserId>byz123</LoginUserId>" +
		"<ApplyStartDate>2014-02-01</ApplyStartDate>" +
		"<ApplyEndDate>2014-02-28</ApplyEndDate>" +
		"</Request>";
temp2="<Request>" +//退废订单详细查询
		"<ResponseDataType>1</ResponseDataType>" +
		"<LoginUserId>byz123</LoginUserId>" +
		"<orderID>4857485964</orderID>"+
		"</Request>";
//temp2="<Request>" +//查询机票详细信息 
//		"<ResponseDataType>1</ResponseDataType>" +
//		"<LoginUserId>byz123</LoginUserId>" +
//		"<OrderID>746888528052140227</OrderID>" +
//		"</Request>";
//String cldh="728955778783140227";
//String  str="747.00728955778783140227vetech123";
//String str2=Md5.GetMd5(str).toUpperCase();
System.out.println(temp2);
//String result = (String) call
//.invoke(new Object[] {
//		"SDJNHD18",
//		Md5.GetMd5(temp2
//				+ Md5.GetMd5("28bbz8dhuh0dugt2egc4")), temp2 });
			String result = (String) call
					.invoke(new Object[] {
							"byz123",
							Md5.GetMd5(temp2
									+ Md5.GetMd5(Md5.GetMd5("123456"))), temp2 });
		//	 给方法传递参数，并且调用方法
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}

	}

	
}
