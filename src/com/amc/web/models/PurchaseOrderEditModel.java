package com.amc.web.models;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class PurchaseOrderEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String orderId;
	private Date orderDate;
	private String vendorId;
	private String vendorName;
	private Double totalPrice;
	private String status;
	private String note;
	
	public void setorderId(String orderId){
		this.orderId=orderId;
	}
	public String getorderId(){
		return this.orderId;
	}
	public void setorderDate(Date orderDate){
		this.orderDate=orderDate;
	}
	public Date getorderDate(){
		return this.orderDate;
	}
	public void setvendorId(String vendorId){
		this.vendorId=vendorId;
	}
	public String getvendorId(){
		return this.vendorId;
	}
	public void setvendorName(String vendorName){
		this.vendorName=vendorName;
	}
	public String getvendorName(){
		return this.vendorName;
	}
	public void settotalPrice(Double totalPrice){
		this.totalPrice=totalPrice;
	}
	public Double gettotalPrice(){
		return this.totalPrice;
	}
	public String getstatus() {
		return this.status;
	}
	public void setstatus(String status) {
		this.status = status;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
}
