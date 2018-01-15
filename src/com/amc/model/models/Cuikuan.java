package com.amc.model.models;

import java.util.Date;
import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Cuikuan extends EnableEntity<Integer> implements ICUDEable{
//催款单
	private String cuikuanId;
	private String deliverId;
	private String cuikuanObjection;
	private String customerId;
	private String orderId;
	private Date orderReceiveDate;
	private double amountMoney;
	private String orderdetailid;
	private Calendar createTime;
	private Date receiveDate;
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
	public String getCuikuanObjection() {
		return cuikuanObjection;
	}
	public void setCuikuanObjection(String cuikuanObjection) {
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
	public Date getOrderReceiveDate() {
		return orderReceiveDate;
	}
	public void setOrderReceiveDate(Date orderReceiveDate) {
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
	public Date getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
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
