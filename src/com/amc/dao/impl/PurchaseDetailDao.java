package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IPurchaseDetailDao;
import com.amc.model.models.PurchaseDetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("PurchaseDetailDao")
public class PurchaseDetailDao extends EnableEntityDao<Integer, PurchaseDetail> implements IPurchaseDetailDao {


}
