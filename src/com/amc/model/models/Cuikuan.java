package com.amc.model.models;

import java.util.Date;
import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Cuikuan extends EnableEntity<Integer> implements ICUDEable{
//催款单
	private String invoiceId;
	private String cuikuanId;
	private String deliverId;
	private int cuikuanObjection;
	private String customerId;
	private String orderId;
	private Calendar orderReceiveDate;
	private double amountMoney;
	private String orderdetailid;
	private Calendar createTime;
	private String remark;
	private String status;
	public String getCuikuanId() {
		return cuikuanId;
	}
	public void setCuikuanId(String cuikuanId) {
		this.cuikuanId = cuikuanId;
	}
	public String getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}
	public int getCuikuanObjection() {
		return cuikuanObjection;
	}
	public void setCuikuanObjection(int cuikuanObjection) {
		this.cuikuanObjection = cuikuanObjection;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Calendar getOrderReceiveDate() {
		return orderReceiveDate;
	}
	public void setOrderReceiveDate(Calendar orderReceiveDate) {
		this.orderReceiveDate = orderReceiveDate;
	}
	public double getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(double amountMoney) {
		this.amountMoney = amountMoney;
	}
	public String getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	
}
