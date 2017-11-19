package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.ICustomerDao;
import com.amc.model.models.Customers;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("CustomerDao")
public class CustomerDao extends EnableEntityDao<Integer, Customers> implements ICustomerDao {


}
