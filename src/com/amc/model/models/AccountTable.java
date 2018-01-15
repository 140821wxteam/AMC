package com.amc.model.models;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class AccountTable extends EnableEntity<Integer> implements ICUDEable{
//账目表
	private String accounttableId;
	private String orderId;
	private String deliverId;
	private String cuikuanId;
	private String invoiceId;
	private String customerId;
	private int objection;
	private double receivable;
	private double salesBusiness;
	private double payable;
	private double purchaseBusiness;
	public String getAccounttableId() {
		return accounttableId;
	}
	public void setAccounttableId(String accounttableId) {
		this.accounttableId = accounttableId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDeliverId() {
		return deliverId;
	}
	public void setDeliverId(String deliverId) {
		this.deliverId = deliverId;
	}
	public String getCuikuanId() {
		return cuikuanId;
	}
	public void setCuikuanId(String cuikuanId) {
		this.cuikuanId = cuikuanId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getObjection() {
		return objection;
	}
	public void setObjection(int objection) {
		this.objection = objection;
	}
	public double getReceivable() {
		return receivable;
	}
	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}
	public double getSalesBusiness() {
		return salesBusiness;
	}
	public void setSalesBusiness(double salesBusiness) {
		this.salesBusiness = salesBusiness;
	}
	public double getPayable() {
		return payable;
	}
	public void setPayable(double payable) {
		this.payable = payable;
	}
	public double getPurchaseBusiness() {
		return purchaseBusiness;
	}
	public void setPurchaseBusiness(double purchaseBusiness) {
		this.purchaseBusiness = purchaseBusiness;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	
}
