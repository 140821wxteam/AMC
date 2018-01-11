package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IAccountTableDao;
import com.amc.model.models.AccountTable;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("AccountTableDao")
public class AccountTableDao extends EnableEntityDao<Integer, AccountTable> implements IAccountTableDao {


}
