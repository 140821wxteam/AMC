package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IPurchaseDetailDao;
import com.amc.model.models.PurchaseDetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IPurchaseDetailService extends IEnableEntityService<Integer, PurchaseDetail, IPurchaseDetailDao> {

	public PageList<PurchaseDetail> listPage(String orderId, int pageNo, int pageSize);
	
	public void savePurchaseDetail(PurchaseDetail purchasedetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updatePurchaseDetail(PurchaseDetail purchasedetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public PageList<PurchaseDetail> listAllPage(String productId, String productName, int pageNo, int pageSize);

}