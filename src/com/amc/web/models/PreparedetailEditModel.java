package com.amc.web.models;

import org.hibernate.validator.constraints.NotEmpty;

public class PreparedetailEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String preparedetailId;
	private String prepareId;
	private String productId;
	private String productName;
	private String factoryId;
	private int amount;
	private String size;
	private String preparePers;
	private String status;
	private String note;
	
	public void setpreparedetailId(String preparedetailId) {
		this.preparedetailId = preparedetailId;
	}
	public String getpreparedetailId() {
		return this.preparedetailId;
	}
	public void setproductId(String productId) {
		this.productId = productId;
	}
	public String getproductId() {
		return this.productId;
	}
	public void setfactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public String getfactoryId() {
		return this.factoryId;
	}
	public void setproductName(String productName) {
		this.productName = productName;
	}
	public String getproductName() {
		return this.productName;
	}
	public void setamount(int amount) {
		this.amount = amount;
	}
	public int getamount() {
		return this.amount;
	}
	
	public void setprepareId(String prepareId){
		this.prepareId=prepareId;
	}
	public String getprepareId(){
		return this.prepareId;
	}
	public void setsize(String size){
		this.size=size;
	}
	public String getsize(){
		return this.size;
	}
	public void setpreparePers(String preparePers){
		this.preparePers=preparePers;
	}
	public String getpreparePers(){
		return this.preparePers;
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
