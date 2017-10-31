package com.amc.model.models;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Vendor extends EnableEntity<Integer> implements ICUDEable{

	private String vendorId;
	private String vendorName;
	private String vendorAddr;
	private String contactPerson;
	private String vendorTele;
	private String vendorEmail;
	private String note;
	
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
	public void setvendorAddr(String vendorAddr){
		this.vendorAddr=vendorAddr;
	}
	public String getvendorAddr(){
		return this.vendorAddr;
	}
	public void setcontactPerson(String contactPerson){
		this.contactPerson=contactPerson;
	}
	public String getcontactPerson(){
		return this.contactPerson;
	}
	public void setvendorTele(String vendorTele){
		this.vendorTele=vendorTele;
	}
	public String getvendorTele(){
		return this.vendorTele;
	}
	public void setvendorEmail(String vendorEmail){
		this.vendorEmail=vendorEmail;
	}
	public String getvendorEmail(){
		return this.vendorEmail;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
}
