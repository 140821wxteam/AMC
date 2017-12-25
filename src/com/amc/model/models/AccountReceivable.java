package com.amc.model.models;

import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class AccountReceivable extends EnableEntity<Integer> implements ICUDEable{
//应收账，属性和发票是一样的，但是不需要有发票明细了，只需要绑定发票号码即可
	private String invoiceId;
	private Calendar createTime;
	private String factoryId;
	private String accountreceivableId;
	private double sumPrice;
	private String status;
	private String note;
	
	public void setaccountreceivableId(String accountreceivableId){
		this.accountreceivableId=accountreceivableId;
	}
	public String getaccountreceivableId(){
		return this.accountreceivableId;
	}
	
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
