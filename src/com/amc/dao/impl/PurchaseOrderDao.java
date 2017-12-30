package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IPurchaseOrderDao;
import com.amc.model.models.PurchaseOrder;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("PurchaseOrderDao")
public class PurchaseOrderDao extends EnableEntityDao<Integer, PurchaseOrder> implements IPurchaseOrderDao {


}