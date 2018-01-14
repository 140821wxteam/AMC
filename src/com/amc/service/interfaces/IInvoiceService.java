package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IInvoiceDao;
import com.amc.model.models.Cuikuan;
import com.amc.model.models.Invoice;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IInvoiceService extends IEnableEntityService<Integer, Invoice, IInvoiceDao> {

	public PageList<Invoice> listPage(String invoiceId, String orderId, int pageNo, int pageSize);
    public void updateInvoice(Invoice invoice) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public void saveInvoice(Invoice invoice) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public void updateInvoice(Invoice invoice) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Invoicefigure(String invoiceId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}