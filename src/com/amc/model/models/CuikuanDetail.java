package com.amc.model.models;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class CuikuanDetail extends EnableEntity<Integer> implements ICUDEable{
//催款单详细
	private String cuikuanId;
	private String productId;
	private String productName;
	private String factoryId;
	private int num;
	private double price;
	private double money;
	public String getCuikuanId() {
		return cuikuanId;
	}
	public void setCuikuanId(String cuikuanId) {
		this.cuikuanId = cuikuanId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFactoryId() {
		return factoryId;
	}
	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
}
