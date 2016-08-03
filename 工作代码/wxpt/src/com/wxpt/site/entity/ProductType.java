package com.wxpt.site.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * ProductType entity. @author MyEclipse Persistence Tools
 */

public class ProductType implements java.io.Serializable {

	// Fields

	private Integer productTypeId;
	private Integer parentId;
	private String typeName;
	private Set products = new HashSet(0);

	// Constructors

	/** default constructor */
	public ProductType() {
	}

	/** minimal constructor */
	public ProductType(Integer parentId, String typeName) {
		this.parentId = parentId;
		this.typeName = typeName;
	}

	/** full constructor */
	public ProductType(Integer parentId, String typeName, Set products) {
		this.parentId = parentId;
		this.typeName = typeName;
		this.products = products;
	}

	// Property accessors

	public Integer getProductTypeId() {
		return this.productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Set getProducts() {
		return this.products;
	}

	public void setProducts(Set products) {
		this.products = products;
	}

}