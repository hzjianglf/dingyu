package test;

import java.text.DecimalFormat;
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
import com.ticket.master.entity.CabinData;
import com.ticket.master.entity.CabinDatas;
import com.ticket.master.entity.FlightData;
import com.ticket.master.entity.FlightDatas;
import com.ticket.master.entity.HdTicketOrder;
import com.ticket.master.entity.Res;
import com.ticket.master.entity.User;
import com.ticket.master.service.ParentService;
import com.ticket.master.service.VetechAsmsB2BTicketWebService;
import com.ticket.master.service.impl.ParentServiceImpl;

/**
 * @author zg SayHiService的实现类
 * 
 */

public class VetechAsmsB2BTicketWebServiceImplCopy{
	/*
	 * 这个类实现了sayHi方法，该方法是可以通过WebService调用访问到的。
	 * 另外还实现了一个方法“不告诉你”，该方法因为没有在接口SayHiService中定义， 所以不能被WebService调用到。
	 */

	FlightDatasDao fddao = new FlightDatasDaoImpl();
	ParentDaoImpl pdi = new ParentDaoImpl();
	HdTicketOrderDao hdodimpl = new HdTicketOrderDaoImpl();
	ParentService parentService = new ParentServiceImpl();
	UserDaoImpl userDaoImpl = new UserDaoImpl();
	// ------------bookTicket
	private String error = "";
	private String warnMsgs = "";
	private String orderno;
	private String pnrno;
	private String payTime = "0000-00-00 00:00:00";
	private String airline = "";

	private String LoginUserId;
	private String LegType;
	private String OrderType;
	private String InsuranceCode;
	private String Pnrno;
	private String PolicyId;
	private String PlatPolicyId;
	private String PlatOth;
	private String ContactMobile;
	private String ContactPhone;
	private String ContactName;
	private String DistrRemark;
	private String Note;

	private String transferCity = "";
	private String ArrCity;
	private String ArrTime;
	private String Cabin;
	private String DepCity;
	private String DepDate;
	private String DepTime;
	private String Discount;
	private String FlightMod;
	private String FlightNo;
	private String IfNoSeat;
	private String Term;
	private int cabinNum;
	private String bacDate = "0000-00-00";
	private String transferDate = "0000-00-00";

	private String Birthday = "0000-00-00";
	private String DocName;
	private String DocType;
	private String DocValid;
	private String EatPrefe;
	private String Insurance;
	private String MobileNum;
	private String Name;
	private String Nationality;
	private String PassenType;
	private String SeatPrefe;
	private String Sex;
	private double price;
	private String delStatus;
	private String addTime = TimeUtil.getOrderTime();
	// ------------bookTicket

	// ------------searchTicket
	// res
	private String departCity;
	private String arrivalCity;
	private String departDate;
	private String passengerType;
	private String cabinType;
	private String airways;
	private String sortType;
	int res_id;
	// flight_data
	private String depart_city;
	private String depart_date;
	private String depart_time;
	private String meal;
	private String carrier_flight_no;
	private String arrival_city;
	private String arrival_time;
	private String e_ticket;
	private String flight_model;
	private String fly_time;
	private String flight_no;
	private String airways2;
	private String depart_terminal;
	private String arrival_terminal;
	private String air_construction_fee;
	private String stop_over;
	private String other_cabin;
	private String fuel_tax;
	private String protocol_num;
	private String stand_price;

	// cabin_data
	private String cabin;
	private String cabin_name;
	private String cabin_type;
	private String is_publish_tariff;
	private String seat_num;
	private String prices;
	private String discount;
	private String reward_rates;
	private String policy_id;
	private String note;
	private String foreign_remark;
	private String js_price;
	private String team_policyId;
	private String team_price;
	private String team_discount;
	private String team_reward_rates;
	private String team_last_seat_num;
	private String team_total_seat_num;
	private String team_pnr;
	private String team_note;
	private String team_foreign_remark;
	private String hl_policy_id;
	private String hl_price;
	private String hl_discount;
	private String hl_reward_rates;
	private String hl_note;
	private String hl_foreign_remark;
	private String hl_last_seat_num;
	private String hk_policy_id;
	private String hk_price;
	private String hk_discount;
	private String hk_reward_rates;
	private String hk_foreign_remark;
	private String plat_policy_id;
	private String plat_policy_type;
	private String plat_policy_rates;
	private String is_special_policy;
	private String plat_policy_remark;
	private String plat_policy_reward_rates;
	private String plat_stay_money;
	private String cash_back;
	private String team_cash_back;
	private String hl_cash_back;
	private String hk_cash_back;
	private String plat_oth;
	private int flight_data_id;
	private String responseDataType;
	private String backType;
	String OrderID = "";

	// ------------searchTicket
	public String searchTicket(String account, String md5, String xml) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			if (user == null) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
						+ "不存在\"}}";
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Res res = new Res();
		String departCity = "";
		String arrivalCity = "";
		String departDate = "";
		String passengerType = "";
		String cabinType = "";
		String airways = "";
		String sortType = "";
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
			backType = parentService.getMothod("searchTicket", xml,account,md5);
			departCity = root.elementText("DepartCity");
			arrivalCity = root.elementText("ArrivalCity");
			departDate = root.elementText("DepartDate");
			passengerType = root.elementText("Airways");
			cabinType = root.elementText("PassengerType");
			airways = root.elementText("CabinType");
			sortType = root.elementText("SortType");
			String sqlRes = "INSERT INTO `res`(`depart_city`, `arrival_city`, `depart_date`, `airways`, `passenger_type`, `cabin_type`, `sort_type`,`user_id`)"
					+ "VALUES ('"
					+ departCity
					+ "','"
					+ arrivalCity
					+ "','"
					+ departDate
					+ "','"
					+ passengerType
					+ "','"
					+ cabinType
					+ "','"
					+ airways
					+ "','"
					+ sortType
					+ "',"
					+ user.getUserId() + ")";
			//res_id = pdi.allSql(sqlRes);
			// 返回类型xml
			if (responseDataType.equals("1")) {
				//backXml(backType, res_id);
			} else {
				//backJson(backType, res_id, xml);
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//pdi.insertInterfaceLog(xml,backType,"searchTicket",user.getUserId());
		return backType;
	}

	// json
	public void backJson(String backType, int resId, String xml) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
			//backType = parentService.getMothod("searchTicket", xml);
			departCity = root.elementText("DepartCity");
			arrivalCity = root.elementText("ArrivalCity");
			departDate = root.elementText("DepartDate");
			passengerType = root.elementText("Airways");
			cabinType = root.elementText("PassengerType");
			airways = root.elementText("CabinType");
			sortType = root.elementText("SortType");
			String sqlRes = "INSERT INTO `res`(`depart_city`, `arrival_city`, `depart_date`, `airways`, `passenger_type`, `cabin_type`, `sort_type`)"
					+ "VALUES ('"
					+ departCity
					+ "','"
					+ arrivalCity
					+ "','"
					+ departDate
					+ "','"
					+ passengerType
					+ "','"
					+ cabinType
					+ "','" + airways + "','" + sortType + "')";
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Res res = new Res();
		backType = backType.replaceAll("\"\"", "无");
		res = fddao.findById(resId);
		try {
			JSONObject jsonObj = new JSONObject(backType);
			String str = jsonObj.getString("Res");
			JSONObject jsonObj2 = new JSONObject(str);
			JSONArray array = jsonObj2.getJSONArray("FlightDatas");
			List<FlightDatas> fdList = new ArrayList<FlightDatas>();
			FlightData fd = new FlightData();
			String cdStr = new String();
			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonItem = array.getJSONObject(i);
				fd = new FlightData();
				cdStr = new String();
				fd.setDepartCity(jsonItem.getString("DepCity"));
				fd.setArrivalCity(jsonItem.getString("ArrCity"));
				fd.setFlightNo(jsonItem.getString("FlightNo"));
				fd.setAirways(jsonItem.getString("Airways"));
				fd.setAirConstructionFee(jsonItem.getString("AirConFee"));
				fd.setFuelTax(jsonItem.getString("FuelTax"));
				fd.setMeal(jsonItem.getString("Meal"));
				fd.setStopOver(jsonItem.getString("StopOver"));
				fd.setStandPrice(jsonItem.getString("StandPrice"));
				fd.setOtherCabin(jsonItem.getString("OtherCabin"));

				fd.setRes(res);
				fd.setDepartCity(departCity);
				fd.setArrivalCity(arrivalCity);
				fd.setDepartDate(TimeUtil.getTime(departDate));
				fd.setDepartTime(TimeUtil.getSysTimes(jsonItem
						.getString("DepTime") + ":00"));
				fd.setArrivalTime(TimeUtil.getSysTimes(jsonItem
						.getString("ArrTime") + ":00"));
				fddao.saveFlightDatas(fd);
				JSONArray arrayCd = jsonItem.getJSONArray("CabinDatas");
				saveCabinData(arrayCd, fd);

			}
			System.out.println(fdList.size());
			System.out.println(array.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// xml
	public void backXml(String xml, int resId) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			delStatus = root.elementText("Status");
			if (delStatus.equals("0")) {
				List<Element> FlightList = root
						.selectNodes("//FlightDatas/FlightData");
				for (Element e : FlightList) {
					depart_city = e.elementText("DepartCity");
					depart_date = e.elementText("DepartDate");
					depart_time = e.elementText("DepartTime");
					meal = e.elementText("Meal");
					carrier_flight_no = e.elementText("CarrierFlightNo");
					arrival_city = e.elementText("ArrivalCity");
					arrival_time = e.elementText("ArrivalTime");
					e_ticket = e.elementText("ETicket");
					flight_model = e.elementText("FlightModel");
					fly_time = e.elementText("FlyTime");
					flight_no = e.elementText("FlightNo");
					airways2 = e.elementText("Airways");
					depart_terminal = e.elementText("DepartTerminal");
					arrival_terminal = e.elementText("ArrivalTerminal");
					air_construction_fee = e.elementText("AirConstructionFee");
					stop_over = e.elementText("StopOver");
					other_cabin = e.elementText("OtherCabin");
					fuel_tax = e.elementText("FuelTax");
					protocol_num = e.elementText("ProtocolNum");
					stand_price = e.elementText("StandPrice");
					String sqlData = "INSERT INTO `flight_data`(`depart_city`, `depart_date`, `depart_time`, `meal`, `carrier_flight_no`, `arrival_city`,"
							+ " `arrival_time`, `e_ticket`, `flight_model`, `fly_time`, `flight_no`, `airways`, `depart_terminal`, `arrival_terminal`, `air_construction_fee`,"
							+ " `stop_over`, `other_cabin`, `fuel_tax`, `protocol_num`, `stand_price`,`res_id`)"
							+ "VALUES ('"
							+ depart_city
							+ "','"
							+ depart_date
							+ "','"
							+ depart_time
							+ "','"
							+ meal
							+ "','"
							+ carrier_flight_no
							+ "','"
							+ arrival_city
							+ "','"
							+ arrival_time
							+ "','"
							+ e_ticket
							+ "','"
							+ flight_model
							+ "','"
							+ fly_time
							+ "',"
							+ "'"
							+ flight_no
							+ "','"
							+ airways2
							+ "','"
							+ depart_terminal
							+ "','"
							+ arrival_terminal
							+ "','"
							+ air_construction_fee
							+ "','"
							+ stop_over
							+ "','"
							+ other_cabin
							+ "','"
							+ fuel_tax
							+ "','"
							+ protocol_num
							+ "','"
							+ stand_price
							+ "','"
							+ res_id + "')";
					flight_data_id = pdi.allSql(sqlData);
					List<Element> CabinList = root
							.selectNodes("//CabinDatas/CabinData");
					for (Element e2 : CabinList) {
						cabin = e2.elementText("Cabin");
						cabin_name = e2.elementText("CabinName");
						cabin_type = e2.elementText("CabinType");
						is_publish_tariff = e2.elementText("IsPublishTariff");
						seat_num = e2.elementText("SeatNum");
						prices = e2.elementText("Price");
						discount = e2.elementText("Discount");
						reward_rates = e2.elementText("RewardRates");
						policy_id = e2.elementText("PolicyId");
						note = e2.elementText("Note");
						foreign_remark = e2.elementText("ForeignRemark");
						js_price = e2.elementText("JsPrice");
						team_policyId = e2.elementText("TeamPolicyId");
						team_price = e2.elementText("TeamPrice");
						team_discount = e2.elementText("TeamDiscount");
						team_reward_rates = e2.elementText("TeamRewardRates");
						team_last_seat_num = e2.elementText("TeamLastSeatNum");
						team_total_seat_num = e2
								.elementText("TeamTotalSeatNum");
						team_pnr = e2.elementText("TeamPnr");
						team_note = e2.elementText("TeamNote");
						team_foreign_remark = e2
								.elementText("TeamForeignRemark");
						hl_policy_id = e2.elementText("HlPolicyId");
						hl_price = e2.elementText("HlPrice");
						hl_discount = e2.elementText("HlDiscount");
						hl_reward_rates = e2.elementText("HlRewardRates");
						hl_note = e2.elementText("HlNote");
						hl_foreign_remark = e2.elementText("HlForeignRemark");
						hl_last_seat_num = e2.elementText("AirConstructionFee");
						hk_policy_id = e2.elementText("HkPolicyId");
						hk_price = e2.elementText("HkPrice");
						hk_discount = e2.elementText("HkDiscount");
						hk_reward_rates = e2.elementText("HkRewardRates");
						hk_foreign_remark = e2.elementText("HkForeignRemark");
						plat_policy_id = e2.elementText("PlatPolicyId");
						plat_policy_type = e2.elementText("PlatPolicyType");
						plat_policy_rates = e2.elementText("DepartTime");
						is_special_policy = e2.elementText("IsSpecialPolicy");
						plat_policy_remark = e2.elementText("PlatPolicyRemark");
						plat_policy_reward_rates = e2
								.elementText("PlatPolicyRewardRates");
						plat_stay_money = e2.elementText("PlatStayMoney");
						cash_back = e2.elementText("CashBack");
						team_cash_back = e2.elementText("TeamCashBack");
						hl_cash_back = e2.elementText("FuelTax");
						hk_cash_back = e2.elementText("HkCashBack");
						plat_oth = e2.elementText("PlatOth");
						String sqlCabin = "INSERT INTO `cabin_data`(`cabin`, `foreign_remark`, `cabin_type`, `cabin_name`,"
								+ " `reward_rates`, `hk_foreign_remark`, `hk_reward_rates`, `hk_price`, `hk_cash_back`, `hk_policy_id`, "
								+ "`hk_discount`, `hl_foreign_remark`, `hl_reward_rates`, `hl_price`, `hl_cash_back`, `hl_last_seat_num`, "
								+ "`hl_note`, `hl_policy_id`, `hl_discount`, `is_publish_tariff`, `js_price`, `price`, `is_special_policy`,"
								+ " `plat_stay_money`, `plat_policy_type`, `plat_policy_id`, `plat_policy_remark`, `plat_policy_reward_rates`,"
								+ " `plat_oth`, `cash_back`, `team_foreign_remark`, `team_reward_rates`, `team_price`, `team_pnr`, `team_cash_back`,"
								+ " `team_note`, `team_policy_id`, `team_discount`, `team_last_seat_num`, `team_total_seat_num`, `note`, `policy_id`,"
								+ " `discount`, `seat_num`, `flight_data_id`) VALUES ('"
								+ cabin
								+ "','"
								+ foreign_remark
								+ "','"
								+ cabin_type
								+ "','"
								+ cabin_name
								+ "','"
								+ reward_rates
								+ "','"
								+ hk_foreign_remark
								+ "',"
								+ "'"
								+ hk_reward_rates
								+ "','"
								+ hk_price
								+ "','"
								+ hk_cash_back
								+ "','"
								+ hk_policy_id
								+ "','"
								+ hk_discount
								+ "','"
								+ hl_foreign_remark
								+ "','"
								+ hl_reward_rates
								+ "','"
								+ hl_price
								+ "','"
								+ hl_cash_back
								+ "','"
								+ hl_last_seat_num
								+ "','"
								+ hl_note
								+ "',"
								+ "'"
								+ hl_policy_id
								+ "','"
								+ hl_discount
								+ "','"
								+ is_publish_tariff
								+ "','"
								+ js_price
								+ "','"
								+ price
								+ "','"
								+ is_special_policy
								+ "','"
								+ plat_stay_money
								+ "','"
								+ plat_policy_type
								+ "','"
								+ plat_policy_id
								+ "','"
								+ plat_policy_remark
								+ "',"
								+ "'"
								+ plat_policy_reward_rates
								+ "','"
								+ plat_oth
								+ "','"
								+ cash_back
								+ "','"
								+ team_foreign_remark
								+ "','"
								+ team_reward_rates
								+ "','"
								+ team_price
								+ "','"
								+ team_pnr
								+ "','"
								+ team_cash_back
								+ "','"
								+ team_note
								+ "','"
								+ team_policyId
								+ "',"
								+ "'"
								+ team_discount
								+ "','"
								+ team_last_seat_num
								+ "','"
								+ team_total_seat_num
								+ "','"
								+ note
								+ "','"
								+ policy_id
								+ "','"
								+ discount
								+ "','"
								+ seat_num + "','" + flight_data_id + "')";
						pdi.allSql(sqlCabin);
					}
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
		}
	}

	@SuppressWarnings("unchecked")
	public String bookTicket(String account, String md5, String xml) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			if (user == null) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
						+ "不存在\"}}";
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			LoginUserId = root.elementText("LoginUserId");
			LegType = root.elementText("LegType");
			OrderType = root.elementText("OrderType");
			InsuranceCode = root.elementText("InsuranceCode");
			Pnrno = root.elementText("Pnrno");
			PolicyId = root.elementText("PolicyId");
			PlatPolicyId = root.elementText("PlatPolicyId");
			PlatOth = root.elementText("PlatOth");
			ContactMobile = root.elementText("ContactMobile");
			ContactName = root.elementText("ContactName");
			ContactPhone = root.elementText("ContactPhone");
			DistrRemark = root.elementText("DistrRemark");
			Note = root.elementText("Note");
			List<Element> xmlList = root.selectNodes("//Legs/Leg");
			for (Element e : xmlList) {
				ArrCity = e.elementText("ArrCity");
				ArrTime = e.elementText("ArrTime");
				Cabin = e.elementText("Cabin");
				DepCity = e.elementText("DepCity");
				DepDate = e.elementText("DepDate");
				DepTime = e.elementText("DepTime");
				Discount = e.elementText("Discount");
				FlightMod = e.elementText("FlightMod");
				FlightNo = e.elementText("FlightNo");
				IfNoSeat = e.elementText("IfNoSeat");
				Term = e.elementText("Term");
			}
			List<Element> xmlList2 = root.selectNodes("//Passengers/Passenger");
			for (Element e : xmlList2) {
				Birthday = e.elementText("Birthday");
				if (Birthday.equals("")) {
					Birthday = "0000-00-00";
				}
				DocName = e.elementText("DocName");
				DocType = e.elementText("DocType");
				DocValid = e.elementText("DocValid");
				if (DocValid.equals("")) {
					DocValid = "0000-00-00";
				}
				EatPrefe = e.elementText("EatPrefe");
				Insurance = e.elementText("Insurance");
				MobileNum = e.elementText("MobileNum");
				Name = e.elementText("Name");
				Nationality = e.elementText("Nationality");
				PassenType = e.elementText("PassenType");
				SeatPrefe = e.elementText("SeatPrefe");
				Sex = e.elementText("Sex");
				if (Sex.equals("M") || Sex.equals("")) {
					Sex = "男";
				} else {
					Sex = "女";
				}
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
		}
		String Xml = parentService.getMothod("bookTicket", xml,account,md5);
		if (LegType.endsWith("1")) {
			try {
				Document document = DocumentHelper.parseText(Xml);
				Element root = document.getRootElement();
				delStatus = root.elementText("Status");
				if (delStatus.equals("-1")) {
					error = root.elementText("Error");
					// warnMsgs = root.elementText("WarnMsgs");
				} else {
					error = "";
					orderno = root.elementText("OrderId");
					Pnrno = root.elementText("Pnrno");
					price = Double.parseDouble(root.elementText("JsPrice"));
				}

				/*
				 * DecimalFormat df = new DecimalFormat("########0.00"); double
				 * prices = Double.parseDouble(root.elementText("JsPrice"));
				 * price = Double.parseDouble(df.format(prices));
				 */

			} catch (DocumentException e) {
				// TODO Auto-generated catch block
			}
		} else {
			try {
				JSONObject jsob = new JSONObject(Xml);
				String str = jsob.getString("Res");
				JSONObject jsob2 = new JSONObject(str);
				delStatus = jsob2.getString("Status");
				if (delStatus.equals("-1")) {
					error = jsob2.getString("Error");
					// warnMsgs = root.elementText("WarnMsgs");
				} else {
					error = "";
					orderno = jsob2.getString("OrderId");
					Pnrno = jsob2.getString("Pnrno");
					price = Double.parseDouble(jsob2.getString("JsPrice"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		sql = "INSERT INTO `hd_ticket_order`(`order_no`, `airline`, `leg_type`, `dep_city`, `arr_city`,"
				+ "`transfer_city`, `dep_date`, `bac_date`, `transfer_date`, `dep_time`, `arr_time`, `distr_remark`,"
				+ "`term`, `flight_no`, `cabin`, `cabin_num`, `discount`, `price`, `flight_mod`,`contact_mobile`,`contact_name`,`contact_phone`, "
				+ "`passenger_type`, `name`, `sex`, `mobile_num`, `birthday`, `doc_type`, `doc_name`, `doc_valid`,`insurance_code`, `insurance`,"
				+ " `nationality`, `eat_prefe`, `seat_prefe`, `order_type`, `pnrno`, `status`, `pay_time`, `warn_msg`,`warn_error`,`delStatus`,`add_time`,`user_id`)"
				+ "VALUES ('"
				+ orderno
				+ "','"
				+ airline
				+ "','"
				+ LegType
				+ "','"
				+ DepCity
				+ "','"
				+ ArrCity
				+ "','"
				+ transferCity
				+ "','"
				+ DepDate
				+ "','"
				+ bacDate
				+ "','"
				+ transferDate
				+ "',"
				+ "'"
				+ DepTime
				+ "','"
				+ ArrTime
				+ "','"
				+ DistrRemark
				+ "','"
				+ Term
				+ "','"
				+ FlightNo
				+ "','"
				+ Cabin
				+ "',"
				+ cabinNum
				+ ",'"
				+ Discount
				+ "',"
				+ "'"
				+ price
				+ "','"
				+ FlightMod
				+ "','"
				+ ContactMobile
				+ "','"
				+ ContactName
				+ "','"
				+ ContactPhone
				+ "','"
				+ PassenType
				+ "','"
				+ Name
				+ "','"
				+ Sex
				+ "','"
				+ MobileNum
				+ "','"
				+ Birthday
				+ "','"
				+ DocType
				+ "',"
				+ "'"
				+ DocName
				+ "','"
				+ DocValid
				+ "','"
				+ InsuranceCode
				+ "','"
				+ Insurance
				+ "','"
				+ Nationality
				+ "','"
				+ EatPrefe
				+ "','"
				+ SeatPrefe
				+ "','"
				+ OrderType
				+ "',"
				+ "'"
				+ Pnrno
				+ "',0,'"
				+ payTime
				+ "','"
				+ warnMsgs
				+ "','"
				+ error
				+ "',"
				+ delStatus
				+ ",'" + addTime + "'," + user.getUserId() + ")";
		pdi.allSql(sql);
		return Xml;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void saveCabinData(JSONArray arrayCd, FlightData fd) {
		List<CabinDatas> cdList = new ArrayList<CabinDatas>();
		CabinData cd = new CabinData();
		FlightDatasDao fddDao = new FlightDatasDaoImpl();
		try {
			for (int i = 0; i < arrayCd.length(); i++) {
				JSONObject jsonItem = arrayCd.getJSONObject(i);
				cd = new CabinData();
				cd.setCabin(jsonItem.getString("Cabin"));
				cd.setCabinType(jsonItem.getString("CabType"));
				cd.setIsPublishTariff(jsonItem.getString("IsPubTar"));
				cd.setSeatNum(jsonItem.getString("SeatNum"));
				cd.setPrice(Double.parseDouble(jsonItem.getString("Price")));
				cd.setDiscount(jsonItem.getString("Discount"));
				cd.setRewardRates(jsonItem.getString("RewRates"));
				cd.setPolicyId(jsonItem.getString("PolicyId"));
				cd.setNote(jsonItem.getString("Note"));
				try {
				} catch (Exception e) {

				}
				cd.setHlPrice(jsonItem.getString("HlPrice"));
				cd.setHlDiscount(jsonItem.getString("HlDiscount"));
				cd.setHkPrice(jsonItem.getString("HkPrice"));
				cd.setHkDiscount(jsonItem.getString("HkDiscount"));
				cd.setIsSpecialPolicy(jsonItem.getString("IsSpePolicy"));
				cd.setCashBack(jsonItem.getString("CashBack"));
				cd.setHlCashBack(jsonItem.getString("HlCashBack"));
				cd.setHkCashBack(jsonItem.getString("HkCashBack"));
				cd.setPlatOth(jsonItem.getString("PlatOth"));
				cd.setJsPrice(Double.parseDouble(jsonItem.getString("JsPrice")));
				cd.setFlightData(fd);
				fddDao.saveCabinData(cd);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String createOrderByPnr(String account, String md5, String xml) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			if (user == null) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
						+ "不存在\"}}";
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return parentService.getMothod("createOrderByPnr", xml,account,md5);
	}

	public String queryTicketOrder(String account, String md5, String xml) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			if (user == null) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
						+ "不存在\"}}";
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = parentService.getMothod("queryTicketOrder", xml,account,md5);
		return result;
	}

	public String queryTicketOrderDetail(String account, String md5, String xml) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			if (user == null) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
						+ "不存在\"}}";
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = parentService.getMothod("queryTicketOrderDetail", xml,account,md5);
		return result;
	}

	public String cancelTicketOrder(String account, String md5, String xml) {
		String sql = "select * from user where username = '" + account + "'";
		User user = userDaoImpl.getUserBySql(sql);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			xml = xml.replace(root.elementText("LoginUserId"), account);
			if (user == null) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"用户" + account
						+ "不存在\"}}";
			} else if (md5.equals(Md5.GetMd5(xml
					+ Md5.GetMd5(user.getPassword()))) == false) {
				return "{\"Res\": {\"Status\": -1,\"Error\": \"校验出错，密钥验证的失败\"}}";

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = parentService.getMothod("cancelTicketOrder", xml,account,md5);
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
			OrderID = root.elementText("OrderID");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
		}

		if (responseDataType.equals("1"))// 请求类型为xml
		{
			cancelTicketXML(result, OrderID);
		} else// 请求类型为json
		{
			HdTicketOrder ho = new HdTicketOrder();
			ho = hdodimpl.findByOrderId("from HdTicketOrder where orderNo ='"
					+ OrderID + "'");

			try {
				JSONObject jsonObj = new JSONObject(result);
				String str = jsonObj.getString("Res");
				JSONObject jsonObj2 = new JSONObject(str);
				String status = jsonObj2.getString("Status");
				try {
					error = jsonObj2.getString("Error");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					error = "";
				}
				ho.setCancelTicketState(status);
				ho.setCancelTicketError(error);
				ho.setUserId(user.getUserId());
				hdodimpl.update(ho);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return result;
	}

	// 取消订单 xml解析
	public void cancelTicketXML(String xml, String orderId) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			delStatus = root.elementText("Status");
			error = root.elementText("Error");
			String sql = "SELECT * FROM `hd_ticket_order` WHERE `order_no`='"
					+ orderId + "'";
			res_id = pdi.selectSql(sql);
			String updateSql = "UPDATE `hd_ticket_order` SET `cancel_ticket_state`='"
					+ delStatus
					+ "',`cancel_ticket_error`='"
					+ error
					+ "' WHERE `order_id`=" + res_id + "";
			pdi.allSql(updateSql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String editIdentityNo(String account, String md5, String xml) {
		/**
		 * 接口13，修改订单证件号码
		 */
    try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("editIdentityNo", xml,account,md5);
		if (responseDataType.equals("1"))// 请求类型为xml
		{
			String Status = "";
			try {
				Document document = DocumentHelper.parseText(result);
				Element root = document.getRootElement();
				Status = root.elementText("Status");
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 修改成功
			if (Status.equals("0")) {
				editIdentityNoXML(xml);
			}
		}
		 else// 请求类型为json
		{
			String strStatus = "";
			try {
				JSONObject jso = new JSONObject(result);
				String str = jso.getString("Res");
				JSONObject jso2 = new JSONObject(str);
				strStatus = jso2.getString("Status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (strStatus.equals("0")) {
				editIdentityNoXML(xml);
			}
		}
		return result;
	}
	//修改证件号
	public void editIdentityNoXML(String xml){
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			String orderID = root.elementText("OrderID");
			
			//Element e=(Element) root.selectNodes("//FlightDatas/FlightData");
			String NewIdentityNo="";
			List<Element> FlightList = root.selectNodes("//PassIdeInfo/PassIde");
			for (Element e : FlightList) {
				 NewIdentityNo = e.elementText("NewIdentityNo");
				 String updateSql="UPDATE `hd_ticket_order` SET `doc_name`='"+NewIdentityNo+"'  WHERE `order_no`='"+orderID+"'";
				 pdi.allSql(updateSql);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String yanzhengUser(String account, String md5, String xml) {
		CheckInterface checkInterface = new CheckInterface();
		return checkInterface.checkInterface(account, md5, xml,"");
	}

	public String editDeliveryInfo(String account, String md5, String xml) {
		 /**
	   	 * 接口14：修改订配送信息、行程单收件地址
	   	 */
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			responseDataType = root.elementText("ResponseDataType");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}
		
		String result = parentService.getMothod("editDeliveryInfo", xml,account,md5);
		if (responseDataType.equals("1"))// 请求类型为xml
		{
			String Status = "";
			try {
				Document document = DocumentHelper.parseText(result);
				Element root = document.getRootElement();
				Status = root.elementText("Status");
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 修改成功
			if (Status.equals("0")) {
				editDeliveryInfoXml(xml);
			}
		}
		 else// 请求类型为json
		{
			String strStatus = "";
			try {
				JSONObject jso = new JSONObject(result);
				String str = jso.getString("Res");
				JSONObject jso2 = new JSONObject(str);
				strStatus = jso2.getString("Status");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (strStatus.equals("0")) {
				editDeliveryInfoXml( xml);
			}
		}
		return result;
	}
	//修改配送信息 并保存
	public void editDeliveryInfoXml(String xml){
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			String orderID = root.elementText("OrderID");
			String deliveryType = root.elementText("DeliveryType");
			String addressee = root.elementText("Addressee");
			String postalCode = root.elementText("PostalCode");
			String mailAddress = root.elementText("MailAddress");
			String deliveryCity = root.elementText("DeliveryCity");
			String deliveryAddress = root.elementText("DeliveryAddress");
			String deliveryTime = root.elementText("DeliveryTime");
			String deliveryRemar = root.elementText("DeliveryRemar");
			 String updateSql="UPDATE `hd_ticket_order` SET  `deliveryType`='"+deliveryType+"',`addressee`='"+addressee+"',`postalCode`='"+postalCode+"',`mailAddress`='"+mailAddress+"',`deliveryCity`='"+deliveryCity+"',`deliveryAddress`='"+deliveryAddress+"',`deliveryTime`='"+deliveryTime+"',`deliveryRemar`='"+deliveryRemar+"' WHERE `order_no`='"+orderID+"'";
			 pdi.allSql(updateSql);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getDeliveryAddress(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("getDeliveryAddress", xml,account,md5);
		return result;
	}

	public String applyTicketRefund(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}
		
		String result = parentService.getMothod("applyTicketRefund", xml,account,md5);
		return result;
	}

	public String queryTicketReturnOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("queryTicketReturnOrder", xml,account,md5);
		return result;
	}

	public String queryTicketReturnOrderDetail(String account, String md5,
			String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("queryTicketReturnOrderDetail",
				xml,account,md5);
		return result;
	}

	public String applyChangeTicket(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("applyChangeTicket", xml,account,md5);
		return result;
	}

	public String queryChangeOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("queryChangeOrder", xml,account,md5);
		return result;
	}

	public String queryChangeOrderDetail(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("queryChangeOrderDetail", xml,account,md5);
		return result;
	}

	public String queryFxsTicketCp(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml).equals("ok")) {
			return yanzhengUser(account, md5, xml);
		}

		String result = parentService.getMothod("queryFxsTicketCp", xml,account,md5);
		return result;
	}
	public String queryMoreCabin(String account, String md5, String xml) {
		if(!yanzhengUser(account, md5, xml).equals("ok")){
			return yanzhengUser(account, md5, xml);
		}
		
		String result=parentService.getMothod("queryMoreCabin", xml,account,md5);
		return result;
	}
	public String queryStandarPrice(String account, String md5, String xml) {
		if(!yanzhengUser(account, md5, xml).equals("ok")){
			return yanzhengUser(account, md5, xml);
		}
		
		String result=parentService.getMothod("queryStandarPrice", xml,account,md5);
		return result;
	}
	public String queryPatByPNR(String account, String md5, String xml) {
		if(!yanzhengUser(account, md5, xml).equals("ok")){
			return yanzhengUser(account, md5, xml);
		}
		
		String result=parentService.getMothod("queryPatByPNR", xml,account,md5);
		return result;
	}
	public String queryPatBySegment(String account, String md5, String xml) {
		if(!yanzhengUser(account, md5, xml).equals("ok")){
			return yanzhengUser(account, md5, xml);
		}
		
		String result=parentService.getMothod("queryPatBySegment", xml,account,md5);
		return result;
	}
	public String editOrderByPat(String account, String md5, String xml) {
		if(!yanzhengUser(account, md5, xml).equals("ok")){
			return yanzhengUser(account, md5, xml);
		}
		
		String result=parentService.getMothod("editOrderByPat", xml,account,md5);
		return result;
	}
	public String queryPolcy(String account, String md5, String xml) {
		if(!yanzhengUser(account, md5, xml).equals("ok")){
			return yanzhengUser(account, md5, xml);
		}
		
		String result=parentService.getMothod("queryPolcy", xml,account,md5);
		return result;
	}
	public String changedPolicy(String account, String md5, String xml) {
		if(!yanzhengUser(account, md5, xml).equals("ok")){
			return yanzhengUser(account, md5, xml);
		}
		
		String result=parentService.getMothod("changedPolicy", xml,account,md5);
		return result;
	}
}