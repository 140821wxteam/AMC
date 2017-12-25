package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IPreparedetailDao;
import com.amc.model.models.Preparedetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("PreparedetailDao")
public class PreparedetailDao extends EnableEntityDao<Integer, Preparedetail> implements IPreparedetailDao {


}
