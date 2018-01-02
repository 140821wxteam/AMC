package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.ICuikuanDao;
import com.amc.model.models.Cuikuan;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("CuikuanDao")
public class CuikuanDao extends EnableEntityDao<Integer, Cuikuan> implements ICuikuanDao {


}
