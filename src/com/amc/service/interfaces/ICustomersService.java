package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.ICustomerDao;
import com.amc.model.models.Customers;
import com.amc.model.models.Vendor;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface ICustomersService extends IEnableEntityService<Integer, Customers, ICustomerDao> {

	/*public Page<Account> listPage(AccountSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer AccountId, Integer[] authorityIds) throws ValidatException, EntityOperateException;*/
	public PageList<Customers> listPage(String customerId, String customerName, int pageNo, int pageSize);
	
	public void saveCustomer(Customers customer) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateCustomer(Customers customer) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public List<String> listcustomersId();
	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;
	public String listprovince(String customerId);
	public List<String> listAllprovince();
	public Customers getCustomer(String customerId);
}