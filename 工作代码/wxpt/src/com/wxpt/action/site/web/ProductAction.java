package com.wxpt.action.site.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.site.entity.Member;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.entity.ShoppingCar;
import com.wxpt.site.entity.pojo.ShopCarShow;
import com.wxpt.site.service.AppraiseService;
import com.wxpt.site.service.MemberService;
import com.wxpt.site.service.ProductService;
import com.wxpt.site.service.ProductTypeService;

@Results({
		@Result(name="product", location="/web/product.jsp"),
		@Result(name="maxtype", location="/web/maxtype.jsp"),
		@Result(name="mintype", location="/web/mintype.jsp"),
		@Result(name="typePro", location="/web/typepro.jsp"),
		@Result(name = "car", type = "json", params = { "root", "result" }),	
//		@Result(name="mslist"  , type="json"  , params = { "root", "result" }) 
	})
@ParentPackage("json-default") 
public class ProductAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	ProductService productService;
	@Autowired
	ProductTypeService productTypeService;
	@Autowired
	AppraiseService appraiseservice;
	@Autowired
	MemberService memberService;
	private List<Product> productList;
	private String hql;
	private int page = 1;
	private int pageSize = 5;
	private int startIndex;
	private List<Product> recomProduct;
	private List<Product> newProduct;
	private int productId;
	private Product product;
	private int listCount = 0;
	private int pageCount = 0;
	private int typeId = 1;
	private int vipId;
	private int enterId;
	private List<ProductType> protTypeList;
	private List<String> urlList;
	private ProductType productType;
	private ShoppingCar shopCar;
	private Member member;
	private int appNum;
	private int productSum = 1;
	protected JSONArray jsonls;
	protected PrintWriter out = null;
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	
	public ProductAction(){
		vipId = GetCurrentUser.getUserID();
		enterId=GetCurrentUser.getEnterId();
		if(vipId == 0||enterId==0){
			System.out.println("cookie错误！");
		}
	}
	public String getPro(){
		hql = "select * from wxpt"+enterId+".product where product_id = "+productId;
		productList = new ArrayList<Product>();
		productList = productService.getProduct(hql);
		if(productList.size()>0){
			product = productList.get(0);
		}
		hql = "select * from wxpt"+enterId+".appraise where product_id = "+productId;
		appNum = appraiseservice.getNumber(hql);
		urlList = new ArrayList<String>();
		String []str = product.getProductDimage().split(";");
		for (int i = 0; i < str.length; i++) {
			urlList.add(str[i].toString());
		}
//		protTypeList = new ArrayList<ProductType>();
//		hql = "from ProductType ";
//		protTypeList = productTypeService.getProductTypeByPage(hql, 0, 8);
		return "product";
	}
	public String getTypePro(){
		hql = "select * from wxpt"+enterId+".product where product_type_id = "+typeId;
		productList = new ArrayList<Product>();
		pageSize = 8;
		startIndex = (page-1)*pageSize;
		listCount = productService.getProductNum(hql);
		if (listCount % pageSize == 0) {
			pageCount = listCount / pageSize;
		}
		else {
			pageCount = listCount / pageSize + 1;
		}		
		productList = productService.getProductByPage(hql, startIndex, pageSize);
		hql = "select * from wxpt"+enterId+".product_type where product_type_id = "+typeId;
		productType = new ProductType();
		try {
			productType = productTypeService.getProductTypeByPage(hql, 0, 1).get(0);
		} catch (Exception e) {
			System.out.println("查询商品分类错误！");
			productType = null;
		}
//		protTypeList = new ArrayList<ProductType>();
//		hql = "from ProductType ";
//		protTypeList = productTypeService.getProductTypeByPage(hql, 0, 8);
		return "typePro";
	}
	public String getMaxType(){
		hql = "select * from wxpt"+enterId+".product_type where parent_id = 0";
		pageSize = 8;
		startIndex = (page-1)*pageSize;
		listCount = productTypeService.getTypeNum(hql);
		if (listCount % pageSize == 0) {
			pageCount = listCount / pageSize;
		}
		else {
			pageCount = listCount / pageSize + 1;
		}		
		protTypeList = productTypeService.getProductTypeByPage(hql, startIndex, pageSize);
		return "maxtype";
	}
	public String getMinType(){
		hql = "select * from wxpt"+enterId+".product_type where parent_id = "+typeId;
		pageSize = 8;
		startIndex = (page-1)*pageSize;
		listCount = productTypeService.getTypeNum(hql);
		if (listCount % pageSize == 0) {
			pageCount = listCount / pageSize;
		}
		else {
			pageCount = listCount / pageSize + 1;
		}		
		protTypeList = productTypeService.getProductTypeByPage(hql, startIndex, pageSize);
		hql = "select * from wxpt"+enterId+".product_type where product_type_id = "+typeId;
		productType = new ProductType();
		try {
			productType = productTypeService.getProductTypeByPage(hql, 0, 1).get(0);
		} catch (Exception e) {
			System.out.println("查询商品分类错误！");
			productType = null;
		}
		return "mintype";
	}
	
	//加入购物车，输出1开头数字为保存失败，输出2开头数字为购物车内已存在，输出3开头数字为加入成功
	//开头数字后所跟数字为购物车商品数量
	public String saveCar(){
		shopCar = new ShoppingCar();
		member = new Member();
		product = new Product();
		ShopCarShow scs = new ShopCarShow();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			out = response.getWriter();
		} catch (IOException e) {

		}
		hql = "select * from wxpt"+enterId+".shopping_car where member_id="+vipId+" and product_id="+productId;
		int i = productService.getShopCarById(hql);
		hql = "select * from wxpt"+enterId+".shopping_car where member_id="+vipId;
		int shopsum = productService.getShopCarById(hql);
		if(i == 0){
			try {
				member = memberService.getOne(vipId, enterId);
			} catch (Exception e) {
				System.out.println("获取会员信息错误");
				member = null;
			}
			hql = "select * from wxpt"+enterId+".product where product_id = "+productId;
			try {
				product = productService.getProduct(hql).get(0);
			} catch (Exception e) {
				System.out.println("获取商品信息错误");
				product = null;
			}
			shopCar.setMember(member);
			shopCar.setProduct(product);
			shopCar.setProductSum(productSum);
			int j = 0;
			try {
				j = productService.saveShopCar(shopCar,enterId);
			} catch (Exception e) {
				j = 0;
			}
			if(j == 0){
				scs.setShow("1");
				scs.setShopSum(shopsum);
			}else{
				scs.setShow("3");
				scs.setShopSum(shopsum);
			}
		}else{
			scs.setShow("2");
			scs.setShopSum(shopsum);
		}
		jsonls = JSONArray.fromObject(scs);
		System.out.println(jsonls);
		out.print(jsonls);
		out.flush();
		out.close();
		return "car";
	}
	
	public int getVipId() {
		return vipId;
	}
	public void setVipId(int vipId) {
		this.vipId = vipId;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public List<Product> getRecomProduct() {
		return recomProduct;
	}
	public void setRecomProduct(List<Product> recomProduct) {
		this.recomProduct = recomProduct;
	}
	public List<Product> getNewProduct() {
		return newProduct;
	}
	public void setNewProduct(List<Product> newProduct) {
		this.newProduct = newProduct;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<ProductType> getProtTypeList() {
		return protTypeList;
	}
	public void setProtTypeList(List<ProductType> protTypeList) {
		this.protTypeList = protTypeList;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public List<String> getUrlList() {
		return urlList;
	}
	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
	public int getProductSum() {
		return productSum;
	}
	public void setProductSum(int productSum) {
		this.productSum = productSum;
	}
	public int getAppNum() {
		return appNum;
	}
	public void setAppNum(int appNum) {
		this.appNum = appNum;
	}
	
}
