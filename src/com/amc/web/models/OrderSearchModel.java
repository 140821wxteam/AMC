package com.amc.web.models;

public class OrderSearchModel {

	private String orderId;
	private String customerId;
	private String status;
	public String getorderId() {
		return this.orderId;
	}
	public void setorderId(String orderId) {
		this.orderId = orderId;
	}
	public String getcustomerId() {
		return this.customerId;
	}
	public void setcustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getstatus() {
		return this.status;
	}
	public void setstatus(String status) {
		this.status = status;
	}
	
}
