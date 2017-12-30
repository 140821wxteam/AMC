package com.amc.model.models;

import java.sql.Date;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class PurchaseOrder extends EnableEntity<Integer> implements ICUDEable{
	
	private String orderId;
	private Date orderDate;
	private String vendorId;
	private String vendorName;
	private double totalPrice;
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
	public void settotalPrice(double totalPrice){
		this.totalPrice=totalPrice;
	}
	public double gettotalPrice(){
		return this.totalPrice;
	}
	public void setstatus(String status){
		this.status=status;
	}
	public String getstatus(){
		return this.status;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}


}
