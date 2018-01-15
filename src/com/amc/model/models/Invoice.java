package com.amc.model.models;

import java.util.Calendar;
import java.util.Date;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Invoice extends EnableEntity<Integer> implements ICUDEable{
//发票
	private String invoiceId;
	private int objection;
	private String orderId;
	private Calendar orderReceiveDate;
	private double amountMoney;
	private Calendar createTime;
	private String remark;
	private String status;
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public int getObjection() {
		return objection;
	}
	public void setObjection(int objection) {
		this.objection = objection;
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
	
}
