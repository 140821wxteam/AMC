package com.amc.service.interfaces;

import com.amc.dao.IInvoiceDetailDao;
import com.amc.model.models.InvoiceDetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.utilities.PageList;

public interface IInvoiceDetailService extends IEnableEntityService<Integer, InvoiceDetail, IInvoiceDetailDao> {

	public PageList<InvoiceDetail> listPage(String invoiceId,int pageNo, int pageSize);

}