package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IBackoderDao;
import com.amc.model.models.Backoder;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("BackoderDao")
public class BackoderDao extends EnableEntityDao<Integer, Backoder> implements IBackoderDao {


}
