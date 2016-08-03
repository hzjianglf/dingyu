package com.wxpt.action.site.web;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.site.dao.RadiusDao;
import com.wxpt.site.entity.Customers;
import com.wxpt.common.HttpRequest;
import com.wxpt.common.ReplyStr;
import com.wxpt.site.service.CustomersService;
import com.wxpt.site.service.RadiusService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "choujiang", location = "/choujiang/demo.html") })
public class WebWeixinAction extends ActionSupport {
	private List<Customers> customersList;
	@Autowired
	CustomersService customersService;
	@Autowired
	RadiusService radiusService;
	RadiusDao radiusDao;
	private int industryId;
	private int areaId;
	private int type;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//String hql = "from Customers where locationX != '' and locationY != '' ";
		String hql="SELECT * FROM wxpt"+enterId+".customers WHERE 1=1 and `location_x`=''and `location_y`='' ";
		if (areaId != 0) {
			hql += " and area_id =" + areaId;
		}
		if (industryId != 0) {
			hql += " and industry_id=" + industryId;
			center =radiusService.getRadiusList(enterId).get(0).getCenterName();
		}
		customersList = customersService.getCustomersList(enterId,hql);
		for (int i = 0; i < customersList.size(); i++) {
			Customers customers = customersList.get(i);
			String xyResults = HttpRequest.sendPost(
					"http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
							+ customers.getLocationX() + "&y="
							+ customers.getLocationY() + "", "");
			String xyResult[] = xyResults.split(",");
			String x = xyResult[1].split(":")[1].substring(1,
					(xyResult[1].split(":")[1]).length() - 1);
			String y = xyResult[2].split(":")[1].substring(1,
					(xyResult[2].split(":")[1]).length() - 2);
			customers.setLocationX(x);
			customers.setLocationY(y);
			customersList.set(i, customers);
		}
		customersList = customersService.getCustomersList(enterId,hql);
		count = customersList.size();
		return "success";
	}

	public void getAllImg() throws IOException {
		String markerStyles="";
		String markers="";
		int j=0;
		customersList = customersService.getCustomersListByXY(enterId);
		String ids = "";
		for (int i = 0; i < customersList.size(); i++) {
			Customers customers = customersList.get(i);

			if ((i + 1) == customersList.size()) {
				ids += customers.getCustomersId();
			} else {
				ids += customers.getCustomersId() + ",";
			}
			if ((i + 1) == customersList.size()||i==110) {
				markers += customers.getLocationY() + ","
						+ customers.getLocationX();
				break;
			} else {
				markers += customers.getLocationY()
						+ ","
						+ customers.getLocationX()
						+ "|";
			}

			if ((i + 1) == customersList.size()) {
				markerStyles += "l" + "," + j;
			} else {
				markerStyles += "l" + "," + j + ReplyStr.getStr("|");
			}
			j++;

		}
		System.out.println(markers);
		String url = "http://api.map.baidu.com/staticimage?center="+ReplyStr.getStr("济南")
				+ "&width=640&height=320&zoom=10&markers=" + markers;
		this.getImg(url);
	}

	public void getImg(String u) throws IOException {
		System.out.println(u);
		System.out.println("http://api.map.baidu.com/staticimage?center=%E8%A5%BF%E5%AE%89&width=640&height=320&zoom=10&markers=108.937831,34.269365|108.935969,34.259359|108.932856,34.270381|108.935088,34.255906|108.930606,34.260831|108.927010,34.260853|108.932865,34.259260|108.949810,34.271578|108.966917,34.261965|108.964582,34.275715|108.961815,34.264468|108.995563,34.264965|109.070198,34.272636|108.986979,34.269720|109.014396,34.263742|108.980021,34.259421|109.016251,34.253270|109.010775,34.260959|108.995549,34.267072|108.985105,34.261028|109.016165,34.254962|109.066755,34.264798|109.063732,34.267807|108.984031,34.263591|109.021314,34.270450|108.930736,34.241031|108.937986,34.195137|108.953787,34.243322|108.922295,34.198698|108.914057,34.215804|108.935238,34.156317|108.931938,34.225299|108.953759,34.240290|108.960418,34.235738|108.936595,34.241328|108.943483,34.201950|108.971698,34.241362|108.952858,34.218383|108.968702,34.246034|108.940186,34.222785|108.932100,34.239903|108.923950,34.238486|108.935216,34.207818|108.956941,34.239350|108.921508,34.220980|108.930189,34.159033|108.939844,34.150430|108.892436,34.259887|108.921398,34.255269|108.901104,34.253437|108.869806,34.275127|108.872179,34.252947|108.875013,34.252216|108.840785,34.259885|108.881473,34.261803|108.915941,34.263530|108.932083,34.228086|108.917587,34.265092|108.919697,34.257074|108.923258,34.256773|108.872156,34.268644|108.931671,34.284779|108.934520,34.280109|108.927623,34.280284|108.938484,34.280215|108.955421,34.322910|108.925417,34.280235|108.842185,34.320899|108.946754,34.293728|108.876485,34.286404|108.949514,34.280429|108.892626,34.216102|108.889929,34.245971|108.876700,34.237670|108.907910,34.236166|108.872720,34.235510|108.874597,34.245421|108.873095,34.230516|108.871765,34.247567|108.873310,34.224222|108.898909,34.241288|108.898002,34.211420|108.873199,34.222954|108.957341,34.211539|108.972793,34.197783|108.949422,34.259060|108.927414,34.272799|108.935214,34.264134|108.973099,34.259012|109.004870,34.277573|108.988201,34.263012|108.982972,34.237611|109.009464,34.242998|108.980176,34.259263|108.99761".length());
		System.out.println("http://api.map.baidu.com/staticimage?center=116.403874,39.914889&width=500&height=500&zoom=11&labels=海淀|116.487812,40.017524|朝阳|大红门|116.442968,39.797022|丰台|116.275093,39.935251|116.28377,39.903743&labelStyles=%E6%B5%B7%E6%B7%80,1,32,0x990099,0xff00,1|%E4%B8%9C%E5%8C%97%E4%BA%94%E7%8E%AF,1,14,0xffffff,0x996600,1|%E6%9C%9D%E9%98%B3,1,14,,0xff6633,1|%E5%A4%A7%E7%BA%A2%E9%97%A8,1,32,0,0xffffff,1|%E6%9C%AA%E7%9F%A5%EF%BC%9F%EF%BC%81%23%EF%BF%A5%25%E2%80%A6%E2%80%A6%26*%EF%BC%88%EF%BC%89%EF%BC%81,1,14,0xff0000,0xffffff,1|%E4%B8%B0%E5%8F%B0%E5%A4%A7%E8%90%A5,1,24,0,0xcccccc,1|%E8%A5%BF%E5%9B%9B%E7%8E%AF,,14,0,0xffffff,|%E6%88%91%E4%BB%AC%E4%BC%9F%E5%A4%A7%E7%A5%96%E5%9B%BD%E9%A6%96%E9%83%BD%E5%8C%97%E4%BA%AC,1,25,0xffff00,0xff0000,0".length());
		/*URL url = new URL(u);
		URLConnection con = url.openConnection();
		con.setRequestProperty("User-Agent",
				"Mozilla/4.0(compatible;MSIE 5.0;Windows NT;DigExt)");
		// 输入流
		InputStream is = con.getInputStream();

		byte bytes[] = this.InputStreamTOByte(is);
		is.close();
		ServletActionContext.getResponse().getOutputStream().write(bytes);*/
		InputStream is =  new URL(u).openStream();

		byte[] bs =  this.InputStreamTOByte(is);
		is.close();
		ServletActionContext.getResponse().setContentType("image/jpeg");
		//out.print(bs);
		//ImageIO.write(bs, "jpeg", response.getOutputStream());   
		ServletOutputStream os = ServletActionContext.getResponse().getOutputStream();
		os.write(bs);
		os.close();

	}

	public static byte[] InputStreamTOByte(InputStream in) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int size = -1;
		while ((size = in.read(data, 0, 4096)) != -1) {
			outStream.write(data, 0, size);
		}
		data = null;
		return outStream.toByteArray();
	}

	private String ids;
	private String markers;
	private String userXY;
	private String center;
	private int count;
	private double radius;
	private int enterId;

	public String queryRadius() {
		customersList = new ArrayList<Customers>();
		List<Customers> cList = customersService.getCustomersListByXY(enterId);
		for (int i = 0; i < cList.size(); i++) {
			Customers customers = cList.get(i);
			String Location_Y = userXY.split(",")[0];
			String Location_X = userXY.split(",")[1];
			double distance = HttpRequest.GetDistance(
					Double.parseDouble(Location_X),
					Double.parseDouble(Location_Y),
					Double.parseDouble(customers.getLocationX()),
					Double.parseDouble(customers.getLocationY()));
			if (distance < radius * 1000 && distance >= 0) {
				String xyResults = HttpRequest.sendPost(
						"http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
								+ customers.getLocationX() + "&y="
								+ customers.getLocationY() + "", "");
				String xyResult[] = xyResults.split(",");
				String x = xyResult[1].split(":")[1].substring(1,
						(xyResult[1].split(":")[1]).length() - 1);
				String y = xyResult[2].split(":")[1].substring(1,
						(xyResult[2].split(":")[1]).length() - 2);
				customers.setLocationX(x);
				customers.setLocationY(y);
				cList.set(i, customers);
				customersList.add(cList.get(i));
			}
		}
		count = customersList.size();
		return "success";
	}

	public String getList() {
		markers = "";
		try {
			customersList = customersService.getCustomersListByXY(enterId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//enterId="";
		}
		System.out.println(customersList.size()+"hhhhhhhhhhhhhhh");
		for (int i = 0; i < customersList.size(); i++) {
			Customers customers = customersList.get(i);
			String str2="http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
					+ customers.getLocationX() + "&y="
					+ customers.getLocationY() ;
			System.out.println(str2);
			String xyResults = HttpRequest.sendPost(
					"http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x="
							+ customers.getLocationX() + "&y="
							+ customers.getLocationY() + "", "");
			//String str="http://api.map.baidu.com/ag/coord/convert?from=2&to=4&x=36.679100&=117.134628";
			String xyResult[] = xyResults.split(",");
			String x = xyResult[1].split(":")[1].substring(1,
					(xyResult[1].split(":")[1]).length() - 1);
			String y = xyResult[2].split(":")[1].substring(1,
					(xyResult[2].split(":")[1]).length() - 2);
			customers.setLocationX(x);
			customers.setLocationY(y);
			customersList.set(i, customers);
		}
		count = customersList.size();
		center =radiusDao.getRadiusList(enterId).get(0).getCenterName();
		return "success";
	}

	public String choujiang() {
		return "choujiang";
	}

	public String getUserXY() {
		return userXY;
	}

	public void setUserXY(String userXY) {
		this.userXY = userXY;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Customers> getCustomersList() {
		return customersList;
	}

	public void setCustomersList(List<Customers> customersList) {
		this.customersList = customersList;
	}

	public int getIndustryId() {
		return industryId;
	}

	public void setIndustryId(int industryId) {
		this.industryId = industryId;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getMarkers() {
		return markers;
	}

	public void setMarkers(String markers) {
		this.markers = markers;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public RadiusDao getRadiusDao() {
		return radiusDao;
	}

	public void setRadiusDao(RadiusDao radiusDao) {
		this.radiusDao = radiusDao;
	}

}
