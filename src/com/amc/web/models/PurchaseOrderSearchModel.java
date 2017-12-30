package com.amc.web.models;

public class PurchaseOrderSearchModel {

	private String orderId;
	private String vendorName;
	private String status;
	public String getorderId() {
		return this.orderId;
	}
	public void setorderId(String orderId) {
		this.orderId = orderId;
	}
	public String getvendorName() {
		return this.vendorName;
	}
	public void setvendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getstatus() {
		return this.status;
	}
	public void setstatus(String status) {
		this.status = status;
	}
	
}
