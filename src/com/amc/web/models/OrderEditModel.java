package com.amc.web.models;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

public class OrderEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String orderId;
	private Calendar createTime;
	private String customerId;
	private Double totalPrice;
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
