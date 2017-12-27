package com.amc.model.models;

import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Deliver extends EnableEntity<Integer> implements ICUDEable{
//发货单，不包括明细
	private String deliverId;
	private String prepareId;
	private String customerId;
	private String orderId;
	private String deliverDetailId;
	private Calendar createTime;
	private String receivePers;
	private String receiveAddr;
	private double amountMoney;
	private String remark;
	private String status;
	
	public String getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}
	public String getPrepareId() {
		return prepareId;
	}
	public void setPrepareId(String prepareId) {
		this.prepareId = prepareId;
	}
	public String getDeliverDetailId() {
		return deliverDetailId;
	}
	public void setDeliverDetailId(String deliverDetailId) {
		this.deliverDetailId = deliverDetailId;
	}
	public Calendar getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
	public double getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(double amountMoney) {
		this.amountMoney = amountMoney;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getReceivePers() {
		return receivePers;
	}
	public void setReceivePers(String receivePers) {
		this.receivePers = receivePers;
	}
	public String getReceiveAddr() {
		return receiveAddr;
	}
	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
