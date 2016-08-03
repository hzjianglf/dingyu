package com.wxpt.action.site;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.wxpt.common.FileUploadBean;
import com.wxpt.common.FileUploadBeanTwo;
import com.wxpt.common.MyTaskFile;
import com.wxpt.common.TimeUtil;
import com.wxpt.site.dao.ManageDao;
import com.wxpt.site.entity.EnterInfor;
import com.wxpt.site.entity.ManageUser;
import com.wxpt.site.entity.Privilege;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.entity.Role;
import com.wxpt.site.entity.Shiyong;
import com.wxpt.site.entity.ShoppingAds;
import com.wxpt.site.service.EnterService;
import com.wxpt.site.service.ManageService;
import com.wxpt.site.service.ProductTypeService;
import com.wxpt.site.service.RoleService;
import com.wxpt.site.service.UserService;
import com.wxpt.site.entity.pojo.productPojo;

@SuppressWarnings("serial")
public class ManageAction extends ParentAction {
	private int enterId;
	private int enId;
	private int type;
	EnterService enterService;
	EnterInfor enter;
	RoleService roleService;
	Role role;
	Privilege privilege;
	@Autowired
	UserService userService;
	ManageUser user;

	@Autowired
	ManageService manageService;
	@Autowired
	ManageDao manageDao;
	List<Product> listProduct;
	Product product;
	List<ShoppingAds> shoppingAds;
	List<productPojo> productPojos;
	productPojo productPojo;
	ProductType productType;
	List<ProductType> listType;
	List<ProductType> listType1;
	List<ProductType> listTypes;
	ProductTypeService productTypeService;
	JSONArray jsonls;
	int count;// 总记录数
	String rows;
	String page;
	String temp;
	private String value = "0";
	private String productId;
	private String proName;
	private String proNum;
	private String proTime;
	private String productTypes;
	private String endTime;
	private int parentId;
	// private int type;

	private File xpic;
	private File xpic1;
	private File dpic1;
	private File dpic;
	private File banner;

	static List<String> list = new ArrayList<String>();
	// 全局变量
	static String fileName;
	private String fileNames;
	private String userName;
	private String pwd1;
	private String pwd2;
	List<EnterInfor> enterList = new ArrayList<EnterInfor>();
	StringBuffer buffer = new StringBuffer();
	FileUploadBeanTwo bean = new FileUploadBeanTwo();

	public String manage() {
		return "login";
	}

	public String question() {
		return "question";
	}

	public String keywords() {
		return "keywords";
	}

	public String vote() {
		return "vote";
	}

	public String voteactive() {
		return "voteactive";
	}

	public String checkin() {
		return "checkin";
	}

	public String disposition() {
		return "disposition";
	}

	public String userLuck() {
		return "userluck";
	}

	public String questionLuck() {
		return "questionLuck";
	}

	public String prize() {
		return "prize";
	}

	public String prize1() {
		return "prize1";
	}

	public String prize2() {
		return "prize2";
	}

	public String login() {
		return "login2";
	}

	public String index2() {
		return "index2";
	}

	public String enterInfor() {
		return "enterInfor";
	}

	public String dailystatistic() {
		return "dailystatistic";
	}

	public String mouthstatistic() {
		return "mouthstatistic";
	}

	public String weekstatistic() {
		return "weekstatistic";
	}

	public String cardMan() {
		return "card";
	}

	public String cardRecords() {
		return "cardLuck";
	}

	public String prizeluck() {
		return "prizeluck";
	}

	public String jump() {
		return "huiyuan";
	}

	public String reply() {
		return "reply";
	}

	public String voteSheZhi() {
		return "voteSz";
	}

	// 移植光向
	public String industry() {
		return "industry";
	}

	public String area() {
		return "area";
	}

	public String canton() {
		return "canton";
	}

	public String radius() {
		return "radius";
	}

	public String customers() {
		return "customers";
	}

	public String webSite() {
		return "webSite";
	}

	public String Menu() {
		return "menu";
	}

	public String quanxian() {
		return "quanxian";
	}

	public String questionRule() {
		return "quertionRule";
	}

	public String questionTishi() {
		return "questionTishi";
	}

	public String sheying() {
		return "sheying";
	}

	public String sheUser() {
		return "sheUser";
	}

	public String page() {
		return "page";
	}
	
	public String tongji(){
		return "tongji";
	}
	
	
	public EnterService getEnterService() {
		return enterService;
	}

	public void setEnterService(EnterService enterService) {
		this.enterService = enterService;
	}

	public EnterInfor getEnter() {
		return enter;
	}

	public void setEnter(EnterInfor enter) {
		this.enter = enter;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String qydisposition() {
		return "qydisposition";
	}

	public String manageYPay() {
		return "ypay";
	}

	public String manageZPay() {
		return "zpay";
	}

	public String huiYuanGrade() {
		return "memberGrade";
	}

	public String greetingCard() {
		return "greetingcard";
	}

	public String surveyquestion() {
		return "surveyquestion";
	}

	public String survey() {
		return "survey";
	}

	public String suroption() {
		return "suroption";
	}

	private boolean peizhi;
	private boolean caidan;
	private boolean guanjianzi;
	private boolean huifu;
	private boolean huodong;
	private boolean toupiao;
	private boolean dati;
	private boolean qiandao;
	private boolean huiyuan;
	private boolean quyu;
	private boolean wangzhan;
	private boolean guaguale;
	private boolean guanli;
	private boolean quanxian;
	private boolean tongji;
	private boolean enterp;
	private boolean vshop;
	private boolean qy;
	private boolean qygl;
	private boolean sheying;
	private boolean tiyan;
	private boolean diaoyan;
	private int enterType;
	private boolean heka;
	private String tianqi;
	private String kongqi;
	private String shouji;
	private String kuaidi;
	private String huoche;
	private String daohang;
	private String zhoubian;
	private String gongjiao;
	private String chengyu;
	private String shiyi;
	private String shenfen;
	private String shengao;
	EnterInfor enters;
	List<Shiyong> shiList = new ArrayList<Shiyong>();

	@SuppressWarnings("static-access")
	public String execute() {
		peizhi = true;
		enter = enterService.getById(enId);
		String qyId = enter.getChilendQyId();
		if (qyId == null || qyId.equals("") || qyId.equals("null")) {
			qy = false;
		} else {
			qy = true;
			String[] qy = qyId.split(";");
			for (int i = 0; i < qy.length; i++) {
				enter = enterService.getById2(Integer.parseInt(qy[i]));
				enterList.add(enter);
				if (i == qy.length - 1) {
					enter = enterService.getById(enId);
					enterList.add(enter);
				}
			}
		}
		enters = enterService.getById(enterId);
		if (enters.getAppId() == "" || enters.getAppId().equals("")
				|| enters.getAppSecret() == ""
				|| enters.getAppSecret().equals("")) {
			caidan = false;
		} else {
			caidan = true;
		}
		if (enters.getEnterPerson() == null
				|| enters.getEnterPerson().equals("null")) {
		} else {
			endTime = this.addDate(enters.getRegistTime(),
					Integer.parseInt(enters.getEnterPerson()));
			tiyan = true;
		}
		getRole();
		getShiYong();
		return "index";

	}

	// 查看角色
	public void getRole() {
		// peizhi=pidui(3);
		// caidan = pidui(7,enterId);
		vshop = pidui(168, enterId);
		guanjianzi = pidui(18, enterId);
		huifu = pidui(28, enterId);
		huodong = pidui(32, enterId);
		toupiao = pidui(39, enterId);
		dati = pidui(51, enterId);
		qiandao = pidui(62, enterId);
		huiyuan = pidui(79, enterId);
		quyu = pidui(93, enterId);
		wangzhan = pidui(119, enterId);
		guaguale = pidui(156, enterId);
		// quanxian=pidui(166,enterId);
		guanli = pidui(165, enterId);
		sheying = pidui(203, enterId);
		diaoyan = pidui(206, enterId);
		heka = pidui(210, enterId);
		// tongji=pidui(167,enterId);
		if (qy == false) {
			qy = pidui(195, enterId);
		}
		quanxian = pidui(197, enterId);
		tongji = pidui(199, enterId);

	}

	// 查询实用功能
	public  void getShiYong() {
		// 查询实用功能
		String sql = "SELECT * FROM wxpt" + enterId + ".shiyong WHERE 1";
		shiList = enterService.getall(sql);
		tianqi = shiList.get(0).getShiyongValue();
		kongqi = shiList.get(1).getShiyongValue();
		shouji = shiList.get(2).getShiyongValue();
		kuaidi = shiList.get(3).getShiyongValue();
		huoche = shiList.get(4).getShiyongValue();
		daohang = shiList.get(5).getShiyongValue();
		zhoubian = shiList.get(6).getShiyongValue();
		gongjiao = shiList.get(7).getShiyongValue();
		chengyu = shiList.get(8).getShiyongValue();
		shiyi = shiList.get(9).getShiyongValue();
		shenfen = shiList.get(10).getShiyongValue();
		shengao = shiList.get(11).getShiyongValue();
	}
	public void updateShiyong(){
		System.out.println(tianqi);
		if(tianqi==null){
			tianqi="0";
		}
		if(kongqi==null){
			kongqi="0";
		}
		if(shouji==null){
			shouji="0";
		}
		if(kuaidi==null){
			kuaidi="0";
		}
		if(huoche==null){
			huoche="0";
		}
		if(daohang==null){
			daohang="0";
		}
		if(zhoubian==null){
			zhoubian="0";
		}
		if(gongjiao==null){
			gongjiao="0";
		}
		if(chengyu==null){
			chengyu="0";
		}
		if(shiyi==null){
			shiyi="0";
		}
		if(shenfen==null){
			shenfen="0";
		}
		if(shengao==null){
			shengao="0";
		}
		String up1="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+tianqi+"' where `shiyong_id`=1";
		enterService.updateAll(up1, enterId);
		String up2="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+kongqi+"' where `shiyong_id`=2";
		enterService.updateAll(up2, enterId);
		String up3="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+shouji+"' where `shiyong_id`=3";
		enterService.updateAll(up3, enterId);
		String up4="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+kuaidi+"' where `shiyong_id`=4";
		enterService.updateAll(up4, enterId);
		String up5="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+huoche+"' where `shiyong_id`=5";
		enterService.updateAll(up5, enterId);
		String up6="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+daohang+"' where `shiyong_id`=6";
		enterService.updateAll(up6, enterId);
		String up7="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+zhoubian+"' where `shiyong_id`=7";
		enterService.updateAll(up7, enterId);
		String up8="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+gongjiao+"' where `shiyong_id`=8";
		enterService.updateAll(up8, enterId);
		String up9="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+chengyu+"' where `shiyong_id`=9";
		enterService.updateAll(up9, enterId);
		String up10="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+shiyi+"' where `shiyong_id`=10";
		enterService.updateAll(up10, enterId);
		String up11="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+shenfen+"' where `shiyong_id`=11";
		enterService.updateAll(up11, enterId);
		String up12="UPDATE wxpt"+enterId+".shiyong SET `shiyong_value`='"+shengao+"' where `shiyong_id`=12";
		enterService.updateAll(up12, enterId);
	}

	public boolean pidui(int pId, int enterId) {
		enter = enterService.getById(enterId);
		String sql0 = "SELECT * FROM webchat.manage_user WHERE `enterid`="
				+ enterId;
		user = userService.getByEnterId(sql0);
		String sql = "SELECT * FROM webchat.role WHERE `role_id`="
				+ user.getRoleList();
		role = roleService.getById(sql);
		String roles = role.getPrivilegeList();
		String[] prie = roles.split(",");
		for (int i = 0; i < prie.length; i++) {
			if (Integer.parseInt(prie[i]) == pId) {
				return true;
			}
		}
		return false;
	}

	public void qiehuan() throws Exception {
		System.out.println(enId);
		String sql0 = "SELECT * FROM webchat.manage_user WHERE `enterid`="
				+ enterId;
		user = userService.getByEnterId(sql0);
		user = userService.checkLogin(enterId, user.getManageUserName(),
				user.getPasswrod());
		if (user != null) {
			System.out.println(user.getManageUserName());
			if (user.getUserType() == 1) {
				Cookie wxpts = new Cookie("wxpts", user.getManageUserName()
						+ ":" + user.getEnterid() + ":" + enterId);
				wxpts.setMaxAge(60 * 60 * 24 * 365);
				wxpts.setPath("/");
				ServletActionContext.getResponse().addCookie(wxpts);
			}
		}
	}

	// 评价管理
	public String appraise() {
		return "appraise";
	}

	// 物流
	public String logistic() {
		return "logistics";
	}

	// 广告管理页面
	public String showAds() {
		return "ads";
	}

	// 企业信息
	public String enterinfor() {
		return "enterinfor";
	}

	// 微商城
	public String vshop() {
		return "vshop";
	}

	public void showAdsConent() {

		String hql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".shopping_ads where 1=1";
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page); // 当前第几页 //每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "5"
				: rows);// 每页显示条数
		int start = (intPage - 1) * number;
		shoppingAds = manageService.showShoppAds(0, hql, start, number);
		count = manageService.getCountAd(0, hql);
		jsonls = JSONArray.fromObject(shoppingAds);

		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();

	}

	// banner上传
	public void uploadBanner() {
		String value = "";
		try {

			fileName = "banner.jpg";
			if (banner != null) {
				bean.upLoad(fileName, banner);
				if (manageService.check(1) == 0) {
					int i = manageService.saveBanner(fileName,
							super.getCookieByEnterID());
					if (i == 1) {
						value = "1";
					}
				} else {
					int i = manageService.updateBanner(fileName, 1);
					if (i == 1) {
						value = "1";
					}
				}

			} else {
				value = "0";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {

			try {
				HttpServletResponse hs = ServletActionContext.getResponse();
				hs.setContentType("text/html;charset=utf-8");
				PrintWriter out = hs.getWriter();
				out.print(value);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 商品小图上传
	public void uploadShopPic() {
		String value = "";
		try {

			fileName = new StringBuffer().append(
					new SimpleDateFormat("yyyyMMddss").format(new Date())
							+ "_xshop.jpg").toString();
			if (xpic != null) {
				bean.upLoad(fileName, xpic);
				value = "1";
			} else {
				value = "0";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {

			try {
				HttpServletResponse hs = ServletActionContext.getResponse();
				hs.setContentType("text/html;charset=utf-8");
				PrintWriter out = hs.getWriter();
				out.print(value);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 商品修改
	public void updateProduct() {
		productType = new ProductType();
		Product pro = new Product();
		pro = manageService.getByIdProduct(product.getProductId(),
				super.getCookieByEnterID());
		pro.setProductName(product.getProductName());
		pro.setProductNum(product.getProductNum());
		pro.setPrice(product.getPrice());
		pro.setCheapPrice(product.getCheapPrice());
		pro.setSellNum(product.getSellNum());
		pro.setProductRecomme(product.getProductRecomme());
		pro.setProductAddtime(product.getProductAddtime());
		pro.setProductUpdateTime(TimeUtil.getTime());
		pro.setProductColor(product.getProductColor());
		pro.setProductDetails(product.getProductDetails());
		pro.setProductOverview(product.getProductOverview());
		pro.setCheapPrice(product.getCheapPrice());
		pro.setInventoryNum(product.getInventoryNum());
		pro.setProductBrand(product.getProductBrand());
		if (fileName == null) {

		} else {
			bean.deletefile(pro.getProductXimage());
			pro.setProductXimage(fileName);
			fileName = null;
		}

		if (list.size() == 0) {

		} else {
			bean.deletefile2(pro.getProductDimage());
			String li = "";
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					li += list.get(i);
				} else {
					li += list.get(i) + ";";
				}
			}
			pro.setProductDimage(li);
			list.clear();
		}

		productType.setProductTypeId(type);
		pro.setProductType(productType);
		manageDao.updateProduct(pro, product.getProductId(),
				super.getCookieByEnterID());
	}

	// 商品小图上传
	public void uploadShopPic1() {
		String value = "";
		try {

			fileName = new StringBuffer().append(
					new SimpleDateFormat("yyyyMMddss").format(new Date())
							+ "_xshop.jpg").toString();
			if (xpic1 != null) {
				bean.upLoad(fileName, xpic1);
				value = "1";
			} else {
				value = "0";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {

			try {
				HttpServletResponse hs = ServletActionContext.getResponse();
				hs.setContentType("text/html;charset=utf-8");
				PrintWriter out = hs.getWriter();
				out.print(value);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 商品添加
	public void addProduct() {
		if (fileName == null) {
			product.setProductXimage("1.png");
		} else {
			product.setProductXimage(fileName);
			fileName = null;
		}
		if (list.size() == 0) {
			product.setProductDimage("1.png");
		} else {
			String li = "";
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					li += list.get(i);
				} else {
					li += list.get(i) + ";";
				}

			}
			System.out.println(li);
			product.setProductDimage(li);
			list.clear();
		}
		product.setProductType(manageService.getByIdType(type,
				super.getCookieByEnterID()));
		System.out.println(TimeUtil.getTime());
		product.setProductUpdateTime(TimeUtil.getTime());

		try {
			int i = manageService.addProduct(product,
					super.getCookieByEnterID());
			if (i == 0) {
				value = "0";
			} else {
				value = "1";
			}

			PrintWriter out = ServletActionContext.getResponse().getWriter();
			out.print(value);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 商品显示页面
	public String showProductView() {
		listType = manageService.getProductType(0, super.getCookieByEnterID());
		if (listType.size() != 0) {
			listType1 = manageService.getProductType(listType.get(0)
					.getProductTypeId(), super.getCookieByEnterID());
		}
		return "product";
	}

	public String showType() {
		listTypes = manageService.getProductType(0, super.getCookieByEnterID());
		return "typeajax";
	}

	// 二级类
	public String getProType() {
		listType1 = manageService.getProductType(parentId,
				super.getCookieByEnterID());
		return "proajax";
	}

	public void showProduct() {
		String hql = "select * from wxpt" + super.getCookieByEnterID()
				+ ".product where 1=1";
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page); // 当前第几页 //每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "5"
				: rows);// 每页显示条数
		int start = (intPage - 1) * number;
		if (proName != null && !proName.equals("")) {
			hql += " and product_name like '%" + proName + "%'";
		}
		if (proNum != null && !proNum.equals("")) {
			hql += " and product_num = '" + proNum + "'";
		}
		if (proTime != null && !proTime.equals("")) {
			hql += " and product_brand like '%" + proTime + "%'";
		}

		listProduct = manageService.getProductList(super.getCookieByEnterID(),
				hql, start, number);
		count = manageService.getCount(super.getCookieByEnterID(), hql);
		productPojos = new ArrayList<productPojo>();
		for (int i = 0; i < listProduct.size(); i++) {
			productPojo = new productPojo();
			productPojo.setProductNum(listProduct.get(i).getProductNum());
			productPojo.setInventoryNum(listProduct.get(i).getInventoryNum());
			productPojo.setProductBrand(listProduct.get(i).getProductBrand());
			productPojo.setProductAddtime(listProduct.get(i)
					.getProductAddtime());
			productPojo.setProductUpdateTime(listProduct.get(i)
					.getProductUpdateTime());
			productPojo.setProductColor(listProduct.get(i).getProductColor());
			productPojo.setProductDetails(listProduct.get(i)
					.getProductDetails());
			productPojo.setProductOverview(listProduct.get(i)
					.getProductOverview());
			String strImg = listProduct.get(i).getProductDimage();
			String Img[] = strImg.split(";");
			productPojo.setProductDimage(Img[0]);
			productPojo.setProductXimage(listProduct.get(i).getProductXimage());
			productPojo.setProductName(listProduct.get(i).getProductName());
			productPojo.setProductId(listProduct.get(i).getProductId());
			productPojo.setProductRecomme(listProduct.get(i)
					.getProductRecomme());
			productPojo.setSellNum(listProduct.get(i).getSellNum());
			productPojo.setPrice(listProduct.get(i).getPrice());
			productPojo.setCheapPrice(listProduct.get(i).getCheapPrice());
			String sql = "SELECT * FROM wxpt" + super.getCookieByEnterID()
					+ ".product_type WHERE `product_type_id`="
					+ listProduct.get(i).getProductType().getProductTypeId();

			productTypeService.productTypeObject(sql);

			manageService.getByIdType(productTypeService.productTypeObject(sql)
					.getParentId(), super.getCookieByEnterID());

			productPojo.setDtype(manageService.getByIdType(
					listProduct.get(i).getProductType().getParentId(),
					super.getCookieByEnterID()).getTypeName());
			productPojo.setXtype(listProduct.get(i).getProductType()
					.getTypeName());
			productPojo.setXtypeId(listProduct.get(i).getProductType()
					.getProductTypeId());
			productPojo
					.setImg("<img  width='80' height='80' src=\"/wxpt/web/images/"
							+ listProduct.get(i).getProductXimage() + "\"/");
			productPojos.add(productPojo);
		}
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.setExcludes(new String[] { "product", "productType",
					"shoppingCars", "appraises" });
			jsonls = JSONArray.fromObject(productPojos, jsonConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.out.print("{\"total\":" + count + ",\"rows\":" + jsonls + "}");
		super.out.flush();
		super.out.close();
	}

	// 商品类别显示
	public String selectType() {
		product = manageService.getByIdProduct(Integer.parseInt(productId),
				super.getCookieByEnterID());
		int id = product.getProductType().getProductTypeId();
		String sql = "SELECT * FROM wxpt" + super.getCookieByEnterID()
				+ ".product_type WHERE `product_type_id`=" + id;
		productType = productTypeService.productTypeObject(sql);
		productTypes = productType.getTypeName();
		return "selecttype";
	}

	// 商品删除
	public void delProduct() {
		String ids[] = productId.split(",");
		for (int i = 0; i < ids.length; i++) {
			bean.deletefile(manageService.getByIdProduct(
					Integer.parseInt(ids[i]), super.getCookieByEnterID())
					.getProductXimage());
			bean.deletefile2(manageService.getByIdProduct(
					Integer.parseInt(ids[i]), super.getCookieByEnterID())
					.getProductDimage());
			manageService.delProduct(Integer.parseInt(ids[i]),
					super.getCookieByEnterID());
		}

	}

	// 上传大图
	public void uploadShopPics() {
		String value = "";
		try {
			System.out.println(temp);

			fileNames = new StringBuffer().append(
					new SimpleDateFormat("yyyyMMddss").format(new Date())
							+ "_dshop_" + temp + ".jpg").toString();
			if (dpic1 != null) {
				bean.upLoad(fileNames, dpic1);
				list.add(fileNames);
				value = "1";
			} else {
				value = "0";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {

			try {
				HttpServletResponse hs = ServletActionContext.getResponse();
				hs.setContentType("text/html;charset=utf-8");
				PrintWriter out = hs.getWriter();
				out.print(value);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// 上传大图
	public void uploadShopPics1() {
		String value = "";
		try {
			System.out.println(temp);

			fileNames = new StringBuffer().append(
					new SimpleDateFormat("yyyyMMddss").format(new Date())
							+ "_dshop_" + temp + ".jpg").toString();
			if (dpic != null) {
				bean.upLoad(fileNames, dpic);
				list.add(fileNames);
				value = "1";
			} else {
				value = "0";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			value = "-1";
		} finally {

			try {
				HttpServletResponse hs = ServletActionContext.getResponse();
				hs.setContentType("text/html;charset=utf-8");
				PrintWriter out = hs.getWriter();
				out.print(value);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static String addDate(String dateStr, int Month) {
		Calendar ca = Calendar.getInstance();
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date dd;
		try {
			dd = sdf.parse(dateStr);
			ca.setTime(dd);
			ca.add(Calendar.MONTH, Month);
			s = sdf.format(ca.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

	public File getXpic() {
		return xpic;
	}

	public void setXpic(File xpic) {
		this.xpic = xpic;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}

	public FileUploadBeanTwo getBean() {
		return bean;
	}

	public void setBean(FileUploadBeanTwo bean) {
		this.bean = bean;
	}

	public List<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public JSONArray getJsonls() {
		return jsonls;
	}

	public void setJsonls(JSONArray jsonls) {
		this.jsonls = jsonls;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProNum() {
		return proNum;
	}

	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

	public String getProTime() {
		return proTime;
	}

	public void setProTime(String proTime) {
		this.proTime = proTime;
	}

	public List<ProductType> getListType() {
		return listType;
	}

	public void setListType(List<ProductType> listType) {
		this.listType = listType;
	}

	public List<ProductType> getListType1() {
		return listType1;
	}

	public void setListType1(List<ProductType> listType1) {
		this.listType1 = listType1;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getProductTypes() {
		return productTypes;
	}

	public void setProductTypes(String productTypes) {
		this.productTypes = productTypes;
	}

	public List<productPojo> getProductPojos() {
		return productPojos;
	}

	public void setProductPojos(List<productPojo> productPojos) {
		this.productPojos = productPojos;
	}

	public productPojo getProductPojo() {
		return productPojo;
	}

	public void setProductPojo(productPojo productPojo) {
		this.productPojo = productPojo;
	}

	public List<ProductType> getListTypes() {
		return listTypes;
	}

	public void setListTypes(List<ProductType> listTypes) {
		this.listTypes = listTypes;
	}

	public File getXpic1() {
		return xpic1;
	}

	public void setXpic1(File xpic1) {
		this.xpic1 = xpic1;
	}

	public File getDpic1() {
		return dpic1;
	}

	public void setDpic1(File dpic1) {
		this.dpic1 = dpic1;
	}

	public static List<String> getList() {
		return list;
	}

	public static void setList(List<String> list) {
		ManageAction.list = list;
	}

	public String getTemp() {
		return temp;
	}

	public String zontTongji(){
		return "tongjiStatistics";
	}
	
	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public File getDpic() {
		return dpic;
	}

	public void setDpic(File dpic) {
		this.dpic = dpic;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd1() {
		return pwd1;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public String getPwd2() {
		return pwd2;
	}

	public void setPwd2(String pwd2) {
		this.pwd2 = pwd2;
	}

	public File getBanner() {
		return banner;
	}

	public void setBanner(File banner) {
		this.banner = banner;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isPeizhi() {
		return peizhi;
	}

	public void setPeizhi(boolean peizhi) {
		this.peizhi = peizhi;
	}

	public boolean isCaidan() {
		return caidan;
	}

	public void setCaidan(boolean caidan) {
		this.caidan = caidan;
	}

	public boolean isGuanjianzi() {
		return guanjianzi;
	}

	public void setGuanjianzi(boolean guanjianzi) {
		this.guanjianzi = guanjianzi;
	}

	public boolean isHuifu() {
		return huifu;
	}

	public boolean isDiaoyan() {
		return diaoyan;
	}

	public void setDiaoyan(boolean diaoyan) {
		this.diaoyan = diaoyan;
	}

	public void setHuifu(boolean huifu) {
		this.huifu = huifu;
	}

	public boolean isHuodong() {
		return huodong;
	}

	public void setHuodong(boolean huodong) {
		this.huodong = huodong;
	}

	public boolean isToupiao() {
		return toupiao;
	}

	public void setToupiao(boolean toupiao) {
		this.toupiao = toupiao;
	}

	public boolean isDati() {
		return dati;
	}

	public void setDati(boolean dati) {
		this.dati = dati;
	}

	public boolean isQiandao() {
		return qiandao;
	}

	public void setQiandao(boolean qiandao) {
		this.qiandao = qiandao;
	}

	public boolean isHuiyuan() {
		return huiyuan;
	}

	public void setHuiyuan(boolean huiyuan) {
		this.huiyuan = huiyuan;
	}

	public boolean isQuyu() {
		return quyu;
	}

	public void setQuyu(boolean quyu) {
		this.quyu = quyu;
	}

	public int getEnterId() {
		return enterId;
	}

	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}

	public boolean isWangzhan() {
		return wangzhan;
	}

	public void setWangzhan(boolean wangzhan) {
		this.wangzhan = wangzhan;
	}

	public boolean isGuaguale() {
		return guaguale;
	}

	public void setGuaguale(boolean guaguale) {
		this.guaguale = guaguale;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ManageUser getUser() {
		return user;
	}

	public void setUser(ManageUser user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isGuanli() {
		return guanli;
	}

	public void setGuanli(boolean guanli) {
		this.guanli = guanli;
	}

	public boolean isQuanxian() {
		return quanxian;
	}

	public void setQuanxian(boolean quanxian) {
		this.quanxian = quanxian;
	}

	public boolean isTongji() {
		return tongji;
	}

	public void setTongji(boolean tongji) {
		this.tongji = tongji;
	}

	public boolean isEnterp() {
		return enterp;
	}

	public void setEnterp(boolean enterp) {
		this.enterp = enterp;
	}

	public int getEnterType() {
		return enterType;
	}

	public void setEnterType(int enterType) {
		this.enterType = enterType;
	}

	public boolean isVshop() {
		return vshop;
	}

	public void setVshop(boolean vshop) {
		this.vshop = vshop;
	}

	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}

	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public ManageDao getManageDao() {
		return manageDao;
	}

	public void setManageDao(ManageDao manageDao) {
		this.manageDao = manageDao;
	}

	public List<EnterInfor> getEnterList() {
		return enterList;
	}

	public void setEnterList(List<EnterInfor> enterList) {
		this.enterList = enterList;
	}

	public int getEnId() {
		return enId;
	}

	public void setEnId(int enId) {
		this.enId = enId;
	}

	public boolean isQy() {
		return qy;
	}

	public void setQy(boolean qy) {
		this.qy = qy;
	}

	public boolean isQygl() {
		return qygl;
	}

	public void setQygl(boolean qygl) {
		this.qygl = qygl;
	}

	public boolean isSheying() {
		return sheying;
	}

	public void setSheying(boolean sheying) {
		this.sheying = sheying;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public boolean isTiyan() {
		return tiyan;
	}

	public void setTiyan(boolean tiyan) {
		this.tiyan = tiyan;
	}

	public EnterInfor getEnters() {
		return enters;
	}

	public void setEnters(EnterInfor enters) {
		this.enters = enters;
	}

	public boolean isHeka() {
		return heka;
	}

	public void setHeka(boolean heka) {
		this.heka = heka;
	}

	public String getTianqi() {
		return tianqi;
	}

	public void setTianqi(String tianqi) {
		this.tianqi = tianqi;
	}

	public String getKongqi() {
		return kongqi;
	}

	public void setKongqi(String kongqi) {
		this.kongqi = kongqi;
	}

	public String getShouji() {
		return shouji;
	}

	public void setShouji(String shouji) {
		this.shouji = shouji;
	}

	public String getKuaidi() {
		return kuaidi;
	}

	public void setKuaidi(String kuaidi) {
		this.kuaidi = kuaidi;
	}

	public String getHuoche() {
		return huoche;
	}

	public void setHuoche(String huoche) {
		this.huoche = huoche;
	}

	public String getDaohang() {
		return daohang;
	}

	public void setDaohang(String daohang) {
		this.daohang = daohang;
	}

	public String getZhoubian() {
		return zhoubian;
	}

	public void setZhoubian(String zhoubian) {
		this.zhoubian = zhoubian;
	}

	public String getGongjiao() {
		return gongjiao;
	}

	public void setGongjiao(String gongjiao) {
		this.gongjiao = gongjiao;
	}

	public String getChengyu() {
		return chengyu;
	}

	public void setChengyu(String chengyu) {
		this.chengyu = chengyu;
	}

	public String getShenfen() {
		return shenfen;
	}

	public void setShenfen(String shenfen) {
		this.shenfen = shenfen;
	}

	public String getShengao() {
		return shengao;
	}

	public void setShengao(String shengao) {
		this.shengao = shengao;
	}

	public List<Shiyong> getShiList() {
		return shiList;
	}

	public void setShiList(List<Shiyong> shiList) {
		this.shiList = shiList;
	}

	public String getShiyi() {
		return shiyi;
	}

	public void setShiyi(String shiyi) {
		this.shiyi = shiyi;
	}
	private String name;
	private String pwd;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String test(){
		try {
			if (enterId == 4 && name.equals("adminadmin")
					&& pwd.equals("adminadmin")) {
				//if(enterId == 4){	

				Cookie wxpts = new Cookie("wxpts", "qq627955224" + ":" + "4"
						+ ":" + enterId);
				wxpts.setMaxAge(60 * 60 * 24 * 365);
				//wxpts.setMaxAge(Integer.MAX_VALUE);
				wxpts.setPath("/");
				ServletActionContext.getResponse().addCookie(wxpts);
				return "index2";
			} else {
				return "index3";
			}
		} catch (Exception e) {
			// TODO: handle exception
			return "index3";
		}
		
	}
}