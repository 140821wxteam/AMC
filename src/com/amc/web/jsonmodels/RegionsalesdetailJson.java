package com.amc.web.jsonmodels;


import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class RegionsalesdetailJson {
	
	private String province;
	private double salesamount;
	private String customerId;
	private Calendar orderTime;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public double getSalesamount() {
		return salesamount;
	}
	public void setSalesamount(double salesamount) {
		this.salesamount = salesamount;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Calendar getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Calendar orderTime) {
		this.orderTime = orderTime;
	}
	
}
