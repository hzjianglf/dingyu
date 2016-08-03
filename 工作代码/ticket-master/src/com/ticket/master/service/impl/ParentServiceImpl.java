package com.ticket.master.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ticket.master.common.FlightDiscountDiscount;
import com.ticket.master.common.JsonFormatter;
import com.ticket.master.common.Md5;
import com.ticket.master.common.TimeUtil;
import com.ticket.master.dao.impl.InterfacelogDaoImpl;
import com.ticket.master.dao.impl.ParentDaoImpl;
import com.ticket.master.dao.impl.UserDaoImpl;
import com.ticket.master.entity.User;
import com.ticket.master.service.ParentService;

@SuppressWarnings("static-access")
public class ParentServiceImpl implements ParentService {
	ParentDaoImpl pdi = new ParentDaoImpl();
	TimeUtil time = new TimeUtil();
	UserDaoImpl userDaoImpl = new UserDaoImpl();

	public String getMothod(String methodName, String xml, String account,
			String md5) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);

		String requestXml = xml;
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (Exception e) {

		}

		try {
			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BTicketWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
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
			// xml = new String
			// (xml.getBytes("iso-8859-1"),"utf-8");//linux服务器可能不需要转码
			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });
			// 日志存储
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ result
					+ "')";
			pdi.allSql(insertSql);

			return result;
		} catch (Exception e) {
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime() + "','" + requestXml + "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}
	}

	public String getMothod2(String methodName, String xml, String account,
			String md5) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);

		String requestXml = xml;
		try {

			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (Exception e) {

		}
		try {

			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BDataBaseWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
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
			// xml = new String
			// (xml.getBytes("iso-8859-1"),"utf-8");//linux服务器可能不需要转码
			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });
			// 日志存储
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ result
					+ "')";
			pdi.allSql(insertSql);

			return result;
		} catch (Exception e) {
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime() + "','" + requestXml + "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}
	}

	public String queryTicketOrderMoth(String methodName, String xml,
			String account, String md5) {
		String requestXml = xml;
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (DocumentException e) {

		}

		try {
			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BTicketWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
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
			// xml = new String
			// (xml.getBytes("iso-8859-1"),"utf-8");//linux服务器可能不需要转码
			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });

			return result;
		} catch (Exception e) {
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			return s;
		}
	}

	public String getInsuranceSpecies(String methodName, String xml,
			String account, String md5) {

		String requestXmls = xml;
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (DocumentException e) {

		}

		try {
			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BInsuranceWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
			call.addParameter("account",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.addParameter("key",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.addParameter("xml",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型

			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXmls
					+ "','"
					+ result
					+ "')";
			pdi.allSql(insertSql);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			String s2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXmls
					+ "','"
					+ s2
					+ "')";
			pdi.allSql(insertSql);
			return s2;
		}
	}

	public List<String> getQueryTicketOrder(String methodName, String xml,
			String account, String md5) {
		InterfacelogDaoImpl interfacelogDaoImpl = new InterfacelogDaoImpl();
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			String dateType = root.elementText("DateType");
			String StartDate = root.elementText("StartDate");
			String EndDate = root.elementText("EndDate");
			if (dateType.equals("2")) {
				sql = "SELECT * FROM `interface_log` WHERE `user_id` = "
						+ user.getUserId() + " and interface_id = "
						+ interfaceId;
			} else {
				sql = "SELECT * FROM `interface_log` WHERE datediff(`invoking_time`,str_to_date('"
						+ StartDate
						+ " 00:00:00"
						+ "','%Y-%m-%d %H:%i:%s') )>=0 and datediff(str_to_date('"
						+ EndDate
						+ " 23:59:59"
						+ "','%Y-%m-%d %H:%i:%s'),`invoking_time`)>=0 and `user_id` = "
						+ user.getUserId()
						+ " and interface_id = "
						+ interfaceId;
			}
		} catch (Exception e) {

		}

		return interfacelogDaoImpl.getOrderIDList(
				interfacelogDaoImpl.getInterfaceLog(sql), xml);
	}

	public String getPayInterface(String methodName, String xml,
			String account, String md5) {

		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (DocumentException e) {

		}
		try {
			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BAccountWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
			call.addParameter("account",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.addParameter("key",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.addParameter("xml",
					org.apache.axis.encoding.XMLType.XSD_STRING,
					javax.xml.rpc.ParameterMode.IN);// 接口的参数
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型
			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });
			return result;
		} catch (Exception e) {
			return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常</Error>"
					+ " <Status>-1</Status>" + "</Response>";
		}
	}

	public String getHotelService(String methodName, String xml,
			String account, String md5) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);

		String requestXml = xml;
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (DocumentException e) {

		}

		try {
			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BHotelWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
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
			// xml = new String
			// (xml.getBytes("iso-8859-1"),"utf-8");//linux服务器可能不需要转码
			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });
			// 日志存储
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ result
					+ "')";
			pdi.allSql(insertSql);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime() + "','" + requestXml + "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}
	}

	public String getgonggongService(String methodName, String xml,
			String account, String md5) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);

		String requestXml = xml;
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (DocumentException e) {

		}

		try {
			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BCommonWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
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
			// xml = new String
			// (xml.getBytes("iso-8859-1"),"utf-8");//linux服务器可能不需要转码
			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });
			// 日志存储
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ result
					+ "')";
			pdi.allSql(insertSql);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
					+ methodName + "'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime() + "','" + requestXml + "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}
	}

	/**
	 * 三方结果集处理
	 * */
	public String getThreeMothod(String methodName, String xml, String account,
			String md5) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), "SDJNHD18");
		} catch (Exception e) {

		}
		try {
			String endpoint = "http://113.105.64.194:8000/services/VetechAsmsB2BTicketWebService";
			// 直接引用远程的wsdl文件
			// 以下都是套路
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(endpoint);
			call.setOperationName(methodName);// WSDL里面描述的接口名称
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
			// xml = new String
			// (xml.getBytes("iso-8859-1"),"utf-8");//linux服务器可能不需要转码
			String result = (String) call
					.invoke(new Object[] {
							"SDJNHD18",
							Md5.GetMd5(xml + Md5.GetMd5("28bbz8dhuh0dugt2egc4")),
							xml });
			return result;
		} catch (Exception e) {
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			return s;
		}
	}

	/**
	 * 机票查询 结果集处理
	 */
	@SuppressWarnings("unchecked")
	public String searchTcketxml(String account, String Xml, String methodName,
			String requestXml) {
		String returnXml = "";
		String DepartCity = "";
		String ArrivalCity = "";
		String DepartDate = "";
		String Airways = "";
		String Cabin = "";
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		try {
			Document document = DocumentHelper.parseText(Xml);
			Element root = document.getRootElement();
			String status = root.elementText("Status");
			if (status.equals("0")) {
				List<Element> FlightData = root
						.selectNodes("//FlightDatas/FlightData");
				for (Element elements : FlightData) {
					DepartCity = elements.elementText("DepartCity");
					ArrivalCity = elements.elementText("ArrivalCity");
					DepartDate = elements.elementText("DepartDate");
					Airways = elements.elementText("Airways");
					List<Element> FlightList = root
							.selectNodes("//FlightDatas/FlightData/CabinDatas/CabinData");
					returnXml = Xml;
					for (Element element : FlightList) {
						String price = element.elementText("Price");
						// 返点数据 目前死的
						// String discount=user.getDiscount();
						Cabin = element.elementText("Cabin");
						FlightDiscountDiscount flightDiscountDiscount = new FlightDiscountDiscount();
						String Discount = flightDiscountDiscount.getCondition(
								DepartCity, ArrivalCity, Airways, Cabin,
								DepartDate);
						System.out.println(Discount);
						String fandian = "80%";
						Double fan = new BigDecimal(Double.parseDouble(fandian
								.replace("%", "")) / 100.00).doubleValue();
						Double priceq = new BigDecimal(price).doubleValue();
						Double priceh = priceq - (priceq * fan);
						returnXml = returnXml
								.replaceAll("<Price>" + price + "</Price>",
										"<Price>" + priceh + "</Price>")
								.replaceAll(
										"<CashBack>"
												+ element.elementText("CashBack")
												+ "</CashBack>", "")
								.replaceAll(
										"<PlatPolicyRewardRates>"
												+ element.elementText("PlatPolicyRewardRates")
												+ "</PlatPolicyRewardRates>",
										"")
								.replaceAll(
										"<Discount>"
												+ element.elementText("Discount")
												+ "</Discount>", "")
								.replaceAll(
										"<RewardRates>"
												+ element.elementText("RewardRates")
												+ "</RewardRates>", "")
								.replaceAll(
										"<PolicyId>"
												+ element.elementText("PolicyId")
												+ "</PolicyId>", "")
								.replaceAll(
										"<TeamPolicyId>"
												+ element.elementText("TeamPolicyId")
												+ "</TeamPolicyId>", "")
								.replaceAll(
										"<TeamDiscount>"
												+ element.elementText("TeamDiscount")
												+ "</TeamDiscount>", "")
								.replaceAll(
										"<TeamRewardRates>"
												+ element.elementText("TeamRewardRates")
												+ "</TeamRewardRates>", "")
								.replaceAll(
										"<HlPolicyId>"
												+ element.elementText("HlPolicyId")
												+ "</HlPolicyId>", "")
								.replaceAll(
										"<HlPrice>"
												+ element.elementText("HlPrice")
												+ "</HlPrice>", "")
								.replaceAll(
										"<HlDiscount>"
												+ element.elementText("HlDiscount")
												+ "</HlDiscount>", "")
								.replaceAll(
										"<HlRewardRates>"
												+ element.elementText("HlRewardRates")
												+ "</HlRewardRates>", "")
								.replaceAll(
										"<HkPrice>"
												+ element.elementText("HkPrice")
												+ "</HkPrice>", "")
								.replaceAll(
										"<HkDiscount>"
												+ element.elementText("HkDiscount")
												+ "</HkDiscount>", "")
								.replaceAll(
										"<HkRewardRates>"
												+ element.elementText("HkRewardRates")
												+ "</HkRewardRates>", "")
								.replaceAll(
										"<HkForeignRemark>"
												+ element.elementText("HkForeignRemark")
												+ "</HkForeignRemark>", "")
								.replaceAll(
										"<PlatPolicyRates>"
												+ element.elementText("PlatPolicyRates")
												+ "</PlatPolicyRates>", "")
								.replaceAll(
										"<HkPolicyId>"
												+ element.elementText("HkPolicyId")
												+ "</HkPolicyId>", "");
					}
				}
				// System.out.println(returnXml);
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + returnXml + "')";
				pdi.allSql(insertSql);
				return returnXml;
			} else {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + Xml + "')";
				pdi.allSql(insertSql);
				return Xml;
			}

		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ Xml
					+ "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}

	}

	/**
	 * 其余舱位匹配政策 结果集处理
	 */
	@SuppressWarnings("unchecked")
	public String queryMoreCabinXml(String account, String Xml,
			String methodName, String requestXml) {
		String returnXml = "";
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		try {
			Document document = DocumentHelper.parseText(Xml);
			Element root = document.getRootElement();
			String status = root.elementText("Status");
			if (status.equals("0")) {
				List<Element> FlightList = root
						.selectNodes("//FlightDatas/FlightData/CabinDatas/CabinData");
				returnXml = Xml;
				for (Element element : FlightList) {
					returnXml = returnXml
							.replaceAll(
									"<Discount>"
											+ element.elementText("Discount")
											+ "</Discount>", "")
							.replaceAll(
									"<RewardRates>"
											+ element.elementText("RewardRates")
											+ "</RewardRates>", "")
							.replaceAll(
									"<PolicyId>"
											+ element.elementText("PolicyId")
											+ "</PolicyId>", "")
							.replaceAll(
									"<TeamPrice>"
											+ element.elementText("TeamPrice")
											+ "</TeamPrice>", "")
							.replaceAll(
									"<TeamDiscount>"
											+ element.elementText("TeamDiscount")
											+ "</TeamDiscount>", "")
							.replaceAll(
									"<TeamRewardRates>"
											+ element.elementText("TeamRewardRates")
											+ "</TeamRewardRates>", "")
							.replaceAll(
									"<HlPrice>"
											+ element.elementText("HlPrice")
											+ "</HlPrice>", "")
							.replaceAll(
									"<HlDiscount>"
											+ element.elementText("HlDiscount")
											+ "</HlDiscount>", "")
							.replaceAll(
									"<HlRewardRates>"
											+ element.elementText("HlRewardRates")
											+ "</HlRewardRates>", "")
							.replaceAll(
									"<HkPolicyId>"
											+ element.elementText("HkPolicyId")
											+ "</HkPolicyId>", "")
							.replaceAll(
									"<HkPrice>"
											+ element.elementText("HkPrice")
											+ "</HkPrice>", "")
							.replaceAll(
									"<HkDiscount>"
											+ element.elementText("HkDiscount")
											+ "</HkDiscount>", "")
							.replaceAll(
									"<HkRewardRates>"
											+ element.elementText("HkRewardRates")
											+ "</HkRewardRates>", "")
							.replaceAll(
									"<PlatPolicyRates>"
											+ element.elementText("PlatPolicyRates")
											+ "</PlatPolicyRates>", "")
							.replaceAll(
									"<PlatPolicyRewardRates>"
											+ element.elementText("PlatPolicyRewardRates")
											+ "</PlatPolicyRewardRates>", "")
							.replaceAll(
									"<CashBack>"
											+ element.elementText("CashBack")
											+ "</CashBack>", "")
							.replaceAll(
									"<HlCashBack>"
											+ element.elementText("HlCashBack")
											+ "</HlCashBack>", "")
							.replaceAll(
									"<HkCashBack>"
											+ element.elementText("HkCashBack")
											+ "</HkCashBack>", "");
				}
				System.out.println(returnXml);
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + returnXml + "')";
				pdi.allSql(insertSql);
				return returnXml;
			} else {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + Xml + "')";
				pdi.allSql(insertSql);
				return Xml;
			}

		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ Xml
					+ "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}

	}

	@SuppressWarnings("unchecked")
	public String queryPolcyXml(String account, String Xml, String methodName,
			String requestXml) {
		String returnXml = "";
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		try {
			Document document = DocumentHelper.parseText(Xml);
			Element root = document.getRootElement();
			String status = root.elementText("Status");
			returnXml = Xml;
			if (status.equals("0")) {
				List<Element> FlightList = root.selectNodes("//LocalList/data");
				for (Element element : FlightList) {
					returnXml = returnXml.replaceAll(
							"<RateShow>" + element.elementText("RateShow")
									+ "</RateShow>", "");
				}
				// System.out.println(returnXml);
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + returnXml + "')";
				pdi.allSql(insertSql);
				return returnXml;
			} else {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + Xml + "')";
				pdi.allSql(insertSql);
				return Xml;
			}

		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "\n<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>" + " <Status>-1</Status>" + "</Response>";
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ Xml
					+ "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}

	}

	@SuppressWarnings("unchecked")
	public String queryTicketOrderXml(String account, String Xml,
			String methodName, String requestXml) {
		String returnXml = "";
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		try {
			Document document = DocumentHelper.parseText(Xml);
			Element root = document.getRootElement();
			String status = root.elementText("Status");
			if (status.equals("0")) {
				List<Element> FlightList = root.selectNodes("//Orders/Order");
				returnXml = Xml;
				for (Element element : FlightList) {
					returnXml = returnXml.replaceAll(
							"<RewardRate>" + element.elementText("RewardRate")
									+ "</RewardRate>", "").replaceAll(
							"<RewardMoney>"
									+ element.elementText("RewardMoney")
									+ "</RewardMoney>", "");
				}
				System.out.println(returnXml);
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + returnXml + "')";
				pdi.allSql(insertSql);
				return returnXml;
			} else {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + Xml + "')";
				pdi.allSql(insertSql);
				return Xml;
			}

		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ Xml
					+ "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}

	}

	/**
	 * 退废订单详查询
	 */
	@SuppressWarnings("unchecked")
	public String queryTicketReturnOrderDetailXml(String account, String Xml,
			String methodName, String requestXml) {
		String returnXml = "";
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		try {
			Document document = DocumentHelper.parseText(Xml);
			Element root = document.getRootElement();
			String status = root.elementText("Status");
			if (status.equals("0")) {
				List<Element> FlightList = root
						.selectNodes("//TTickets/TTicket");
				returnXml = Xml;
				for (Element element : FlightList) {
					returnXml = returnXml.replaceAll(
							"<RewardMoney>"
									+ element.elementText("RewardMoney")
									+ "</RewardMoney>", "");
				}
				System.out.println(returnXml);
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + returnXml + "')";
				pdi.allSql(insertSql);
				return returnXml;
			} else {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + Xml + "')";
				pdi.allSql(insertSql);
				return Xml;
			}

		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ Xml
					+ "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}

	}

	public String searchTcketJson(String account, String responseJson,
			String methodName, String requestXml) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			JSONObject jsonObj = new JSONObject(responseJson);
			JSONObject jsonObj2 = jsonObj.getJSONObject("Res");
			String state = jsonObj2.getString("Status");
			String discount = user.getDiscount();
			if (!state.equals("0")) {
				this.savaInterlog(methodName, user.getUserId(), requestXml,
						responseJson, responseJson);
				return responseJson;
			}
			JSONArray array = jsonObj2.getJSONArray("FlightDatas");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonItem = array.getJSONObject(i);
				JSONArray arrayCd = jsonItem.getJSONArray("CabinDatas");
				for (int j = 0; j < arrayCd.length(); j++) {
					JSONObject jItem = arrayCd.getJSONObject(j);
					Double fan = new BigDecimal(Double.parseDouble(discount
							.replace("%", "")) / 100.00).doubleValue();
					Double priceq = jItem.getDouble("Price");
					Double priceh = priceq - (priceq * fan);
					jItem.remove("Price");
					jItem.put("Price", priceh);
					jItem.remove("Discount");
					jItem.remove("RewRates");
					jItem.remove("PolicyId");
					jItem.remove("TeamPolicyId");
					jItem.remove("TeamDiscount");
					jItem.remove("TeamRewardRates");
					jItem.remove("HlPrice");
					jItem.remove("HlDiscount");
					jItem.remove("HlPolId");
					jItem.remove("HlRewRates");
					jItem.remove("HkPolicyId");
					jItem.remove("TDiscount");
					jItem.remove("TPolId");
					jItem.remove("TRewRates");
					jItem.remove("HkPolId");
					jItem.remove("HkPrice");
					jItem.remove("HkDiscount");
					jItem.remove("HkRewRates");
					jItem.remove("HkForRemark");
					jItem.remove("PPolRewRates");
					jItem.remove("PPolRates");
					jItem.remove("CashBack");
				}
			}
			jsonObj2.remove("FlightDatas");
			jsonObj2.put("FlightDatas", array);
			jsonObj.remove("Res");
			jsonObj.put("Res", jsonObj2);
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson,
					JsonFormatter.jsonFormatter(jsonObj.toString()));
			return JsonFormatter.jsonFormatter(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String rJson = this
					.getResultXmlOrJson("2", e.getLocalizedMessage());
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson, rJson);
			return rJson;
		}
	}

	public void savaInterlog(String methodName, int userId, String requestXml,
			String responseContent, String dispose_return) {
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
				+ "("
				+ userId
				+ ","
				+ interfaceId
				+ ",'"
				+ time.getSysTime()
				+ "','"
				+ requestXml
				+ "','"
				+ responseContent
				+ "','"
				+ dispose_return + "')";
		pdi.allSql(insertSql);
	}

	public String getResultXmlOrJson(String responseDataType, String content) {
		if (responseDataType.equals("2")) {
			String str = "{\"Res\": {\"Status\": -1,\"Error\": \"" + content
					+ "\"}}";
			return str;
		} else {
			String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Status>-1</Status>"
					+ "<Error>"
					+ content + "</Error>" + "</Response>";
			return str;
		}

	}

	@SuppressWarnings("unchecked")
	public String queryFxsTicketCpXml(String account, String Xml,
			String methodName, String requestXml) {
		String returnXml = "";
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='"
				+ methodName + "'";
		int interfaceId = pdi.selectSql(selectInterface);
		try {
			Document document = DocumentHelper.parseText(Xml);
			Element root = document.getRootElement();
			String status = root.elementText("Status");
			if (status.equals("0")) {
				List<Element> FlightList = root.selectNodes("//rows/row");
				returnXml = Xml;
				for (Element element : FlightList) {
					returnXml = returnXml
							.replaceAll(
									"<js_edfl>"
											+ element.elementText("js_edfl")
											+ "</js_edfl>", "")
							.replaceAll("<js_edfl/>", "")
							.replaceAll(
									"<js_khecfl>"
											+ element.elementText("js_khecfl")
											+ "</js_khecfl>", "")
							.replaceAll("<js_khecfl/>", "");
				}
				System.out.println(returnXml);
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + returnXml + "')";
				pdi.allSql(insertSql);
				return returnXml;
			} else {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ requestXml
						+ "','"
						+ Xml
						+ "','" + Xml + "')";
				pdi.allSql(insertSql);
				return Xml;
			}

		} catch (Exception e) {
			e.printStackTrace();
			String s = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
					+ "<Response>"
					+ "<Error>请求异常:"
					+ e.toString()
					+ "</Error>"
					+ " <Status>-1</Status>" + "</Response>";
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`,`return_content`, `dispose_return_xml`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime()
					+ "','"
					+ requestXml
					+ "','"
					+ Xml
					+ "','" + s + "')";
			pdi.allSql(insertSql);
			return s;
		}

	}

	public String queryTicketOrderJson(String account, String responseJson,
			String methodName, String requestXml) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			JSONObject jsonObj = new JSONObject(responseJson);
			JSONObject jsonObj2 = jsonObj.getJSONObject("Res");
			String state = jsonObj2.getString("Status");
			if (!state.equals("0")) {
				this.savaInterlog(methodName, user.getUserId(), requestXml,
						responseJson, responseJson);
				return responseJson;
			}
			JSONArray array = jsonObj2.getJSONArray("Orders");
			for (int i = 0; i < array.length(); i++) {

				JSONObject jItem = array.getJSONObject(i);
				jItem.remove("RewRate");
				jItem.remove("RewMoney");

			}
			jsonObj2.remove("Orders");
			jsonObj2.put("Orders", array);
			jsonObj.remove("Res");
			jsonObj.put("Res", jsonObj2);
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson,
					JsonFormatter.jsonFormatter(jsonObj.toString()));
			return JsonFormatter.jsonFormatter(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String rJson = this
					.getResultXmlOrJson("2", e.getLocalizedMessage());
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson, rJson);
			return rJson;
		}
	}

	public String queryMoreCabinJson(String account, String responseJson,
			String methodName, String requestXml) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			JSONObject jsonObj = new JSONObject(responseJson);
			JSONObject jsonObj2 = jsonObj.getJSONObject("Res");
			String state = jsonObj2.getString("Status");
			String discount = user.getDiscount();
			if (!state.equals("0")) {
				this.savaInterlog(methodName, user.getUserId(), requestXml,
						responseJson, responseJson);
				return responseJson;
			}
			JSONArray array = jsonObj2.getJSONArray("FlightDatas");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonItem = array.getJSONObject(i);
				JSONArray arrayCd = jsonItem.getJSONArray("CabinDatas");
				for (int j = 0; j < arrayCd.length(); j++) {
					JSONObject jItem = arrayCd.getJSONObject(j);
					Double fan = new BigDecimal(Double.parseDouble(discount
							.replace("%", "")) / 100.00).doubleValue();
					Double priceq = jItem.getDouble("Price");
					Double priceh = priceq - (priceq * fan);
					jItem.remove("Price");
					jItem.put("Price", priceh);
					jItem.remove("Discount");
					jItem.remove("RewRates");
					jItem.remove("PolicyId");
					jItem.remove("TeamPolicyId");
					jItem.remove("TeamDiscount");
					jItem.remove("TeamRewardRates");
					jItem.remove("HlPrice");
					jItem.remove("HlDiscount");
					jItem.remove("HlPolId");
					jItem.remove("HlRewRates");
					jItem.remove("HkPolicyId");
					jItem.remove("TDiscount");
					jItem.remove("TPolId");
					jItem.remove("TRewRates");
					jItem.remove("HkPolId");
					jItem.remove("HkPrice");
					jItem.remove("HkDiscount");
					jItem.remove("HkRewRates");
					jItem.remove("HkForRemark");
					jItem.remove("PPolRewRates");
					jItem.remove("PPolRates");
					jItem.remove("CashBack");
				}
			}
			jsonObj2.remove("FlightDatas");
			jsonObj2.put("FlightDatas", array);
			jsonObj.remove("Res");
			jsonObj.put("Res", jsonObj2);
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson,
					JsonFormatter.jsonFormatter(jsonObj.toString()));
			return JsonFormatter.jsonFormatter(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String rJson = this
					.getResultXmlOrJson("2", e.getLocalizedMessage());
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson, rJson);
			return rJson;
		}
	}

	public String queryPolcyJson(String account, String responseJson,
			String methodName, String requestXml) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			JSONObject jsonObj = new JSONObject(responseJson);
			JSONObject jsonObj2 = jsonObj.getJSONObject("Res");
			String state = jsonObj2.getString("Status");
			if (!state.equals("0")) {
				this.savaInterlog(methodName, user.getUserId(), requestXml,
						responseJson, responseJson);
				return responseJson;
			}
			JSONArray array = jsonObj2.getJSONArray("localList");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jItem = array.getJSONObject(i);
				jItem.remove("RateShow");
			}
			jsonObj2.remove("localList");
			jsonObj2.put("localList", array);
			jsonObj.remove("Res");
			jsonObj.put("Res", jsonObj2);
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson,
					JsonFormatter.jsonFormatter(jsonObj.toString()));
			return JsonFormatter.jsonFormatter(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String rJson = this
					.getResultXmlOrJson("2", e.getLocalizedMessage());
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson, rJson);
			return rJson;
		}
	}

	public String queryTicketReturnOrderDetailJson(String account,
			String responseJson, String methodName, String requestXml) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			JSONObject jsonObj = new JSONObject(responseJson);
			JSONObject jsonObj2 = jsonObj.getJSONObject("Res");
			String state = jsonObj2.getString("Status");
			if (!state.equals("0")) {
				this.savaInterlog(methodName, user.getUserId(), requestXml,
						responseJson, responseJson);
				return responseJson;
			}
			JSONArray array = jsonObj2.getJSONArray("Tickets");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jItem = array.getJSONObject(i);
				jItem.remove("RewMo");
			}
			jsonObj2.remove("Tickets");
			jsonObj2.put("Tickets", array);
			jsonObj.remove("Res");
			jsonObj.put("Res", jsonObj2);
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson,
					JsonFormatter.jsonFormatter(jsonObj.toString()));
			return JsonFormatter.jsonFormatter(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String rJson = this
					.getResultXmlOrJson("2", e.getLocalizedMessage());
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson, rJson);
			return rJson;
		}
	}

	public String queryFxsTicketCpJson(String account, String responseJson,
			String methodName, String requestXml) {
		// TODO Auto-generated method stub
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			JSONObject jsonObj = new JSONObject(responseJson);
			JSONObject jsonObj2 = jsonObj.getJSONObject("Res");
			String state = jsonObj2.getString("Status");
			if (!state.equals("0")) {
				this.savaInterlog(methodName, user.getUserId(), requestXml,
						responseJson, responseJson);
				return responseJson;
			}
			JSONArray array = jsonObj2.getJSONArray("rows");
			for (int i = 0; i < array.length(); i++) {
				JSONObject jItem = array.getJSONObject(i);
				jItem.remove("js_edfl");
				jItem.remove("js_khecfl");
			}
			jsonObj2.remove("rows");
			jsonObj2.put("rows", array);
			jsonObj.remove("Res");
			jsonObj.put("Res", jsonObj2);
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson,
					JsonFormatter.jsonFormatter(jsonObj.toString()));
			return JsonFormatter.jsonFormatter(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String rJson = this
					.getResultXmlOrJson("2", e.getLocalizedMessage());
			this.savaInterlog(methodName, user.getUserId(), requestXml,
					responseJson, rJson);
			return rJson;
		}
	}

	public String getBookTicketRequestXml(String account, String md5,
			String requestXml, String methodName) {
		// TODO Auto-generated method stub
		try {
			Document document = DocumentHelper.parseText(requestXml);
			Element root = document.getRootElement();
			List<Element> xmlList = root.selectNodes("//Legs/Leg");
			for (Element e : xmlList) {
				Element nameElement = root.addElement("Discount");
	            nameElement.setText("名称");
	            String queryMoreCabin = "<Request>"
	    				+ "<ResponseDataType>2</ResponseDataType>"
	    				+ "<LoginUserId>byz123</LoginUserId>"
	    				+ "<Head>31DEC(MON) SZXWNZ DIRECT ONLY</Head>"
	    				+ "<FlightNo>"+e.elementText("FlightNo")+"</FlightNo>"
	    				+ "<Cabins>"
	    				+ e.elementText("Cabin")
	    				+ "</Cabins>"
	    				+ "<Voyage>"+e.elementText("DepCity")+e.elementText("ArrCity")+"</Voyage>"
	    				+ "<DepartureTime>"+e.elementText("DepTime")+"</DepartureTime>"
	    				+ "<ArrivalTime>"+e.elementText("ArrTime")+"</ArrivalTime>"
	    				+ "<AircraftType>"+e.elementText("FlightMod")+"</AircraftType>"
	    				+ "<YPrice>1310</YPrice>"
	    				+ "</Request>";
			}
			List<Element> xmlList2 = root.selectNodes("//Passengers/Passenger");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
		}
		return null;
	}

}
