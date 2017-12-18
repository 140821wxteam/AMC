package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IOutofstockdetailDao;
import com.amc.model.models.Outofstockdetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("OutofstockdetailDao")
public class OutofstockdetailDao extends EnableEntityDao<Integer, Outofstockdetail> implements IOutofstockdetailDao {


}
