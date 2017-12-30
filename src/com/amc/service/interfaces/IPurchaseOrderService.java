package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IPurchaseOrderDao;
import com.amc.model.models.PurchaseOrder;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IPurchaseOrderService extends IEnableEntityService<Integer, PurchaseOrder, IPurchaseOrderDao> {

	public PageList<PurchaseOrder> listPage(String orderId, String vendorName, String status, int pageNo, int pageSize);
	
	public List<String> listVendorNames();
	
	public String getVendorId(String vendorName);
	
	public void saveOrder(PurchaseOrder purchaseorder) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updateOrder(PurchaseOrder purchaseorder) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Orderfigure(String orderId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}