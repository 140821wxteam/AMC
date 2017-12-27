package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IDeliverDao;
import com.amc.model.models.Deliver;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("DeliverDao")
public class DeliverDao extends EnableEntityDao<Integer, Deliver> implements IDeliverDao {


}
