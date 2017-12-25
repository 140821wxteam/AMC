package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IPrepareDao;
import com.amc.model.models.Prepare;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("PrepareDao")
public class PrepareDao extends EnableEntityDao<Integer, Prepare> implements IPrepareDao {


}
