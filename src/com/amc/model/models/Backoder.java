package com.amc.model.models;

import java.util.Date;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Backoder extends EnableEntity<Integer> implements ICUDEable{
	private String backoderId;
	private String productId;
	private String productName;
	private String factoryId;
	private String size;
	private int amount;
	private Date date;
	private String note;

	public void setbackoderId(String backoderId){
		this.backoderId=backoderId;
	}
	public String getbackoderId(){
		return this.backoderId;
	}
	public void setproductId(String productId){
		this.productId=productId;
	}
	public String getproductId(){
		return this.productId;
	}
	public void setproductName(String productName){
		this.productName=productName;
	}
	public String getproductName(){
		return this.productName;
	}
	public void setfactoryId(String factoryId){
		this.factoryId=factoryId;
	}
	public String getfactoryId(){
		return this.factoryId;
	}
	public String getsize(){
		return this.size;
	}
	public void setsize(String size){
		this.size=size;
	}
	public void setamount(int amount){
		this.amount=amount;
	}
	public int getamount(){
		return this.amount;
	}
	public Date getdate(){
		return this.date;
	}
	public void setdate(Date date){
		this.date=date;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
	
	
}
