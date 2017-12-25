package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IInvoiceDao;
import com.amc.model.models.Invoice;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("InvoiceDao")
public class InvoiceDao extends EnableEntityDao<Integer, Invoice> implements IInvoiceDao {


}
