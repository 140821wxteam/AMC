package com.amc.web.models.extension;

import com.amc.model.models.Orderdetail;
import com.amc.web.models.OrderdetailEditModel;

public class OrderdetailModelExtension {
	public static Orderdetail toOrderdetail(OrderdetailEditModel orderdetailEditModel){
		Orderdetail ret=new Orderdetail();
		//ret.setId(vendorEditModel.getId());
		ret.setorderdetailId(orderdetailEditModel.getorderdetailId());
		ret.setorderId(orderdetailEditModel.getorderId());
		ret.setproductId(orderdetailEditModel.getproductId());
		ret.setproductName(orderdetailEditModel.getproductName());
		ret.setquantityDemand(orderdetailEditModel.getquantityDemand());
		ret.setquantitySupplied(orderdetailEditModel.getquantitySupplied());
		ret.setunitPrice(orderdetailEditModel.getunitPrice());
		ret.settotalPrice(orderdetailEditModel.gettotalPrice());
		ret.setstatus(orderdetailEditModel.getstatus());
		ret.setnote(orderdetailEditModel.getnote());
		return ret;
	}
	
	public static OrderdetailEditModel toOrderdetailEditModel(Orderdetail model){
		OrderdetailEditModel ret=new OrderdetailEditModel();
		//ret.setId(model.getId());
		ret.setorderdetailId(model.getorderdetailId());
		ret.setorderId(model.getorderId());
		ret.setproductId(model.getproductId());
		ret.setproductName(model.getproductName());
		ret.setquantityDemand(model.getquantityDemand());
		ret.setquantitySupplied(model.getquantitySupplied());
		ret.setunitPrice(model.getunitPrice());
		ret.settotalPrice(model.gettotalPrice());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
