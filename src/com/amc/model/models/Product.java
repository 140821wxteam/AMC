package com.amc.model.models;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Product extends EnableEntity<Integer> implements ICUDEable{

	private String productId;
	private String productName;
	private String productType;
	private String productSpecification;
	private String productOrigin;
	private String productUnit;
	private int safeStock;
	private String note;
	private String images;
	
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
	public void setproductType(String productType){
		this.productType=productType;
	}
	public String getproductType(){
		return this.productType;
	}
	public void setproductSpecification(String productSpecification){
		this.productSpecification=productSpecification;
	}
	public String getproductSpecification(){
		return this.productSpecification;
	}
	public void setproductOrigin(String productOrigin){
		this.productOrigin=productOrigin;
	}
	public String getproductOrigin(){
		return this.productOrigin;
	}
	public void setproductUnit(String productUnit){
		this.productUnit=productUnit;
	}
	public String getproductUnit(){
		return this.productUnit;
	}
	public void setsafeStock(int safeStock){
		this.safeStock=safeStock;
	}
	public int getsafeStock(){
		return this.safeStock;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
	public void setimages(String images){
		this.images=images;
	}
	public String getimages(){
		return this.images;
	}
}
