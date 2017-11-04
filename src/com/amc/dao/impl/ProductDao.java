package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IProductDao;
import com.amc.model.models.Product;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("ProductDao")
public class ProductDao extends EnableEntityDao<Integer, Product> implements IProductDao {


}
