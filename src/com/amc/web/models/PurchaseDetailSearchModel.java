package com.amc.web.models;

public class PurchaseDetailSearchModel {

	private String orderId;
	private String productId;
	private String productName;
	public String getorderId() {
		return this.orderId;
	}
	public void setorderId(String orderId) {
		this.orderId = orderId;
	}	
	public String getproductId() {
		return this.productId;
	}
	public void setproductId(String productId) {
		this.productId = productId;
	}
	public String getproductName() {
		return this.productName;
	}
	public void setproductName(String productName) {
		this.productName = productName;
	}
}
