package com.amc.service.interfaces;

import java.security.NoSuchAlgorithmException;

import com.amc.model.models.Customer;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface ICustomerService {

	public PageList<Customer> listPage(String englishName, String chineseName, int pageNo, int pageSize);
	//public boolean accountExist(String username);
	//public Account login(String username, String password) throws NoSuchAlgorithmException;
	//public void saveRegister(Account account) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;

}
