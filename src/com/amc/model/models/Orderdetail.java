package com.amc.model.models;

import java.util.List;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Orderdetail extends EnableEntity<Integer> implements ICUDEable{

	private String orderdetailId;
	private String orderId;
	private String productId;
	private String productName;
	private int quantityDemand;
	private int quantitySupplied;
	private double unitPrice;
	private double totalPrice;
	private String status;
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
	public void setquantityDemand(int quantityDemand) {
		this.quantityDemand = quantityDemand;
	}
	public int getquantityDemand() {
		return this.quantityDemand;
	}
	public void setquantitySupplied(int quantitySupplied) {
		this.quantitySupplied = quantitySupplied;
	}
	public int getquantitySupplied() {
		return this.quantitySupplied;
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
	
	public class OrderdetailListForm {
	    private List<Orderdetail> orderdetails;

	    public List<Orderdetail> getOrderdetails() {
	        return orderdetails;
	    }

	    public void setOrderdetails(List<Orderdetail> orderdetails) {
	        this.orderdetails = orderdetails;
	    }

	}
}
