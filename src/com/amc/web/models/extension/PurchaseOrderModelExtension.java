package com.amc.web.models.extension;

import com.amc.model.models.PurchaseOrder;
import com.amc.web.models.PurchaseOrderEditModel;

public class PurchaseOrderModelExtension {
	public static PurchaseOrder toPurchaseOrder(PurchaseOrderEditModel model){
		PurchaseOrder ret=new PurchaseOrder();

		ret.setorderId(model.getorderId());
		ret.setorderDate(model.getorderDate());
		ret.setvendorId(model.getvendorId());
		ret.setvendorName(model.getvendorName());
		ret.settotalPrice(model.gettotalPrice());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
	
	public static PurchaseOrderEditModel toPurchaseOrderEditModel(PurchaseOrder model){
		PurchaseOrderEditModel ret=new PurchaseOrderEditModel();

		ret.setorderId(model.getorderId());
		ret.setorderDate(model.getorderDate());
		ret.setvendorId(model.getvendorId());
		ret.setvendorName(model.getvendorName());
		ret.settotalPrice(model.gettotalPrice());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
