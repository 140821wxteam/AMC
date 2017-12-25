package com.amc.model.models;

import java.util.Calendar;

import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Prepare extends EnableEntity<Integer> implements ICUDEable{
//备货单，不包括明细
	private String prepareId;
	private String orderId;
	private String customerId;
	private Calendar createTime;
	private String receivePers;
	private String receiveAddr;
	private int orderNum;
	private int fitNum;
	private int partfitNum;
	private int outofstockNum;
	private String status;
	private String note;
	
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
	public void setreceivePers(String receivePers){
		this.receivePers=receivePers;
	}
	public String getreceivePers(){
		return this.receivePers;
	}
	public void setreceiveAddr(String receiveAddr){
		this.receiveAddr=receiveAddr;
	}
	public String getreceiveAddr(){
		return this.receiveAddr;
	}
}
