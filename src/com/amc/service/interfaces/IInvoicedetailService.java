package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IInvoicedetailDao;
import com.amc.model.models.Invoicedetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IInvoicedetailService extends IEnableEntityService<Integer, Invoicedetail, IInvoicedetailDao> {

	public PageList<Invoicedetail> listPage(String invoiceId, int pageNo, int pageSize);
	//public List<Invoicedetail> listEnable();
	public void saveInvoicedetail(Invoicedetail invoicedetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateInvoicedetail(Invoicedetail invoicedetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}