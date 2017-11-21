package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IOrderdetailDao;
import com.amc.model.models.Orderdetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("OrderdetailDao")
public class OrderdetailDao extends EnableEntityDao<Integer, Orderdetail> implements IOrderdetailDao {


}
