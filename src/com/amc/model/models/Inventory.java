package com.amc.model.models;

import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Inventory extends EnableEntity<Integer> implements ICUDEable{

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
