package com.amc.web.jsonmodels;


import java.util.Date;
import java.util.List;


public class OrderdetailJson {
	
	private String productId;
	private String productName;
	private String orderdetailId;
	private String orderId;
	private String createTime;
	private	int saleLevel;
	//private int safestock;
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
	public String getOrderdetailId() {
		return orderdetailId;
	}
	public void setOrderdetailId(String orderdetailId) {
		this.orderdetailId = orderdetailId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getSaleLevel() {
		return saleLevel;
	}
	public void setSaleLevel(int saleLevel) {
		this.saleLevel = saleLevel;
	}
	
}
