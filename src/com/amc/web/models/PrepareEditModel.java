package com.amc.web.models;

import java.util.Calendar;

import org.hibernate.validator.constraints.NotEmpty;

public class PrepareEditModel {
	private Integer id;
	
	public void setId(Integer id){
		this.id=id;
	}
	
	public Integer getId(){
		return this.id;
	}
	@NotEmpty(message="{name.not.empty}")
	private String prepareId;
	private String orderId;
	private Calendar createTime;
	private String customerId;
	private int orderNum;
	private int fitNum;
	private int partfitNum;
	private int outofstockNum;
	private String status;
	private String note;
	private String credit;
	
	public void setprepareId(String prepareId){
		this.prepareId=prepareId;
	}
	public String getprepareId(){
		return this.prepareId;
	}
	public void setorderId(String orderId){
		this.orderId=orderId;
	}
	public String getorderId(){
		return this.orderId;
	}
	public void setcreateTime(Calendar createTime){
		this.createTime=createTime;
	}
	public Calendar getcreateTime(){
		return this.createTime;
	}
	public void setcustomerId(String customerId){
		this.customerId=customerId;
	}
	public String getcustomerId(){
		return this.customerId;
	}
	public void setorderNum(int orderNum){
		this.orderNum=orderNum;
	}
	public int getorderNum(){
		return this.orderNum;
	}
	public void setfitNum(int fitNum){
		this.fitNum=fitNum;
	}
	public int getfitNum(){
		return this.fitNum;
	}
	public void setpartfitNum(int partfitNum){
		this.partfitNum=partfitNum;
	}
	public int getpartfitNum(){
		return this.partfitNum;
	}
	public void setoutofstockNum(int outofstockNum){
		this.outofstockNum=outofstockNum;
	}
	public int getoutofstockNum(){
		return this.outofstockNum;
	}
	public String getstatus() {
		return this.status;
	}
	public void setstatus(String status) {
		this.status = status;
	}
	public void setnote(String note){
		this.note=note;
	}
	public String getnote(){
		return this.note;
	}
	public void setcredit(String credit){
		this.credit=credit;
	}
	public String getcredit(){
		return this.credit;
	}
}
