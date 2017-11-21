package com.amc.model.models;

import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Order extends EnableEntity<Integer> implements ICUDEable{

	private String orderId;
	private Calendar createTime;
	private String customerId;
	private double totalPrice;
	private String status;
	private String note;
	
	public void setorderId(String orderId){
		this.orderId=orderId;
	}
	public String getorderId(){
		return this.orderId;
	}
	public void setcreateTime(Calendar createTime){
		this.createTime=createTime;
	}
	public Calendar getcreateTime(){
		return this.createTime;
	}
	public void setcustomerId(String customerId){
		this.customerId=customerId;
	}
	public String getcustomerId(){
		return this.customerId;
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
