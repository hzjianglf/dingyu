package test;

import net.sf.json.xml.XMLSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.json.JSONArray;

import com.ticket.master.common.Md5;

public class Myinterface {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			//String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BTicketWebService";
			String endpoint = "http://192.168.16.122:8080/ticket-master/services/VetechAsmsB2BTicketWebService";
			//String endpoint = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BTicketWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			//call.setOperationName("queryTicketOrderDetail ");// WSDL里面描述的接口名称
			//call.setOperationName("searchTicket");// WSDL里面描述的接口名称
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
			String temp = "<Request>"
					+ "<ResponseDataType>1</ResponseDataType>"
					+ "<LoginUserId>byz123</LoginUserId>"
					+ "<DepartCity>WUH</DepartCity>"
					+ "<ArrivalCity>PEK</ArrivalCity>"
					+ "<DepartDate>2014-02-26</DepartDate>"
					+ "<Airways></Airways>"
					+ "<PassengerType>1</PassengerType>"
					+ "<CabinType></CabinType>" + "<SortType></SortType>"
					+ "</Request>";
			/*temp="<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
					"<LoginUserId>byz123</LoginUserId>" +
					"<Head>31DEC(MON) WUHSZX DIRECT ONLY</Head>" +
					"<FlightNo>MU3093</FlightNo>" +
					"<Cabins>YA BA EA HA KA NA SA</Cabins>" +
					"<Voyage>WUHPEK</Voyage>" +
					"<DepartureTime>16:20</DepartureTime>" +
					"<ArrivalTime>18:10</ArrivalTime>" +
					"<AircraftType></AircraftType>" +
					"<YPrice></YPrice>" +
					"</Request>";
			temp="<Request>"//查询订单
					 +"<ResponseDataType>1</ResponseDataType>" +
					 "<LoginUserId>byz123</LoginUserId>" +
					 "<Start></Start>" +
					 "<Count></Count>" +
					 "<OrderID></OrderID>" +
					 "<DateType>1</DateType>" +
					 "<StartDate>2014-02-08</StartDate>" +
					 "<EndDate>2014-04-10</EndDate>" +
					 "</Request>";
			temp="<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
					"<LoginUserId>SDJNHD18</LoginUserId>" +
					"<OrderID>746888528052140227</OrderID>" +
					"</Request>";*///取消订单
//			temp="<Request>" +//详细查询订单
//					"<ResponseDataType>1</ResponseDataType>" +
//					"<LoginUserId>zcd123</LoginUserId>" +
//					"<OrderID>728976887450140210</OrderID>" +
//					"</Request>";
//			temp="<Request>" +
//					"<ResponseDataType>1</ResponseDataType>" +
//					"<LoginUserId></LoginUserId>" +
//					"<OrderID>748478546856140207</OrderID>" +
//					"<PassIdeInfo>" +
//					"<PassIde>" +
//					"<PassengerID>370101197807052213</PassengerID>" +
//					"<NewIdentityNo>370101197807052213</NewIdentityNo>" +
//					"</PassIde>" +
//					"<PassIde>" +
//					"		<PassengerID></PassengerID>" +
//					"		<NewIdentityNo></NewIdentityNo>" +
//					"</PassIde>" +
//					"</PassIdeInfo>" +
//					"</Request>";
//			temp="<Request>" +//修改证件号码
//					"<ResponseDataType>1</ResponseDataType>" +
//					"<LoginUserId>zcd123</LoginUserId>" +
//					"<OrderID>728976887450140210</OrderID>" +
//					"<PassIdeInfo>" +
//					"<PassIde>" +
//					"<PassengerID>HYLXJ21402101119199989</PassengerID>" +
//					"<NewIdentityNo>37120219911103211x</NewIdentityNo>" +
//					"</PassIde>" +
//					"</PassIdeInfo>" +
//					"</Request>";
			
//			temp="<Request>" +
//					"<ResponseDataType>1</ResponseDataType>" +
//					"<LoginUserId>zcd123</LoginUserId>" +
//					"<OrderID>748487766868140210 </OrderID>" +
//					"<DeliveryType>1</DeliveryType>" +//改为机场取票
//					"<OrderType>0</OrderType>"+
//					"<IfNeedItinerary></IfNeedItinerary>"+
//					"<Addressee>济南</Addressee>"+
//					"<PostalCode>250100</PostalCode>"+
//					"<MailAddress>309213564@qq.com</MailAddress>"+
//					"<DeliveryCity></DeliveryCity>"+
//					"<DeliveryAddress></DeliveryAddress>"+
//					"<DeliveryTime></DeliveryTime>"+
//					"<DeliveryRemar>测试修改配送信息</DeliveryRemar>"+
//					"</Request>";//修改配送信息
//			String result = (String) call
//					.invoke(new Object[] {
//							"zcd123",
//							Md5.GetMd5(temp
//									+ Md5.GetMd5("28bbz8dhuh0dugt2egc4")), temp });
//			temp= "<Request>"
//					+ "<LoginUserId>zcd123</LoginUserId>"
//					+ "<ResponseDataType>1</ResponseDataType>"
//					+ "<LegType>1</LegType>"
//					+ "<OrderType>1</OrderType>"
//					+ "<InsuranceCode>DL0502_97</InsuranceCode>"
//					+ "<Legs>"
//					+ "<Leg>"
//					+ "<ArrCity>PEK</ArrCity>"
//					+ "<ArrTime>09:50</ArrTime>"
//					+ "<Cabin>V</Cabin>"
//					+ "<DepCity>WUH</DepCity>"
//					+ "<DepDate>2014-03-26</DepDate>"
//					+ "<DepTime>08:05</DepTime>"
//					+ "<Discount>0.45</Discount>"
//					+ "<FlightMod>738</FlightMod>"
//					+ "<FlightNo>CZ3117</FlightNo>"
//					+ "<IfNoSeat></IfNoSeat>"
//					+ "<Term> T2</Term>"
//					+ "</Leg>"
//					+ "</Legs>"
//					+ "<Passengers>"
//					+ "<Passenger>"
//					+ "<Birthday></Birthday>"
//					+ "<DocName>371522198604035711</DocName>"
//					+ "<DocType>NI</DocType>"
//					+ "<DocValid></DocValid>"
//					+ "<EatPrefe></EatPrefe>"
//					+ "<Insurance>1</Insurance>"
//					+ "<MobileNum>13615319882</MobileNum>"
//					+ "<Name>张成达</Name>"
//					+ "<Nationality>0002</Nationality>"
//					+ "<PassenType>1</PassenType>"
//					+ "<SeatPrefe></SeatPrefe>"
//					+ "<Sex>M</Sex>"
//					+ "</Passenger>"
//					+ "</Passengers>"
//					+ "<Pnrno></Pnrno>"
//					+ "<PolicyId>SD20130101095501234106</PolicyId>"
//					+ "<PlatPolicyId></PlatPolicyId>"
//					+ "<PlatOth>"
//					+ "4F7CBF216DB11463085A64440ED94C90B703FD183CD938105B4900A627ED3C55F71B837E773215AE426519F20ADFB51E00EC4879FEB862E4"
//					+ "</PlatOth>" + "<ContactMobile>15902814254</ContactMobile>"
//					+ "<ContactName>Geng/fei</ContactName>"
//					+ "<ContactPhone>027-254554</ContactPhone>"
//					+ "<DistrRemark>请填写备注信息</DistrRemark>" 
//					+ "<Note></Note>" 
//					+ "</Request>";
			
//			temp="<Request>" +
//					"<ResponseDataType>1</ResponseDataType>" +
//					"<LoginUserId>byz123</LoginUserId>" +
//					//"<CityId>101010100</CityId>" +
//					"<CheckInDate>2014-02-13</CheckInDate>" +
//					"<CheckOutDate>2014-02-14</CheckOutDate>" +
//					"</Request>";
//			temp="<Request>" +
//					"<ResponseDataType>1</ResponseDataType>" +
//					"<Category >1001</Category >" +
//					"<LoginUserId>byz123</LoginUserId>" +
//					"</Request>";
//			temp="Request>" +
//					"<ResponseDataType>1</ResponseDataType>" +
//					"< LoginUserId >byz123</ LoginUserId >" +
//					"</Request>";
			
			
		/*	
			temp="<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
				"< UserId >byz123</ UserId >" +
					"<Platform>B2B</Platform>" +
					"</Request>";
			
			temp="<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
					"<LoginUserId>byz123</LoginUserId>" +
					"</Request>";
			
			temp= "<Request>"
					+ "<ResponseDataType>2</ResponseDataType>"
					+ "<LoginUserId>byz123</LoginUserId>"
					+ "<Head>31DEC(MON) WUHSZX DIRECT ONLY</Head>"
					+ "<FlightNo>CZ3256</FlightNo>"
					+ "<Cabins>"
					+ "F7 P6 P16 WA YA B6 M5 H4 K3 U2 Q1"
					+ "</Cabins>"
					+ "<Voyage>PEKSZX</Voyage>"
					+ "<DepartureTime>07:55</DepartureTime>"
					+ "<ArrivalTime>13:05</ArrivalTime>"
					+ "	<AircraftType>320</AircraftType>"
					+ "	<YPrice>1750</YPrice>"
					+ "</Request>";*/
			/*temp="　　<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
					"<LoginUserId>SDJNHD18</LoginUserId>" +
					"<start>0</start>" +
					"<count>10</count>" +
					"<datetype>1</datetype>" +
					"<startdate>2014-02-25</startdate>" +
					"<enddate>2014-02-28</enddate>" +
					"</Request>";*/
			/*String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(temp
									+ Md5.GetMd5("28bbz8dhuh0dugt2egc4")), temp });*/
			String result = (String) call
					.invoke(new Object[] {
							"byz123",
							Md5.GetMd5(temp
									+ Md5.GetMd5(Md5.GetMd5("123456"))), temp });
			// 给方法传递参数，并且调用方法
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString());
		}

	}


}
