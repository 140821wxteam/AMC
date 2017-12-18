package com.amc.web.models;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductEditModel {
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
	private String productType;
	private String productSpecification;
	private String productOrigin;
	private String productUnit;
	private Integer safeStock;
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
	public void setsafeStock(Integer safeStock){
		this.safeStock=safeStock;
	}
	public Integer getsafeStock(){
		return this.safeStock;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
}
