package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IInvoicedetailDao;
import com.amc.model.models.Invoicedetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("InvoicedetailDao")
public class InvoicedetailDao extends EnableEntityDao<Integer, Invoicedetail> implements IInvoicedetailDao {


}
