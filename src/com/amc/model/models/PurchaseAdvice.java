package com.amc.model.models;


import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class PurchaseAdvice extends EnableEntity<Integer> implements ICUDEable{

	private String productId;
	private String productName;
	private int inventoryLevel;
	private int demand;
	private int advice;
	
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
	public void setdemand(int demand){
		this.demand=demand;
	}
	public int getdemand(){
		return this.demand;
	}
	public void setadvice(int advice){
		this.advice=advice;
	}
	public int getadvice(){
		return this.advice;
	}
	
}
