package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IInventoryDao;
import com.amc.model.models.Inventory;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IInventoryService extends IEnableEntityService<Integer, Inventory, IInventoryDao> {

	/*public Page<Account> listPage(AccountSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer AccountId, Integer[] authorityIds) throws ValidatException, EntityOperateException;*/
	public PageList<Inventory> listPage(String productId, String productName, int pageNo, int pageSize);//只显示最新日期的库存状况
	public PageList<Inventory> listAllPage(String productId, String productName, int pageNo, int pageSize);//显示所有的库存状况
	public Inventory listinventory(String productId);//返回某一产品的最新库存状况
	public void saveInventory(Inventory inventory) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateInventory(Inventory inventory) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;

}