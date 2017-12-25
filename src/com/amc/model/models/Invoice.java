package com.amc.model.models;

import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Invoice extends EnableEntity<Integer> implements ICUDEable{
//发票
	private String invoiceId;
	private Calendar createTime;
	private String factoryId;
	
	private double sumPrice;
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
	public void setsumPrice(double sumPrice){
		this.sumPrice=sumPrice;
	}
	public double getsumPrice(){
		return this.sumPrice;
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
