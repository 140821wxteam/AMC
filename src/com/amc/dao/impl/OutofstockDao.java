package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IOutofstockDao;
import com.amc.model.models.Outofstock;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("OutofstockDao")
public class OutofstockDao extends EnableEntityDao<Integer, Outofstock> implements IOutofstockDao {


}
