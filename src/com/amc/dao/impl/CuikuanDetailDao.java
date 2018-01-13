package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.ICuikuanDetailDao;
import com.amc.model.models.CuikuanDetail;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("CuikuanDetailDao")
public class CuikuanDetailDao extends EnableEntityDao<Integer, CuikuanDetail> implements ICuikuanDetailDao {


}
