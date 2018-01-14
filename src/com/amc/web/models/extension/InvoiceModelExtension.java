package com.amc.web.models.extension;

import com.amc.model.models.Invoice;
import com.amc.web.models.InvoiceEditModel;

public class InvoiceModelExtension {
	public static Invoice toInvoice(InvoiceEditModel ied) {
		Invoice ret=new Invoice();
		ret.setInvoiceId(ied.getInvoiceId());
		ret.setOrderId(ied.getOrderId());
		ret.setObjection(ied.getObjection());
		ret.setCreateTime(ied.getCreateTime());
		ret.setAmountMoney(ied.getAmountMoney());
		ret.setRemark(ied.getRemark());
		ret.setStatus(ied.getStatus());

		return ret;
	}
	

}
