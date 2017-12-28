package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IDeliverDetailDao;
import com.amc.model.models.DeliverDetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("DeliverDetailDao")
public class DeliverDetailDao extends EnableEntityDao<Integer, DeliverDetail> implements IDeliverDetailDao {


}
