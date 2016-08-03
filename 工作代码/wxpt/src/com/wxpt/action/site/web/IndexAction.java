package com.wxpt.action.site.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.wxpt.common.GetCurrentUser;
import com.wxpt.common.SavaCookie;
import com.wxpt.site.entity.Product;
import com.wxpt.site.entity.ProductType;
import com.wxpt.site.service.ProductService;
import com.wxpt.site.service.ProductTypeService;
@Results({
	@Result(name="index", location="/web/index.jsp"),
})
public class IndexAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	ProductService productService;
	ProductTypeService productTypeService;
	private List<Product> productList;
	private String sql;
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
	private int memberId;
	private String memberName;
	private List<ProductType> protTypeList;
	private int enterId;//
	
	public String execute(){
		if(memberId != 0 || memberName != null){
			String name = null;
			try {
				name = URLDecoder.decode(memberName, "gb2312");
				name=new String(memberName.getBytes("ISO-8859-1"),"GBK");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SavaCookie.savaCookie(memberId, name,enterId);
		}
		/*SavaCookie.savaCookie(1, "",1);*/
		recomProduct = new ArrayList<Product>();
		sql = "select * from wxpt"+enterId+".product where product_recomme ='已推荐' order by product_addtime desc";
		startIndex = (page-1)*pageSize;
		recomProduct = productService.getProductByPage(sql, startIndex, pageSize);
		newProduct = new ArrayList<Product>();
		sql = "select  * from wxpt"+enterId+".product  order by str_to_date(product_update_time,'%Y/%m/%d %H:%i:%s') desc";
		startIndex = (page-1)*pageSize;;
		newProduct = productService.getProductByPage(sql, startIndex, pageSize);
		protTypeList = new ArrayList<ProductType>();
		sql = "select * from wxpt"+enterId+".product_type ";
		protTypeList = productTypeService.getProductTypeByPage(sql, 0, 8);
		return "index";
	}
	
	
	
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}



	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}



	public int getEnterId() {
		return enterId;
	}



	public void setEnterId(int enterId) {
		this.enterId = enterId;
	}



	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	public int getVipId() {
		return vipId;
	}
	public void setVipId(int vipId) {
		this.vipId = vipId;
	}
	public List<ProductType> getProtTypeList() {
		return protTypeList;
	}
	public void setProtTypeList(List<ProductType> protTypeList) {
		this.protTypeList = protTypeList;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
