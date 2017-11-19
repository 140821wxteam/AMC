package com.amc.model.models;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Customers  extends EnableEntity<Integer> implements ICUDEable{
	private String customerId;
	private String customerName;
	private String customerAddr;
	private String contactPerson;
	private String customerTele;
	private String customerEmail;
	private String note;
	
	public void setcustomerId(String customerId){
		this.customerId=customerId;
	}
	public String getcustomerId(){
		return this.customerId;
	}
	public void setcustomerName(String customerName){
		this.customerName=customerName;
	}
	public String getcustomerName(){
		return this.customerName;
	}
	public void setcustomerAddr(String customerAddr){
		this.customerAddr=customerAddr;
	}
	public String getcustomerAddr(){
		return this.customerAddr;
	}
	public void setcontactPerson(String contactPerson){
		this.contactPerson=contactPerson;
	}
	public String getcontactPerson(){
		return this.contactPerson;
	}
	public void setcustomerTele(String customerTele){
		this.customerTele=customerTele;
	}
	public String getcustomerTele(){
		return this.customerTele;
	}
	public void setcustomerEmail(String customerEmail){
		this.customerEmail=customerEmail;
	}
	public String getcustomerEmail(){
		return this.customerEmail;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
}
