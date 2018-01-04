package com.amc.web.models;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

public class InventoryEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String productId;
	private String productName;
	private int inventoryLevel;
	private Calendar createTime;
	private String status;
	private String note;
	
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
	public void setinventoryLevel(int inventoryLevel){
		this.inventoryLevel=inventoryLevel;
	}
	public int getinventoryLevel(){
		return this.inventoryLevel;
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
	public Calendar getcreateTime() {
		return this.createTime;
	}
	public void setcreateTime(Calendar createTime) {
		this.createTime = createTime;
	}
}
