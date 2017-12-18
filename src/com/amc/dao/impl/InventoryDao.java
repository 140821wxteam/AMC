package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IInventoryDao;
import com.amc.model.models.Inventory;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("InventoryDao")
public class InventoryDao extends EnableEntityDao<Integer, Inventory> implements IInventoryDao {


}
