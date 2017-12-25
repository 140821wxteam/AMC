package com.amc.web.models;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

public class InvoiceEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String invoiceId;
	private Calendar createTime;
	private String factoryId;
	private Double sumPrice;
	private String status;
	private String note;
	
	public void setinvoiceId(String invoiceId){
		this.invoiceId=invoiceId;
	}
	public String getinvoiceId(){
		return this.invoiceId;
	}
	public void setcreateTime(Calendar createTime){
		this.createTime=createTime;
	}
	public Calendar getcreateTime(){
		return this.createTime;
	}
	public void setfactoryId(String factoryId){
		this.factoryId=factoryId;
	}
	public String getfactoryId(){
		return this.factoryId;
	}
	public void setsumPrice(Double sumPrice){
		this.sumPrice=sumPrice;
	}
	public Double getsumPrice(){
		return this.sumPrice;
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
