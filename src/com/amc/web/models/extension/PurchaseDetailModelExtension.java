package com.amc.web.models.extension;

import com.amc.model.models.Orderdetail;
import com.amc.model.models.PurchaseDetail;
import com.amc.web.models.OrderdetailEditModel;
import com.amc.web.models.PurchaseDetailEditModel;

public class PurchaseDetailModelExtension {
	public static PurchaseDetail toPurchaseDetail(PurchaseDetailEditModel purchaseDetailEditModel){
		PurchaseDetail ret=new PurchaseDetail();
		//ret.setId(vendorEditModel.getId());
		ret.setorderdetailId(purchaseDetailEditModel.getorderdetailId());
		ret.setorderId(purchaseDetailEditModel.getorderId());
		ret.setproductId(purchaseDetailEditModel.getproductId());
		ret.setproductName(purchaseDetailEditModel.getproductName());
		ret.setquantity(purchaseDetailEditModel.getquantity());
		ret.setunitPrice(purchaseDetailEditModel.getunitPrice());
		ret.settotalPrice(purchaseDetailEditModel.gettotalPrice());
		ret.setnote(purchaseDetailEditModel.getnote());
		return ret;
	}
	
	public static PurchaseDetailEditModel toPurchaseDetailEditModel(PurchaseDetail model){
		PurchaseDetailEditModel ret=new PurchaseDetailEditModel();
		//ret.setId(model.getId());
		ret.setorderdetailId(model.getorderdetailId());
		ret.setorderId(model.getorderId());
		ret.setproductId(model.getproductId());
		ret.setproductName(model.getproductName());
		ret.setquantity(model.getquantity());
		ret.setunitPrice(model.getunitPrice());
		ret.settotalPrice(model.gettotalPrice());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
