package test;

import net.sf.json.xml.XMLSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.json.JSONArray;

import com.ticket.master.common.Md5;

public class Zdtest {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try {
			//String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BCommonWebService";
		//String endpoint = "http://192.168.16.114:8080/ticket-master/services/VetechAsmsB2BTicketWebService";
			String endpoint = "http://www.uniqyw.com:80/ticket-master/services/VetechAsmsB2BDataBaseWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			//call.setOperationName("queryTicketOrderDetail ");// WSDL里面描述的接口名称
			/**/
			//call.setOperationName("searchTicket");// WSDL里面描述的接口名称
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
			String 
			temp = "<Request>" +//分销商出票清单查询
					"<ResponseDataType>1</ResponseDataType>" +
					"<LoginUserId>byz123</LoginUserId>" +
					"<start>0</start>" +
					"<count>10</count>" +
					"<datetype>1</datetype>" +
					"<startdate>2014-01-22</startdate>" +
					"<enddate>2014-02-19</enddate>" +
					"</Request>";
			temp="<Request>" +//获取标准票价接口
					"<ResponseDataType>1</ResponseDataType>" +
					"<YPirce>1190</YPirce>" +
					"<SalePirce>540</SalePirce>" +
					"<Voyage>WUHSZX</Voyage>" +
					"<Airline>CZ</Airline>" +
					"<Cabin>Q</Cabin>" +
					"<DDate>2014-02-22</DDate>" +
					"<AircraftType>738</AircraftType>" +
					"</Request>";
			temp="<Request>" +
					"<ResponseDataType>0</ResponseDataType>" +
					"<LoginUserId>byz123</LoginUserId" +
					"><OrderID>728453526882130107</OrderID>" +
					"</Request>";//获取实施政策接口
			temp="<Request>" +//获取后台发布图片
					"<ResponseDataType>1</ResponseDataType>" +
					"< LoginUserId >byz123</ LoginUserId >" +
					"</Request>";
			temp="<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
					"< UserId >byz123</ UserId >" +
					"<Platform>B2B</Platform>" +
					"</Request>";
			temp="<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
					"<LoginUserId>byz123</LoginUserId>" +
					"<XmlName>THotelBrand.xml</XmlName>" +
					"</Request>";
			temp="<Request>" +//酒店订单查询
					"<ResponseDataType>1</ResponseDataType>" +
					"<LoginUserId>byz123</LoginUserId>" +
					"<OrderId>32432432</OrderId>" +
					"</Request>";
			temp="<Request>" +
					"<ResponseDataType>1</ResponseDataType>" +
					"<OrderID>23432432</OrderID>" +
					"<LoginUserId>byz123</LoginUserId>" +
					"<DeliveryType>3</DeliveryType>" +
					"</Request>";
			temp="<Request><" +//获取友情链接
					"ResponseDataType>1</ResponseDataType>" +
					"< UserId >byz123</ UserId >" +
					"<Platform>B2B</Platform>" +
					"</Request>";
			temp="<Request>" +//分销所用参数 
					"<ResponseDataType>1</ResponseDataType>" +
					"< LoginUserId >byz123</ LoginUserId >" +
					"< ParameterId>7734</ ParameterId >" +
					"<Platform>B2B</Platform>" +
					"</Request>";
		temp="<Request>" +
				"<ResponseDataType>1</ResponseDataType>" +
				"<Category >1001</Category >" +
				"<LoginUserId>byz123</LoginUserId>" +
				"</Request>";
		temp="<Request>" +//取数据字典
				"<ResponseDataType>1</ResponseDataType>" +
				"< LoginUserId  >byz123 </ LoginUserId  >" +
				"</Request>";
		temp="<Request>" +
				"<ResponseDataType>1</ResponseDataType>" +
				"<LoginUserId >byz123 </ LoginUserId >" +
				"<Pt >B2B </ Pt >" +
				"<Ywlx >1 </ Ywlx >" +
				"</Request>";
		temp="<Request>" +
				"<ResponseDataType>1</ResponseDataType>" +
				"<Category >1001</Category >" +
				"<LoginUserId>byz123</LoginUserId>" +
				"</Request>";
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
