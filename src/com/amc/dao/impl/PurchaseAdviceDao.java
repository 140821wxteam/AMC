package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IInventoryDao;
import com.amc.dao.IPurchaseAdviceDao;
import com.amc.model.models.Inventory;
import com.amc.model.models.PurchaseAdvice;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("PurchaseAdviceDao")
public class PurchaseAdviceDao extends EnableEntityDao<Integer, PurchaseAdvice> implements IPurchaseAdviceDao {


}
