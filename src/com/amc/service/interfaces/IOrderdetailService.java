package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IOrderdetailDao;
import com.amc.model.models.Orderdetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IOrderdetailService extends IEnableEntityService<Integer, Orderdetail, IOrderdetailDao> {

	public PageList<Orderdetail> listPage(String orderId, int pageNo, int pageSize);
	//public List<Orderdetail> listEnable();
	public void saveOrderdetail(Orderdetail orderdetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateOrderdetail(Orderdetail orderdetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public PageList<Orderdetail> listAllPage(String productId, String productName, int pageNo, int pageSize);
	
	public Orderdetail getorderdetailByoIdpId(String orderId,String productId);//按照订单号与产品号搜索明细
}