package com.amc.web.models.extension;

import com.amc.model.models.Invoicedetail;
import com.amc.web.models.InvoicedetailEditModel;

public class InvoicedetailModelExtension {
	public static Invoicedetail toInvoicedetail(InvoicedetailEditModel invoicedetailEditModel){
		Invoicedetail ret=new Invoicedetail();
		//ret.setId(vendorEditModel.getId());
		ret.setinvoicedetailId(invoicedetailEditModel.getinvoicedetailId());
		ret.setinvoiceId(invoicedetailEditModel.getinvoiceId());
		ret.setproductId(invoicedetailEditModel.getproductId());
		ret.setproductName(invoicedetailEditModel.getproductName());
		ret.setamount(invoicedetailEditModel.getamount());
		ret.setunitPrice(invoicedetailEditModel.getunitPrice());
		ret.settotalPrice(invoicedetailEditModel.gettotalPrice());
		ret.setstatus(invoicedetailEditModel.getstatus());
		ret.setnote(invoicedetailEditModel.getnote());
		return ret;
	}
	
	public static InvoicedetailEditModel toInvoicedetailEditModel(Invoicedetail model){
		InvoicedetailEditModel ret=new InvoicedetailEditModel();
		//ret.setId(model.getId());
		ret.setinvoicedetailId(model.getinvoicedetailId());
		ret.setinvoiceId(model.getinvoiceId());
		ret.setproductId(model.getproductId());
		ret.setproductName(model.getproductName());
		ret.setamount(model.getamount());
		ret.setunitPrice(model.getunitPrice());
		ret.settotalPrice(model.gettotalPrice());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
