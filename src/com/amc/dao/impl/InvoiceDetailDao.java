package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IInvoiceDetailDao;
import com.amc.model.models.InvoiceDetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("InvoiceDetailDao")
public class InvoiceDetailDao extends EnableEntityDao<Integer, InvoiceDetail> implements IInvoiceDetailDao {


}
