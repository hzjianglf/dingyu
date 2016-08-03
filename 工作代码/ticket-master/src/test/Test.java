package test;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;



public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		
		
		String 
//		temp = "<Request>"
//				+ "<ResponseDataType>1</ResponseDataType>"
//				+ "<LoginUserId>SDJNHD18</LoginUserId>"
//				+ "<DepartCity>WUH</DepartCity>"
//				+ "<ArrivalCity>PEK</ArrivalCity>"
//				+ "<DepartDate>2014-03-26</DepartDate>"
//				+ "<Airways></Airways>"
//				+ "<PassengerType>1</PassengerType>"
//				+ "<CabinType></CabinType>" + "<SortType></SortType>"
//				+ "</Request>";
//		temp="<Request>" +
//				" <ResponseDataType>1</ResponseDataType> " +
//				"<LoginUserId>SDJNHD18</LoginUserId> " +
//				"<PnrNo>HNHHYH </PnrNo>" +
//				" <BigPnrNo></BigPnrNo> " +
//				"<PnrContent></PnrContent>" +
//				" </Request>";
		temp="<Request>" +//取消订单
				"<ResponseDataType>1</ResponseDataType>" +
				"<LoginUserId>SDJNHD18</LoginUserId>" +
				"<OrderID>748478546856140207</OrderID>" +
				"</Request> ";
		String json=getValue(temp);
		
		System.out.println(json);
		    
		   	
	}
	
	
	
	public static String getValue(String xml) {
		try {
		//	String endpoint = "http://192.168.16.114:8080/ticket-master/services/VetechAsmsB2BTicketWebService";
		//	String endpoint = "http://211.154.224.228:8080/ticket-master/services/VetechAsmsB2BTicketWebService";
		//	String endpoint = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BTicketWebService";
				String endpoint="http://10.10.10.29/IGetData/GetFlowCtrlCountByUseID.asmx";
			//String endpoint="http://hairstar.duapp.com/ticket-master/services/VetechAsmsB2BTicketWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			//call.setOperationName("createOrderByPnr");// WSDL里面描述的接口名称
			call.setOperationName("GetFlowsWaitCount");
			call.addParameter("username",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.addParameter("type",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			return (String) call
					.invoke(new Object[]{"admin","DZZW"
});
		} catch (Exception e) {
			e.printStackTrace();
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:</Error>"
					+ " <Status>-1</Status>" + "</Response>";
		}
	}

}
