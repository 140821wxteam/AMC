package com.amc.model.models;

import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Outofstockdetail extends EnableEntity<Integer> implements ICUDEable{

	private String outofstockdetailId;
	private String outofstockId;
	private String productId;
	private	int quantityDemand;
	private	int quantitySupplied;
	private	int quantityNeeded;
	private String operatorName;
	private Calendar operateTime;
	private String status;
	private String note;
	
	public void setoutofstockdetailId(String outofstockdetailId){
		this.outofstockdetailId=outofstockdetailId;
	}
	public String getoutofstockdetailId(){
		return this.outofstockdetailId;
	}
	public void setoutofstockId(String outofstockId){
		this.outofstockId=outofstockId;
	}
	public String getoutofstockId(){
		return this.outofstockId;
	}
	public void setproductId(String productId){
		this.productId=productId;
	}
	public String getproductId(){
		return this.productId;
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
	public void setquantityNeeded(int quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}
	public int getquantityNeeded() {
		return this.quantityNeeded;
	}
	public void setoperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getoperatorName() {
		return this.operatorName;
	}
	public void setoperateTime(Calendar operateTime){
		this.operateTime=operateTime;
	}
	public Calendar getoperateTime(){
		return this.operateTime;
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
