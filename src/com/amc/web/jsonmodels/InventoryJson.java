package com.amc.web.jsonmodels;

import java.util.Calendar;
import java.util.List;


public class InventoryJson {
	
	private String productId;
	private String productName;
	private String createTime;
	private	int inventoryLevel;
	private int safestock;
	
	public String getproductId() {
		return productId;
	}
	public void setproductId(String productId) {
		this.productId = productId;
	}
	public String getproductName() {
		return productName;
	}
	public void setproductName(String productName) {
		this.productName = productName;
	}
	public String getcreateTime() {
		return createTime;
	}
	public void setcreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getinventoryLevel() {
		return inventoryLevel;
	}
	public void setinventoryLevel(int inventoryLevel) {
		this.inventoryLevel = inventoryLevel;
	}
	public int getsafestock() {
		return safestock;
	}
	public void setsafestock(int safestock) {
		this.safestock = safestock;
	}
	
}
