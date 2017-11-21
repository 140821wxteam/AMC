package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IOrderDao;
import com.amc.model.models.Order;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("OrderDao")
public class OrderDao extends EnableEntityDao<Integer, Order> implements IOrderDao {


}
