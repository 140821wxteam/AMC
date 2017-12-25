package com.amc.model.models;

import java.util.Calendar;
import java.util.List;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Invoicedetail extends EnableEntity<Integer> implements ICUDEable{
//发票
	private String invoiceId;
	private String invoicedetailId;
	private String productId;
	private String factoryId;
	private String productName;
	private double unitPrice;
	private double totalPrice;
	private int amount;
	private String status;
	private String note;
	
	public void setinvoiceId(String invoiceId){
		this.invoiceId=invoiceId;
	}
	public String getinvoiceId(){
		return this.invoiceId;
	}
	public void setinvoicedetailId(String invoicedetailId){
		this.invoicedetailId=invoicedetailId;
	}
	public String getinvoicedetailId(){
		return this.invoicedetailId;
	}
	
	public void setproductId(String productId){
		this.productId=productId;
	}
	public String getproductId(){
		return this.productId;
	}
	public void setfactoryId(String factoryId){
		this.factoryId=factoryId;
	}
	public String getfactoryId(){
		return this.factoryId;
	}
	public void setproductName(String productName){
		this.productName=productName;
	}
	public String getproductName(){
		return this.productName;
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
	public void setamount(int amount){
		this.amount=amount;
	}
	public int getamount(){
		return this.amount;
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
	public class InvoicedetailListForm {
	    private List<Invoicedetail> invoicedetails;

	    public List<Invoicedetail> getInvoicedetails() {
	        return invoicedetails;
	    }

	    public void setInvoicedetails(List<Invoicedetail> invoicedetails) {
	        this.invoicedetails =invoicedetails;
	    }

	}
}
