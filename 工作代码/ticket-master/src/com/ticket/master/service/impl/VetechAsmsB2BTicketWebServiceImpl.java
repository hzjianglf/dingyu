package com.ticket.master.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ticket.master.common.CheckInterface;
import com.ticket.master.common.Md5;
import com.ticket.master.common.TimeUtil;
import com.ticket.master.dao.FlightDatasDao;
import com.ticket.master.dao.HdTicketOrderDao;
import com.ticket.master.dao.impl.FlightDatasDaoImpl;
import com.ticket.master.dao.impl.ParentDaoImpl;
import com.ticket.master.dao.impl.UserDaoImpl;
import com.ticket.master.dao.impl.HdTicketOrderDaoImpl;
import com.ticket.master.entity.User;
import com.ticket.master.service.ParentService;
import com.ticket.master.service.VetechAsmsB2BTicketWebService;

/**
 * @author zg SayHiService的实现类
 * 
 */
@SuppressWarnings("static-access")
public class VetechAsmsB2BTicketWebServiceImpl implements
		VetechAsmsB2BTicketWebService {
	/*
	 *
	 */

	FlightDatasDao fddao = new FlightDatasDaoImpl();
	ParentDaoImpl pdi = new ParentDaoImpl();
	HdTicketOrderDao hdodimpl = new HdTicketOrderDaoImpl();
	ParentService parentService = new ParentServiceImpl();
	UserDaoImpl userDaoImpl = new UserDaoImpl();
	TimeUtil time = new TimeUtil();

	// ------------searchTicket
	public String searchTicket(String account, String md5, String xml) {

		if (!yanzhengUser(account, md5, xml, "searchTicket").equals("ok")) {
			return yanzhengUser(account, md5, xml, "searchTicket");
		}
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String responseDataType = "";
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (user.getUserType() != 1) {
			if (responseDataType.equals("1")) {
				// 航班单程查询接口 xml处理
				// xml处理
				return parentService.searchTcketxml(account, parentService
						.getThreeMothod("searchTicket", xml, account, md5),
						"searchTicket", xml);
			} else {
				// jsion处理
				return parentService.searchTcketJson(account, parentService
						.getThreeMothod("searchTicket", xml, account, md5),
						"searchTicket", xml);
			}

		} else {
			return parentService.getMothod("searchTicket", xml, account, md5);
		}
	}

	public String bookTicket(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "bookTicket").equals("ok")) {
			return yanzhengUser(account, md5, xml, "bookTicket");
		}else{
			//这是里是对用户发送的xml进行重新拼接处理，将处理后的发送给腾邦
			//xml = parentService.getBookTicketRequestXml(account, md5, xml, "bookTicket");
		}
		return parentService.getMothod("bookTicket", xml, account, md5);
	}

	public String createOrderByPnr(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "createOrderByPnr").equals("ok")) {
			return yanzhengUser(account, md5, xml, "createOrderByPnr");
		}
		return parentService.getMothod("createOrderByPnr", xml, account, md5);
	}

	public String queryTicketOrder(String account, String md5, String xml) {
		List<String> orderIdlist = new ArrayList<String>();
		if (!yanzhengUser(account, md5, xml, "queryChangeOrder").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryChangeOrder");
		}
		String responseDataType = "";
		String LoginUserId = "";
		String StartDate = "";
		String EndDate = "";
		String DateType = "";
		String Start = "";
		String Count = "";
		String OrderID = "";
		String DepartureCity = "";
		String ArrivalCity = "";
		String OrderStatus = "";
		String InterFlag = "";
		String Booker = "";
		String FlightNo = "";
		String PassengerName = "";
		String Pnr = "";
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
			LoginUserId = root.elementText("LoginUserId");
			StartDate = root.elementText("StartDate");
			EndDate = root.elementText("EndDate");
			DateType = root.elementText("DateType");
			Start = root.elementText("Start");
			Count = root.elementText("Count");
			DepartureCity = root.elementText("DepartureCity");
			ArrivalCity = root.elementText("ArrivalCity");
			OrderStatus = root.elementText("OrderStatus");
			InterFlag = root.elementText("InterFlag");
			Booker = root.elementText("Booker");
			FlightNo = root.elementText("FlightNo");
			PassengerName = root.elementText("PassengerName");
			Pnr = root.elementText("Pnr");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = "";
		String temp = "";

		String strOrder = "";

		orderIdlist = parentService.getQueryTicketOrder("bookTicket", xml,
				account, md5);
		String xmlcount = "";
		for (int i = 0; i < orderIdlist.size(); i++) {
			temp = new String();
			temp = "<Request>" + "<ResponseDataType>" + responseDataType
					+ "</ResponseDataType>" + "<LoginUserId>" + LoginUserId
					+ "</LoginUserId>" + "<Start>" + Start + "</Start>"
					+ "<Count>" + Count + "</Count>" + "<OrderID>"
					+ orderIdlist.get(i) + "</OrderID>" + "<DateType>"
					+ DateType + "</DateType>" + "<StartDate>" + StartDate
					+ "</StartDate>" + "<EndDate>" + EndDate + "</EndDate>"
					+ "<OrderStatus>" + OrderStatus + "</OrderStatus>"
					+ "<Pnr>" + Pnr + "</Pnr>" + "<InterFlag>" + InterFlag
					+ "</InterFlag>" + "<DepartureCity>" + DepartureCity
					+ "</DepartureCity>" + "<ArrivalCity>" + ArrivalCity
					+ "</ArrivalCity>" + "<Booker>" + Booker + "</Booker>"
					+ "<FlightNo>" + FlightNo + "</FlightNo>"
					+ "<PassengerName>" + PassengerName + "</PassengerName>"
					+ "</Request>";

			if (responseDataType.equals("1")) {
				try {
					String resultXml = parentService.queryTicketOrderMoth(
							"queryTicketOrder", temp.replaceAll("null", ""),
							account, md5);
					Document document = DocumentHelper.parseText(resultXml);
					Element root = document.getRootElement();
					String status = root.elementText("Status");
					if (status.equals("0")) {
						List<Element> FlightList = root
								.selectNodes("//Orders/Order");
						for (Element element : FlightList) {
							xmlcount += "\n" + element.asXML();
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			} else {
				// 返回json拼装
				String resultSom = parentService.queryTicketOrderMoth(
						"queryTicketOrder", temp.replaceAll("null", ""),
						account, md5);
				try {
					JSONObject jsoStr = new JSONObject(resultSom);
					String res = jsoStr.getString("Res");
					JSONObject jsoStr2 = new JSONObject(res);
					JSONArray orderArray = jsoStr2.getJSONArray("Orders");
					if (i == orderIdlist.size() - 1) {
						strOrder += orderArray.get(0);
					} else {
						strOrder += orderArray.get(0) + ",";
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}

			}
		}

		if (responseDataType.equals("1")) {
			if (orderIdlist.size() == 0) {
				result = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n<Status>0</Status>\n<AllCount>"
						+ orderIdlist.size()
						+ "</AllCount>\n<Orders/>\n</Response>";
			} else {
				result = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<Response>\n<Status>0</Status>\n<AllCount>"
						+ orderIdlist.size()
						+ "</AllCount>\n<Orders>"
						+ xmlcount + " \n</Orders>\n</Response>";
			}

		} else {
			result = "{\"Res\": {\"Status\": 0,\"AllCount\": "
					+ orderIdlist.size() + ",\"Orders\": [" + strOrder + "]}}";
		}
		/*------------------------------------------------------------------------------结果集处理-----------------------------------------------*/
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		if (user.getUserType()!= 1) {
			if (responseDataType.equals("1")) {
				// 其余仓位匹配政策 xml处理
				// xml处理
				return parentService.queryTicketOrderXml(account,
						parentService.getThreeMothod("queryTicketOrder", xml, account,
								md5), "queryTicketOrder", xml);
			} else {
				// jsion处理
				return parentService.queryTicketOrderJson(account,
						parentService.getThreeMothod("queryTicketOrder", xml, account,
								md5), "queryTicketOrder", xml);
			}

		} else {
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='queryTicketOrder'";
			int interfaceId = pdi.selectSql(selectInterface);
			String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
					+ "("
					+ user.getUserId()
					+ ","
					+ interfaceId
					+ ",'"
					+ time.getSysTime() + "','" + xml + "','" + result + "')";
			pdi.allSql(insertSql);
			return result;
		}
		
		
		

	}

	public String queryTicketOrderDetail(String account, String md5, String xml) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			String selectInterface = "SELECT * FROM `interfacs` WHERE `interface_name`='queryTicketOrderDetail'";
			int interfaceId = pdi.selectSql(selectInterface);
			if (user == null) {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
						+ "("
						+ 0
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ xml
						+ "','{\"Res\": {\"Status\": -1,\"Error\": \"用户"
						+ account + "不存在\"}}')";
				pdi.allSql(insertSql);
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
						+ "不存在\"}}";
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				String insertSql = "INSERT INTO `interface_log`( `user_id`, `interface_id`, `invoking_time`, `request_content`, `return_content`) VALUES"
						+ "("
						+ user.getUserId()
						+ ","
						+ interfaceId
						+ ",'"
						+ time.getSysTime()
						+ "','"
						+ xml
						+ "','{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}')";
				pdi.allSql(insertSql);
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = parentService.getMothod("queryTicketOrderDetail", xml,
				account, md5);
		return result;
	}

	public String cancelTicketOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "cancelTicketOrder").equals("ok")) {
			return yanzhengUser(account, md5, xml, "cancelTicketOrder");
		}

		return parentService.getMothod("cancelTicketOrder", xml, account, md5);
	}

	/**
	 * 接口13，修改订单证件号码
	 */
	public String editIdentityNo(String account, String md5, String xml) {

		if (!yanzhengUser(account, md5, xml, "editIdentityNo").equals("ok")) {
			return yanzhengUser(account, md5, xml, "editIdentityNo");
		}

		String result = parentService.getMothod("editIdentityNo", xml, account,
				md5);
		return result;
	}

	public String yanzhengUser(String account, String md5, String xml,
			String method) {
		CheckInterface checkInterface = new CheckInterface();
		return checkInterface.checkInterface(account, md5, xml, method);
	}

	/**
	 * 接口14：修改订配送信息、行程单收件地址
	 */
	public String editDeliveryInfo(String account, String md5, String xml) {

		if (!yanzhengUser(account, md5, xml, "editDeliveryInfo").equals("ok")) {
			return yanzhengUser(account, md5, xml, "editDeliveryInfo");
		}

		String result = parentService.getMothod("editDeliveryInfo", xml,
				account, md5);

		return result;
	}

	public String getDeliveryAddress(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "getDeliveryAddress").equals("ok")) {
			return yanzhengUser(account, md5, xml, "getDeliveryAddress");
		}

		String result = parentService.getMothod("getDeliveryAddress", xml,
				account, md5);
		return result;
	}

	public String applyTicketRefund(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "applyTicketRefund").equals("ok")) {
			return yanzhengUser(account, md5, xml, "applyTicketRefund");
		}

		String result = parentService.getMothod("applyTicketRefund", xml,
				account, md5);
		return result;
	}

	public String queryTicketReturnOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryTicketReturnOrder").equals(
				"ok")) {
			return yanzhengUser(account, md5, xml, "queryTicketReturnOrder");
		}

		String result = parentService.getMothod("queryTicketReturnOrder", xml,
				account, md5);
		return result;
	}

	public String queryTicketReturnOrderDetail(String account, String md5,
			String xml) {
		if (!yanzhengUser(account, md5, xml, "queryTicketReturnOrderDetail")
				.equals("ok")) {
			String sql = "select * from user where username = '" + account + "'";
			User user = userDaoImpl.getUserBySql(sql);
			String responseDataType = "";
			System.out.println(user.getUserId());
			try {
				Document document = DocumentHelper.parseText(xml);
				Element root = document.getRootElement();
				responseDataType = root.elementText("ResponseDataType");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			if (user.getUserType()!= 1) {
				if (responseDataType.equals("1")) {
					// 其余仓位匹配政策 xml处理
					// xml处理
					return parentService.queryTicketReturnOrderDetailXml(account,
							parentService.getThreeMothod("queryTicketReturnOrderDetail", xml, account,
									md5), "queryTicketReturnOrderDetail", xml);
				} else {
					// jsion处理
					return parentService.queryTicketReturnOrderDetailJson(account,
							parentService.getThreeMothod("queryTicketReturnOrderDetail", xml, account,
									md5), "queryTicketReturnOrderDetail", xml);
				}

			} else {
				return yanzhengUser(account, md5, xml,
						"queryTicketReturnOrderDetail");
			}
			
		}

		String result = parentService.getMothod("queryTicketReturnOrderDetail",
				xml, account, md5);
		return result;
	}

	public String applyChangeTicket(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "applyChangeTicket").equals("ok")) {
			return yanzhengUser(account, md5, xml, "applyChangeTicket");
		}

		String result = parentService.getMothod("applyChangeTicket", xml,
				account, md5);
		return result;
	}

	public String queryChangeOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryChangeOrder").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryChangeOrder");
		}

		String result = parentService.getMothod("queryChangeOrder", xml,
				account, md5);
		return result;
	}

	public String queryChangeOrderDetail(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryChangeOrderDetail").equals(
				"ok")) {
			return yanzhengUser(account, md5, xml, "queryChangeOrderDetail");
		}

		String result = parentService.getMothod("queryChangeOrderDetail", xml,
				account, md5);
		return result;
	}

	public String queryFxsTicketCp(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryFxsTicketCp").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryFxsTicketCp");
		}
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String responseDataType = "";
		System.out.println(user.getUserId());
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (user.getUserType()!= 1) {
			if (responseDataType.equals("1")) {
				// 其余仓位匹配政策 xml处理
				// xml处理
				return parentService.queryFxsTicketCpXml(account,
						parentService.queryFxsTicketCpXml("queryFxsTicketCp", xml, account,
								md5), "queryFxsTicketCp", xml);
			} else {
				// jsion处理
				return parentService.queryPolcyJson(account,
						parentService.queryFxsTicketCpJson("queryFxsTicketCp", xml, account,
								md5), "queryFxsTicketCp", xml);
			}

		} else {
			String result = parentService.getMothod("queryFxsTicketCp", xml,
					account, md5);
			return result;
		}

			
		
	}

	public String queryMoreCabin(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryMoreCabin").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryMoreCabin");
		}
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String responseDataType = "";
		System.out.println(user.getUserId());
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (user.getUserType()!= 1) {
			if (responseDataType.equals("1")) {
				// 其余仓位匹配政策 xml处理
				// xml处理
				return parentService.queryMoreCabinXml(account,
						parentService.getThreeMothod("queryMoreCabin", xml, account,
								md5), "queryMoreCabin", xml);
			} else {
				// jsion处理
				return parentService.queryMoreCabinJson(account,
						parentService.getThreeMothod("queryMoreCabin", xml, account,
								md5), "queryMoreCabin", xml);
			}

		} else {
			String result = parentService.getMothod("queryMoreCabin", xml,
					account, md5);
			return result;
		}

	}

	public String queryTicketPolcy(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryTicketPolcy").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryTicketPolcy");
		}

		String result = parentService.getMothod("queryTicketPolcy", xml,
				account, md5);
		return result;
	}

	public String queryStandarPrice(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryStandarPrice").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryStandarPrice");
		}

		String result = parentService.getMothod("queryStandarPrice", xml,
				account, md5);
		return result;
	}

	public String queryPatByPNR(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryPatByPNR").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryPatByPNR");
		}

		String result = parentService.getMothod("queryPatByPNR", xml, account,
				md5);
		return result;
	}

	public String queryPatBySegment(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryPatBySegment").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryPatBySegment");
		}

		String result = parentService.getMothod("queryPatBySegment", xml,
				account, md5);
		return result;
	}

	public String editOrderByPat(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "editOrderByPat").equals("ok")) {
			return yanzhengUser(account, md5, xml, "editOrderByPat");
		}

		String result = parentService.getMothod("editOrderByPat", xml, account,
				md5);
		return result;
	}
	public String queryPolcy(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "queryPolcy").equals("ok")) {
			return yanzhengUser(account, md5, xml, "queryPolcy");
		}
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		String responseDataType = "";
		System.out.println(user.getUserId());
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (user.getUserType()!= 1) {
			if (responseDataType.equals("1")) {
				// 其余仓位匹配政策 xml处理
				// xml处理
				return parentService.queryPolcyXml(account,
						parentService.getThreeMothod("queryPolcy", xml, account,
								md5), "queryPolcy", xml);
			} else {
				// jsion处理
				return parentService.queryPolcyJson(account,
						parentService.getThreeMothod("queryPolcy", xml, account,
								md5), "queryPolcy", xml);
			}
		} else {
			String result = parentService
					.getMothod("queryPolcy", xml, account, md5);
			return result;
		}
	}

	public String changedPolicy(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml, "changedPolicy").equals("ok")) {
			return yanzhengUser(account, md5, xml, "changedPolicy");
		}

		String result = parentService.getMothod("changedPolicy", xml, account,
				md5);
		return result;
	}
}