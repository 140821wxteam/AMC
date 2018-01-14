package com.amc.web.jsonmodels;



public class PurchaseDetailJson {
	
	private String productId;
	private String productName;
	private String orderdetailId;
	private String orderId;
	private String createTime;

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

	
}
