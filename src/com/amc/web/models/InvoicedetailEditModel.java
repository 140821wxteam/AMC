package com.amc.web.models;

import org.hibernate.validator.constraints.NotEmpty;

public class InvoicedetailEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String invoicedetailId;
	private String invoiceId;
	private String productId;
	private String productName;
	private int amount;
	private double unitPrice;
	private double totalPrice;
	private String status;
	private String note;
	
	public void setinvoicedetailId(String invoicedetailId) {
		this.invoicedetailId = invoicedetailId;
	}
	public String getinvoicedetailId() {
		return this.invoicedetailId;
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
	public void setamount(int amount) {
		this.amount = amount;
	}
	public int getamount() {
		return this.amount;
	}
	
	public void setinvoiceId(String invoiceId){
		this.invoiceId=invoiceId;
	}
	public String getinvoiceId(){
		return this.invoiceId;
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
}
