package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IAccountDao;
import com.amc.model.models.Account;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("AccountDao")
public class AccountDao extends EnableEntityDao<Integer, Account> implements IAccountDao {


}
