package com.amc.web.models;

import org.hibernate.validator.constraints.NotEmpty;

public class CustomersEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String customerId;
	private String customerName;
	private String province;
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
	public void setprovince(String province){
		this.province=province;
	}
	public String getprovince(){
		return this.province;
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
