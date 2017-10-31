package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IVendorDao;
import com.amc.model.models.Vendor;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("VendorDao")
public class VendorDao extends EnableEntityDao<Integer, Vendor> implements IVendorDao {


}
