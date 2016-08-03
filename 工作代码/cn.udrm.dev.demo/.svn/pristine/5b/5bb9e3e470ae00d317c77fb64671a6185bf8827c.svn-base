package cn.udrm.dev.demo.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Product")
@Table(name = "Demo_Product")
public class Product {

	@Id()
	private String productId = null;
	
	@Basic(optional = false)
	private String productName = null;
	
	@Basic(optional = true)
	private int amount = 0;
	
	@Basic(optional = true)
	private double price = 0;

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, optional = true, targetEntity = Order.class)
	@JoinColumns({ @JoinColumn() })
	private Order order = null;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
