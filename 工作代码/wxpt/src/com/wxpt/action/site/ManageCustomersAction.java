package com.wxpt.action.site;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxpt.common.FileUploadBean;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.CustomersDao;
import com.wxpt.site.entity.Area;
import com.wxpt.site.entity.Canton;
import com.wxpt.site.entity.Customers;
import com.wxpt.site.entity.Industry;
import com.wxpt.site.entity.pojo.CustomersPojo;
import com.wxpt.site.service.AreaService;
import com.wxpt.site.service.CantonService;
import com.wxpt.site.service.CustomersService;
import com.wxpt.site.service.IndustryService;
import com.wxpt.site.service.UserService;

@SuppressWarnings("serial")
@ParentPackage("json-default")
@Results({ @Result(name = "customers", type = "json", params = { "root",
		"result" }) })
public class ManageCustomersAction extends ParentAction {
	@Autowired
	IndustryService industryService;
	@Autowired
	AreaService areaService;
	@Autowired
	CantonService cantonService;
	@Autowired
	CustomersService customersService;
	@Autowired
	CustomersDao customersDao;

	private int industry;
	private int area;
	private int canton;
	private String customersNo;
	private String industryName;
	private String areaName;
	private String cantonName;
	private int customersID;
	GetCookie cookies = new GetCookie();
	int enterId = cookies.getCookie();

	public String showIndustry() throws UnsupportedEncodingException {
		// String message = "";
		List<Industry> industryList = industryService.getIndustryList(enterId);
		/*
		 * Customers customers = customersService.getCustomersByID(customersID);
		 * if(customers == null){ industryName = null; }else{ industryName =
		 * customers.getIndustry().getIndustryName(); } if(industryName ==
		 * null){ message = "[{\"id\":\"-1\",\"text\":\"全部行业\"}"; }else{
		 * Industry industry = industryService.getIndustryByName(industryName);
		 * if(industry == null){ message = "[{\"id\":\"-1\",\"text\":\"全部行业\"}";
		 * }else{ message =
		 * "[{\"id\":\""+industry.getIndustryId()+"\",\"text\":\""
		 * +industry.getIndustryName()+"\"},{\"id\":\"-1\",\"text\":\"全部行业\"}";
		 * }
		 * 
		 * }
		 */

		String message = "[{\"id\":\"-1\",\"text\":\"全部行业\"}";

		for (Industry industry : industryList) {
			message += ",{\"id\":\"" + industry.getIndustryId()
					+ "\",\"text\":\"" + industry.getIndustryName() + "\"}";
		}
		message += "]";
		super.out.print(message);
		super.out.flush();
		super.out.close();
		return "customers";
	}

	public String showArea() throws UnsupportedEncodingException {
		// String message = "";
		/*
		 * Customers customers = customersService.getCustomersByID(customersID);
		 * if(customers == null){ areaName = null; }else{ areaName =
		 * customers.getArea().getAreaName(); } if(areaName == null){ message =
		 * "[{\"id\":\"-1\",\"text\":\"全部区域\"}"; }else{ Area area =
		 * areaService.getAreaByName(areaName); if(area == null){ message =
		 * "[{\"id\":\"-1\",\"text\":\"全部区域\"}"; }else{ message =
		 * "[{\"id\":\""+area.getAreaId()+"\",\"text\":\""+areaName+
		 * "\"},{\"id\":\"-1\",\"text\":\"全部区域\"}"; } }
		 */
		String message = "[{\"id\":\"-1\",\"text\":\"全部区域\"}";
		List<Area> areaList = areaService.getAreaList(enterId);
		for (Area area : areaList) {
			message += ",{\"id\":\"" + area.getAreaId() + "\",\"text\":\""
					+ area.getAreaName() + "\"}";
		}
		message += "]";
		super.out.print(message);
		super.out.flush();
		super.out.close();
		return "customers";
	}

	public String showCanton() throws UnsupportedEncodingException {
		/*
		 * String message = ""; Customers customers =
		 * customersService.getCustomersByID(customersID); if(customers == null
		 * || customers.getCanton() == null){ cantonName = null; }else{
		 * cantonName = customers.getCanton().getCantonName(); } if(cantonName
		 * == null || cantonName.equals("")){ message =
		 * "[{\"id\":\"-1\",\"text\":\"全部行政区域\"}"; }else{ Canton canton =
		 * cantonService.getCantonByName(cantonName); if(canton == null){
		 * message = "[{\"id\":\"-1\",\"text\":\"全部行政区域\"}"; }else{ message =
		 * "[{\"id\":\""+canton.getCantonId()+"\",\"text\":\""+cantonName+
		 * "\"},{\"id\":\"-1\",\"text\":\"全部行政区域\"}"; } }
		 */
		String message = "[{\"id\":\"-1\",\"text\":\"全部行政区域\"}";
		List<Canton> cantonList = cantonService.getCantonList(enterId);
		for (Canton canton : cantonList) {
			message += ",{\"id\":\"" + canton.getCantonId() + "\",\"text\":\""
					+ canton.getCantonName() + "\"}";
		}
		message += "]";
		super.out.print(message);
		super.out.flush();
		super.out.close();
		return "customers";
	}

	JSONArray jsonls;
	int count;// 总记录数
	int page;
	int rows;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCustomersID() {
		return customersID;
	}

	public void setCustomersID(int customersID) {
		this.customersID = customersID;
	}

	List<Customers> customersList;
	List<CustomersPojo> customersPojos;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.pageList(page, rows);
		customersPojos = new ArrayList<CustomersPojo>();
		for (Customers customers : customersList) {
			String cantonName = "";
			int cantonId = 0;
			if (customers.getCanton() != null) {

				String sql = "SELECT * FROM wxpt" + enterId
						+ ".canton WHERE `canton_id`="
						+ customers.getCanton().getCantonId();
				Canton anton = customersService.getbyId(sql);
				cantonName = anton.getCantonName();
				cantonId = anton.getCantonId();
			}
			String sql1 = "SELECT * FROM wxpt" + enterId
					+ ".area WHERE `area_id`="
					+ customers.getArea().getAreaId();
			Area area = customersService.getById(sql1);
			String sql2 = "SELECT * FROM wxpt" + enterId
					+ ".industry WHERE `industry_id`="
					+ customers.getIndustry().getIndustryId();
			Industry indu = customersService.getIndustryById(sql2);

			CustomersPojo customersPojo = new CustomersPojo(
					customers.getCustomersId(), area.getAreaName(),
					indu.getIndustryName(), cantonName,
					customers.getCustomersName(),
					customers.getCustomersAddress(), customers.getModel(),
					customers.getCount(), customers.getIntroduce(),
					customers.getLocationX(), customers.getLocationY(),
					customers.getAddTime(), customers.getState(),
					customers.getFromUsername());
			customersPojo.setAreaId(area.getAreaId());
			customersPojo.setIndustryId(indu.getIndustryId());
			customersPojo.setCantonId(cantonId);
			customersPojo.setCustomersNo(customers.getCustomersNo());
			customersPojo.setCustomersImage("<img src=\"../../wxpt/web/images/"
					+ enterId + "/" + customers.getCustomersImage()
					+ "\" style=\"width:600px ; height:200px ;\">");
			customersPojos.add(customersPojo);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig
					.setExcludes(new String[] { "industry", "canton", "area" });
			jsonls = JSONArray.fromObject(customersPojos, jsonConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
		return "customers";
	}

	private void pageList(int page2, int rows2) {
		// TODO Auto-generated method stub
		String hql = "select * from wxpt" + enterId + ".customers where 1=1 ";
		if (industry != 0 && industry != -1) {
			hql += " and industry_id = " + industry;
		}
		if (area != 0 && area != -1) {
			hql += " and area_id = " + area;
		}
		if (canton != 0 && canton != -1) {
			hql += " and canton_id = " + canton;
		}
		count = customersService.getCustomersCount(enterId, hql);
		customersList = customersService.getCustomersList(enterId, hql, page,
				rows);
	}

	private String customersIds;

	public String delete() {
		String path = ServletActionContext.getServletContext().getRealPath("/");
		String[] ids = customersIds.split(",");
		for (int i = 0; i < ids.length; i++) {
			Customers customers = customersService.getCustomersByID(enterId,
					Integer.parseInt(ids[i]));
			if (customers.getCustomersImage() != null
					&& customers.getCustomersImage().equals("") == false) {
				if ((new File(path + "images/" + customers.getCustomersImage()))
						.exists()) {
					(new File(path + "images/" + customers.getCustomersImage()))
							.delete();
				}
			}
			customersService.deleteById(enterId, Integer.parseInt(ids[i]));
		}
		return "customers";
	}

	UserService userService;
	FileUploadBean bean = new FileUploadBean();
	private File customersImage;

	public String add() {
		Customers customers = new Customers();
		Industry industry = industryService
				.getIndustryByID(enterId, industryId);
		Area area = areaService.getAreaByID(enterId, areaId);
		Canton canton = cantonService.getCantonByID(enterId, cantonId);
		customers.setAddTime(TimeUtil.getTime());
		customers.setArea(area);
		customers.setCanton(canton);
		customers.setCustomersAddress(customersAddress);
		customers.setCustomersName(customersName);
		customers.setCount(customersCount + "");
		customers.setIntroduce(customersIntroduce);
		customers.setIndustry(industry);
		customers.setState(0);
		customers.setLocationX(locationx);
		customers.setLocationY(locationy);
		customers.setModel(customersModel);
		customers.setCustomersNo(customersNo);
		if (customersImage != null) {
			String image = customersNo + TimeUtil.getPayTime() + ".jpg";
			bean.upLoad(image, customersImage, enterId + "");
			customers.setCustomersImage(image);
		}
		try {
			customersService.addCustomers(enterId, customers);
			message = "添加成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message = "添加失败";
		}
		return "success";
	}

	private String message;

	public String update() {
		Customers customers = customersService.getCustomersByID(enterId,
				customersId);
		String sql;
		if (customersImage != null) {
			bean.deletefile(enterId + "/" + customers.getCustomersImage());
			String image = customersNo + TimeUtil.getPayTime() + ".jpg";
			bean.upLoad(image, customersImage, enterId + "");
			// customers.setCustomersImage(image);
			sql = "UPDATE wxpt" + enterId + ".customers SET `customers_name`='"
					+ customersName + "',`industry_id`=" + industryId
					+ ",`area_id`=" + areaId + ",`canton_id`=" + cantonId
					+ ",`customers_address`='" + customersAddress + "',"
					+ "`model`='" + customersModel + "',`count`="
					+ customersCount + ",`introduce`='" + customersIntroduce
					+ "',`location_x`='" + locationx + "',`location_y`='"
					+ locationx + "',`state`=0,`customers_no`='" + customersNo
					+ "',`customers_image`='" + image
					+ "' WHERE `customers_id`=" + customersId + "";
		} else {
			sql = "UPDATE wxpt" + enterId + ".customers SET `customers_name`='"
					+ customersName + "',`industry_id`=" + industryId
					+ ",`area_id`=" + areaId + ",`canton_id`=" + cantonId
					+ ",`customers_address`='" + customersAddress + "',"
					+ "`model`='" + customersModel + "',`count`="
					+ customersCount + ",`introduce`='" + customersIntroduce
					+ "',`location_x`='" + locationx + "',`location_y`='"
					+ locationx + "',`state`=0,`customers_no`='" + customersNo
					+ "' WHERE `customers_id`=" + customersId + "";
		}
		try {
			customersDao.update(enterId, sql);
			message = "修改成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			message = "修改失败";
		}
		return "success";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCustomersIds() {
		return customersIds;
	}

	public void setCustomersIds(String customersIds) {
		this.customersIds = customersIds;
	}

	private int customersId;

	private String customersName;
	private int industryId;
	private int areaId;
	private int cantonId;
	private String customersModel;
	private int customersCount;
	private String locationx;
	private String locationy;
	private String customersAddress;
	private String customersIntroduce;

	public String getCustomersName() {
		return customersName;
	}

	public void setCustomersName(String customersName) {
		this.customersName = customersName;
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

	public int getCantonId() {
		return cantonId;
	}

	public void setCantonId(int cantonId) {
		this.cantonId = cantonId;
	}

	public String getCustomersModel() {
		return customersModel;
	}

	public void setCustomersModel(String customersModel) {
		this.customersModel = customersModel;
	}

	public int getCustomersCount() {
		return customersCount;
	}

	public void setCustomersCount(int customersCount) {
		this.customersCount = customersCount;
	}

	public String getLocationx() {
		return locationx;
	}

	public void setLocationx(String locationx) {
		this.locationx = locationx;
	}

	public String getLocationy() {
		return locationy;
	}

	public void setLocationy(String locationy) {
		this.locationy = locationy;
	}

	public String getCustomersAddress() {
		return customersAddress;
	}

	public void setCustomersAddress(String customersAddress) {
		this.customersAddress = customersAddress;
	}

	public String getCustomersIntroduce() {
		return customersIntroduce;
	}

	public void setCustomersIntroduce(String customersIntroduce) {
		this.customersIntroduce = customersIntroduce;
	}

	public int getCustomersId() {
		return customersId;
	}

	public void setCustomersId(int customersId) {
		this.customersId = customersId;
	}

	public int getIndustry() {
		return industry;
	}

	public void setIndustry(int industry) {
		this.industry = industry;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getCanton() {
		return canton;
	}

	public void setCanton(int canton) {
		this.canton = canton;
	}

	public String getCustomersNo() {
		return customersNo;
	}

	public void setCustomersNo(String customersNo) {
		this.customersNo = customersNo;
	}

	public File getCustomersImage() {
		return customersImage;
	}

	public void setCustomersImage(File customersImage) {
		this.customersImage = customersImage;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCantonName() {
		return cantonName;
	}

	public void setCantonName(String cantonName) {
		this.cantonName = cantonName;
	}

	public CustomersDao getCustomersDao() {
		return customersDao;
	}

	public void setCustomersDao(CustomersDao customersDao) {
		this.customersDao = customersDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
