package com.amc.model.models;

import java.util.Calendar;
import java.util.List;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Preparedetail extends EnableEntity<Integer> implements ICUDEable{
//发票
	private String prepareId;
	private String preparedetailId;
	private String productId;
	private String factoryId;
	private String productName;
	private String preparePers;
	private String size;
	private int amount;
	private String status;
	private String note;
	
	public void setprepareId(String prepareId){
		this.prepareId=prepareId;
	}
	public String getprepareId(){
		return this.prepareId;
	}
	public void setpreparedetailId(String preparedetailId){
		this.preparedetailId=preparedetailId;
	}
	public String getpreparedetailId(){
		return this.preparedetailId;
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
	public void setpreparePers(String preparePers){
		this.preparePers=preparePers;
	}
	public String getpreparePers(){
		return this.preparePers;
	}
	public void setsize(String size){
		this.size=size;
	}
	public String getsize(){
		return this.size;
	}
	
	public void setamount(int amount){
		this.amount=amount;
	}
	public int getamount(){
		return this.amount;
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
	public class PreparedetailListForm {
	    private List<Preparedetail> preparedetails;

	    public List<Preparedetail> getPreparedetails() {
	        return preparedetails;
	    }

	    public void setPreparedetails(List<Preparedetail> preparedetails) {
	        this.preparedetails =preparedetails;
	    }

	}
}
