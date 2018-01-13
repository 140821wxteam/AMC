package com.amc.model.models;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class DeliverDetail extends EnableEntity<Integer> implements ICUDEable{
//发货单详细
	private String deliverId;
	private String deliverdetailId;
	private String productId;
	private String productName;
	private String factoryId;
	private int num;
	private int shortNum;
	private String remark;
	public String getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}
	public String getDeliverdetailId() {
		return deliverdetailId;
	}
	public void setDeliverdetailId(String deliverdetailId) {
		this.deliverdetailId = deliverdetailId;
	}
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
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getShortNum() {
		return shortNum;
	}
	public void setShortNum(int shortNum) {
		this.shortNum = shortNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
