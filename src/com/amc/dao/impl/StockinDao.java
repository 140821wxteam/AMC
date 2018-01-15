package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IStockinDao;
import com.amc.model.models.Stockin;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("StockinDao")
public class StockinDao extends EnableEntityDao<Integer, Stockin> implements IStockinDao {


}
