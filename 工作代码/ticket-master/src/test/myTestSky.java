package test;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.ticket.master.common.Md5;

public class myTestSky {
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// TODO Auto-generated method stub


		String bookTicket = "<Request>"
				+ "<LoginUserId>SDJNHD18</LoginUserId>"
				+ "<ResponseDataType>1</ResponseDataType>"
				+ "<LegType>1</LegType>"
				+ "<OrderType>1</OrderType>"
				+ "<InsuranceCode>DL0502_97</InsuranceCode>"
				+ "<Legs>"
				+ "<Leg>"
				+ "<ArrCity>PEK</ArrCity>"
				+ "<ArrTime>11:45</ArrTime>"
				+ "<Cabin>E</Cabin>"
				+ "<DepCity>WUH</DepCity>"
				+ "<DepDate>2014-02-08</DepDate>"
				+ "<DepTime>09:45</DepTime>"
				+ "<Discount>0.5</Discount>"
				+ "<FlightMod>320</FlightMod>"
				+ "<FlightNo>CZ6606</FlightNo>"
				+ "<IfNoSeat></IfNoSeat>"
				+ "<Term> T2</Term>"
				+ "</Leg>"
				+ "</Legs>"
				+ "<Passengers>"
				+ "<Passenger>"
				+ "<Birthday></Birthday>"
				+ "<DocName>370101197807052213</DocName>"
				+ "<DocType>NI</DocType>"
				+ "<DocValid></DocValid>"
				+ "<EatPrefe></EatPrefe>"
				+ "<Insurance>1</Insurance>"
				+ "<MobileNum>13513513512</MobileNum>"
				+ "<Name>周越潇</Name>"
				+ "<Nationality>0002</Nationality>"
				+ "<PassenType>1</PassenType>"
				+ "<SeatPrefe></SeatPrefe>"
				+ "<Sex>M</Sex>"
				+ "</Passenger>"
				+ "</Passengers>"
				+ "<Pnrno></Pnrno>"
				+ "<PolicyId>SDT2013571445191483115071</PolicyId>"
				+ "<PlatPolicyId></PlatPolicyId>"
				+ "<PlatOth>"
				+ "064AACF182593F18085A64440ED94C909BE6CFDFFCCB8A0A9C154D6F0B953B4834E921EC6C747E27D56770A5DF562CAF0A7AEAFD16C34BDF4F26AE2F6C1E6293"
				+ "</PlatOth>" + "<ContactMobile>15902814254</ContactMobile>"
				+ "<ContactName>Geng/fei</ContactName>"
				+ "<ContactPhone>027-254554</ContactPhone>"
				+ "<DistrRemark>请填写备注信息</DistrRemark>" + "<Note>"
				+ "skysky" + "</Note>" + "</Request>";

		String searchTicket = "<Request>"
				+ "<ResponseDataType>1</ResponseDataType>"
				+ "<LoginUserId>byz123</LoginUserId>"
				+ "<DepartCity>WUH</DepartCity>"
				+ "<ArrivalCity>PEK</ArrivalCity>"
				+ "<DepartDate>2014-02-08</DepartDate>"
				+ "<Airways></Airways>"
				+ "<PassengerType>1</PassengerType>"
				+ "<CabinType></CabinType>" 
				+ "<SortType></SortType>"
				+ "</Request>";

		String queryInsuranceSpecies = "<Request>"
				+ "<ResponseDataType>1</ResponseDataType>"
				+ "<LoginUserId>SDJNHD18</LoginUserId>" + "</Request>";
		System.out.println(getValue(bookTicket));
	}

	public static String getValue(String xml) {
		try {
			//String endpoint = "http://211.154.224.228:8080/ticket-master/services/VetechAsmsB2BInsuranceWebService";
			//String endpoint = "http://192.168.16.130:8080/ticket-master/services/VetechAsmsB2BTicketWebService";
			//String endpoint = "http://192.168.16.114:8080/ticket-master/services/VetechAsmsB2BTicketWebService";
			String endpoint = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BTicketWebService";
			
			// 直接引用远程的wsdl文件                                                                                                                         
			// 以下都是套路
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
			return (String) call
					.invoke(new Object[] {
							"byz123",
							Md5.GetMd5(xml + Md5.GetMd5("123456")),
							xml });
		} catch (Exception e) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常</Error>"
					+ " <Status>-1</Status>" + "</Response>";
		}
	}
}
