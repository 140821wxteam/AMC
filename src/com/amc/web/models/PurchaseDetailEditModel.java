package com.amc.web.models;

import org.hibernate.validator.constraints.NotEmpty;

public class PurchaseDetailEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String orderdetailId;
	private String orderId;
	private String productId;
	private String productName;
	private int quantity;
	private double unitPrice;
	private double totalPrice;
	private String note;
	
	public void setorderdetailId(String orderdetailId) {
		this.orderdetailId = orderdetailId;
	}
	public String getorderdetailId() {
		return this.orderdetailId;
	}
	public void setproductId(String productId) {
		this.productId = productId;
	}
	public String getproductId() {
		return this.productId;
	}
	public void setproductName(String productName) {
		this.productName = productName;
	}
	public String getproductName() {
		return this.productName;
	}
	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
	public int getquantity() {
		return this.quantity;
	}
	public void setorderId(String orderId){
		this.orderId=orderId;
	}
	public String getorderId(){
		return this.orderId;
	}
	public void setunitPrice(double unitPrice){
		this.unitPrice=unitPrice;
	}
	public double getunitPrice(){
		return this.unitPrice;
	}
	public void settotalPrice(double totalPrice){
		this.totalPrice=totalPrice;
	}
	public double gettotalPrice(){
		return this.totalPrice;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
}
