package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Integer productId;
	private ProductType productType;
	private String productNum;
	private String productName;
	private String productBrand;
	private Integer inventoryNum;
	private Double price;
	private Double cheapPrice;
	private Integer sellNum;
	private String productDetails;
	private String productOverview;
	private String productColor;
	private String productXimage;
	private String productDimage;
	private String productAddtime;
	private String productRecomme;
	private String productUpdateTime;
	private Set shoppingCars = new HashSet(0);
	private Set buyProducts = new HashSet(0);
	private Set appraises = new HashSet(0);

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(ProductType productType, String productName,
			Integer inventoryNum, Double price, Integer sellNum,
			String productDetails, String productXimage, String productDimage,
			String productAddtime, String productRecomme) {
		this.productType = productType;
		this.productName = productName;
		this.inventoryNum = inventoryNum;
		this.price = price;
		this.sellNum = sellNum;
		this.productDetails = productDetails;
		this.productXimage = productXimage;
		this.productDimage = productDimage;
		this.productAddtime = productAddtime;
		this.productRecomme = productRecomme;
	}

	/** full constructor */
	public Product(ProductType productType, String productNum,
			String productName, String productBrand, Integer inventoryNum,
			Double price, Double cheapPrice, Integer sellNum,
			String productDetails, String productOverview, String productColor,
			String productXimage, String productDimage, String productAddtime,
			String productRecomme, String productUpdateTime, Set shoppingCars,
			Set buyProducts, Set appraises) {
		this.productType = productType;
		this.productNum = productNum;
		this.productName = productName;
		this.productBrand = productBrand;
		this.inventoryNum = inventoryNum;
		this.price = price;
		this.cheapPrice = cheapPrice;
		this.sellNum = sellNum;
		this.productDetails = productDetails;
		this.productOverview = productOverview;
		this.productColor = productColor;
		this.productXimage = productXimage;
		this.productDimage = productDimage;
		this.productAddtime = productAddtime;
		this.productRecomme = productRecomme;
		this.productUpdateTime = productUpdateTime;
		this.shoppingCars = shoppingCars;
		this.buyProducts = buyProducts;
		this.appraises = appraises;
	}

	// Property accessors

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public ProductType getProductType() {
		return this.productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getProductNum() {
		return this.productNum;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return this.productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public Integer getInventoryNum() {
		return this.inventoryNum;
	}

	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCheapPrice() {
		return this.cheapPrice;
	}

	public void setCheapPrice(Double cheapPrice) {
		this.cheapPrice = cheapPrice;
	}

	public Integer getSellNum() {
		return this.sellNum;
	}

	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	public String getProductDetails() {
		return this.productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductOverview() {
		return this.productOverview;
	}

	public void setProductOverview(String productOverview) {
		this.productOverview = productOverview;
	}

	public String getProductColor() {
		return this.productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductXimage() {
		return this.productXimage;
	}

	public void setProductXimage(String productXimage) {
		this.productXimage = productXimage;
	}

	public String getProductDimage() {
		return this.productDimage;
	}

	public void setProductDimage(String productDimage) {
		this.productDimage = productDimage;
	}

	public String getProductAddtime() {
		return this.productAddtime;
	}

	public void setProductAddtime(String productAddtime) {
		this.productAddtime = productAddtime;
	}

	public String getProductRecomme() {
		return this.productRecomme;
	}

	public void setProductRecomme(String productRecomme) {
		this.productRecomme = productRecomme;
	}

	public String getProductUpdateTime() {
		return this.productUpdateTime;
	}

	public void setProductUpdateTime(String productUpdateTime) {
		this.productUpdateTime = productUpdateTime;
	}

	public Set getShoppingCars() {
		return this.shoppingCars;
	}

	public void setShoppingCars(Set shoppingCars) {
		this.shoppingCars = shoppingCars;
	}

	public Set getBuyProducts() {
		return this.buyProducts;
	}

	public void setBuyProducts(Set buyProducts) {
		this.buyProducts = buyProducts;
	}

	public Set getAppraises() {
		return this.appraises;
	}

	public void setAppraises(Set appraises) {
		this.appraises = appraises;
	}

}