package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IAccountTableDao;
import com.amc.model.models.AccountTable;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IAccountTableService extends IEnableEntityService<Integer, AccountTable, IAccountTableDao> {

	public PageList<AccountTable> listPage(String orderId, String customerId, int pageNo, int pageSize);
	
	//public void saveAccountTable(AccountTable prepare) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updateAccountTable(AccountTable accountTable) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}