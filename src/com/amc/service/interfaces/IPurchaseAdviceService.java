package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IPurchaseAdviceDao;
import com.amc.model.models.PurchaseAdvice;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IPurchaseAdviceService extends IEnableEntityService<Integer, PurchaseAdvice, IPurchaseAdviceDao> {

	/*public Page<Account> listPage(AccountSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer AccountId, Integer[] authorityIds) throws ValidatException, EntityOperateException;*/
	public PageList<PurchaseAdvice> listPage(/*IInventoryService inventoryService,*/int pageNo, int pageSize);
	//public PageList<PurchaseAdvice> listAllPage(String productId, String productName, int pageNo, int pageSize);//显示所有的库存状况
	//public PurchaseAdvice listinventory(String productId);//返回某一产品的最新库存状况
	public void savePurchaseAdvice(PurchaseAdvice PurchaseAdvice) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updatePurchaseAdvice(PurchaseAdvice PurchaseAdvice) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;

}